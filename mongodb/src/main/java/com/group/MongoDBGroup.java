package com.group;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class MongoDBGroup {
	private MongoTemplate mongoTemplate ;	//spring DI(依赖注入)

	/**
	 * 构建数据统计
	 */
	public Map getAppBuildCount(String begainTime, String endTime) {
		Map<String, BasicDBList> dbs = new HashMap<String, BasicDBList>();
		// 统计构建成功数
		String reduceSuccess = "function(doc, aggr){aggr.count += 1;}";		//统计count数量
		Query querySuccess = Query.query(Criteria.where("createDate").exists(true).and("status").is("SUCCESS"));	
		//查询条件，createDate：exists(true),必须，字段status值为SUCCESS。
		
		BasicDBList resultSuccess = (BasicDBList) mongoTemplate.getCollection("build")		//表名
				.group(new BasicDBObject("createDate", 1),
				querySuccess.getQueryObject(), 
				new BasicDBObject("count", 0),
				reduceSuccess);
		//类似：select * from build group by createDate where status="SUCCESS";
		dbs.put("success", resultSuccess);

		// 统计构建失败数
		String reduceFail = "function(doc, aggr){aggr.count += 1;}";
		Query queryFail = Query.query(Criteria.where("createDate").exists(true).and("status").is("FAIL"));
		BasicDBList resultFail = (BasicDBList) mongoTemplate.getCollection("build")
				.group(new BasicDBObject("createDate", 1),
				queryFail.getQueryObject(), 
				new BasicDBObject("count", 0),
				reduceFail);
		dbs.put("fail", resultFail);

		// 统计构建的总数
		String reduceAll = "function(doc, aggr){aggr.count += 1;}";
		Query queryAll = Query.query(Criteria.where("createDate").exists(true));	//查询条件去除
		BasicDBList resultAll = (BasicDBList) mongoTemplate.getCollection("build")
				.group(new BasicDBObject("createDate", 1),
				queryAll.getQueryObject(), 
				new BasicDBObject("count", 0),
				reduceAll);
		dbs.put("all", resultAll);
		
		Map xml = null;		//deal the dbs and return!
		return xml;

	}
}
