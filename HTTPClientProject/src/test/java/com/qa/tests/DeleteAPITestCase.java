package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;

public class DeleteAPITestCase {
	String url = "https://reqres.in/api/users";
	String apiurl;
	RestClient restclient;

	@BeforeMethod
	public void setup() {
		apiurl = url + "/2";
	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		CloseableHttpResponse closeableHttpResponse = restclient.delete(apiurl);
		// status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The status code is: " + statusCode);
		Assert.assertEquals(statusCode, 204);

		// json string
		HttpEntity httpentity = closeableHttpResponse.getEntity();
		Assert.assertNull(httpentity);
		

		// headers
		Header[] HeadersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header header : HeadersArray) {
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers are coming in response " + allheaders);
		
	}
}
