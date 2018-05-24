package com.method.articleservice.bl.impl;

import static com.method.articleservice.util.Constants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.method.articleservice.bl.ArticleManager;
import com.method.articleservice.bl.FilesManager;
import com.method.articleservice.entity.Article;
import com.method.articleservice.entity.User;
import com.method.articleservice.pojo.Attachment;
import com.method.articleservice.pojo.Section;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class ArticleManagerImpl implements ArticleManager {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	FilesManager filesManager;

	@Autowired
	ObjectMapper objectMapper;

	public Article add(Article article) {
		mongoTemplate.save(article);
		return article;
	}

	@Override
	public String remove(String articleId) {
		Article article = mongoTemplate.findById(articleId, Article.class);
		mongoTemplate.remove(article);
		return OK;
	}

	@Override
	public Article update(String articleId, Article article) {
		return null;
	}

	@Override
	public Article findById(String articleId) {
		return mongoTemplate.findById(articleId, Article.class);
	}

	@Override
	public List<Article> findByUsername(String username) {
		Query queryUser = new Query();
		queryUser.addCriteria(Criteria.where(NAME).is(username));
		User user = mongoTemplate.findOne(queryUser, User.class);
		Query query = new Query();
		query.addCriteria(Criteria.where(USER_ID).is(user.getId()));
		List<Article> list = mongoTemplate.find(query, Article.class);
		return list;
	}

	@Override
	public List<Article> list() {
		return mongoTemplate.findAll(Article.class);
	}

	@Override
	public Article add(Article article, String sectionsStr, MultipartFile[] headPhotos)
			throws JsonMappingException, IOException {
		List<Section> industry = objectMapper.readValue(sectionsStr, new TypeReference<List<Section>>() {
		});

		return null;
	}

	@Override
	public List<Article> findBySort() {
		return null;
	}

	private String storeFile(MultipartFile file, String username, String articleId) throws IOException {
		DBObject dbObject = new BasicDBObject();
		dbObject.put(USERNAME, username);
		dbObject.put(MODULE, ARTICLE);
		dbObject.put(ARTICLE, articleId);
		String fileType = null;
		if (file.getOriginalFilename().lastIndexOf(".") != -1)
			fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		else
			fileType = ".unknown";
		return filesManager.storeFile(file.getInputStream(), username + "-" + new Date() + fileType,
				file.getContentType(), dbObject);
	}

	private List<Attachment> storeFile(MultipartFile[] files, String username, String articleId) throws IOException {
		List<Attachment> list = new ArrayList<Attachment>();
		String fileType = null;
		Attachment temp_attachment = null;
		for (MultipartFile mFile : files) {
			DBObject dbObject = new BasicDBObject();
			dbObject.put(USERNAME, username);
			dbObject.put(MODULE, "");
			dbObject.put(ARTICLE_ID, articleId);

			temp_attachment = new Attachment();
			if (mFile.getOriginalFilename().lastIndexOf(".") != -1)
				fileType = mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf("."));
			else
				fileType = ".unknown";
			temp_attachment.setId(filesManager.storeFile(mFile.getInputStream(), username + "-" + new Date() + fileType,
					mFile.getContentType(), dbObject));
			temp_attachment.setName(mFile.getOriginalFilename());
			list.add(temp_attachment);
		}
		return list;
	}

}
