package com.method.articleservice.bl;

import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.mongodb.core.query.Query;

import java.io.InputStream;

public interface FilesManager {
	String storeFile(InputStream inputStream, String fileName, String fileType, DBObject metaData);

	GridFSFile findOne(Query query);

	Iterable<GridFSFile> find(Query query);

	void delete(Query query);
}
