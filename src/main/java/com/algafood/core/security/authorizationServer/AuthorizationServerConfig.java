package com.algafood.core.security.authorizationServer;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private JwtKeyStoreProperties jwtKeyStoreProperties;

	@Autowired
	private DataSource datasource;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(datasource);
//			.inMemory()
//				.withClient("algafood-web")//oath2
//				.secret(passwordEncoder.encode("web123"))
//				.authorizedGrantTypes("password", "refresh_token")
//				.scopes("WRITE", "READ")
//				.accessTokenValiditySeconds(60 * 60 * 6) //6 horas (padrão é 12 horas)
//				.refreshTokenValiditySeconds(60 * 24 *60 *60) //60 dias
//				
//			.and()
//				.withClient("foodanalytcs")	//Authorization COde
//				.secret(passwordEncoder.encode("food123"))
//				.authorizedGrantTypes("authorization_code")
//				.scopes("WRITE", "READ")
//				.redirectUris("http://aplicacao-cliente")
//				
//			.and().withClient("webadmin") //IMPLICIT RETIRAR ESSE
//				.authorizedGrantTypes("implicit")
//				.scopes("WRITE", "READ")
//				.redirectUris("http://aplicacao-cliente")
//			.and()
//				.withClient("faturamento") //aplicação acessando a nossa api
//				.secret(passwordEncoder.encode("faturamento123"))
//				.authorizedGrantTypes("client_credentials")
//				.scopes("READ")				
//			.and()
//				.withClient("checktoken") //resource server uri instrosdpeccao
//				.secret(passwordEncoder.encode("check987"));

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("isAuthenticated()");
		security.checkTokenAccess("permitAll()").tokenKeyAccess("permitAll()").allowFormAuthenticationForClients();
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		var enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));

		endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailService)
				.reuseRefreshTokens(false).accessTokenConverter(jwtAccessTokenConverter()).tokenEnhancer(enhancerChain)
				.approvalStore(approvalStore(endpoints.getTokenStore())).tokenGranter(tokenGranter(endpoints));
	}

	private ApprovalStore approvalStore(TokenStore tokenStore) {
		var approvalStore = new TokenApprovalStore();
		approvalStore.setTokenStore(tokenStore);

		return approvalStore;
	}
	
	@Bean
	public JWKSet jwkSet() {
		RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey)keyPair().getPublic())
				.keyUse(KeyUse.SIGNATURE)
				.algorithm(JWSAlgorithm.RS256)
				.keyID("algafood-key-id");
		
		return new JWKSet(builder.build());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		var jwtAccessTokenConverter = new JwtAccessTokenConverter();
		
		jwtAccessTokenConverter.setKeyPair(keyPair());

		return jwtAccessTokenConverter;

	}

	private KeyPair keyPair() {
		var keyStorePass = jwtKeyStoreProperties.getPassword();
		var keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();

		var keyStoreKeyFactory = new KeyStoreKeyFactory(jwtKeyStoreProperties.getPath(), keyStorePass.toCharArray());
		return keyStoreKeyFactory.getKeyPair(keyPairAlias);
	}

	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
		var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
				endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory());

		var granters = Arrays.asList(pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());

		return new CompositeTokenGranter(granters);
	}
}
