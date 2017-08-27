package com.example.demojavamongoDB.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.demojavamongoDB.dao.ItemDao;
import com.example.demojavamongoDB.model.Item;
import com.mongodb.Mongo;

@Repository("ItemDaoImpl")
public class ItemDaoImpl implements ItemDao {

	// #region Field

	private MongoOperations oMongoOperations;

	// #endregion

	// #region construction

	public ItemDaoImpl(String strDataBase) {
		this.oMongoOperations = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), strDataBase));
	}

	// #endregion

	// #region insert

	@Override
	public void insert(Item oItem, String strCollectionName) {
		oMongoOperations.insert(oItem, strCollectionName);
	}

	// #endregion

	// #region delete

	@Override
	public void delete(Map<String, Object> mapConditions, String strCollectionName) {
		if (mapConditions.isEmpty()) {
			// TODO
			return;
		}
		Criteria oCriteria = new Criteria();
		for (Map.Entry<String, Object> entry : mapConditions.entrySet()) {
			oCriteria = oCriteria.andOperator(Criteria.where(entry.getKey()).is(entry.getValue()));
		}
		oMongoOperations.remove(new Query(oCriteria), Item.class, strCollectionName);
	}

	// #endregion

	// #region find

	@Override
	public Item findOne(Map<String, Object> mapConditions, String strCollectionName) {
		if (mapConditions.isEmpty()) {
			// TODO
			return null;
		}
		Criteria oCriteria = new Criteria();
		for (Map.Entry<String, Object> entry : mapConditions.entrySet()) {
			oCriteria.andOperator(Criteria.where(entry.getKey()).is(entry.getValue()));
		}
		Item oRes = oMongoOperations.findOne(new Query(oCriteria), Item.class, strCollectionName);
		return oRes;
	}

	@Override
	public List<Item> findAll(String strCollectionName) {
		List<Item> lstItem = oMongoOperations.findAll(Item.class, strCollectionName);
		return lstItem;
	}

	// #endregion

	// #region update

	@Override
	public void update(Map<String, Object> mapConditions, Map<String, Object> mapUpdate, String strCollectionName) {
		if (mapConditions.isEmpty() || mapUpdate.isEmpty()) {
			// TODO
			return;
		}
		Criteria oCriteria = new Criteria();
		for (Map.Entry<String, Object> entry : mapConditions.entrySet()) {
			oCriteria = oCriteria.andOperator(Criteria.where(entry.getKey()).is(entry.getValue()));
		}
		// TODO update once
		for (Map.Entry<String, Object> entry : mapUpdate.entrySet()) {
			oMongoOperations.updateMulti(new Query(oCriteria), Update.update(entry.getKey(), entry.getValue()),
					Item.class, strCollectionName);
		}
	}

	public void updateFirst(Map<String, Object> mapConditions, Map<String, Object> mapUpdate,
			String strCollectionName) {
		if (mapConditions.isEmpty() || mapUpdate.isEmpty()) {
			// TODO
			return;
		}
		Criteria oCriteria = new Criteria();
		for (Map.Entry<String, Object> entry : mapConditions.entrySet()) {
			oCriteria = oCriteria.andOperator(Criteria.where(entry.getKey()).is(entry.getValue()));
		}
		// TODO update once
		for (Map.Entry<String, Object> entry : mapUpdate.entrySet()) {
			oMongoOperations.updateFirst(new Query(oCriteria), Update.update(entry.getKey(), entry.getValue()),
					Item.class, strCollectionName);
		}
	}

	// #endregion
}
