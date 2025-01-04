package in.kkpit.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.kkpit.entity.User;
import in.kkpit.service.UserService;
import in.kkpit.service.serviceImpl.UserServiceimpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService us;
	
	@Autowired
	private UserServiceimpl ss;
	@PostMapping
	public User createUser(@RequestBody User u) {
		
		return us.createUser(u);
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		
		return us.getUserById(id);
		
	}
    
	
	
	
	
}
