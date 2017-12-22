package org.action.sp.webservice;

import net.sf.json.JSONObject;

import org.action.sp.webservice.service.ElectronicScaleService;
import org.action.sp.webservice.service.ElectronicScaleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feed.ecp.common.modelDTO.ResultDto;
import com.feed.util.ElectronicScaleDES;


import flexjson.JSONSerializer;

@Controller
public class ElectronicScaleController {
private ElectronicScaleService electronicScaleService=new ElectronicScaleServiceImpl();
	
	@RequestMapping(value="/services/getElectronicScaleService/requestData",produces={"text/html;charset=utf-8"})
	public String getProductInfo(String requestData){
		ResultDto resultDto=new ResultDto();
		//接收的数据
		System.out.println(requestData);
		//经营户账号
		String account="";
		//电子秤设备编号
		String deviceNO="";
		//Des对接口传过来的数据进行解密验证
		try {
			String desData=ElectronicScaleDES.decrypt(requestData,ElectronicScaleDES.DES_KEY_STRING);
			JSONObject json=JSONObject.fromObject(desData);
			account=(String) json.get("account");
			deviceNO=(String) json.get("deviceNO");
			System.out.println("经营户账号："+account);
			System.out.println("设备编号："+deviceNO);
			//插入设备编号到数据库
			resultDto.setError("false");
		} catch (Exception e) {
			resultDto.setError("true");
			resultDto.setMessage(e.getMessage());
			e.printStackTrace();
		}
		if("false".equals(resultDto.getError())){
			return electronicScaleService.getProductInfo(account);
		}else{
			JSONSerializer jsonSerializer=new JSONSerializer();
			jsonSerializer.exclude("*.class");
			return jsonSerializer.deepSerialize(resultDto);
		}
	}
	
	
	@RequestMapping(value="/services/getElectronicScaleService/postProductData",produces={"text/html;charset=utf-8"})
	public String postProductData(String productData){
		//接收的数据
		String desData="";
		try {
			System.out.println(productData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return electronicScaleService.postProductData(productData);
	}
}
