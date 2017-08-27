package com.example.demojavamongoDB.dao;

import java.util.List;
import java.util.Map;

public interface MongoBase<T> {

	public void insert(T oItem, String strCollectionName);

	public void delete(Map<String, Object> mapConditions, String strCollectionName);

	public T findOne(Map<String, Object> mapConditions, String strCollectionName);

	public List<T> findAll(String strCollectionName);

	public void update(Map<String, Object> mapConditions, Map<String, Object> mapUpdates, String strCollectionName);
}
