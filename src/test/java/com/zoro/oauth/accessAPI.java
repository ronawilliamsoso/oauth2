package com.zoro.oauth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class accessAPI {

    
	  



	@Test
    public void whenObtainingAccessToken_thenCorrect() {
        final Response authServerResponse = obtainAccessToken("clientpassword", "john", "123");
        final String accessToken = authServerResponse.jsonPath().getString("access_token");
        assertNotNull(accessToken);

        final Response resourceServerResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8082/spring-security-oauth-resource/foos/100");
        assertThat(resourceServerResponse.getStatusCode(), equalTo(200));
    }

    //

    private Response obtainAccessToken(String clientId, String username, String password) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        return RestAssured.given().auth().preemptive().basic(clientId, "secret").and().with().params(params).when().post("http://localhost:8080/oauth/token");
        // response.jsonPath().getString("refresh_token");
        // response.jsonPath().getString("access_token")
    }

  
 
}