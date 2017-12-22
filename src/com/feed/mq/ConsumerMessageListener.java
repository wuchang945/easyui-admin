package com.feed.mq;

 
import java.util.List;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import net.sf.json.JSONObject;

import com.feed.ecp.common.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
 
public class ConsumerMessageListener implements MessageListener {
	private MongoTemplate mongoTemplate;
	public DBCollection getConnection(){
	            // 实例化Mongo对象，连接27017端口  
	            Mongo mongo = new Mongo("localhost", 27017);  
	            // 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立  
	            DB db = mongo.getDB("tmsyj");  
	            // Get collection from MongoDB, database named "yourDB"  
	            // 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立  
	            DBCollection collection = db.getCollection("operatorLog");  
	            return collection;
	}
 
//	public DBCollection getConnection(){
//	            // 实例化Mongo对象，连接27017端口  
//	            Mongo mongo = new Mongo("36.111.34.118", 27017);  
//	            // 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立  
//	            DB db = mongo.getDB("tmsyj");  
//	            // Get collection from MongoDB, database named "yourDB"  
//	            // 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立  
//	            DBCollection collection = db.getCollection("operatorLog");  
//	            return collection;
//	}
// 
    public void onMessage(Message message) {
//    	DB db = mongo.getDB("tmsyj");  
        // Get collection from MongoDB, database named "yourDB"  
        // 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立  
//        DBCollection collection = db.getCollection("operatorLog");  
    	// 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。  
        BasicDBObject document = new BasicDBObject();  
        try {
        	JSONObject jsonObject=JSONObject.fromObject(((TextMessage)message).getText());
        	if(jsonObject!=null){
        		if("1".equals(jsonObject.get("type"))){
        			//系统异常日志
        			document.put("page", jsonObject.get("page")==null?"":jsonObject.get("page"));
        			document.put("action", jsonObject.get("action")==null?"":jsonObject.get("action"));
        			document.put("buttoncode", jsonObject.get("buttoncode")==null?"":jsonObject.get("buttoncode"));
        			document.put("buttonname", jsonObject.get("buttonname")==null?"":jsonObject.get("buttonname"));
        			document.put("begincontent", jsonObject.get("begincontent")==null?"":jsonObject.get("begincontent"));
        			document.put("endcontent", jsonObject.get("endcontent")==null?"":jsonObject.get("endcontent"));
        			document.put("ip", jsonObject.get("ip")==null?"":jsonObject.get("ip"));
        			document.put("description", jsonObject.get("description")==null?"":jsonObject.get("description"));
        			//由于mongodb采用标准时间，我们处于东8区，所以要加上8小时时区差
        			long datetime=Long.parseLong(jsonObject.get("createdate").toString())+8*60*60*1000;
        			document.put("createdate",DateUtil.string2Date(DateUtil.transferLongToDate(DateUtil.PATTERN_STANDARD,datetime),DateUtil.PATTERN_STANDARD));
        			document.put("createunitid", jsonObject.get("createunitid")==null?"":jsonObject.get("createunitid"));
        			document.put("createunitname", jsonObject.get("createunitname")==null?"":jsonObject.get("createunitname"));
        			document.put("createuserid", jsonObject.get("createuserid")==null?"":jsonObject.get("createuserid"));
        			document.put("createusername", jsonObject.get("createusername")==null?"":jsonObject.get("createusername"));
        			mongoTemplate.insert(document, "operatorLog");//自定义集合名插入文档.insert(document); 
        		}else if("2".equals(jsonObject.get("type"))){
        			//系统操作日志
        			document.put("description", jsonObject.get("description")==null?"":jsonObject.get("description"));
        			document.put("requestIp", jsonObject.get("requestIp")==null?"":jsonObject.get("requestIp"));
        			document.put("exceptionCode", jsonObject.get("exceptionCode")==null?"":jsonObject.get("exceptionCode"));
        			document.put("type", jsonObject.get("type")==null?"":jsonObject.get("type"));
        			document.put("exceptionDetail", jsonObject.get("exceptionDetail")==null?"":jsonObject.get("exceptionDetail"));
        			document.put("createBy", jsonObject.get("createBy")==null?"":jsonObject.get("createBy"));
        			//由于mongodb采用标准时间，我们处于东8区，所以要加上8小时时区差
        			long datetime=Long.parseLong(jsonObject.get("createDate").toString())+8*60*60*1000;
        			document.put("createdate",DateUtil.string2Date(DateUtil.transferLongToDate(DateUtil.PATTERN_STANDARD,datetime),DateUtil.PATTERN_STANDARD));
        			mongoTemplate.insert(document, "exceptionLog");//自定义集合名插入文档.insert(document); 
        		}
        	}
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        // 将新建立的document保存到collection中去  
//        Query query=new Query();
//        List<SysOperationalLog> list=mongoTemplate.find(query,SysOperationalLog.class, "operatorLog");
//        System.out.println(list.size());
    }

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	
    
 
}