package com.ayouris.nawat.controller.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ayouris.nawat.model.entity.UserNawat;
import com.ayouris.nawat.repository.parametrage.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UserRepository userRepository;

	@Autowired
	public CustomAuthenticationProvider(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String name = auth.getName();
		String password = auth.getCredentials().toString();

		UserNawat userNawat = userRepository.findUserNawatByLogin(name);

		// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// //String rawPassword = passwordEncoder.encode(password);
		// if (userNawat == null) {
		// return null;
		// } else if (!passwordEncoder.matches(password, userNawat.getPassword())) {
		// return null;
		// }

		if (userNawat == null) {
			return null;
		} else if (!userNawat.getPassword().equals(password)) {
			return null;
		}

		final List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		// grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		authentication.setDetails(userNawat);

		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		boolean value = UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		return value;
	}

}