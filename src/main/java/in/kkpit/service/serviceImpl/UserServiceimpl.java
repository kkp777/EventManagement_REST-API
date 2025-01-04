package in.kkpit.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.kkpit.entity.User;
import in.kkpit.repo.UserRepository;
import in.kkpit.service.UserService;

@Service
public class UserServiceimpl implements UserService {
	
	
	
	private UserRepository userRepo;
	@Autowired
    public UserServiceimpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
	@Override
	public User createUser(User i) {
//		if(userRepo.existsByEmail(i.getEmail())) {
//			throw new RuntimeException("Email is already exist");
//		}
		return userRepo.save(i);
	}

	@Override
	public User getUserById(Long id) {
		return userRepo.findById(id).orElseThrow(()->new RuntimeException("user not found"));
	}
	
	
}
