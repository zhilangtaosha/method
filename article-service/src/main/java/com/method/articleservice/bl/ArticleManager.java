package com.method.articleservice.bl;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.method.articleservice.entity.Article;

public interface ArticleManager {
	Article add(Article article, String sectionsStr, MultipartFile[] headPhotos)
			throws JsonMappingException, IOException;

	void remove(String articleId);

	Article update(String articleId, Article article);

	Article findById(String articleId);

	List<Article> findByUsername(String username);

	List<Article> findBySort();

	List<Article> list();
}
