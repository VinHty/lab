package com.pgis.dao;

import java.util.List;

import com.pgis.domain.Employee;

/*
 * 员工管理Dao接口
 */

public interface EmployeeDao {
	
	
	
	public Employee findByUsernameAndPassword(Employee employee);

	public int findCount();
	
	public List<Employee> findByPage(int begin, int pageSize);

	public void save(Employee employee);

	public Employee findById(int eid);

	public void update(Employee employee);

	public void delete(Employee employee);

	
	

}
