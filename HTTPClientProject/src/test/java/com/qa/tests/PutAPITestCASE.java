package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.data.UsersUpdate;

public class PutAPITestCASE {
	String url = "https://reqres.in";
	String apiurl;
	RestClient restclient;

	@BeforeMethod
	public void setup() {
		apiurl = url + "/api/users/2";
	}
	
	@Test
	public void createusertest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		//1.HEADER PREP
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type","application/json");
		//2.prep JSON payload : jackson api :core and databind dependencies we need to use
		ObjectMapper mapper = new ObjectMapper();
		
		UsersUpdate users = new UsersUpdate("Tom", "Manager");
		//3.convert java object to json string: serialization -- marshalling
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		CloseableHttpResponse putresp = restclient.put(apiurl, usersJsonString, headermap);
		
		//4.get the status code
		int statusCode = putresp.getStatusLine().getStatusCode();
		System.out.println("The status code is: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
//		//5. get the JSON payload
//		HttpEntity httpentity = postresp.getEntity();
//		String respstring = EntityUtils.toString(httpentity);
//		System.out.println("The response string is: " + respstring);
//		respstring.toString();
		
		
		String respstringusers = EntityUtils.toString(putresp.getEntity(),"UTF-8");
		JSONObject responsejson = new JSONObject(respstringusers);
		System.out.println("the JSON response is: "+responsejson);
		
		//6. convert JSON string to java object :: deserialization : unmarashalling
		UsersUpdate usersobj = mapper.readValue(respstringusers, UsersUpdate.class);
		System.out.println(usersobj.getName());
		System.out.println(usersobj.getJob());	
		Assert.assertEquals(usersobj.getName(), users.getName());
		System.out.println(usersobj.getUpdatedAt());
		Assert.assertNotNull(usersobj.getUpdatedAt());
	}
}
