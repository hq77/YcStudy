package com.yc.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisHelper {
	private static SqlSessionFactory sqlSessionFactory;
	static{
		try {
			String resource = "mybatis_conf.xml";
			InputStream is = Resources.getResourceAsStream(resource);
			//sqlSessionFactory针对整个应用
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//每调用一次getsession 获取独立的session
	public static SqlSession getSession() throws IOException {
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}

}
