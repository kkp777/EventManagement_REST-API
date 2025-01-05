package in.kkpit.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.kkpit.entity.User;
import in.kkpit.repo.UserRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService {
	
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
		
		// TODO Auto-generated method stub
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.build();
	}

}
