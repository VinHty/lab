package com.pgis.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pgis.dao.DepartmentDao;
import com.pgis.domain.Department;
/*
 * 部门Dao的实现类
 */
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {
	
	//统计数据记录数
	@Override
	public int findCount() {
		String hql="select count(*) from Department";
		List<Long> list=this.getHibernateTemplate().find(hql);
		System.out.println(list.get(0).intValue());
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//分页查询部门
	@Override
	public List<Department> findByPage(int begin, int pageSize) {
		System.out.println("findByPage方法执行了。。。"+begin+"..."+pageSize);
		DetachedCriteria criteria=DetachedCriteria.forClass(Department.class);
		List<Department> list=this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}
	
	//Dao中保存部门的方法
	@Override
	public void save(Department department) {
		this.getHibernateTemplate().save(department);
	}
	
	//Dao中根据部门did查询部门的方法
	@Override
	public Department findById(Integer did) {
		return this.getHibernateTemplate().get(Department.class, did);
	}
	
	
	//Dao保存修改后的部门的方法
	@Override
	public void update(Department department) {
		this.getHibernateTemplate().update(department);
	}
	
	//Dao删除部门的方法
	@Override
	public void delete(Department department) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(department);
	}
	
	//DAO中查询所有部门的方法
	@Override
	public List<Department> findAll() {
		return this.getHibernateTemplate().find("from Department");
	}
}
