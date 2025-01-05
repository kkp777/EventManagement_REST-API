package in.kkpit.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.kkpit.entity.AuthModel;
import in.kkpit.entity.JwtResponse;
import in.kkpit.entity.User;
import in.kkpit.service.JwtTokenService;
import in.kkpit.service.UserService;

@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	 private JwtTokenService jwtTokenService;

	
	 @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel){
	       Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authModel.getEmail(),authModel.getPassword()));
	          var token = jwtTokenService.generateToken(authentication);
	       return new ResponseEntity<>(new JwtResponse(token),HttpStatus.OK);
	    }
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
	}
	
	  @PostMapping("/authenticate")
	    public Authentication authentication(Authentication authentication){
	        return authentication;
	    }

}
