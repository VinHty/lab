package com.pgis.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pgis.dao.DepartmentDao;
import com.pgis.domain.Department;
import com.pgis.domain.PageBean;
import com.pgis.service.DepartmentService;
/*
 * 部门管理Service业务层实现类
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	//注入部门管理Dao
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	//分页查询部门的方法
	@Override
	public PageBean<Department> findByPage(Integer currPage) {
		PageBean<Department> pageBean=new PageBean<Department>();
		//封装当前页数
		pageBean.setCurrPage(currPage);
		//封装每页显示的记录数
		int pageSize=3;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount=departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc=totalCount;//总记录数转为double类型
		Double num=Math.ceil(tc/pageSize);//总记录数÷每页显示记录数，Math.ceil向上取整数返回double类型
		pageBean.setTotalPage(num.intValue());
		//封装每页显示的数据
		int begin=(currPage-1)*pageSize;
		List<Department> list=departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	//业务层保存部门的方法
	@Override
	public void save(Department department) {
		departmentDao.save(department);
	}
	
	//业务层根据部门id查询部门的方法
	@Override
	public Department findById(Integer did) {
		
		return departmentDao.findById(did);
	}
	
	//业务层保存经过修改的部门
	@Override
	public void update(Department department) {
		departmentDao.update(department);
		
	}
	//业务层删除部门的方法
	@Override
	public void delete(Department department) {
		departmentDao.delete(department);
	}
	
	//查询所有部门的方法
	@Override
	public List<Department> findAll() {
		return departmentDao.findAll();
	}
	
	
}
