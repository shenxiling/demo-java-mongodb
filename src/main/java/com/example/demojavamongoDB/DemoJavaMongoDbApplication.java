package com.example.demojavamongoDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demojavamongoDB.impl.ItemDaoImpl;
import com.example.demojavamongoDB.model.Item;

//@SpringBootApplication
public class DemoJavaMongoDbApplication {

	public static void main(String[] args) {

		// SpringApplication.run(DemoJavaMongoDbApplication.class, args);

		// //test MongoDB connect
		// Mongo oMongo=new Mongo("127.0.0.1",27017);
		// DB oDB=oMongo.getDB("test");
		// Set<String> collectionNames = oDB.getCollectionNames();
		// for (String name : collectionNames) {
		// System.out.println("collectionName==="+name);
		// }
		ItemDaoImpl oItemDaoImpl = new ItemDaoImpl("test");
		oItemDaoImpl.insert(new Item("c", 29, 100), "itemLst");
		oItemDaoImpl.insert(new Item("a", 29, 101), "itemLst");
		oItemDaoImpl.insert(new Item("a", 29, 102), "itemLst");
		oItemDaoImpl.insert(new Item("a", 29, 103), "itemLst");

		List<Item> lstItem = oItemDaoImpl.findAll("itemLst");
		Map<String, Object> mapFindConditions = new HashMap<String, Object>();
		mapFindConditions.put("name", "c");
		// TODO multiConditions
		// mapFindConditions.put("price", "102");
		Item oRes = oItemDaoImpl.findOne(mapFindConditions, "itemLst");

		oItemDaoImpl.delete(mapFindConditions, "itemLst");

		Map<String, Object> mapUpdateConditions = new HashMap<String, Object>();
		mapUpdateConditions.put("name", "a");
		Map<String, Object> mapUpdate = new HashMap<String, Object>();
		mapUpdate.put("name", "aa");
		oItemDaoImpl.updateFirst(mapUpdateConditions, mapUpdate, "itemLst");
		oItemDaoImpl.update(mapUpdateConditions, mapUpdate, "itemLst");
	}
}
