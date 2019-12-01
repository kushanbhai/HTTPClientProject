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

public class GetAPITestCase {
	String url = "http://restapi.demoqa.com/utilities/weather/city";
	String apiurl;
	RestClient restclient;

	@BeforeMethod
	public void setup() {
		apiurl = url + "/Pune";
	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		CloseableHttpResponse closeableHttpResponse = restclient.get(apiurl);
		// status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);

		// json string
		HttpEntity httpentity = closeableHttpResponse.getEntity();
		String respstring = EntityUtils.toString(httpentity);
		System.out.println("The response string is: " + respstring);
		respstring.toString();

		// headers
		Header[] HeadersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header header : HeadersArray) {
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers are coming in response " + allheaders);
		String contentType = allheaders.get("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}
}
