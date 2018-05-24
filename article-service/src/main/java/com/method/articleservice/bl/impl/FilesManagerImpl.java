package com.method.articleservice.bl.impl;

import com.method.articleservice.bl.FilesManager;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FilesManagerImpl implements FilesManager {

	@Autowired
	private GridFsTemplate gridFsTemplate;

	@Override
	public String storeFile(InputStream inputStream, String fileName, String fileType, DBObject metaData) {
		return gridFsTemplate.store(inputStream, fileName, fileType, metaData).toString();
	}

	@Override
	public GridFSFile findOne(Query query) {
		return gridFsTemplate.findOne(query);
	}

	@Override
	public Iterable<GridFSFile> find(Query query) {
		return gridFsTemplate.find(query);
	}

	@Override
	public void delete(Query query) {
		gridFsTemplate.delete(query);
	}
}
