package com.shp.test.api.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;

public class SimpleApiTest {
	// Step1 -create the client object -http client
	HttpClient httpClient = HttpClientBuilder.create().build();

	@Test
	public void createProductTest() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost("http://mishoppingkart.com/ShoppingCart/api/addProduct");// select method and
																									// give url
		StringEntity productPayload = new StringEntity(
				"{\"name\": \"navneet333\",\"category\": \"Instrument\",\"description\": \"Piano Indian Instruments\",\"price\": 23,\"condition\": \"new\",\"status\": \"Active\",\"units\": 20,\"manufacturer\": \"Piano India INC\",\"image\": null,\"imageUrl\": null}");
		// payload
		productPayload.setContentType("application/json");// json format
		httpPost.setEntity(productPayload);// add payload to request
		HttpResponse response = httpClient.execute(httpPost);//
		System.out.println(response.getStatusLine().getStatusCode());// status code
		String line = null;// get id
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));// read the
																											// response
																											// and print
																											// it
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}

	}

	@Test
	public void deleteProductTest() throws ClientProtocolException, IOException {
		HttpDelete deleteRquest = new HttpDelete("http://mishoppingkart.com/ShoppingCart/api/deleteProduct/201");
		deleteRquest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(deleteRquest);

		System.out.println("VIN Delete request with status code " + response.getStatusLine().getStatusCode());

		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

		String output = null;
		System.out.println("Output from Server .... \n");
		System.out.println("outpouutt from DELETE request:-----");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
		System.out.println("outpouutt from DELETE ends hereeee :-----");

	}

	@Test
	public void updateProductTest() throws ClientProtocolException, IOException {
		HttpPut httpPut = new HttpPut("http://mishoppingkart.com/ShoppingCart/api/editProduct");// then provide payload
																								// id
		StringEntity updatePayload = new StringEntity(
				"{\"id\":283, \"name\": \"navneet-88880\",\"category\": \"Instrument\",\"description\": \"Piano Indian Instrumentsss\",\"price\": 23,\"condition\": \"new\",\"status\": \"Active\",\"units\": 20,\"manufacturer\": \"Piano India INC\",\"image\": null,\"imageUrl\": null}");
		updatePayload.setContentType("application/json");
		httpPut.setEntity(updatePayload);
		HttpResponse response = httpClient.execute(httpPut);
		System.out.println(response.getStatusLine().getStatusCode());
		String line = null;
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}

	}

	@Test
	public void getProductTest() throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet("http://mishoppingkart.com/ShoppingCart/api/viewProduct/283");

		request.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(request);

		System.out.println(" Get request with status code " + response.getStatusLine().getStatusCode());

		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

		String output = null;
		System.out.println("Output from Server .... \n");
		System.out.println("outpouutt from GET request:-----");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
		System.out.println("outpouutt from GET ends hereeee :-----");

	}

}
