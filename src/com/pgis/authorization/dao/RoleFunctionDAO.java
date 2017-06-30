package com.pgis.authorization.dao;

import com.pgis.authorization.common.BaseDAO;
import com.pgis.authorization.entity.RoleFunction;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RoleFunctionDAO extends BaseDAO {

    private class RoleFunctionMapper implements RowMapper<RoleFunction>{

        @Override
        public RoleFunction mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoleFunction roleFunction = new RoleFunction();
            roleFunction.setId(rs.getLong("id"));
            roleFunction.setRoleId(rs.getLong("role_id"));
            roleFunction.setFunctionId(rs.getLong("functions_id"));
            roleFunction.setStatus(rs.getInt("status"));
            return roleFunction;
        }
    }

    /**
     * 根据ID查询角色功能对应关系
     * @param id 角色功能对应关系ID
     * @return 角色功能对应关系
     */
    public RoleFunction findRoleFunctionsById(Long id){
        String sql="select * from auth_role_funcition where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,new Object[]{id},new RoleFunctionMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 批量保存角色功能对应关系
     * @param roleFunctions 角色功能对应关系集合
     */
    public void saveRoleFunctions(Collection<RoleFunction> roleFunctions){
        String sql="insert into auth_role_function(role_id,function_id,status) values(?,?,?)";
        List<Object[]> batchArgs=new ArrayList<>();
        roleFunctions.forEach((rf -> {
            Object[] objs = new Object[3];
            objs[0] = rf.getRoleId();
            objs[1] = rf.getFunctionId();
            objs[2] = rf.getStatus();
            
            batchArgs.add(objs);
        }));
        jdbcTemplate.batchUpdate(sql,batchArgs);
    }

    /**
     * 根据角色ID查询角色功能对应关系集合
     * @param roleId 角色ID
     * @return 角色功能对应关系集合
     */
    public List<RoleFunction> findRoleFunctionsByRoleId(Long roleId){
        String sql="select * from auth_role_function where role_id = ?";
        try {
            return jdbcTemplate.query(sql,new Object[]{roleId},new RoleFunctionMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据角色ID删除角色功能对应关系
     * @param roleid 角色ID
     */
    public void deleteByRoleId(Long roleid){
        String sql="delete from auth_role_function where role_id=?";
        jdbcTemplate.update(sql,roleid);
    }

}
