package com.open1111.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HelloWorld2 {
	public static void main(String[] args) throws ParseException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
		HttpGet httpGet=new HttpGet("http://www.tuicool.com/"); // 创建httpget实例
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36");
		CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
		HttpEntity entity=response.getEntity(); // 获取返回实体
		System.out.println("Content-Type:"+entity.getContentType().getValue());//访问的返回类型
		//System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8")); // 获取网页内容
		response.close(); // response关闭
		httpClient.close(); // httpClient关闭
	}
}
