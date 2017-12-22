package org.action.sp.webservice;

import org.action.sp.webservice.service.QuickService;
import org.action.sp.webservice.service.QuickServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuickController {
	private QuickService quickService=new QuickServiceImpl();
	
	@RequestMapping("/services/getQuickInfoService/getAllQuickInfo")
	public String[] getAllQuickInfo(String marketId){
		System.out.println("调用webservice接口...");
		return quickService.getAllOperatorsService(marketId);
	}
	
	@RequestMapping("/services/getQuickInfoService/doQuickInfo")
	public Integer doQuickInfo(String paramValues){
		System.out.println("快检信息="+paramValues);
		return quickService.doQuickInfo(paramValues);
	}
	
	@RequestMapping("/services/getCheckData")
	public String getCheckData(String user,String password,String checkData){
		System.out.println("快检信息="+checkData);
		return "1";
	}
	

}
