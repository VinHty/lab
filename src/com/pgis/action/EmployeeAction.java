package com.pgis.action;

import java.util.List;
import java.util.Map;

import com.pgis.domain.Department;
import com.pgis.domain.Employee;
import com.pgis.domain.PageBean;
import com.pgis.service.DepartmentService;
import com.pgis.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
 * 员工管理Action类
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {
	
	private Employee employee= new Employee();//模型驱动使用的对象
	private EmployeeService employeeService;//注入员工Service业务层类：
	private DepartmentService departmentService;//注入部门Service业务层类：
	@Override
	public Employee getModel() {
		return employee;
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private Integer currPage=1;//分页显示时的当前页数
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	
	/*
	 * 登入执行的方法
	 */
	public String login() throws Exception{
		System.out.println("EmployeeAction的login()方法执行了...");
		Employee existEmployee=employeeService.login(employee);
		if(existEmployee==null){
			//登入失败
			System.out.println("登入失败！输入的账号是："+employee.getUsername());
			if(employee.getUsername()==null||employee.getUsername().equals("")){
				ActionContext.getContext().getSession().put("wrong", "请输入用户名密码！");
			}else{
				ActionContext.getContext().getSession().put("wrong", "用户名or密码有误！");
			}
			return LOGIN;
		}else if(existEmployee.getDepartment().getDid()!=3){
			//不是人事部(did=3)员工，没有登入权限
			System.out.println("不是人事部员工，没有登入权限！");
			ActionContext.getContext().getSession().put("wrong", "非人事部员工");
			return LOGIN;
		}else{
			//人事部(did=3)员工登入成功
			System.out.println("登入成功！");
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
		
	}	
	
	//提供一个查询的方法
	public String findAll(){
		PageBean<Employee> pageBean=employeeService.findByPage(currPage);
		//将pageBean存入到值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//跳转到添加员工页面
	public String saveUI(){
		//查询所有部门
		List<Department> list = departmentService.findAll();
		System.out.println(list.get(0));
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	//添加保存员工的执行方法
	public String save(){
		employeeService.save(employee);
		return "saveSuccess";
	}	
	
	//编辑员工的执行方法
	public String edit(){
		employee=employeeService.findById(employee.getEid());
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
		
	}
	
	//修改员工保存到执行
	public String update(){
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	//删除员工的执行方法
	public String delete(){
		employee=employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
	
	//退出员工管理系统的执行方法
	public String exit(){
		System.out.println("EmployeeAction的exit()方法执行了...");
		ActionContext.getContext().getSession().clear();//清除session内容，跳回登入界面
		return LOGIN;
	}

}
	
