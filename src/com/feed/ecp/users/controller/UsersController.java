package com.feed.ecp.users.controller;
/**
 * 系统用户控制器
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.management.relation.Role;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.feed.ecp.common.constants.Constants;
import com.feed.ecp.common.model.SysMenu;
import com.feed.ecp.common.model.Users;
import com.feed.ecp.common.modelDTO.ResultDto;
import com.feed.ecp.common.modelDTO.UsersDto;
import com.feed.ecp.common.pager.PagerModel;
import com.feed.ecp.common.pager.PagerParams;
import com.feed.ecp.common.util.DateUtil;
import com.feed.ecp.common.util.HttpServiceCaller;
import com.feed.ecp.common.util.StringUtil;
import com.feed.ecp.common.web.controller.BaseController;
import com.feed.ecp.menu.service.SysMenuService;
import com.feed.ecp.users.service.UserService;
import com.feed.util.DES;

import flexjson.JSONSerializer;
@Controller
public class UsersController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="manager/UsersController/index",produces={"text/html;charset=utf-8"})
	public String index(int moduleid,Model model){
		SysMenu sysMenu=sysMenuService.selectByPrimaryKey(moduleid);
		Users users= getSessionUser();
		List<SysMenu> list=sysMenuService.selectButtonByGroup(users.getUsergroupcode(), moduleid);
		model.addAttribute("list",list);
		model.addAttribute("sysMenu",sysMenu);
		return "manager/sysuser/sysUsersList";
	}
	
	@RequestMapping(value="manager/UserController/getAllUsers",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String getAllUsers(UsersDto usersDto,PagerParams pagerParams){
		usersDto.setPagerParams(pagerParams);
		List<Users> data=userService.getAllSysUsers(usersDto);
		int total=userService.getAllSysUsersCount(usersDto);
		PagerModel pm=buildPM(data);
		pm.setTotal(total);
		return str2Json(pm);
	}
	@RequestMapping(value="manager/UserController/toAddUser",produces={"text/html;charset=utf-8"})
	public String toAddUser(){
		return "manager/sysuser/addSysUser";
	}
	
	
	@RequestMapping(value="manager/UserController/addUser",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String addUser(Users user){
		ResultDto resultDto=new ResultDto();
		int flag=userService.insertSelective(user);
		if(flag>0){
			resultDto.setFlag(Constants.FLAG_SUCCESS);
			resultDto.setMessage(Constants.MESS_SUCCESS);
		}else{
			resultDto.setFlag(Constants.MESS_FAILED);
			resultDto.setMessage(Constants.MESS_FAILED);
		}
		return str2Json(resultDto);
	}
}
