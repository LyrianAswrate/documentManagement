package hu.due.document.management.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ApplicationSecurityServiceImpl implements ApplicationSecurityService {

	@Autowired
	protected AuthenticationManager authManager;

	private boolean checkAuthorizedToUseApplication(Authentication authentication) {
		return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
	}

	@Override
	public void doLogin(String userName, String password) {
		Assert.notNull(userName, "\"userName\" can't be null");
		Assert.notNull(password, "\"password\" can't be null");

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
		Authentication authentication = authManager.authenticate(token);
		if (!checkAuthorizedToUseApplication(authentication)) {
			// throw new OperationConstraintViolatedException();
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
