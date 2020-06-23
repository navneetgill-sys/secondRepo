package com.shp.test.api.product;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

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

import com.fasterxml.jackson.databind.ObjectMapper;

public class PostAPITest {
	HttpClient httpClient = HttpClientBuilder.create().build();
	public int postId;
	@Test
	public void postTest() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost("http://localhost:3000/posts");
		StringEntity createPayLoad=new StringEntity("{\"userId\": 1,\"title\": \"Navi's post \",\"body\":\"Post bodyy\"}");
		createPayLoad.setContentType("application/json");
		httpPost.setEntity(createPayLoad);
		HttpResponse response = httpClient.execute(httpPost);
 		System.out.println("VIN Get request with status code " + response.getStatusLine().getStatusCode());
 		ObjectMapper mapper = new ObjectMapper();//convert code to javabean
 	Posts post	= mapper.readValue(response.getEntity().getContent(), Posts.class);
 	     postId=post.getId();
 	System.out.println("title is " + post.getTitle());
 	System.out.println("Userid is "  + post.getUserId());
 	System.out.println("body of post "  +post.getBody());
 	System.out.println("post id is " +post.getId());
 	
 	assertTrue(post.getUserId()==1,"id is not correct");
 	assertTrue(post.getTitle().equals("Navi's post "),"title not matched ");
 	assertTrue(post.getBody().equals("Post bodyy")," body not matched");
 	
 	
}
	 @Test(dependsOnMethods="postTest")
	   public void updatePostTest() throws ClientProtocolException, IOException {
		   String rawUrl= "http://localhost:3000/posts/%d";
		   rawUrl= String.format(rawUrl, postId);
		   System.out.println("url is " +rawUrl);   
			HttpPut httpPut = new HttpPut(rawUrl);
			StringEntity updatePayLoad=new StringEntity("{\"userId\": 1,\"title\": \"Navi's post \",\"body\":\"Post Edited bodyy\"}");
			updatePayLoad.setContentType("application/json");
			httpPut.setEntity(updatePayLoad);
			HttpResponse response = httpClient.execute(httpPut);
			System.out.println("VIN Update request with status code " + response.getStatusLine().getStatusCode());
			ObjectMapper mapper = new ObjectMapper();
			Posts post  = mapper.readValue(response.getEntity().getContent(), Posts.class);
			System.out.println(post.getId());
			System.out.println(post.getTitle());
			System.out.println(post.getUserId());
			System.out.println(post.getBody());
	      
	   }
	 
	 @Test(dependsOnMethods="updatePostTest")
	 public void retrieveTest() throws ClientProtocolException, IOException {
		 String rawUrl= "http://localhost:3000/posts/%d";
		   rawUrl= String.format(rawUrl, postId);
		   HttpGet request = new HttpGet(rawUrl) ;
		   request.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(request);

			System.out.println(" Get request with status code " + response.getStatusLine().getStatusCode());
			ObjectMapper mapper = new ObjectMapper();
			Posts post = mapper.readValue(response.getEntity().getContent(), Posts.class);
			System.out.println("id is" + post.getId());
			System.out.println("title is " + post.getTitle());
			System.out.println("body is " + post.getBody());
	 } 
	 @Test(dependsOnMethods="retrieveTest")
	   public void delete() throws ClientProtocolException, IOException
	   {String rawUrl = "http://localhost:3000/posts/%d";
		rawUrl = String.format(rawUrl, postId);
		HttpDelete request = new HttpDelete(rawUrl);
		request.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(request);
		System.out.println(" Get request with status code " + response.getStatusLine().getStatusCode());
		assertTrue(response.getStatusLine().getStatusCode()==200, " status  code is not loaded  ");
	   }  
		  
			
	 
}
