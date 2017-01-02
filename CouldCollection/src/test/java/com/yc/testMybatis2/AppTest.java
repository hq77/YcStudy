package com.yc.testMybatis2;


import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.dao.MybatisHelper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/*
	 * 插入电影信息
	 */
	public void testApp() throws IOException {
		SqlSession session = MybatisHelper.getSession();
		try {
			Favorite fav=new Favorite("京东","www.jingdong.com",",购物,非生活,","爱读书，上百度阅读");
			int result=session.insert("mapper.FavoriteMapper.insertFavorite", fav);
			session.commit();
			System.out.println(result);
		} finally {
			session.close();
		}
	}

	/*
	 * 条件查询电影信息
	 */
	public void testApp2() throws IOException {
		SqlSession session=MybatisHelper.getSession();
		try{
			Favorite fav=new Favorite();
			fav.setF_tags("%,非生活,%");
		List<Favorite> list=session.selectList("mapper.FavoriteMapper.selectFavorite",fav);
		for(Favorite f:list){
	 		System.out.println(f);
		}
		}finally{
			session.close();
		}

	}

	/*
	 * 插入单个电影信息
	 */
	public void testApp3() throws IOException {
		SqlSession session=MybatisHelper.getSession();
		try{
			Tag tag=new Tag("未分类",0);
			int result=session.insert("mapper.TagMapper.addTag", tag);
			session.commit();
			System.out.println(result);
		}finally{
			session.close();
		}

	}

	/*
	 * 查询电影类型
	 */
	public void testApp4() throws IOException {
		SqlSession session=MybatisHelper.getSession();
		try{
			List<Tag> list=session.selectList("mapper.TagMapper.findTag");
			for(Tag t:list){
				System.out.println(t);
			}
		}finally{
			session.close();
		}


	}

	/*
	 *添加电影类型
	 */
	public void testApp5() throws IOException {
		SqlSession session=MybatisHelper.getSession();
		try{
			Tag tag=new Tag();
			tag.setT_count(0);
			tag.setT_name("未分类");
			int result=session.update("mapper.TagMapper.updateTag", tag);
			session.commit();
			System.out.println(result);
		}finally{
			session.close();
		}
	}

	/*
	 * foreach循环查询 条件参数为对象
	 */
	public void testApp6() throws IOException {
		

	}

	/*
	 * foreach循环查询 条件参数为属性
	 */
	public void testApp7() throws IOException {
		

	}

	/*
	 * orcal：插入1
	 */
	public void testApp8() throws IOException {
		
	}

	/*
	 * orcal：插入2
	 */
	public void testApp9() throws IOException {
		

	}

	/*
	 * 批量操作：添加
	 */
	public void testApp10() throws IOException {
		

	}
}
