package in.kkpit.service;

import in.kkpit.entity.User;

public interface UserService {
	
	User createUser(User u);
	User getUserById(Long id);

}
