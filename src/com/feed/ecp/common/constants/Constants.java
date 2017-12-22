package com.feed.ecp.common.constants;


public class Constants
{
	/**
	 * 批发市场
	 */
	public static String PIFASHICHANG="PFSC";
	/**
	 * 零售市场
	 */
	public static String LINGSHOUSHICHANG="LSSC";
	/**
	 * 超市
	 */
	public static String CHAOSHI="CS";
	/**
	 * 电商
	 */
	public static String DIANSHANG="DS";
	/**
	 * 经营户
	 */
	public static String JINGYINGHU="JYH";
	/**
	 * 缓存中的sessionUser
	 */
	public static String SessionUser="loginuser";
	/**
	 * 缓存中的sessionWebUser
	 */
	public static String SessionWebuser="loginWebuser";
	/** 
	 *  文件服务器地址信息
	 */
	//上传地址
	public static String FILE_ADDRESS = "";
//	public static String FILE_ADDRESS = "http://218.2.36.50:8079/tmcsc/";
	//访问地址
//	public static String FILE_IP_ADDRESS = "http://218.2.36.50:8079/tmcsc/";
//	public static String FILE_IP_ADDRESS = "http://222.184.79.76:8079/tmcsc/";
//	public static String FILE_IP_ADDRESS = "http://218.2.36.50:8079/tmcsc/";
	public static String FILE_IP_ADDRESS = "";
//	public static String FILE_IP_ADDRESS ="http://localhost:8079/tmcsc/";
	
	
	public static String FLAG_SUCCESS="SUCCESS";
	public static String FLAG_FAILED="FAILED";
	public static String MESS_SUCCESS="操作成功";
	public static String MESS_FAILED="操作失败";
	//生产
//	public static String shengchan="http://222.184.79.76:8078/";
	
	
	public static String SERVERPATH="http://localhost:8080/ecp";	
	/** 
	 *  文件上传的系统名称
	 */
	public static String FILE_UPLOAD_SYS_NAME = "tmcsc";
	
	public static int WEBCHAT_ROWS=20;
	
	public static String[] SCQYCODE={"ZICHAN:自产自销区:9:1","SHU1:蔬菜1区:1:1","SHU2:蔬菜2区:1:2","SHU3:蔬菜3区:1:3","SHU4:蔬菜4区:1:4","ROU1:肉类1区:2:1","ROU2:肉类2区:2:2","SHUICHAN:水产区:3:1","GANHUO:干货区:7:1","SHUIGUO1:水果1区:5:1","SHUIGUO2:水果2区:5:2","HECAI:盒菜:8:1","SHUSHI:熟食区:6:1","DOUZHIPIN:豆制品区:4:1","TUZAI:屠宰区:10:1","QITA:其它:11:1"};
	
	public static String[] HONGJIN={"SHU1:蔬菜1区:1:1","SHU2:蔬菜2区:1:2","SHU3:蔬菜3区:1:3","SHU4:蔬菜4区:1:4","SHU5:蔬菜5区:2:1","SHU6:蔬菜6区:2:2","SHU7:蔬菜7区:2:3","SHU8:蔬菜8区:2:4","SHU9:蔬菜9区:3:1"};

//	系统角色 start
	public static String role_sc="sc";
	public static String role_jyh="jyh";
	public static String role_ds="ds";
	public static String role_sadmin="sadmin";
	public static String role_jadmin="jadmin";
	public static String role_fjadmin="fjadmin";
	public static String role_xqjquickcenter="xqjquickcenter";
	
	
	/**
	 * 发送短信验证码
	 */
	public final static String SMSURL = "http://202.102.95.18:8099/Yxtsms-Interface/SendMsg";
	
	/**
	 *   MemCached高速缓存地址及端口
	 */
	
	public final static String memcachedServer="localhost:11211";
}


