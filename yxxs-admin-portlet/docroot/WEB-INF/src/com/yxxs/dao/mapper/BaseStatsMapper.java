package com.yxxs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.yxxs.common.dao.mapper.BaseViewMapper;

public abstract class BaseStatsMapper extends BaseViewMapper<Hashtable<String,Object>> {

	public Hashtable<String,Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hashtable<String,Object> data =getPojo();

		Hashtable<String, String> mapperHash = getColumnFieldMap();
		for(String v :mapperHash.values()){
			try {
				data.put(v, rs.getObject(v));
			} catch (Exception e) {
			}
		}
		
		return data;
	}

	@Override
	public Hashtable<String,Object> getPojo() {
		return new Hashtable<String,Object>();
	}
}
