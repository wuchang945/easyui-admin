package com.feed.ecp.users.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import atg.taglib.json.util.JSONObject;

import com.feed.ecp.common.constants.Constants;
import com.feed.ecp.common.log.SystemControllerLog;
import com.feed.ecp.common.model.SysMenu;
import com.feed.ecp.common.model.Users;
import com.feed.ecp.common.modelDTO.ResultDto;
import com.feed.ecp.common.web.controller.BaseController;
import com.feed.ecp.menu.service.SysMenuService;
import com.feed.ecp.users.service.UserService;
import com.feed.util.DES;
import com.feed.util.HttpRequest;

import flexjson.JSONSerializer;
/**
 * 
 * @ClassName: LoginController
 * @author: 李纯远
 * @Description: 用户登陆控制器
 */
@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	@RequestMapping(value="/loginController/login",produces = {"text/html;charset=UTF-8"})
	@ResponseBody
//	@SystemControllerLog(description="用户登陆")
	public String userLogin(Users user, Model model, HttpSession session,HttpServletRequest request){
		ResultDto resultMess = new ResultDto();
		JSONSerializer jsonSerializer = new JSONSerializer();
		jsonSerializer.exclude("*.class"); // 排除class
		
		if(user.getUsername() == null || user.getUsername().equals("")){
			resultMess.setFlag("error");
			resultMess.setMessage("*用户名不能为空！");
			String resultJson = jsonSerializer.serialize(resultMess);
			return resultJson;
		}
		if(user.getPassword() == null || user.getPassword().equals("")){
			resultMess.setFlag("error");
			resultMess.setMessage("*密码不能为空！");
			String resultJson = jsonSerializer.serialize(resultMess);
			return resultJson;
		}
		// 将原始的password进行md5加密
		String md5pass = "";
		try {
			 //md5pass = MD5Util.BASE64Encode(user.getPassword());
			 //user.setPassword(md5pass);
			user.setPassword(DES.encrypt(user.getPassword(),DES.DES_KEY_STRING).trim());
			Users loginUsers = userService.userLogin(user);
			 if(loginUsers == null){
				 resultMess.setFlag("error");
				 resultMess.setMessage("*用户名或者密码不正确！");
			 }else{
				 resultMess.setFlag("true");
				 resultMess.setMessage("登陆成功！");
				 //密码置为空，防止页面上密码泄露
				 loginUsers.setPassword(null);
				 session.setAttribute(Constants.SessionUser, loginUsers);
			 }
		} catch (Exception e) {
			resultMess.setFlag("error");
			resultMess.setMessage("*登陆失败,请联系系统管理员！"+e.getMessage());
			e.printStackTrace();
		}
		String resultJson = jsonSerializer.serialize(resultMess);
		return resultJson;
	}
	
	
	/**
	 * 登陆成功后，根据用户权限加载菜单
	 * @param model
	 * @return
	 */
	@RequestMapping("/loginController/index")
	public String index(Model model,HttpSession session){
		Users login = getSessionUser();
		if(login==null){
			return "../login";
		}
		//查询用户角色
		//List<SysRoles> rolesList=sysRolesService.selectRolesByUserId(login.getId());
		//获取用户权限菜单
		List<SysMenu> moduleList=sysMenuService.selectMenuByGroup(login);
		
		// 将所有的菜单以json格式存放在系统中
		JSONSerializer jsonSerializer = new JSONSerializer();
		jsonSerializer.exclude("*.class","*.callbacks"); // 排除class
		String menuJson = jsonSerializer.serialize("menus", moduleList);
		//System.out.println(menuJson);
		model.addAttribute("allmenu",menuJson);
//		model.addAttribute("sysType", systype);
		return "../index";
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	@RequestMapping("/loginController/logout")
//	@SystemControllerLog(description="用户退出登陆") 
	public String logout(){
		request.get().getSession().invalidate();
		return "../login";
	}
	
	@RequestMapping(value="loginController/userAuthority",produces={"text/html;charset=utf-8"})
	@ResponseBody
	public String userAuthority(){
//		Users user=getSessionUser();
//		String[] sysTypes=user.getSystype().split(",");
//		StringBuilder json=new StringBuilder();
//		json.append("[");
//        for (int i = 0; i < sysTypes.length; i++)
//        {
//            if ("1".equals(sysTypes[i]))//系统管理
//            {
//                json.append("{ \"sysType\":1, \"url\":\"loginController/index.do\" },");
//            }
//            if ("2".equals(sysTypes[i]))//透明生产
//            {
//                json.append("{ \"sysType\":2, \"url\":\"loginController/index.do\"},");
//            }
//            if ("3".equals(sysTypes[i]))//透明餐饮
//            {
//                json.append("{ \"sysType\":3, \"url\":\"loginController/index.do\" },");
//            }
//            if ("4".equals(sysTypes[i]))//透明菜市场
//            {
//                json.append("{ \"sysType\":4, \"url\":\"loginController/index.do\" },");
//            }
//            if ("5".equals(sysTypes[i]))//透明预包装
//            {
//                json.append("{ \"sysType\":5, \"url\":\"loginController/index.do\" },");
//            }
//            if ("6".equals(sysTypes[i]))//执法
//            {
//                json.append("{ \"sysType\":6, \"url\":\"loginController/index.do\" },");
//            }
//            if ("7".equals(sysTypes[i]))//透明药品
//            {
//                json.append("{ \"sysType\":7, \"url\":\"\" },");
//            }
//            if ("8".equals(sysTypes[i]))//追溯
//            {
//                json.append("{ \"sysType\":8, \"url\":\"" + "" + "\" },");
//            }
//        }
//        String returnStr=json.toString();
//        returnStr=returnStr.substring(0, returnStr.lastIndexOf(","))+"]";
//        
//		return returnStr;
		return "";
	}
	
	
	@RequestMapping(value="/loginController/getIpInfo",produces={"text/html;charset=UTF-8"})
	  @ResponseBody
	  public String getIpInfo(HttpServletRequest request){
		  String ip=request.getRemoteAddr();
		  String resultMsg=HttpRequest.sendGet("http://ip.taobao.com/service/getIpInfo.php", "ip="+ip);
		  JSONObject jsonTemp=new JSONObject();
		  try{
			 JSONObject json=new JSONObject(resultMsg);
			 if("0".equals(json.get("code").toString())){
				 jsonTemp=new JSONObject(json.get("data").toString());
			 }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  request.getSession().setAttribute("ip_info", resultMsg);
		  return jsonTemp==null?null:jsonTemp.toString();
	  }
	/**
	 * 跳转到修改密码的页面
	 * @return
	 */
	@RequestMapping("/LoginController/toUpdatePassword")
	public String toUpdatePassword(){
		return "../changepwd";
	}
	
	/**
	 * 验证用户输入的原始密码
	 * @param newPassword
	 * @return json
	 */
	@RequestMapping(value="/LoginController/validatePwd",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String validatePwd(String oldPassword){
		Users user=getSessionUser();
//		user=userService.selectByPrimaryKey(user.getId());
		ResultDto resultDto=new ResultDto();
		try {
			if(user.getPassword().trim().equals(DES.encrypt(oldPassword, DES.DES_KEY_STRING).trim())){
				resultDto.setError("false");
				resultDto.setMessage("旧密码输入正确");
			}else{
				resultDto.setError("true");
				resultDto.setMessage("旧密码输入不正确");
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		JSONSerializer jsonSerializer = new JSONSerializer();
		jsonSerializer.exclude("*.class"); // 排除class
		String message  = jsonSerializer.serialize(resultDto);
		return message;
	}
	/**
	 * 修改密码
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(value="/LoginController/updatePassword",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String updatePassword(String oldPassword,String newPassword,HttpServletRequest request){
		Users user=getSessionUser();
		try {
			user.setPassword(DES.encrypt(newPassword, DES.DES_KEY_STRING).trim());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ResultDto resultDto=new ResultDto();
		int flag=userService.updateByPrimaryKeySelective(user);
		if(flag>0){
			resultDto.setFlag("true");
			resultDto.setMessage("操作成功");
		}else{
			resultDto.setFlag("false");
			resultDto.setMessage("操作失败");
		}	
		session.get().setAttribute(Constants.SessionUser,user);
		JSONSerializer jsonSerializer = new JSONSerializer();
		jsonSerializer.exclude("*.class"); // 排除class
		String message  = jsonSerializer.serialize(resultDto);
		return message;
	}
}
