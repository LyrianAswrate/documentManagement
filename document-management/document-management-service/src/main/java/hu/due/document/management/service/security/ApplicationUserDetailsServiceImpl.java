package hu.due.document.management.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.due.document.management.dto.UserDTO;
import hu.due.document.management.service.user.UserService;

@Service(value = "applicationUserDetailsService")
public class ApplicationUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new ApplicationUserDetails(user);
	}

}
