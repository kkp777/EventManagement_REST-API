package in.kkpit.config;

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
import org.springframework.security.web.SecurityFilterChain;

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
	
	
}
