package com.ayouris.nawat.service.parametrage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.Article;
import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.ArticleRepository;
import com.ayouris.nawat.repository.parametrage.v2_UserRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.ArticleService;
import com.ayouris.nawat.service.parametrage.v2_UserService;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl extends GenericServiceImpl<Article, String> implements ArticleService{

	@Autowired
	ArticleRepository articleRepository;

	@Override
	public GenericRepository<Article, String> getRepository() {
		return articleRepository;
	}

	/*@Override
	public Article findUserNawatByLogin(String login) {
		return v2_userRepository.findUserNawatByLogin(login);
	}*/
}
