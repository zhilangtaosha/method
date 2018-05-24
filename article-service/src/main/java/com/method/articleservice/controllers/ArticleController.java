package com.method.articleservice.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.method.articleservice.bl.ArticleManager;
import com.method.articleservice.entity.Article;

@RestController
@RequestMapping("article")
public class ArticleController {
	@Autowired
	ArticleManager articleManager;

	@PostMapping("add")
	public ResponseEntity<Article> add(@ModelAttribute @Valid Article articles,
			@RequestParam("sectionStr") String sectionsStr,
			@RequestParam("headPhotos") MultipartFile[] headPhotos
			) throws JsonMappingException, IOException{
		Article article = articleManager.add(articles, sectionsStr, headPhotos);
		return ResponseEntity.status(HttpStatus.OK).body(article);
	}

}
