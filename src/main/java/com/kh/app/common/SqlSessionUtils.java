package com.kh.app.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtils {

	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		String resource = "/mybatis-config.xml";
		
		// 1. FactoryBuilder
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		// 2. Factory
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource); // resource를 읽어서 stream으로 반환
		} catch (IOException e) {
			e.printStackTrace();
		} 
		SqlSessionFactory factory = builder.build(is);
		
		// 3. SqlSession
		sqlSession = factory.openSession(false); // autoCommit 여부
		
		return sqlSession;
	}
}
