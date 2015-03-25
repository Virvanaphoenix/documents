package com.sweetmanor.database.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoCRUDDemo {
	private MongoClient client;
	private DB db;
	private DBCollection coll;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void initConn() {
		client = new MongoClient("localhost", 27017);
		db = client.getDB("mydb");
		coll = db.getCollection("emp");
	}

	public void insert() {
		BasicDBObject doc = new BasicDBObject("name", "MongoDB")
				.append("type", "database").append("count", 1)
				.append("info", new BasicDBObject("x", 203).append("y", 102));
		coll.insert(doc);
	}

	public void queryOne() {
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);
	}

	public void query() {
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

	public void queryWhile() {
		BasicDBObject query = new BasicDBObject("j",
				new BasicDBObject("$ne", 3)).append("k", new BasicDBObject(
				"$gt", 10));
		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

	public void queryWhere() {
		BasicDBObject query = new BasicDBObject("j",
				new BasicDBObject("$ne", 3)).append("k", new BasicDBObject(
				"$gt", 10));
		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

	public void count() {
		System.out.println(coll.getCount());
	}

	public void getDatabase() {
		for (String s : client.getDatabaseNames()) {
			System.out.println(s);
		}
	}

	public void bulk() {

		BulkWriteOperation builder = coll.initializeOrderedBulkOperation();
		builder.insert(new BasicDBObject("_id", 1));
		builder.insert(new BasicDBObject("_id", 2));
		builder.insert(new BasicDBObject("_id", 3));

		builder.find(new BasicDBObject("_id", 1)).updateOne(
				new BasicDBObject("$set", new BasicDBObject("x", 2)));
		builder.find(new BasicDBObject("_id", 2)).removeOne();
		builder.find(new BasicDBObject("_id", 3)).replaceOne(
				new BasicDBObject("_id", 3).append("x", 4));

		BulkWriteResult result = builder.execute();

		// 2. Unordered bulk operation - no guarantee of order of operation
		builder = coll.initializeUnorderedBulkOperation();
		builder.find(new BasicDBObject("_id", 1)).removeOne();
		builder.find(new BasicDBObject("_id", 2)).removeOne();

		result = builder.execute();
	}

	public void drop() {
		client.dropDatabase("mydb");
	}

	public void dropCollection() {
		DBCollection coll = db.getCollection("emp");
		coll.drop();
		System.out.println(db.getCollectionNames());
	}

	public void getCollection() {
		for (String s : db.getCollectionNames()) {
			System.out.println(s);
		}
	}
}
