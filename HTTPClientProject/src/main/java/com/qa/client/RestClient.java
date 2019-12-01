package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	//GET call
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse closehttpresp = httpclient.execute(httpget);
		return closehttpresp;
		
	}
	
	//POST Call
	public CloseableHttpResponse post(String url, String entitystring, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();//creating httpclient
		HttpPost httppost = new HttpPost(url);//http post request
		httppost.setEntity(new StringEntity(entitystring));//for adding payload
		//add headers:
		for(Entry<String, String> entry: headermap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
			
	}
		
		CloseableHttpResponse closeablehttpresp = httpclient.execute(httppost);
		return closeablehttpresp;
}
	//PUT CALL
	public CloseableHttpResponse put(String url, String entitystring, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();//creating httpclient
		HttpPut httpput = new HttpPut(url);//http post request
		httpput.setEntity(new StringEntity(entitystring));//for adding payload
		//add headers:
		for(Entry<String, String> entry: headermap.entrySet()) {
			httpput.addHeader(entry.getKey(), entry.getValue());
			
	}
		
		CloseableHttpResponse closeablehttpresp = httpclient.execute(httpput);
		return closeablehttpresp;
}
	//DELETE call
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpDelete httpdel = new HttpDelete(url);
		CloseableHttpResponse closehttpresp = httpclient.execute(httpdel);
		return closehttpresp;
		
	}
	
}
