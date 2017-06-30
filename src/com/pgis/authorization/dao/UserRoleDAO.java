package com.pgis.authorization.dao;

import com.pgis.authorization.common.BaseDAO;
import com.pgis.authorization.entity.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserRoleDAO extends BaseDAO {

    private class UserRoleMapper implements RowMapper<UserRole>{

        @Override
        public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserRole userRole = new UserRole();
            userRole.setId(rs.getLong("id"));
            userRole.setRoleId(rs.getLong("role_id"));
            userRole.setUserId(rs.getLong("user_id"));
            return userRole;
        }
    }
    
    /**
     * 根据ID查询用户角色对应关系
     * @param id 用户角色对应关系ID
     * @return 用户角色对应关系
     */
    public UserRole findUserRoleById(Long id){
        String sql="select * from auth_user_role where id =?";
        try {
            return jdbcTemplate.queryForObject(sql,new Object[]{id},new UserRoleMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存用户角色对应关系
     * @param userRole 用户角色对应关系
     */
    public void saveUserRole(UserRole userRole){
        String sql="insert into auth_user_role(id,role_id,user_id) values(?,?,?)";
        jdbcTemplate.update(sql,userRole.getId(),userRole.getRoleId(),userRole.getUserId());
    }
    
    /**
     * 根据ID删除用户角色对应关系
     * @param userId 用户角色对应关系ID
     */
    public void deleteByUserId(Long userId){
    	String sql = "delete from auth_user_role where user_id = ?";
    	jdbcTemplate.update(sql, userId);
    }
    
    /**
     * （自己加的功能）
     * 根据ID更新用户角色对应关系
     * @param userRole 用户角色对应关系
     */
    public void updateUserRole(UserRole userRole){
        String sql="update auth_user_role set role_id =?,user_id =? where id= ?";
        jdbcTemplate.update(sql,userRole.getRoleId(),userRole.getUserId(),userRole.getId());
    }
    
    /**
     * 分页查询用户角色对应关系
     * @param page 页码
     * @param size 每页记录数
     * @return 用户角色对应关系集合
     */
    public List<UserRole> findUserRoles(int page,int size){
        String sql="select * from auth_user_role order by user_id limit ?,?";
        try {
            return jdbcTemplate.query(sql,new Object[]{(page-1)*size,size},new UserRoleMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据用户ID查询用户角色对应关系（集合）
     * @param userId 用户ID
     * @return 用户角色对应关系集合
     */
    public List<UserRole> findUserRoleByUserId(Long userId){
        String sql="select * from auth_user_role where user_id =?";
        try {
            return jdbcTemplate.query(sql,new Object[]{userId},new UserRoleMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存用户角色对应关系集合
     * @param userRoles 用户角色对应关系集合
     */
    public void saveUserRoles(Collection<UserRole> userRoles){
        String sql="insert into auth_user_role(role_id,user_id) values(?,?)";
        List<Object[]> batchArgs=new ArrayList<>();
        userRoles.forEach((ur -> {
            Object[] objs = new Object[2];
            objs[0] = ur.getRoleId();
            objs[1] = ur.getUserId();
            
            batchArgs.add(objs);
        }));
        jdbcTemplate.batchUpdate(sql,batchArgs);
    }

}
