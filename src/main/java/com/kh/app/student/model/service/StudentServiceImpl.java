package com.kh.app.student.model.service;

import static com.kh.app.common.SqlSessionUtils.*;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.app.common.SqlSessionUtils;
import com.kh.app.student.model.dao.StudentDao;
import com.kh.app.student.model.dto.Student;

public class StudentServiceImpl implements StudentService {
	private StudentDao studentDao;
	
	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	public int insertStudent(Student student) {
		SqlSession sqlSession = getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(sqlSession, student);
			sqlSession.commit();
		} catch(Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	@Override
	public int insertStudentMap(Map<String, Object> map) {
		SqlSession sqlSession = getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudentMap(sqlSession, map);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	@Override
	public int getTotalCount() {
		try(SqlSession sqlSession = getSqlSession()) {			
			return studentDao.getTotalCount(sqlSession);
		}
	}
	
	@Override
	public Student selectOneStudent(int no) {
		try(SqlSession sqlSession = getSqlSession()) {
			return studentDao.selectOneStudent(sqlSession, no);
		}
	}
}
