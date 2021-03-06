package com.zoro.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import javax.websocket.Endpoint;

import java.util.concurrent.TimeUnit;


 
@Configuration
public class OAuthSecurityConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;
    
    @Value("classpath:schema.sql")
    private Resource schemaScript;

    @Value("classpath:data.sql")
    private Resource dataScript;

    @Bean // declare TokenStore implements
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore());

      //tokenServices do creating and setting valid  durations
        DefaultTokenServices tokenServices = new DefaultTokenServices(); 
        tokenServices.setTokenStore(endpoints.getTokenStore());   // to store tokens
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(30)); // set valid duration to 30 days
        endpoints.tokenServices(tokenServices);
        
        
    }
    
    
  
    @Override
    public void configure(AuthorizationServerSecurityConfigurer Security) throws Exception {
    	Security.tokenKeyAccess("permitAll()")
         .checkTokenAccess("permitAll()");// to permit auth/** like  oauth/check_token
    	Security.allowFormAuthenticationForClients();
    }

    @Bean  
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       // clients.withClientDetails(clientDetails());
        
    	
    	
        clients.jdbc(dataSource)
        .withClient("wang")  // one default client will be create when the server start
        .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token")
        .authorities("USER")
        .scopes("read", "write")
        .resourceIds("resourceid")
        .secret("123").and().build();
        
        
        
        /**
         * for local use :
         * 
         *      clients.inMemory() // use in-memory
                .withClient("client") // The client ID you received when you first created the application
                .secret("secret") // client_secret
                
                //Authorization Code for apps running on a web server, browser-based and mobile apps   
                //Password for logging in with a username and password  
                //Client credentials for application access
                .authorizedGrantTypes("authorization_code", "client_credentials",  "password")
                .scopes("app")// The scope to which the client is limited. If scope is undefined or empty (the default) the client is not limited by scope.
                .resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(120)
                .refreshTokenValiditySeconds(60);
         * 
         * 
         * **/

                
    }
    
    
    
    // init the database when start the app
    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        populator.addScript(dataScript);
        return populator;
    }
    
    
 

}
