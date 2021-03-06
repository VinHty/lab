package com.pgis.authorization.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgis.authorization.dao.RoleDAO;
import com.pgis.authorization.dao.RoleFunctionDAO;
import com.pgis.authorization.entity.Role;
import com.pgis.authorization.entity.RoleFunction;

@Service
public class RoleService {
	
	@Autowired private RoleDAO roleDAO;
	@Autowired private RoleFunctionDAO roleFunctionDAO;
	
	/**
	 * 保存角色信息，同时保存角色对应的功能
	 * @param role 角色
	 * @param roleFunctions 角色对应的功能（即角色功能的关联关系）
	 */
    public void addRole(Role role, Collection<RoleFunction> roleFunctions){
    	roleDAO.saveRole(role);
    	roleFunctions.forEach((rf) -> rf.setRoleId(role.getId()));
    	roleFunctionDAO.saveRoleFunctions(roleFunctions);
    }
	
    /**
     * 修改角色信息，同时修改角色对应的功能
     * @param role
     * @param roleFunctions
     */
    public void editRole(Role role, Collection<RoleFunction> roleFunctions){
    	roleDAO.updateRole(role);
    	roleFunctionDAO.deleteByRoleId(role.getId());
    	roleFunctions.forEach((rf) -> rf.setRoleId(role.getId()));
    	roleFunctionDAO.saveRoleFunctions(roleFunctions);
    }
	
    /**
     * 根据ID删除角色
     * @param roleId 角色ID
     */
	public void deleteRole(Long roleId){
		roleDAO.deleteRoleById(roleId);
		roleFunctionDAO.deleteByRoleId(roleId);
	}
    
	/**
	 * 分页查询角色信息
	 * @param page 当前页码
	 * @param size 每页记录数
	 * @return 角色集合
	 */
    public List<Role> getRoles(int page, int size){
    	List<Role> roles = roleDAO.findRolesByPages(page, size);
    	roles.forEach((role) -> {
    		List<RoleFunction> roleFunctions = roleFunctionDAO.findRoleFunctionsByRoleId(role.getId());
    		StringBuilder functionIds = new StringBuilder();
    		roleFunctions.forEach((rf) -> {
    			functionIds.append(rf.getFunctionId()).append(",");
    		});
    		
    		if (functionIds.length() > 1){
    			role.setFunctionIds(functionIds.deleteCharAt(functionIds.length() - 1).toString());
    		}
    	});
    	return roles;
    }
    
    /**
     * 根据ID集合查询角色信息
     * @param ids 角色ID集合
     * @return 角色集合
     */
    public List<Role> getRoles(Collection<Long> ids){
    	return roleDAO.findRoleByIds(ids);
    }
    
    /**
     * 根据用户ID查询角色功能对应关系
     * @param roleId 角色ID
     * @return 角色功能对应关系集合
     */
    public List<RoleFunction> getRoleFunctions(Long roleId){
    	return roleFunctionDAO.findRoleFunctionsByRoleId(roleId);
    }
     
}
