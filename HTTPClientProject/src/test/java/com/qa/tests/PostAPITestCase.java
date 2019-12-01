package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITestCase {
	String url = "https://reqres.in";
	String apiurl;
	RestClient restclient;

	@BeforeMethod
	public void setup() {
		apiurl = url + "/api/users";
	}
	
	@Test
	public void createusertest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		//1.HEADER PREP
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type","application/json");
		//2.prep JSON payload : jackson api :core and databind dependencies we need to use
		ObjectMapper mapper = new ObjectMapper();
		
		Users users = new Users("kushan", "senior consultant","547", "2019-10-02T09:49:13.762Z");
		//3.convert java object to json string: serialization -- marshalling
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		CloseableHttpResponse postresp = restclient.post(apiurl, usersJsonString, headermap);
		
		//4.get the status code
		int statusCode = postresp.getStatusLine().getStatusCode();
		System.out.println("The status code is: "+statusCode);
		Assert.assertEquals(statusCode, 201);
		
//		//5. get the JSON payload
//		HttpEntity httpentity = postresp.getEntity();
//		String respstring = EntityUtils.toString(httpentity);
//		System.out.println("The response string is: " + respstring);
//		respstring.toString();
		
		
		String respstringusers = EntityUtils.toString(postresp.getEntity(),"UTF-8");
		JSONObject responsejson = new JSONObject(respstringusers);
		System.out.println("the JSON response is: "+responsejson);
		
		//6. convert JSON string to java object :: deserialization : unmarashalling
		Users usersobj = mapper.readValue(respstringusers, Users.class);
		System.out.println(usersobj.getName());
		System.out.println(usersobj.getJob());	
		Assert.assertEquals(usersobj.getName(), users.getName());
	}
}
