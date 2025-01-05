package in.kkpit.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

// import com.nimbusds.jose.jwk.RSAKey;

import in.kkpit.security.CustomerUserDetailService;

@Configuration
public class masterConfigSecurity {
	
	private CustomerUserDetailService userDetailsService;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		return http.cors(Customizer.withDefaults())
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(request->request.requestMatchers("/login","/register").permitAll().anyRequest().authenticated())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				//.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults())
				.oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults()))
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvieder=new DaoAuthenticationProvider();
		daoAuthenticationProvieder.setUserDetailsService(userDetailsService);
		daoAuthenticationProvieder.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvieder;
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	  @Bean
	    public KeyPair keyPair(){
	        try{
	            var keyPairGenerator= KeyPairGenerator.getInstance("RSA");
	            keyPairGenerator.initialize(2048);
	            return keyPairGenerator.generateKeyPair();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    @Bean
	    public RSAKey rsaKey(){
	       KeyPair keyPair = keyPair();
	       return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
	               .privateKey((RSAPrivateKey) keyPair.getPrivate())
	               .keyID(UUID.randomUUID().toString())
	               .build();
	    }

	    @Bean
	    public JWKSource<SecurityContext> jwtSource(){
	       JWKSet jwkSet = new JWKSet(rsaKey());
	       return ((jwkSelector,context)-> jwkSelector.select(jwkSet));
	    }

	    @Bean
	    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){
	        return new NimbusJwtEncoder(jwkSource);
	    }

	    @Bean
	    JwtDecoder jwtDecoder() throws JOSEException {
	        return NimbusJwtDecoder.withPublicKey(rsaKey().toRSAPublicKey()).build();
	    }
	    @Bean
	    public WebMvcConfigurer corsConfigurer(){
	        return new WebMvcConfigurer(){
	            public void addCorsMappings(CorsRegistry registry){
	                registry.addMapping("/**") // Allow all paths
	                        .allowedOrigins("http://localhost:5173") // Specify your front-end URL
	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
	                        .allowedHeaders("*"); // Allow all headers

	            }
	        };
	    }
	
}
