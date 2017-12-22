    package com.feed.util;  
      
    import java.io.ByteArrayInputStream;  
    import java.io.ByteArrayOutputStream;  
    import java.io.IOException;  
    import java.io.ObjectInputStream;  
    import java.io.ObjectOutputStream;  
    import java.io.Serializable;  
import java.io.UnsupportedEncodingException;  

import org.action.sp.webservice.test.user;
import org.apache.axiom.om.ds.ByteArrayDataSource.ByteArray;
      
    /** 
     * @作者 王建明 
     * @创建日期 2012-10-12 
     * @创建时间 上午10:48:41 
     * @版本号 V 1.0 
     */  
    public class ObjectSerializeUtil {  
        /** 
         * @param serStr 
         * @throws UnsupportedEncodingException 
         * @throws IOException 
         * @throws ClassNotFoundException 
         * @描述 —— 将字符串反序列化成对象 
         */  
        public static Object getObjFromStr(String serStr)  
                throws UnsupportedEncodingException, IOException,  
                ClassNotFoundException {  
            String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");  
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(  
                    redStr.getBytes("ISO-8859-1"));  
            ObjectInputStream objectInputStream = new ObjectInputStream(  
                    byteArrayInputStream);  
            Object result = objectInputStream.readObject();  
            objectInputStream.close();  
            byteArrayInputStream.close();  
            return result;  
        }  
      
        /** 
         * @return 
         * @throws IOException 
         * @throws UnsupportedEncodingException 
         * @作者 王建明 
         * @创建日期 2012-10-12 
         * @创建时间 上午10:51:07 
         * @描述 —— 将对象序列化成字符串 
         */  
        public static String getStrFromObj(Object obj) throws IOException,  
                UnsupportedEncodingException {  
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(  
                    byteArrayOutputStream);  
            objectOutputStream.writeObject(obj); 
            String serStr = byteArrayOutputStream.toString("ISO-8859-1");  
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");  
            objectOutputStream.close();  
            byteArrayOutputStream.close();  
            return serStr;  
        }  
        
        public static void main(String[] args) {
        	user u=new user();
        	u.setUser_id("1");
        	u.setUser_name("2");
			try {
				System.out.println(getStrFromObj(u));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				user r=(user)getObjFromStr(getStrFromObj(u));
				System.out.println(r.getUser_id());
				System.out.println(r.getUser_name());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    }  
      
   