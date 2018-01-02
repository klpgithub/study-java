package com.my.study.mongo.gridfs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;

public class GridFSDemo {

	private static final String MONGO_SERVER_URL = "localhost:27017";
	private static final String DB_NAME = "gridfs";

	private static MongoClient client = new MongoClient(MONGO_SERVER_URL);

	private static GridFSBucket gridFSBucket = GridFSBuckets.create(client.getDatabase(DB_NAME), "testFiles1");

	public static void main(String[] args) throws Exception {
		insert();
		search();
		
		ObjectId fileId	= new ObjectId("5a4aefbfb240dd5164c4c13c");
		
//		String string = download(fileId);
//		System.out.println(string);
		
//		delete(fileId);
//		search();
		
	}

	public static void insert() throws Exception {
		InputStream inputStream = new FileInputStream("C:\\Users\\hasee\\Desktop\\码云群英会-路小磊-Hutool.pptx");
		GridFSUploadOptions gridFSUploadOptions = new GridFSUploadOptions();
		gridFSUploadOptions.chunkSizeBytes(102400);// 将文件切分成多块,每块100k
		gridFSUploadOptions.metadata(new Document("type", "pptx"));

		ObjectId objectId = gridFSBucket.uploadFromStream("码云群英会-路小磊-Hutool.pptx", inputStream, gridFSUploadOptions);
		System.out.println(objectId);
		client.close();
	}

	public static void search() {
		GridFSFindIterable iterable = gridFSBucket.find();
		Block<GridFSFile> block = new Block<GridFSFile>() {
			@Override
			public void apply(GridFSFile gridFSFile) {
				System.out.println(gridFSFile.getId() + ": " + gridFSFile.getFilename());
			}
		};
		iterable.forEach(block);
	}

	/**
	 * 根据文件id进行下载
	 * 
	 * @author : KLP
	 * @param fileId
	 * @return
	 */
	public static String download(ObjectId fileId) {
		GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(fileId);
		return download(downloadStream);
	}

	/**
	 * 根据文件名下载
	 * 
	 * @author : KLP
	 * @param fileName
	 * @return
	 */
	public static String download(String fileName) {
		GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(fileName);
		return download(downloadStream);
	}

	public static String download(GridFSDownloadStream downloadStream) {
		int fileLength = (int) downloadStream.getGridFSFile().getLength();
		int chunkSize = downloadStream.getGridFSFile().getChunkSize();
		byte[] bytesToWriteTo = new byte[Math.min(fileLength, chunkSize)];
		int count = (fileLength / chunkSize) + 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			downloadStream.read(bytesToWriteTo);
			sb.append(new String(bytesToWriteTo, StandardCharsets.UTF_8));
		}
		return sb.toString();
	}

	/**
	 * 这种方法只会下载第一个分块
	 * 
	 * @author : KLP
	 * @param fileId
	 * @param path
	 * @throws IOException
	 */
	public static void download(ObjectId fileId, String path) throws IOException {
		FileOutputStream streamToDownloadTo = new FileOutputStream(path);
		gridFSBucket.downloadToStream(fileId, streamToDownloadTo);
		streamToDownloadTo.close();
	}

	/**
	 * 对文件重命名
	 * 
	 * @author : KLP
	 * @param fileId
	 * @param newFileName
	 */
	public static void rename(ObjectId fileId, String newFileName) {
		gridFSBucket.rename(fileId, newFileName);
	}

	/**
	 * 根据文件id删除文件
	 * @author : KLP
	 * @param fileId
	 */
	public static void delete(ObjectId fileId) {
		gridFSBucket.delete(fileId);
	}

}
