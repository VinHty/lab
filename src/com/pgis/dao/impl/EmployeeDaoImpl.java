package com.pgis.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pgis.dao.EmployeeDao;
import com.pgis.domain.Employee;
/*
 * 员工管理Dao的实现类
 */
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {
	
	/*
	 * Dao中根据用户名和密码查询用户的方法
	 */
	@Override
	public Employee findByUsernameAndPassword(Employee employee) {
			String hql = "from Employee where username =  ? and password = ?";
			System.out.println("您登入的账号是："+employee.getUsername()+"===登入的密码是:"+employee.getPassword());
			List<Employee> list = this.getHibernateTemplate().find(hql, employee.getUsername(),employee.getPassword());
			if (list.size() > 0) {
				return list.get(0);
			}
			return null;
	}
	
	//统计数据记录数
		@Override
		public int findCount() {
			String hql="select count(*) from Employee";
			List<Long> list=this.getHibernateTemplate().find(hql);
			System.out.println("查询结果记录数："+list.get(0).intValue());
			if(list.size()>0){
				return list.get(0).intValue();
			}
			return 0;
		}
	
	//分页查询部门
	@Override
	public List<Employee> findByPage(int begin, int pageSize) {
		System.out.println("EmployeeDaoImpl的findByPage方法执行了...");
		DetachedCriteria criteria=DetachedCriteria.forClass(Employee.class);
		List<Employee> list=this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}
		
	//Dao中保存员工的方法
	@Override
	public void save(Employee employee) {
		this.getHibernateTemplate().save(employee);
	}
		
	//Dao中根据Id查询员工的方法
	@Override
	public Employee findById(int eid) {
		return this.getHibernateTemplate().get(Employee.class,eid);
	}
		
	//Dao中保存修改后的员工信息
	@Override
	public void update(Employee employee) {
		this.getHibernateTemplate().update(employee);
	}
	
	//Dao中删除员工的方法
	@Override
	public void delete(Employee employee) {
		this.getHibernateTemplate().delete(employee);	
	}
}
