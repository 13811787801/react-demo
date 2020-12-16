package com.yxxs.dao;

import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.dao.RBaseDao;
import com.yxxs.common.dto.PagerInfo;
import com.yxxs.common.dto.PagerResult;
import com.yxxs.dao.mapper.BaseStatsMapper;

@Repository
public class CommonStatisticDao extends RBaseDao<Hashtable<String,Object>> {

	public PagerResult<List<Hashtable<String, Object>>> search(BaseStatsMapper mapper, String condition, Object[] params, String orderCnd, PagerInfo pager){
		return select(getFieldsStr(mapper.getColumnFields()), mapper, condition, params, orderCnd, pager, mapper);
	}
	
	@Override
	protected Class getMapperClass() {
		return null;
	}
}
