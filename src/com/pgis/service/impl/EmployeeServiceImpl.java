package com.pgis.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pgis.dao.EmployeeDao;
import com.pgis.domain.Employee;
import com.pgis.domain.PageBean;
import com.pgis.service.EmployeeService;
/*
 * 员工管理的业务层实现类
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	//注入员工管理的Dao
	private EmployeeDao employeeDao;
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	/*
	 * 业务层登录方法
	 */
	@Override
	public Employee login(Employee employee) {
		System.out.println("EmployeeServiceImpl的login()方法执行了...");
		Employee existEmployee=employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}
	
	//分页查询员工的方法
		@Override
		public PageBean<Employee> findByPage(Integer currPage) {
			PageBean<Employee> pageBean=new PageBean<Employee>();
			//封装当前页数
			pageBean.setCurrPage(currPage);
			//封装每页显示的记录数
			int pageSize=3;
			pageBean.setPageSize(pageSize);
			//封装总记录数
			int totalCount=employeeDao.findCount();
			pageBean.setTotalCount(totalCount);
			//封装总页数
			double tc=totalCount;//总记录数转为double类型
			Double num=Math.ceil(tc/pageSize);//总记录数÷每页显示记录数，Math.ceil向上取整数返回double类型
			pageBean.setTotalPage(num.intValue());
			//封装每页显示的数据
			int begin=(currPage-1)*pageSize;
			List<Employee> list=employeeDao.findByPage(begin,pageSize);
			pageBean.setList(list);
			return pageBean;
		}
	
	
	//业务层保存员工的方法
	public void save(Employee employee) {
		employeeDao.save(employee);
	}
	
	
	//业务层根据Id查询员工的方法
	@Override
	public Employee findById(int eid) {
		// TODO Auto-generated method stub
		return employeeDao.findById(eid);
	}
	
	//业务层保存修改的员工信息执行方法
	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
	
	//业务层根据Id删除员工
	@Override
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
	
}
