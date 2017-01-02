package com.yc.biz.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.biz.FavoriteBiz;
import com.yc.dao.MybatisHelper;

public class FavoriteBizImpl implements FavoriteBiz{

	public int addFavorite(Favorite fav,Tag tag) {
		SqlSession session;
		int result=0;
		try {
			session = MybatisHelper.getSession();
			if(tag.getTname()!=null && tag.getTname().length>0){
				for(int i=0;i<tag.getTname().length;i++){
					tag.setT_name(tag.getTname()[i]);
					List<Tag> tagList=session.selectList("mapper.TagMapper.findTag", tag);
					if(tagList!=null && tagList.size()>0){
						tag.setT_count(tagList.get(0).getT_count()+1);
						session.update("mapper.TagMapper.updateTag", tag);
					}else{
						tag.setT_count(1);
						session.insert("mapper.TagMapper.addTag", tag);
					}
				}
				result=session.insert("mapper.FavoriteMapper.insertFavorite", fav);
			}else{
				tag.setT_name("未分类");
				tag=session.selectOne("mapper.TagMapper.findTag", tag);
				tag.setT_count(tag.getT_count()+1);
				session.update("mapper.TagMapper.updateTag", tag);
				fav.setF_tags(",未分类,");
				result=session.insert("mapper.FavoriteMapper.insertFavorite", fav);
			}
		
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Favorite> findFavoriteByType(Favorite fav) {
		SqlSession session;
		List<Favorite> list=null;
		try {
			session = MybatisHelper.getSession();
			 list=session.selectList("mapper.FavoriteMapper.selectFavorite", fav);
			 session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
