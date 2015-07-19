package com.ayouris.nawat.repository.parametrage;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.Article;
import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface ArticleRepository extends GenericRepository<Article, String> {

}