package com.vinodh.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import com.vinodh.springboot.service.impl.AdminDetailsService;

@Configuration
@EnableAuthorizationServer
public class SpringBootoAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AdminDetailsService adminDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	 

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
		configurer.authenticationManager(authenticationManager);
		configurer.userDetailsService(adminDetailsService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		 clients.inMemory()
         .withClient("vinodh")
         .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
         .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
         .scopes("read", "write", "trust")
         .secret("secret")
         .accessTokenValiditySeconds(120).//Access token is only valid for 2 minutes.
         refreshTokenValiditySeconds(600);//Refresh token is only valid for 10 minutes.
	}
}
