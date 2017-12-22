package org.action.sp.webservice.test;


import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;

class test2 {
	public static void main(String[] args) {
		
      String xmlStr = "";  
//      String str1="{\"productType\":\"20\",\"productId\":\"234\",\"productName\":\"白菜\",\"detectPro\":\"二氧化碳\",\"standarValue\":\"<=20\",\"detectValue\":\"0 mg/kg\",\"checkResult\":\"1\",\"detectUnit\":\"淮安市农贸管理员机构\",\"shopName\":\"商铺01\",\"operatorId\":\"21\",\"detectType\":\"2\",\"detectTime\":\"2015-01-01\",\"detectDevice\":\"检测厂商\",\"checkPerson\":\"程骞\",\"submitType\":\"1\",\"submitTime\":\"2015-09-20\",\"marketAccount\":\"175769\"}";
//      String str2="{\"productType\":\"20\",\"productId\":\"234\",\"productName\":\"黄瓜\",\"detectPro\":\"二氧化碳\",\"standarValue\":\"<=20\",\"detectValue\":\"0 mg/kg\",\"checkResult\":\"1\",\"detectUnit\":\"淮安市农贸管理员机构\",\"shopName\":\"商铺01\",\"operatorId\":\"21\",\"detectType\":\"1\",\"detectTime\":\"2015-01-01\",\"detectDevice\":\"检测厂商\",\"checkPerson\":\"李纯远\",\"submitType\":\"1\",\"submitTime\":\"2015-09-20\",\"marketAccount\":\"175769\"}";
//      xmlStr="["+str1+","+str2+"]";
      xmlStr="[{\"productType\":\"10\",\"productId\":\"21371\",\"productName\":\"荷花\",\"detectPro\":\"农残\",\"standarValue\":\"<=20\",\"detectValue\":\"mg/kg\",\"checkResult\":\"1\",\"detectUnit\":\"宏进清江农副批发市场\",\"shopName\":\"\",\"operatorId\":\"1229\",\"detectType\":\"2\",\"detectTime\":\"2016-01-15 21:00:00\",\"detectDevice\":\"\",\"checkPerson\":\"001\",\"submitType\":\"1\",\"submitTime\":\"2016-01-15 21:00:00\",\"marketAccount\":\"sc\"}]";
      String  url = "http://localhost:8080/ecp/services/getQuickInfoService";   
      String method="doQuickInfo";   
      Object[] webObject = null; 
       try {         
             RPCServiceClient serviceClient = new RPCServiceClient();   
             Options options = serviceClient.getOptions();     
             EndpointReference targetEPR = new EndpointReference(url);   
             options.setTo(targetEPR);     
             /** 在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名，
                也就是<wsdl:definitions>元素的targetNamespace属性值,method指定方法名  
             */
             QName opAddEntry = new  QName("http://webservice.sp.action.org",method);
              /** 参数，如果有多个，继续往后面增加即可，不用指定参数的名称*/   
             Object[] opAddEntryArgs = new Object[] {xmlStr};      
             // 返回参数类型，这个和axis1有点区别      
             /**invokeBlocking方法有三个参数，
              *  其中第一个参数的类型是QName对象，表示要调用的方法名；      
                 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；      
                第三个参数表示WebService方法的返回值类型的Class对象，参数类型为Class[]。  
              */
             /**注意： 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}      
                      如果被调用的WebService方法没有返回值，应使用RPCServiceClient类的invokeRobust方法，     
                      该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同
              */     
             Class[] classes = new Class[] {String.class };      
             webObject = serviceClient.invokeBlocking(opAddEntry,opAddEntryArgs, classes);  
            	  System.out.println(webObject[0]);
             }
       catch (Exception e) {   
          e.printStackTrace();     
          long end = System.currentTimeMillis();   
       }
}
}