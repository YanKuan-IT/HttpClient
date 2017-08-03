package com.open1111.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
/**
 * 基础内容
 *
 */
public class HelloWorld {
	
	public static void main(String[] args) throws Exception{
		//获取自1970年1月1日0时起的毫秒数
		long millisOne = System.currentTimeMillis();
		testOne();//1169
//		testTwo();//482
		long millisTwo = System.currentTimeMillis();
		//运行时间
		System.out.println("用时"+(float)(millisTwo-millisOne)/1000+"秒");
	}
	
	@Test
	public static void testOne() throws Exception{
		// 创建httpClient实例
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建httpget实例
		HttpGet httpGet = new HttpGet("http://www.tuicool.com/");
		//设置代理ip 《高匿》
		//设置超时时间
		HttpHost proxy = new HttpHost("110.73.2.14", 8123);
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(10000)	// 设置连接超时时间 10s
				.setSocketTimeout(10000)	// 设置读取超时时间 10s
				.setProxy(proxy)			// 设置代理ip
				.build();
		httpGet.setConfig(config);
		// 设置请求头消息User-Agent模拟浏览器
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36");
		// 执行httpGet请求
		CloseableHttpResponse response = httpClient.execute(httpGet);
		// 获取响应状态Status
		//	200 	正常
		// 	403		拒绝
		// 	500		服务器报错
		//	400		未找到页面
		//System.out.println("Status:"+response.getStatusLine().getStatusCode());
		// 获取返回实体
		HttpEntity entity = response.getEntity();
		// 获取访问内容类型Content-Type
		//System.out.println("Content-Type:"+entity.getContentType().getValue());
		// 获取网页内容
		String string = EntityUtils.toString(entity,"utf-8");	
		System.out.println("网页内容："+string);
		//关闭
		response.close();
		httpClient.close();
	}
	
	@Test
	public static void testTwo() throws IOException{
		URL url=new URL("https://my.oschina.net/u/3285916/blog/1499100");
		HttpURLConnection coon=(HttpURLConnection)url.openConnection();
		coon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36");
		BufferedReader bReader=new BufferedReader(new InputStreamReader(coon.getInputStream()));
		while(bReader.readLine()!=null){
			System.out.println(bReader.readLine());
		}
		bReader.close();
	}
}
