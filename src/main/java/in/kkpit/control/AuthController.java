package in.kkpit.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.kkpit.entity.AuthModel;
import in.kkpit.entity.User;
import in.kkpit.service.UserService;

@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody AuthModel authModel){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(),authModel.getPassword()));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
	}

}
