package org.action.sp.webservice.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.feed.ecp.common.constants.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QuickServiceImpl implements QuickService {

	private static Connection getConnect(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String url = "jdbc:sqlserver://192.168.223.138:1433;databaseName=TransparentQualifiedProduction_Merge_New_Test";
//			String userName = "sa";
//			String password = "admin123!@#";
			
			
			String url = "jdbc:sqlserver://192.168.177.40:1433;databaseName=TransparentQualifiedProduction_Merge_New_Test";
			String userName = "sa";
			String password = "1q2w#E$R";
			
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
	public String[] getAllOperatorsService(String marketId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		//如果是县区局快检中心
//		String checkRole="select user_group_code from tb_users where username=?";
		
		String sql = "select enterprise.enterprise_id,enterprise.enterprise_name,enterprise.enterprise_Contact,spb.spbh,spb.spmc "+
" from tb_enterprise enterprise,tb_csc_spb spb "+
" where enterprise.enterprise_id=spb.jyhid " +
"and enterprise.ParentId=(select e.enterprise_id from tb_enterprise e,tb_users u where u.Enterprise_id=e.enterprise_id and u.userName='"+marketId+"')";
		
//		String sql_quickCenter="select e.enterprise_id,e.enterprise_name,e.enterprise_Contact,spb.spbh,spb.spmc"+
//" from tb_enterprise e,tb_csc_quickcenter_jyh_mapping m,tb_csc_spb spb"+
//" where e.enterprise_id=m.jyh_id and spb.jyhid=m.jyh_id and m.quickCenter_id=(select u.enterprise_id from tb_users u where  u.userName='134110')";
		
		String items = "";
		try {
			conn = getConnect();
//			System.out.println("sql=" + sql);
//			ps = conn.prepareStatement(sql);
			CallableStatement c=conn.prepareCall("{call quickCheckUpload(?)}"); 
			c.setString(1,marketId);  
			result = c.executeQuery();
			while (result.next()) {
				items += "{\"operatorId\":\""+ result.getString(1) + "\",";
				items += "\"shopName\":\""+ String.valueOf(result.getString(2)) + "\",";
				items += "\"operatorName\":\""+ String.valueOf(result.getString(3)) + "\",";
				items += "\"productId\":\"" + String.valueOf(result.getString(4))+ "\",";
				items += "\"productName\":\""+ String.valueOf(result.getString(5)) + "\"},";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		String[] returnValue = new String[1];
		if (items != null && !"".equals(items)) {
			returnValue[0] = "["
					+ items.substring(0, items.lastIndexOf(",")).toString()
					+ "]";
		}
		return returnValue;
	}

	@Override
	public Integer doQuickInfo(String quickInfo) {
			JSONArray json=JSONArray.fromObject(quickInfo);
			String saveQuickSql=" insert into tb_csc_sckjjl (id, shmc, jcspid,jcxm, jcjg, jcr, jcfs, jcz, bzz, jcjguo,createTime,submitTime,isDelete,enterprise_id,parent_id) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      		
			String selectSql="select * from quickCheckInnerOuter where spbh=?";
			String updateSql2="update tb_csc_spb set sfyhgz=? where spbh=?";
			
			String groupcode="select e.shopcode,e.enterprise_id from tb_enterprise e,tb_users u where e.enterprise_id=u.enterprise_id and u.username=?";
			Connection conn=null;
			
			
			PreparedStatement ps=null;
			PreparedStatement ps2=null;
			PreparedStatement ps4=null;
			PreparedStatement ps5=null;
			Integer flag=0;
			try {
				conn=getConnect();
				conn.setAutoCommit(false);
			for (int i = 0; i < json.size(); i++) {
				JSONObject jsonValue=JSONObject.fromObject(json.get(i));
					
					ps=conn.prepareStatement(saveQuickSql);
					ps.setString(1,UUID.randomUUID().toString());
					ps.setString(2,jsonValue.get("shopName").toString());
					ps.setString(3,jsonValue.get("productId").toString());
					ps.setString(4,jsonValue.get("detectPro").toString());
					ps.setString(5,jsonValue.get("detectUnit").toString());
					ps.setString(6,jsonValue.get("checkPerson").toString());
					ps.setString(7,jsonValue.get("detectType").toString());
					ps.setString(8,jsonValue.get("detectValue").toString());
					ps.setString(9,jsonValue.get("standarValue").toString());
					ps.setString(10,jsonValue.get("checkResult").toString());
					ps.setString(11,jsonValue.get("detectTime").toString());
					ps.setString(12,jsonValue.get("submitTime").toString());
					ps.setInt(13,1);
					
					ps2=conn.prepareStatement(selectSql);
					ps2.setString(1,jsonValue.get("productId").toString());
					ResultSet rs2=ps2.executeQuery();
					String jyhid="";
					String parentid="";
					String xququickcenterid="";
					while(rs2.next()){
						jyhid=rs2.getString(2);
						parentid=rs2.getString(3);
					}
					ps.setString(14,jyhid);
					String marketAccount=jsonValue.get("marketAccount").toString();
					ps5=conn.prepareStatement(groupcode);
					ps5.setString(1,marketAccount);
					ResultSet rs=ps5.executeQuery();
					String usergroupcode="";
					while(rs.next()){
						usergroupcode=rs.getString(1);
						xququickcenterid=rs.getString(2);
					}
					if(Constants.role_xqjquickcenter.equals(usergroupcode)){
						//加载县区局快检中心的id
						//蔬菜是否来自系统外，经营户是否来自系统外
						ps.setString(15,xququickcenterid);
					}else{
						ps.setString(15,parentid);
					}
					
					flag=ps.executeUpdate();
					
					ps4=conn.prepareStatement(updateSql2);
					//修改productLog快检状态
					int quickResultFlag2=Integer.parseInt(jsonValue.get("checkResult").toString())==1?3:4;
					ps4.setInt(1,quickResultFlag2);
					ps4.setString(2,jsonValue.get("productId").toString());
					flag=ps4.executeUpdate();
					flag=1;
				conn.setAutoCommit(true);
			}
			} catch (SQLException e1) {
				e1.printStackTrace();
				// TODO Auto-generated catch block
				try {
					flag=0;
					conn.rollback();
					e1.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}finally{
				try {
					if(ps!=null)ps.close();
					if(ps2!=null)ps2.close();
					if(ps4!=null)ps4.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		return flag;
	}
}
