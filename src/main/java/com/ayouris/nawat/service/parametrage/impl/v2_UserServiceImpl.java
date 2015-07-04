package com.ayouris.nawat.service.parametrage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.v2_UserRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.v2_UserService;

@Service
@Transactional(readOnly = true)
public class v2_UserServiceImpl extends GenericServiceImpl<v2_UserNawat, String> implements v2_UserService {

	@Autowired
	v2_UserRepository v2_userRepository;

	@Override
	public GenericRepository<v2_UserNawat, String> getRepository() {
		return v2_userRepository;
	}

	@Override
	public v2_UserNawat findUserNawatByLogin(String login) {
		return v2_userRepository.findUserNawatByLogin(login);
	}
}
