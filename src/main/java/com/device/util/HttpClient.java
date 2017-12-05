package com.device.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Title HttpClient.java
 * @Package com.utils
 * @Description HttpClient【new】
 * @author liuwu_eva@163.com
 * @date 2015-9-6 上午11:54:34
 */
public class HttpClient {
	//private static Logger log = Logger.getLogger(HttpClient.class);
	
	/**
	 * post方式
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, Map<String, String> params) {
		String body = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost post = postForm(url, params);
			body = invoke(httpclient, post);
			post.abort();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return body;
	}
	
	/**
	 * get方式
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		DefaultHttpClient httpclient = null;
		String body = null;
		try {
			httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			body = invoke(httpclient, httpGet);
			httpGet.abort();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return body;
	}
	
	private static String invoke(DefaultHttpClient httpclient,HttpUriRequest httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		return body;
	}

	/**
	 * 获取响应结果
	 */
	private static String paseResponse(HttpResponse response) {
		String body = "";
		//返回状态,没有成功就返回空字符串
		if (response.getStatusLine().getStatusCode()==200) {
			HttpEntity entity = response.getEntity();
			String charset = EntityUtils.getContentCharSet(entity);
			try {
				body = EntityUtils.toString(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}

	/**
	 * 执行请求
	 */
	private static HttpResponse sendRequest(DefaultHttpClient httpclient,HttpUriRequest httpost) {
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpost);
		} catch (Exception e) {
			return null;
		}
		return response;
	}

	/**
	 * 添加参数
	 */
	private static HttpPost postForm(String url, Map<String, String> params) {
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpost;
	}
	
	//调用
	public static void main(String[] args) {
//		Map<String, String> params = new HashMap<String, String>();  
//		params.put("name", name);  
//		params.put("password", password);  
		      
		String xml = HttpClient.get("http://flash.weather.com.cn/wmaps/xml/shanghai.xml");  
		System.out.println(xml);
	}
	
}
