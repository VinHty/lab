package com.pgis.service;

import java.util.List;

import com.pgis.domain.Department;
import com.pgis.domain.PageBean;

/*
 * 部门管理Service业务层接口
 */
public interface DepartmentService {

	PageBean<Department> findByPage(Integer currPage);

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);
	
	List<Department> findAll();
}
