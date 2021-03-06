package com.pgis.authorization.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgis.authorization.common.AjaxResult;
import com.pgis.authorization.entity.Role;
import com.pgis.authorization.entity.RoleFunction;
import com.pgis.authorization.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired private RoleService roleService;
	
	@RequestMapping("/index")
	public String index(){
		return "security/role/role_list";
	}
	
	@RequestMapping(value = "/getRoles", method = RequestMethod.POST)
	@ResponseBody
	public List<Role> getRoles(Integer page, Integer rows){
		return roleService.getRoles(page, rows);
	}
	
	@RequestMapping(value = "/addEditRole", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addEditRole(Role role){
		String functionIds = role.getFunctionIds();
		String[] idArray = functionIds.split(",");
		List<RoleFunction> roleFunctions = new ArrayList<RoleFunction>();
		for (int i = 0; i < idArray.length; i++){
			RoleFunction rf = new RoleFunction();
			rf.setFunctionId(Long.valueOf(idArray[i]));
			rf.setStatus(1);
			roleFunctions.add(rf);
		}
		if (null == role.getId()){
			roleService.addRole(role, roleFunctions);
		}else{
			roleService.editRole(role, roleFunctions);
		}
		return AjaxResult.success();
	}
	
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult deleteRole(Long id){
		roleService.deleteRole(id);
		return AjaxResult.success();
	}
	
}
