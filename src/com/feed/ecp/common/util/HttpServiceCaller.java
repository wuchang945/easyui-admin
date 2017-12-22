package com.feed.ecp.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
/**
 * 
 * @ClassName: HttpServiceCaller
 * @author: 周伟        @date: 2016-3-14
 * @Description:  调用webservice服务类 -- 文件上传的时候使用
 */

public class HttpServiceCaller {
	public static String GET = "GET";
	public static String POST = "POST";
	public static String PUT = "PUT";
	
	public static String FILE_FLAG = "__FILE__";
	
	/**
	 * 默认编码
	 */
	public static String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * 默认解码
	 */
	public static String DEFAULT_DECODING = "UTF-8";
	
	/**
	 * 设置字符集
	 */
	public static String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 调用GET方法，并返回处理结果
	 * @param URL WebService地址
	 * @param param 参数
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String callPut(String URL, Map<String, Object> param) 
			throws HttpException, IOException {
		return callService(URL, param, PUT);
	}
	
	/**
	 * 调用GET方法，并返回处理结果
	 * @param URL WebService地址
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String callPut(String URL) 
			throws HttpException, IOException {
		return callService(URL, new HashMap<String, Object>(), PUT);
	}
	
	/**
	 * 调用GET方法，并返回处理结果
	 * @param URL WebService地址
	 * @param param 参数
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String callGet(String URL, Map<String, Object> param, String...charset) 
		throws HttpException, IOException {
		return callService(URL, param, GET, charset);
	}
	
	/**
	 * 调用GET方法，并返回处理结果
	 * @param URL WebService地址
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String callGet(String URL, String...charset) 
		throws HttpException, IOException {
		return callService(URL, new HashMap<String, Object>(), GET, charset);
	}
	
	/**
	 * 调用POST方法，并返回处理结果
	 * @param URL WebService地址
	 * @param param 参数
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String callPost(String URL, Map<String, Object> param) 
		throws HttpException, IOException {
		return callService(URL, param, POST);
	}
	/**
	 * 调用POST方法，并返回处理结果
	 * @param URL WebService地址
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String callPost(String URL) 
		throws HttpException, IOException {
		return callService(URL, new HashMap<String, Object>(), POST);
	}
	
	/**
	 * 调用HttpService服务，并返回处理结果
	 * @param URL WebService地址
	 * @param param 参数
	 * @param methodType 方法类型
	 * @param charset 字符串
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String callService(String URL, Map<String, Object> param, String methodType, String...charset) throws HttpException, IOException {
		String result = null;
		if (URL == null || URL.trim().equals("")) {
			return null;
		}
		
		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
//			HttpMethod method = new GetMethod(URL);
		HttpMethod method = getMethod(URL, methodType, param);
		String defaultCharset = DEFAULT_CHARSET;
		if(null != charset && charset.length > 0) {
			defaultCharset = charset[0];
		}
		client.getParams().setContentCharset(defaultCharset);
		
//		EncodingUtil.formUrlEncode(arg0, arg1);
		
		client.executeMethod(method);
		// 打印服务器返回的状态
//		System.out.println(method.getStatusLine());
		
//		System.out.println(client.getParams().getCredentialCharset());
//		System.out.println(client.getParams().getUriCharset());
//		System.out.println(client.getParams().getHttpElementCharset());
//		System.out.println(client.getParams().getContentCharset());
		
		// 打印返回的信息
//		result = new String(method.getResponseBodyAsString().getBytes(), DEFAULT_DECODING);
		result = method.getResponseBodyAsString();
		// 释放连接
		method.releaseConnection();
		return result;
	}
	
	private static HttpMethod getMethod(String url, String methodType, Map<String, Object> param) throws IOException {
		if(GET.equals(methodType)) {
			return getGetMethod(url, param);
		}
		if(POST.equals(methodType)) {
			return getPostMethod(url, param);
		}
		if(PUT.equals(methodType)) {
			return getPutMethod(url, param);
		}
		return null;
	}
	
	/** 
	 * 使用 GET 方式提交数据 
	 *@return 
	 * @throws FileNotFoundException 
	 */ 
	private static HttpMethod getGetMethod(String url, Map<String, Object> param) throws FileNotFoundException{
		String urlSpliter = "?";
		if(url.indexOf("?") > 0) {
			urlSpliter = "&";
		}
		
		StringBuilder urlBuilder = new StringBuilder(url);
		// 拼写参数
		StringBuilder sb = new StringBuilder();
		if (null != param && !param.isEmpty()) {
			urlBuilder.append(urlSpliter);
			for (String key : param.keySet()) {
				sb.append("&" + key + "=" + param.get(key).toString());
			}
			
			urlBuilder.append(sb.substring(1));
		}
		
		GetMethod get = new GetMethod(urlBuilder.toString());
		// 设置请求头编码
		get.setRequestHeader("Content-Type","text/html;charset=" + DEFAULT_ENCODING);
		return get;
	}
	
	/** 
	 * 使用 PUT 方式提交数据 
	 *@return 
	 * @throws FileNotFoundException 
	 */ 
	private static HttpMethod getPutMethod(String url, Map<String, Object> param) throws FileNotFoundException{
		PutMethod put = new PutMethod(url);
		InputStream filePath = null;
		List<NameValuePair> values = new ArrayList<NameValuePair>();
		if(null != param) {
			for(Entry<String,Object> entry :param.entrySet()){
				if(entry.getKey().indexOf(FILE_FLAG) >= 0) {
					Object obj = entry.getValue();
					
					// 如果为文件路径
					if(obj instanceof String) {
						filePath = new FileInputStream(obj.toString());
					}
					
					// 如果为文件
					if(obj instanceof File) {
						filePath = new FileInputStream((File)obj);
					}
					
					// 如果为文件流
					if(obj instanceof InputStream) {
						filePath = (InputStream)obj;
					}
					
					continue;
				}
				values.add(new NameValuePair(entry.getKey(), entry.getValue().toString()));
			}
		}
		put.setQueryString(values.toArray(new NameValuePair[values.size()]));

		if(null != filePath) {
			RequestEntity requestEntity = new InputStreamRequestEntity(filePath);
			put.setRequestEntity(requestEntity);
		}

		return put;
	}
	
	 /** 
     * 使用 POST 方式提交数据 
     * @return 
	 * @throws IOException 
     */ 
	private static HttpMethod getPostMethod(String url, Map<String, Object> param) throws IOException {
		PostMethod post = new PostMethod(url);
		if (MapUtils.isNotEmpty(param)) {
			List<Part> partList = new ArrayList<Part>();
			for (String key : param.keySet()) {
				if (key.indexOf(FILE_FLAG) >= 0) {
					Object obj = param.get(key);
					InputStream is = null;

					// 如果为文件路径
					if (obj instanceof String) {
						is = new FileInputStream(obj.toString());
					}

					// 如果为文件
					if (obj instanceof File) {
						is = new FileInputStream((File) obj);
					}

					// 如果为文件流
					if (obj instanceof InputStream) {
						is = (InputStream) obj;
					}
					
					if (is != null) {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						int len = 0;
						byte[] b = new byte[1024];
						while ((len = is.read(b, 0, b.length)) != -1) {
							baos.write(b, 0, len);
						}
						byte[] buffer = baos.toByteArray();
						partList.add(new FilePart("pic", new ByteArrayPartSource("pic", buffer)));
					}
					continue;
				}
				partList.add(new StringPart(key, param.get(key).toString(), DEFAULT_ENCODING));
			}
			if (CollectionUtils.isNotEmpty(partList)) {
				Part[] parts = new Part[partList.size()];
				partList.toArray(parts);
				post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
			}
		}
		return post;
	}
    
	
	
	
	
}
