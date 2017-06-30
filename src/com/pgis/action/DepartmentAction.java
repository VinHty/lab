package com.pgis.action;

import com.pgis.domain.Department;
import com.pgis.domain.Employee;
import com.pgis.domain.PageBean;
import com.pgis.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

	private Department department=new Department();//模型驱动使用的对象
	@Override
	public Department getModel() {
		return department;
	}
	
	private Integer currPage=1;//分页显示时的当前页数
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	
	//注入DepartmentService
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	//提供一个查询的方法
	public String findAll(){
		PageBean<Department> pageBean=departmentService.findByPage(currPage);
		//将pageBean存入到值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//跳转到添加部门页面
	public String saveUI(){
		return "saveUI";
	}
	
	//添加部门的执行方法
	public String save(){
		departmentService.save(department);
		return "saveSuccess";
	}
	
	//编辑部门的执行方法
	public String edit(){
		department=departmentService.findById(department.getDid());
		return "editSuccess";
	}
	
	//修改部门的执行方法
	public String update(){
		departmentService.update(department);
		return "updateSuccess";
	}
	
	
	//删除部门的执行方法
	public String delete(){
		department=departmentService.findById(department.getDid());//在删除部门时，级联删除该部门下的员工，需先查询部门
		departmentService.delete(department);
		return "deleteSuccess";
	}
	
	
	
}
