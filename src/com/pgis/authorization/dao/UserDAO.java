package com.pgis.authorization.dao;

import com.pgis.authorization.common.BaseDAO;
import com.pgis.authorization.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;


public class UserDAO extends BaseDAO {

    private class UserMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user=new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setPwd(rs.getString("pwd"));
            return user;
        }
    }
    
    /**
     * 根据用户名、密码查询用户，用于登录
     * @param name 用户名
     * @param pwd 密码
     * @return 查询到的唯一用户实体
     */
    public User findUser(String name,String pwd){
        String sql="select * from user where name =? and pwd =?";

        try {
            return jdbcTemplate.queryForObject(sql,new Object[]{name, pwd}, new UserMapper());
        } catch (DataAccessException e) {
            //logger
            return null;
        }
    }
    
    public void saveUser(User user){
        String sql="insert into user(id,name,pwd) values(?,?,?)";
        jdbcTemplate.update(sql,user.getName(),user.getPwd());
    }

    public void deleteById(Long id){
        String sql="delete from user where id= ?";
        jdbcTemplate.update(sql,id);
    }

    public void update(User user){
        String sql="update user set name=?,pwd=? where id= ?";
        jdbcTemplate.update(sql,user.getName(),user.getPwd(),user.getId());
    }

    public User findById(Long id){
        String sql="select * from user where id =?";
        try {
            return jdbcTemplate.queryForObject(sql,new Object[]{id},new UserMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }   

    public Collection<User> findByIds(Collection<Long> ids){
        StringBuilder sql=new StringBuilder("select * from user where id in (");
        Object[] args = new Object[ids.size()];
        AtomicInteger index = new AtomicInteger(0);
        ids.forEach((id) -> {
        	sql.append(id).append("?,");
        	args[index.getAndIncrement()] = id;
        });
        sql.deleteCharAt(sql.length()-2);
        sql.append(")");
        return jdbcTemplate.query(sql.toString(),ids.toArray(new Object[0]),new UserMapper());
    }
    
    /**
     * 分页查询
     * @param page 页码，第n页
     * @param size 每页记录数
     * @return 行记录集合
     */
    public Collection<User> findPage(int page, int size){
    	if (page < 1){
    		page = 1;
    	}
    	if (size < 0){
    		size = 20;
    	}
    	String sql = "select * from user limit ?,?";
    	int skip = (page -1) * size;
    	return jdbcTemplate.query(sql.toString(), new Object[]{skip, size}, new UserMapper()); 	
    }

}
