package com.scnuweb.dao;


import java.util.List;
import java.util.Map;
import com.scnuweb.util.Page;

public interface BaseDAO<T> {

	public Long save(T t);
	public void saveOrUpdate(T t);
	
	public T get(Long id);
	public T getObject(Map<String, Object> prams);
	
	public List<T> getObjectList(Map<String, Object> prams);
	public List<T> getByPrams(Map<String, Object> prams, Map<String, Object> sortPram,
			Map<String, Object> searchPram);
	public int getByPrams(Map<String, Object> prams, Map<String, Object> searchPram); 
	public Page getByPrams(Map<String, Object> prams, Map<String, Object> sortPram, Map<String, Object> searchPram, Integer iDisplayStart, Integer iDisplayLength);
	
	public void update(T t);
	public void updateByHQL(String hql, Map<String, Object> prams); 
	
	public List<T> getByHQL(String hql, Map<String, Object> prams);
	
	public List<Map<String, Object>> getBySQL(String sql, Map<String, Object> prams);
	public List<Map<String, Object>> getBySQL(String sql, Map<String, Object> prams, Integer star, Integer pageSize);
	
	public void del(Long id);
	public void del(T t);
}
