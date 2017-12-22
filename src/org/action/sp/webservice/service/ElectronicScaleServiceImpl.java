package org.action.sp.webservice.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feed.ecp.common.util.DateUtil;
import com.feed.util.Base64;
import com.feed.util.ElectronicScaleDES;
import com.feed.util.SPUtils;

import flexjson.JSONSerializer;



public class ElectronicScaleServiceImpl implements ElectronicScaleService{
	private static Connection getConnect(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://218.2.36.50:1433;databaseName=TransparentQualifiedProduction_Merge_New_Test";
			String userName = "sa";
//			String password = "1q2w#E$R";
//			String password = "lichunyuan@123";
			String password = "njch@83130911";
			
			try {
				conn = DriverManager.getConnection(url, userName, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public String getProductInfo(String account) {
		String sql="select s.spbh,s.spmc,s.pc,s.jyhid,u.Enterprise_name,s.createTime,s.spicon,s.cpsl from tb_csc_spb s,tb_users u where s.jyhid=u.Enterprise_id and userName=? and s.delete_flag=1";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		JSONArray jsonArray=new JSONArray();
		try {
			conn=getConnect();
			ps=conn.prepareStatement(sql);
			ps.setString(1, account);
			rs=ps.executeQuery();
			while(rs.next()){
				JSONObject json=new JSONObject();
				json.put("productId", rs.getString(1));
				json.put("productName", rs.getString(2));
				json.put("productBatch", rs.getString(3));
				json.put("jyhId", rs.getString(4));
				json.put("jyhName", rs.getString(5));
				json.put("storeTime", rs.getString(6));
				json.put("productIcon", rs.getString(7));
				json.put("productCount", rs.getDouble(8));
				json.put("Ext1", "");
				json.put("Ext2", "");
				json.put("Ext3", "");
				jsonArray.add(json);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONSerializer jsonSerializer=new JSONSerializer();
		jsonSerializer.exclude("*.class");
		String returnMessage=jsonSerializer.deepSerialize(jsonArray);
		return returnMessage;
	}

	
	@SuppressWarnings("unused")
	@Override
	public String postProductData(String productData) {
		String productDataJson=productData;
		JSONObject json=JSONObject.parseObject(productDataJson);
		Connection conn=null;
		conn=getConnect();
		PreparedStatement ps=null;
		String ddNO="ES"+SPUtils.generatorNO();
		//合法性校验
		JSONObject json_return=new JSONObject();
		try {
			String authCodeStr=ElectronicScaleDES.decrypt(json.getString("authCode"),ElectronicScaleDES.DES_KEY_STRING);
			String[] authCodeArray=authCodeStr.split("_");
			String code=authCodeArray[0]+authCodeArray[1];
			
			//经营户id和设备编号后期可以锁定，设备只能是由改商户使用，别人使用不了
			
			if("jschrj9773".equals(code)){
				String timestamp=authCodeArray[1];
				Date now=new Date();
				long dif=now.getTime()-Long.parseLong(timestamp);
				long s = dif/1000;
				if(s>30*60){
					if(json!=null){
					String insertSql="insert into tb_csc_scale_order(flowNO,ddbh,productId,productName,productBatch,poductWeight,productUnit,jyhId,jyhName,status,deviceNO,createTime,Ext1,Ext2,Ext3) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					 System.out.println(json.getString("flowNo").toString()+","+ddNO+","+json.getString("productId").toString()+","+json.getString("productName").toString()+
							 ","+json.getString("productBatch").toString()+","+json.getString("productWeight").toString()+","+json.getString("productUnit").toString()+","
							 +","+json.getString("jyhId").toString()+","+json.getString("jyhName").toString()+","+json.getString("status").toString()+","+json.getString("deviceNO").toString()+","+DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD));
					try {
						ps=conn.prepareStatement(insertSql);
						ps.setString(1, json.getString("flowNo").toString());
						ps.setString(2, ddNO);
						ps.setString(3, json.getString("productId").toString());
						ps.setString(4, json.getString("productName").toString());
						ps.setString(5, json.getString("productBatch").toString());
						ps.setString(6, json.getString("productWeight").toString());
						ps.setString(7, json.getString("productUnit").toString());
						ps.setString(8, json.getString("jyhId").toString());
						ps.setString(9, json.getString("jyhName").toString());
						ps.setString(10, json.getString("status").toString());
						ps.setString(11, json.getString("deviceNO").toString());
						ps.setString(12, DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD));
						ps.setString(13, json.getString("Ext1").toString());
						ps.setString(14, json.getString("Ext2").toString());
						ps.setString(15, json.getString("Ext3").toString());
						int flag=ps.executeUpdate();
						if(flag>0){
							json_return.put("flag", "success");
							json_return.put("message", ddNO);
							json_return.put("flowNo", json.getString("flowNo").toString());
						}
					} catch (SQLException e) {
						json_return.put("flag", "error");
						json_return.put("message", e.getMessage());
						e.printStackTrace();
					}
				}else{
					json_return.put("flag", "error");
					json_return.put("message", "订单已超时！");
				}
				}
			}else{
				json_return.put("flag", "error");
				json_return.put("message", "合法性验证失败！");
			}
		} catch (Exception e1) {
			json_return.put("flag", "error");
			json_return.put("message",e1.getMessage());
			e1.printStackTrace();
		}
		//是否过期验证
		return json_return.toString();
	}

}
