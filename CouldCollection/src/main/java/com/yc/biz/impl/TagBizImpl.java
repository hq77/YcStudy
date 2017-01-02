package com.yc.biz.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yc.bean.Tag;
import com.yc.biz.TagBiz;
import com.yc.dao.MybatisHelper;

public class TagBizImpl implements TagBiz{

	public List<Tag> findType() {
		List<Tag> tagList=null;
		try {
			SqlSession session=MybatisHelper.getSession();
			tagList=session.selectList("mapper.TagMapper.findTag");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tagList;
	}
	
}
