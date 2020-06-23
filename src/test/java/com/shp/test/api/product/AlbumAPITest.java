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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlbumAPITest {
	HttpClient httpClient = HttpClientBuilder.create().build();
	public int albumId;
	
	@Test
	public void albumTest() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost("http://localhost:3000/albums");
		//StringEntity createPayLoad=new StringEntity("{"userId": 1,"title": "amar's album"}");
		StringEntity createPayLoad=new StringEntity("{\"userId\": 8,\"title\": \"myAlbum\"}");
		createPayLoad.setContentType("application/json");
		httpPost.setEntity(createPayLoad);
		HttpResponse response = httpClient.execute(httpPost);
 		System.out.println("VIN Get request with status code " + response.getStatusLine().getStatusCode());
		ObjectMapper mapper = new ObjectMapper();// jackson parser
		//object mapper is in jackson ,maping means get this array stuff and map it into java pojo. mapper read the value from  response 
		// mappper -this is my response and convert it into java pogo
		//code to convert response to java bean (album)
		//mapper read the value and load it 
		
		Album album = mapper.readValue(response.getEntity().getContent(), Album.class); //convert response to java pojo(bean)
        System.out.println("album id is " + album.getId());//mapper read the values and load it
        System.out.println("album user id is " + album.getUserId());
        System.out.println("album title is " + album.getTitle());
        
        assertTrue(album.getTitle().equals("myAlbum"),"album title is not correct");
        assertTrue(album.getUserId()==8,"id is not correct");
        albumId=album.getId();
   System.out.println("album id " +albumId);
	}
	
   @Test(dependsOnMethods="albumTest")
   public void updateAlbumTest() throws ClientProtocolException, IOException {
	   String rawUrl= "http://localhost:3000/albums/%d";
	   rawUrl= String.format(rawUrl, albumId);
	   System.out.println("url is " +rawUrl);   
		HttpPut httpPut = new HttpPut(rawUrl);
		StringEntity updatePayLoad=new StringEntity("{\"userId\": 8,\"title\": \"AmarUpdatedAlbum\"}");
		updatePayLoad.setContentType("application/json");
		httpPut.setEntity(updatePayLoad);
		HttpResponse response = httpClient.execute(httpPut);
		System.out.println("VIN Update request with status code " + response.getStatusLine().getStatusCode());
		ObjectMapper mapper = new ObjectMapper();
		Album album = mapper.readValue(response.getEntity().getContent(), Album.class);
		System.out.println(album.getId());
		System.out.println(album.getTitle());
		System.out.println(album.getUserId());
      
   }
   @Test(dependsOnMethods="updateAlbumTest")
   public void retrieveAlbumTest() throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
	   String rawUrl= "http://localhost:3000/albums/%d";
	   rawUrl= String.format(rawUrl, albumId);
	   HttpGet request = new HttpGet(rawUrl) ;
	   request.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(request);

		System.out.println(" Get request with status code " + response.getStatusLine().getStatusCode());
		ObjectMapper mapper = new ObjectMapper();
		Album album = mapper.readValue(response.getEntity().getContent(), Album.class);
		System.out.println(album.getId());
		System.out.println(album.getTitle());
	   
	   
	   
   }
   @Test(dependsOnMethods="retrieveAlbumTest")
   public void delete() throws ClientProtocolException, IOException
   {String rawUrl = "http://localhost:3000/albums/%d";
	rawUrl = String.format(rawUrl, albumId);
	HttpDelete request = new HttpDelete(rawUrl);
	request.addHeader("accept", "application/json");
	HttpResponse response = httpClient.execute(request);
	System.out.println(" Get request with status code " + response.getStatusLine().getStatusCode());
	assertTrue(response.getStatusLine().getStatusCode()==200, " status  code is not loaded  ");
   }
   }


