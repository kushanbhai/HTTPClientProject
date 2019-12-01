package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;

public class GetCitydetailsAPITestCase {
	String url = "https://restcountries.eu/rest/v2/capital";
	String apiurl;
	RestClient restclient;

	@BeforeMethod
	public void setup() {
		apiurl = url + "/Washington";
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
		respstring = respstring.substring(1, respstring.length());
		respstring = respstring.substring(0, respstring.length() - 1);
		System.out.println("The actual response string is: " + respstring);
		// STRING tO JSON Conversion
		JSONObject jsonrespobj = new JSONObject(respstring);
		System.out.println("the actual json response is: " + jsonrespobj);

		String countryname = jsonrespobj.getString("name");
		System.out.println("The country name is: " + countryname);
		Assert.assertEquals("United States of America", countryname);
		
		//get the values from json array
		JSONArray spellingsarray = jsonrespobj.getJSONArray("altSpellings");
		System.out.println("the values from altspellings array is: "+spellingsarray);
		
		JSONArray currArray = jsonrespobj.getJSONArray("currencies");
		System.out.println(currArray.getJSONObject(0));
		System.out.println(currArray.getJSONObject(0).get("code").toString());
		System.out.println(currArray.getJSONObject(0).get("symbol").toString());
		
		JSONArray regionalblocks = jsonrespobj.getJSONArray("regionalBlocs");
		System.out.println("The values of the regional blocks are: "+regionalblocks);
		System.out.println(regionalblocks.getJSONObject(0).get("acronym").toString());
		System.out.println(regionalblocks.getJSONObject(0).get("otherNames"));
		String othernames = regionalblocks.getJSONObject(0).get("otherNames").toString();
		String[] othernamesarr = othernames.split(",");
		for(int i=0 ; i<othernamesarr.length; i++) {
		System.out.println(othernamesarr[i]);
	}
	}
}
