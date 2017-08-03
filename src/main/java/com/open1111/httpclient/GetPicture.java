package com.open1111.httpclient;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GetPicture {
	public static void main(String[] args) throws Exception{
		String url = "http://static2.tuicool.com//images/mobile/main.jpg";
		Get(url);
	}
	
	public static void Get(String url) throws Exception{
		// 创建httpClient实例
		CloseableHttpClient httpClient=HttpClients.createDefault(); 
		// 创建httpget实例
		HttpGet httpGet=new HttpGet(url); 
		// 设置请求头消息User-Agent模拟浏览器
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		// 执行http get请求
		CloseableHttpResponse response=httpClient.execute(httpGet); 
		// 获取返回实体
		HttpEntity entity=response.getEntity(); 
		if(entity!=null){
			// 获取访问内容类型Content-Type
			System.out.println("ContentType:"+entity.getContentType().getValue());
			InputStream inputStream=entity.getContent();
			FileUtils.copyToFile(inputStream, new File("F://1.jpg"));
		}
		response.close(); 	// response关闭
		httpClient.close(); // httpClient关闭
	}
}
