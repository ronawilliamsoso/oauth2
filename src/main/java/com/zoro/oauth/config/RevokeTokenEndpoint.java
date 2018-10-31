package com.zoro.oauth.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

	@Autowired
	private ConsumerTokenServices tokenServices;
	
	@Resource(name="tokenStore")
	TokenStore tokenStore;

	@RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token/delete")
	@ResponseBody
	public void revokeToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.contains("Bearer")) {
			String tokenId = authorization.substring("Bearer".length() + 1);

			System.out.println("this token is deleted: " + tokenId);

			tokenServices.revokeToken(tokenId);
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/oauth/token/getTokensByClientId")
	@ResponseBody
	public List<String> getTokens(String clientId) {
		List<String> tokenValues = new ArrayList<String>();
	    Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId(clientId); 
	    if (tokens!=null){
	        for (OAuth2AccessToken token:tokens){
	            tokenValues.add(token.getValue());
	        }
	    }
	    return tokenValues;
	}
	
	     
	@RequestMapping(method = RequestMethod.POST, value = "/oauth/token/revokeTokenByTokenId")
	@ResponseBody
	public String revokeTokenByTokenId(String tokenId) {
	    tokenServices.revokeToken(tokenId);
	    return tokenId;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/oauth/token/revokeTokensByClientId")
	@ResponseBody
	public String revokeTokensByClientId(String clientId) {
		
		 Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId(clientId); 
		    if (tokens!=null){
		        for (OAuth2AccessToken token:tokens){
		        	 tokenServices.revokeToken(token.getValue());
		        }
		        return "sucess";
		    }else {
				return "no token found";
			}
	}
	
 
	
	

}