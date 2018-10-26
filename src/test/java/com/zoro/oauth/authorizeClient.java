package com.zoro.oauth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class authorizeClient {

    
	

	
	@Test
    public void authorizeClient() {
        
        final String client = "testwang";
        authorizeClient(client);
 
    }
	
	
	public void authorizeClient(String clientId) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("response_type", "code");
        params.put("client_id", clientId);
        params.put("scope", "read,write");
        final Response response = RestAssured.given().auth().preemptive().basic(clientId, "secret").and().with().params(params).when().post("http://localhost:8080/oauth/authorize");
    }
 
}