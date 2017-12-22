    package org.action.sp.webservice.test;  
    import java.io.File;
    import java.io.FileOutputStream;
import java.io.IOException;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
import java.text.DecimalFormat;
    import java.util.Date;
    import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.feed.ecp.common.util.DateUtil;
    
    public class moveData {  

    	public static void main(String[] args) throws IOException {
    		//导出市场
//    		getScData();
    		//导出经营户
    		getOperatorsData();
    		//表格导入
//    		excelReader();
			//修改联系方式
//    		changeMobile();
    		//导出超市
//    		getCs();
    		//划行规市
//    		hhgs();
    		//市场工作人员
//    		getScworker();
    		//产品库
//    		getProducts();
    		//新旧经营户id映射
    		getOperatorId();
		}
    
	private static Connection getConnect(){
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "sp365";
			String password = "sp365";
			
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
	
	
	public static void getOperatorsData(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
	String sql = "select * from spadmin a,spoperator o where a.operator_id=o.id and a.deleteflag=0 and o.market_id='1721' and a.account not in('131692','196207','187003','156275','139278','122127','136195','151015','102228','138605','136533','198885','113688','118551','175933','173179','125361','199730','185868','152813','107339','138560','110658','110777','100985','195605','177619','195070','160538','176767','102523','129981','123792','183752','107906','107582','189718','118210','181778','101176','199515','132221','166216','198066','103370','151233','109206','121279','176220','160976','127969','105910','120055','106750','125512','188598','193183','179886','192712','135105','173739','163001','169711','166807','198388','139203','150530','172032','138339','173186','116923','120773','139708','173286','156265','102999','181228','110511','186017','179727','117330','132300') ";
						try {
							conn = getConnect();
							ps=conn.prepareStatement(sql);
							result = ps.executeQuery();
							String insertSql="";
							while (result.next()) {
								String insert_users = "insert into tb_users(Username, Password, True_name, Tellphone, Mobilephone, Avatar, User_group_code, User_group_name, User_status, Register_time, Enterprise_id, Enterprise_name, ParentId, AddDate, AddUserId, AddName, UpdateDate, UpdateId, UpdateName, ext1, ext2,SysType) values(";
								String insert_enterprise = "insert into tb_enterprise(Enterprise_id,Enterprise_name,Organization_code,Enterprise_IdentCode,Business_license,Product_license_type,Enterprise_legaler,Tellphone,Mobilephone,Enterprise_Contact,Contact_Tell,Contact_Mobile,Contact_Email,Enterprise_profile,Enterprise_address,Enterprise_Url,Complaint_phone,icon,Type,SysType,ParentId,Coordinate,RecordDate,UploadDate,LicensePicture,BusinessLicensePicture,shopcode,IdCard,openstatus,bu_creditLicense,CoopLicense,HomeLicense,FoodRegisterBak,scope) values(";
								String insert_sc = "insert into tb_csc_sc(id, mc, lb, dz, lxdh, fzrmc, scyj,sclx, sczbzlx, sccq,scxz, zyfw, jyfwids,scjs, sczb, scmttpid, lttpid, yyzztpid, pmtid, scssq, scssfj, enterpriseid, activescore, fixscore, csztlb, guihuatu, kjsb,deleteFlag) values(";
								//插入用户表
								String enterprise_id=UUID.randomUUID().toString();
								//经营许可证
								String permitImg=result.getString(54);
								String busnessImg=result.getString(55);
								String nowTime=DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD);
								insert_users+="'"+result.getString(3)+"','VZR2z1LfL5I=','"+result.getString(36)+"','"+result.getString(9)+"','"+result.getString(9)+"','"+result.getString(30)+"','jyh','经营户',1,'"+nowTime+"','"+enterprise_id+"','"+result.getString(32)+"','','"+nowTime+"','','','"+nowTime+"','','','','',3";
								//插入企业表
								insert_enterprise+="'"+enterprise_id+"','"+result.getString(32)+"','','','','','"+result.getString(36)+"','"+result.getString(9)+"','"+result.getString(9)+"','"+result.getString(36)+"','"+result.getString(9)+"','"+result.getString(9)+"','"+result.getString(8)+"','"+result.getString(66)+"','"+result.getString(65)+"','"+result.getString(70)+"','','','Enterprise','3','1721','"+result.getString(71)+"','"+nowTime+"','"+nowTime+"','"+permitImg+"','"+busnessImg+"','jyh','"+result.getString(19)+"','1','','','','',''";
								String sc_id=UUID.randomUUID().toString();
								//插入企业详情表
								insert_sc+=	"'"+sc_id+"','"+result.getString(36)+"','jyh','"+result.getString(65)+"','"+result.getString(9)+"','"+result.getString(36)+"','','','','','','"+result.getString(59)+"','"+result.getString(53)+"','','','','','"+busnessImg+"','','','','"+enterprise_id+"','40','40','','','',1";
										
								insert_users+=");\n";
								insert_enterprise+=");\n";
								insert_sc+=");\n";
								insertSql+=insert_users+insert_enterprise+insert_sc;
							}
							File file=new File("D:\\淮海东路补_jyh_sql.sql");
							FileOutputStream fos=new FileOutputStream(file); 
							fos.write(insertSql.getBytes());
							fos.flush();
							fos.close();
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
	}
	
	
	public static void getScData(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String sql = "select * from spmarket where isdelete=0 and id='40895' ";
						try {
							conn = getConnect();
							ps=conn.prepareStatement(sql);
							result = ps.executeQuery();
							String insertSql="";
							while (result.next()) {
								String insert_users = "insert into tb_users(Username, Password, True_name, Tellphone, Mobilephone, Avatar, User_group_code, User_group_name, User_status, Register_time, Enterprise_id, Enterprise_name, ParentId, AddDate, AddUserId, AddName, UpdateDate, UpdateId, UpdateName, ext1, ext2,SysType) values(";
								String insert_enterprise = "insert into tb_enterprise(Enterprise_id,Enterprise_name,Organization_code,Enterprise_IdentCode,Business_license,Product_license_type,Enterprise_legaler,Tellphone,Mobilephone,Enterprise_Contact,Contact_Tell,Contact_Mobile,Contact_Email,Enterprise_profile,Enterprise_address,Enterprise_Url,Complaint_phone,icon,Type,SysType,ParentId,Coordinate,RecordDate,UploadDate,LicensePicture,BusinessLicensePicture,shopcode,IdCard,openstatus,bu_creditLicense,CoopLicense,HomeLicense,FoodRegisterBak,scope) values(";
								String insert_sc = "insert into tb_csc_sc(id, mc, lb, dz, lxdh, fzrmc, scyj,sclx, sczbzlx, sccq,scxz, zyfw, jyfwids,scjs, sczb, scmttpid, lttpid, yyzztpid, pmtid, scssq, scssfj, enterpriseid, activescore, fixscore, csztlb, guihuatu, kjsb,deleteFlag) values(";
								//插入用户表
//								String enterprise_id=UUID.randomUUID().toString();
								
								//parentId是我们系统网格片区的enterprise_id
								String parentId="1830b3b1-c8c8-40c1-ae8f-883cbe0c6bb7";
								//营业执照
								//许可证
								String nowTime=DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD);
								insert_users+="'"+result.getString(2)+"','VZR2z1LfL5I=','"+result.getString(19)+"','"+result.getString(10)+"','"+result.getString(10)+"','','scadmin','市场',1,'"+nowTime+"','"+result.getString(1)+"','"+result.getString(9)+"','','"+nowTime+"','','','"+nowTime+"','','','','',3";
								//插入企业表
								//市场类型1：批发市场 2批零兼营 3零售市场
								String marketType="";
								if(result.getInt(17)==1){
									marketType="PFSC";
								}else{
									marketType="LSSC";
								}
								//经营许可证
								String permitImg=null;
								String busnessImg=null;
								if(result.getString(27)!=null){
									permitImg=(String) result.getString(27).subSequence(result.getString(27).lastIndexOf("/")+1, result.getString(27).length());
								}
								if(result.getString(3)!=null){
									busnessImg=(String) result.getString(3).subSequence(result.getString(3).lastIndexOf("/")+1, result.getString(3).length());
								}
								//营业执照
								insert_enterprise+="'"+result.getString(1)+"','"+result.getString(9)+"','','','','','"+result.getString(19)+"','"+result.getString(10)+"','"+result.getString(10)+"','"+result.getString(19)+"','"+result.getString(10)+"','"+result.getString(10)+"','','"+result.getString(42)+"','"+result.getString(40)+"','','"+result.getString(10)+"','','Enterprise','3','"+parentId+"','"+result.getString(50)+"','"+nowTime+"','"+nowTime+"','"+permitImg+"','"+busnessImg+"','sc','','1','','','','',''";
								
								String sc_id=UUID.randomUUID().toString();
								//插入企业详情表
								insert_sc+=	"'"+sc_id+"','"+result.getString(19)+"','"+marketType+"','"+result.getString(40)+"','"+result.getString(10)+"','"+result.getString(19)+"','"+result.getInt(18)+"','"+result.getInt(17)+"','"+result.getInt(20)+"','"+result.getInt(22)+"','1','','"+result.getString(13)+"','"+result.getString(42)+"','"+result.getString(50)+"','','','"+busnessImg+"','','','','"+result.getString(1)+"','40','40','','','',1";
										
								insert_users+=");\n";
								insert_enterprise+=");\n";
								insert_sc+=");\n";
								insertSql+=insert_users+insert_enterprise+insert_sc;
							}
							File file=new File("D:\\新街_sc_sql.sql");
							FileOutputStream fos=new FileOutputStream(file); 
							fos.write(insertSql.getBytes());
							fos.flush();
							fos.close();
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
	}
	
	public static void changeMobile(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String sql = "select * from spmarket where isdelete=0";
		conn=getConnect();
		try {
			ps=conn.prepareStatement(sql);
			result=ps.executeQuery();
			String update_sql="";
//			String update_marketMobile="";
			while(result.next()){
				update_sql+="update tb_csc_sc set lttpid='"+result.getString(27)+"',yyzztpid='"+result.getString(3)+"',scmttpid='"+result.getString(43)+"' where enterpriseid=(select top 1 enterprise_id from tb_users where username='"+result.getString(2)+"' and Register_time>'2016-07-08');\n";
			}
			File file=new File("D:\\sc_tp_sql.sql");
			FileOutputStream fos=new FileOutputStream(file); 
			fos.write(update_sql.getBytes());
			fos.flush();
			fos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getCs(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String sql="select * from spadmin a,spoperator o where a.operator_id=o.id and o.operatortype=7 and a.deleteflag=0";
		try {
			conn = getConnect();
			ps=conn.prepareStatement(sql);
			result = ps.executeQuery();
			String insertSql="";
			while (result.next()) {
				String update_enterprise_cs = "update tb_enterprise set licensePicture='"+result.getString(54)+"',BusinessLicensePicture='"+result.getString(55)+"' where enterprise_id=(select top 1 enterprise_id from tb_users where username='"+result.getString(3)+"');\n";
				String update_csc_sc= "update tb_csc_sc set scmttpid='"+result.getString(69)+"',lttpid='"+result.getString(54)+"',yyzztpid='"+result.getString(55)+"' where enterpriseid=(select top 1 enterprise_id from tb_users where username='"+result.getString(3)+"');\n";
				insertSql+=update_enterprise_cs+update_csc_sc;
			}
			File file=new File("D:\\update_cs_imgPath_sql.sql");
			FileOutputStream fos=new FileOutputStream(file); 
			fos.write(insertSql.getBytes());
			fos.flush();
			fos.close();
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
	}
	
	public static void getScworker(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String sql = "select * from SPMEMBERS t ";
		conn=getConnect();
		try {
			ps=conn.prepareStatement(sql);
			result=ps.executeQuery();
			String insert_sql="";
			while(result.next()){
				String id=UUID.randomUUID().toString();
				String enterprise_id=result.getString(6)==null?result.getString(9):result.getString(6);
				insert_sql+="insert into tb_csc_scgzry values('"+id+"','"+result.getString(2)+"','"+result.getString(5)+"','','"+result.getString(3)+"','','','"+enterprise_id+"','"+result.getString(4)+"','');\n";
			}
			File file=new File("D:\\scworker_sql.sql");
			FileOutputStream fos=new FileOutputStream(file); 
			fos.write(insert_sql.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void hhgs(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String sql = "select s.*,a.* from spadmin a,spmarketshop s where a.operator_id=s.operator_id ";
		conn=getConnect();
		try {
			ps=conn.prepareStatement(sql);
			result=ps.executeQuery();
			String insert_sql="";
//			String update_marketMobile="";
			while(result.next()){
//				insert_sql+="insert into tb_csc_scqy(id,qymc,ssscid,zjsj,line,columns,code) values('"+id+"','"+result.getString(4)+"','"+result.getString(5)+"','"+DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD)+"','"+result.getString(2)+"','"+result.getString(3)+"','"+result.getString(6)+"');\n";
				String account=result.getString(11);
				
				insert_sql+="insert into tb_csc_shop(line,columns,enterpriseid,scqyid,boothno) values('"+result.getString(4)+"','"+result.getString(5)+"',(select top 1 enterprise_id from tb_users where username='"+account+"'),'"+result.getString(2)+"','"+result.getString(8)+"');\n";
			}
			File file=new File("D:\\scqy_shop_sql.sql");
			FileOutputStream fos=new FileOutputStream(file); 
			fos.write(insert_sql.getBytes());
			fos.flush();
			fos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getProducts(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String sql = "select * from SPProduct ";
		conn=getConnect();
		try {
			ps=conn.prepareStatement(sql);
			result=ps.executeQuery();
			String insert_sql="";
			while(result.next()){
				String id=UUID.randomUUID().toString();
				String enterprise_id=result.getString(6)==null?result.getString(9):result.getString(6);
				insert_sql+="insert into tb_csc_product values('"+id+"','"+result.getString(2)+"','"+result.getString(5)+"','','"+result.getString(3)+"','','','"+enterprise_id+"','"+result.getString(4)+"','');\n";
			}
			File file=new File("D:\\scworker_sql.sql");
			FileOutputStream fos=new FileOutputStream(file); 
			fos.write(insert_sql.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getOperatorId(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String sql = "select * from spadmin a,spoperator o where a.operator_id=o.id and a.deleteflag=0";
		conn=getConnect();
		try {
			ps=conn.prepareStatement(sql);
			result=ps.executeQuery();
			String insert_sql="";
			while(result.next()){
				insert_sql+="insert into tb_webcode_mapping values('','"+result.getString(18)+"','"+result.getString(3)+"');\n";
			}
			File file=new File("D:\\新旧经营户id映射_sql.sql");
			FileOutputStream fos=new FileOutputStream(file); 
			fos.write(insert_sql.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void excelReader() throws IOException{
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook xwb = new XSSFWorkbook("D:\\operators.xlsx");
        // 读取第一章表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        // 定义 row、cell
        XSSFRow row;
        XSSFCell cell;
        String insertSql="";
        // 循环输出表格中的内容
        for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
//            for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
//                // 通过 row.getCell(j).toString() 获取单元格内容，
//            	cell=row.getCell(j);
//            	 String contents="";
//            	if(i>0&&(j==2||j==1)){
//            	DecimalFormat df = new DecimalFormat("0");//使用DecimalFormat类对科学计数法格式的数字进行格式化
//            	contents = df.format(cell.getNumericCellValue());
//            }else{
//            	contents=cell.getStringCellValue();
//            }
            	
//						String insert_users = "insert into tb_users(Username, Password, True_name, Tellphone, Mobilephone, Avatar, User_group_code, User_group_name, User_status, Register_time, Enterprise_id, Enterprise_name, ParentId, AddDate, AddUserId, AddName, UpdateDate, UpdateId, UpdateName, ext1, ext2,SysType) values(";
//						String insert_enterprise = "insert into tb_enterprise(Enterprise_id,Enterprise_name,Organization_code,Enterprise_IdentCode,Business_license,Product_license_type,Enterprise_legaler,Tellphone,Mobilephone,Enterprise_Contact,Contact_Tell,Contact_Mobile,Contact_Email,Enterprise_profile,Enterprise_address,Enterprise_Url,Complaint_phone,icon,Type,SysType,ParentId,Coordinate,RecordDate,UploadDate,LicensePicture,BusinessLicensePicture,shopcode,IdCard,openstatus,bu_creditLicense,CoopLicense,HomeLicense,FoodRegisterBak,scope) values(";
//						String insert_sc = "insert into tb_csc_sc(id, mc, lb, dz, lxdh, fzrmc, scyj,sclx, sczbzlx, sccq,scxz, zyfw, jyfwids,scjs, sczb, scmttpid, lttpid, yyzztpid, pmtid, scssq, scssfj, enterpriseid, activescore, fixscore, csztlb, guihuatu, kjsb,deleteFlag) values(";
//						//插入用户表
//						String enterprise_id=UUID.randomUUID().toString();
//						//经营许可证
//						String nowTime=DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD);
//						insert_users+="'','VZR2z1LfL5I=','"++"','"+result.getString(9)+"','"+result.getString(9)+"','"+result.getString(30)+"','jyh','经营户',1,'"+nowTime+"','"+enterprise_id+"','"+result.getString(32)+"','','"+nowTime+"','','','"+nowTime+"','','','','',3";
//						//插入企业表
//						insert_enterprise+="'"+enterprise_id+"','"+result.getString(32)+"','','','','','"+result.getString(36)+"','"+result.getString(9)+"','"+result.getString(9)+"','"+result.getString(36)+"','"+result.getString(9)+"','"+result.getString(9)+"','"+result.getString(8)+"','"+result.getString(66)+"','"+result.getString(65)+"','"+result.getString(70)+"','','','Enterprise','3','48670','"+result.getString(71)+"','"+nowTime+"','"+nowTime+"','"+permitImg+"','"+busnessImg+"','jyh','"+result.getString(19)+"','1','','','','',''";
//						String sc_id=UUID.randomUUID().toString();
//						//插入企业详情表
//						insert_sc+=	"'"+sc_id+"','"+result.getString(36)+"','jyh','"+result.getString(65)+"','"+result.getString(9)+"','"+result.getString(36)+"','','','','','','"+result.getString(59)+"','"+result.getString(53)+"','','','','','"+busnessImg+"','','','','"+enterprise_id+"','40','40','','','',1";
//								
//						insert_users+=");\n";
//						insert_enterprise+=");\n";
//						insert_sc+=");\n";
//						insertSql+=insert_users+insert_enterprise+insert_sc;
//					
//                       System.out.println("");
//        }
        }
//        File file=new File("D:\\星光菜市场补_jyh_sql.sql");
//		FileOutputStream fos=new FileOutputStream(file); 
//		fos.write(insertSql.getBytes());
//		fos.flush();
//		fos.close();
	}
	}
	
	

