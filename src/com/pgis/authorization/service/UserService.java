package com.pgis.authorization.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgis.authorization.dao.UserDAO;
import com.pgis.authorization.dao.UserRoleDAO;
import com.pgis.authorization.entity.User;
import com.pgis.authorization.entity.UserRole;

@Service
public class UserService {
     
	@Autowired private UserDAO userDAO;
	@Autowired private UserRoleDAO userRoleDAO;
	
	/**
	 * 保存用户信息
	 * @param user 用户信息
	 */
	public void addUser(User user){
		userDAO.saveUser(user);
	}
	
	/**
	 * 更新用户信息
	 * @param user 用户信息
	 */
	public void updateUser(User user){
		userDAO.update(user);
	}
	
	/**
	 * 根据ID删除用户
	 * @param id 用户ID
	 */
	public void deleteUserById(Long id){
		userDAO.deleteById(id);
	}
	
	/**
	 * 根据用户名、密码查询用户，用于登录
	 * @param name 用户名
	 * @param pwd 密码
	 * @return 用户信息
	 */
	public User getUser(String name, String pwd){
		return userDAO.findUser(name, pwd);
	}
	
	/**
	 * 分页查询用户信息
	 * @param page 当前页码
	 * @param size 每页记录数
	 * @return 用户信息集合
	 */
	public Collection<User> getUsers(int page, int size){
		return userDAO.findPage(page, size);
	}
	
	/**
	 * 根据ID集合查询对应的用户信息
	 * @param ids 用户ID集合
	 * @return 用户信息集合
	 */
	public Collection<User> getUsers(Collection<Long> ids){
		return userDAO.findByIds(ids);
	}
	
	/**
	 * 分页查询用户角色对应关系
	 * @param page 当前页码
	 * @param size 每页记录数
	 * @return 用户角色对应关系集合
	 */
	public List<UserRole> getUserRoles(Integer page, Integer size){
		return userRoleDAO.findUserRoles(page, size);
	}
	
	/**
	 * 根据用户ID查询用户角色对应关系
	 * @param userId 用户ID
	 * @return 用户角色对应关系集合
	 */
	public List<UserRole> getUserRolesByUserId(Long userId){
		return userRoleDAO.findUserRoleByUserId(userId);
	}
	
	/**
	 * 保存用户角色对应关系
	 * @param userId 用户ID
	 * @param roleIds用户对应的角色ID数组
	 */
	public void addUserRoles(Long userId, Long[] roleIds){
		List<UserRole> userRoles = new ArrayList<>();
		
		Arrays.asList(roleIds).forEach((roleId) -> {
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoles.add(userRole);
		});
		userRoleDAO.saveUserRoles(userRoles);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
