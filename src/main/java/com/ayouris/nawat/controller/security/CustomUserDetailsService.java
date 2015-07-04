package com.ayouris.nawat.controller.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ayouris.nawat.model.entity.UserNawat;
import com.ayouris.nawat.repository.parametrage.UserRepository;

/**
 * This class loads the requested user by using a Spring Data JPA repository.
 * 
 * @author Petri Kainulainen
 */
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserNawat userNawat = userRepository.findOne("E004000000001");

		if (userNawat == null) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return new org.springframework.security.core.userdetails.User(userNawat.getLogin(), userNawat.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

	}
}
