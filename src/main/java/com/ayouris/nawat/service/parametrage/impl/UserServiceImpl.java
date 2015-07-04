package com.ayouris.nawat.service.parametrage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.UserNawat;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.UserRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends GenericServiceImpl<UserNawat, String> implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public GenericRepository<UserNawat, String> getRepository() {
		return userRepository;
	}

	@Override
	public UserNawat findUserNawatByLogin(String login) {
		return userRepository.findUserNawatByLogin(login);
	}
}
