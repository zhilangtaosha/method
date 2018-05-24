package com.method.articleservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.method.articleservice.pojo.Attachment;
import com.method.articleservice.pojo.Section;

@Document
public class Article {
	@Id
	String Id;
	String userId;
	
	//head
	String title;
	String description;
	String sortLevelOne;
	String sortLevelTwo;
	List<Attachment> headPhotos;
	
	//body
	List<Section> sections;
	
	//tail
	String tips;
	String wranings;
	List<String> references;

	}
