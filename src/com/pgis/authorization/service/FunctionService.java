package com.pgis.authorization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgis.authorization.dao.FunctionDAO;
import com.pgis.authorization.entity.Function;

@Service
public class FunctionService {
     
	@Autowired private FunctionDAO functionDAO;
	
	/**
	 * 增加功能（菜单）
	 * @param function
	 */
	public void addFunction(Function function){
		functionDAO.saveFunction(function);
	}
	
	/**
	 * 根据功能ID更新其URL信息
	 * @param id 功能ID
	 * @param url URL
	 */
	public void updateUrl(Long id, String url){
		functionDAO.updateUrl(id, url);
	}
	
	/**
	 * 分页查询指定父节点的子节点
	 * @param page 当前页码
	 * @param size 每页记录数
	 * @param parentId 父节点ID
	 * @return 功能集合
	 */
	public List<Function> getFunctions(int page, int size, Long parentId){
		return functionDAO.findFunctions(page, size, parentId);
	}
	
	/**
	 * 根据ID删除功能
	 * @param id 功能ID
	 */
	public void deleteFunctionById(Long id){
		functionDAO.deleteById(id);
	}
	
	/**
	 * 查询全部功能信息
	 * @return 全部功能信息
	 */
	public List<Function> getAllFunctions(){
		return functionDAO.findAllFunctions();
	}
	
	
	
	
	
}
