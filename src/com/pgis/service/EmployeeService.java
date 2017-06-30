package com.pgis.service;

import com.pgis.domain.Employee;
import com.pgis.domain.PageBean;

/*
 * 员工管理的业务层接口
 */
public interface EmployeeService {

	public Employee login(Employee employee);

	public PageBean<Employee> findByPage(Integer currPage);

	public void save(Employee employee);

	public Employee findById(int eid);

	public void update(Employee employee);

	public void delete(Employee employee);

}
