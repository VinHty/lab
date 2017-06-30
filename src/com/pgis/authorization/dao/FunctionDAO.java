package com.pgis.authorization.dao;

import com.pgis.authorization.common.BaseDAO;
import com.pgis.authorization.entity.Function;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;


public class FunctionDAO extends BaseDAO {
	private class FunctionMapper implements RowMapper<Function>{

        @Override
        public Function mapRow(ResultSet rs, int rowNum) throws SQLException {
            Function function = new Function();
            function.setId(rs.getLong("id"));
            function.setName(rs.getString("name"));
            function.setParentId(rs.getLong("parent_id"));
            function.setSerialNum(rs.getInt("serial_num"));
            function.setUrl(rs.getString("url"));
            function.setAccordion(rs.getInt("accordion"));
            return function;
        }
    }

    /**
     *  保存功能
     *  @param function 功能对象    
     */
    public void saveFunction(Function function){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql="insert into auth_function(name,parent_id,url,serial_num,accordion) values(?,?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
        	
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,function.getName());
                ps.setLong(2,function.getParentId());
                ps.setString(3,function.getUrl());
                ps.setInt(4,function.getSerialNum());
                ps.setInt(5,function.getAccordion());
                return ps;
            }
        },keyHolder);

        function.setId(keyHolder.getKey().longValue());
    }

    /**
     * 根据ID更新功能的URL
     * @param id 功能ID
     * @param url URL
     */
    public void updateUrl(Long id,String url){
        String sql="update from auth_function set url=? where id=?";
        jdbcTemplate.update(sql,url,id);
    }

    /**
     * 分页查询功能（根据父节点）
     * @param page 当前页码
     * @param size 每页记录数
     * @param parentId 父节点ID
     * @return 功能集合
     */
    public List<Function> findFunctions(int page,int size,Long parentId){
        String sql="select * from auth_function where parent_id =? limit ?,?";
        try {
            return jdbcTemplate.query(sql,new Object[]{parentId,(page-1)*size,size},new FunctionMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据ID删除功能
     * @param id 功能ID
     */
    public void deleteById(Long id){
        String sql="delete from auth_function where id =?";
        jdbcTemplate.update(sql,id);
    }

    /**
     * 查询全部功能
     * @return 功能集合
     */
    public List<Function> findAllFunctions(){
        String sql="select * from auth_function";
        try {
            return jdbcTemplate.query(sql,new FunctionMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
