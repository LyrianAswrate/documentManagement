package hu.due.document.management.service.security;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hu.due.document.management.dto.UserDTO;

public class ApplicationUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final UserDTO user;

    public ApplicationUserDetails(UserDTO user) {
        this.user = user;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

    public UserDTO getUser() {
        return user;
    }

}
