package hu.due.document.management.service.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ApplicationUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	// DB-re lesz cserélve ez csak ideiglenesen rántotma ide másik házi projektemböl
	// :D
	private static final String DEFAULT_USERNAME = "root";
	/**
	 * SajtosTészta
	 */
	private static final String DEFAULT_PASSWORD = "84063211c79c4142f4b85f81655dcdc6";// "37023d649de3b8bd76409d6b74dcf612";
	private final MessageDigest digester;

	public ApplicationUserDetailsAuthenticationProvider() throws NoSuchAlgorithmException {
		digester = MessageDigest.getInstance("MD5");
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (!crypt(String.valueOf(authentication.getCredentials())).equals(userDetails.getPassword())) {
			throw new BadCredentialsException("Password missmatch");
		}
	}

	public String crypt(String str) {
		if ((str == null) || (str.length() == 0)) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}

		digester.update(str.getBytes());
		byte[] hash = digester.digest();
		StringBuffer hexString = new StringBuffer();
		for (byte element : hash) {
			if ((0xff & element) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & element)));
			} else {
				hexString.append(Integer.toHexString(0xFF & element));
			}
		}
		return hexString.toString();
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		System.out.println(crypt(String.valueOf(authentication.getCredentials())));
		System.out.println(ApplicationUserDetailsAuthenticationProvider.DEFAULT_PASSWORD);
		if (crypt(String.valueOf(authentication.getCredentials()))
				.equals(ApplicationUserDetailsAuthenticationProvider.DEFAULT_PASSWORD)) {

			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ADMIN"));

			return new ApplicationUserDetails(ApplicationUserDetailsAuthenticationProvider.DEFAULT_USERNAME,
					ApplicationUserDetailsAuthenticationProvider.DEFAULT_PASSWORD, true, true, true, true, authorities);
		}
		throw new UsernameNotFoundException("User was not found!");
	}

}
