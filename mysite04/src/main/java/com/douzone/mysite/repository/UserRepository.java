package com.douzone.mysite.repository;

//import java.sql.Connection;
import java.util.HashMap;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo UserVo) {
		return sqlSession.insert("user.insert", UserVo);

//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		//int count = 0;
//
//		try {
//			connection = dataSource.getConnection();
//
//			String sql = "insert into user values (null, ?, ?, ?, ?, now())";
//			pstmt = connection.prepareStatement(sql);
//
//			pstmt.setString(1, UserVo.getName());
//			pstmt.setString(2, UserVo.getEmail());
//			pstmt.setString(3, UserVo.getPassword());
//			pstmt.setString(4, UserVo.getGender());
//
//			count = pstmt.executeUpdate();
//			// 5. 성공 여부
//		} catch (SQLException e) {
//			throw new UserRepositoryException("error :" + e);	
//		} finally {
//			try {
//				// 6. 자원정리
//				if (connection != null)
//					connection.close();
//				if(pstmt != null)
//					pstmt.close();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return count;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword", vo);
		
//		UserVo userVo = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = dataSource.getConnection();
//
//			String sql =
//				"select no, name, email" +
//				"  from user" + 
//				" where email = ?" +
//				"   and password = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getEmail());
//			pstmt.setString(2, vo.getPassword());
//			rs = pstmt.executeQuery();
//
//			if(rs.next()) {
//				Long no = rs.getLong(1);
//				String name = rs.getString(2);
//				String email = rs.getString(3);
//
//				userVo = new UserVo();
//				userVo.setNo(no);
//				userVo.setName(name);
//				userVo.setEmail(email);
//			}
//		} catch (SQLException e) {
//			throw new UserRepositoryException("error :" + e);	
//		} finally {
//			// 자원 정리
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}		
//		
//		return userVo;
	}
	
	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		return sqlSession.selectOne("user.findByEmailAndPassword2", map);
	
	}
	
	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.find", no);
		
//		UserVo userVo = null;
//
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			connection = dataSource.getConnection();
//
//			String sql = "select name, email, gender from user where no = ?";
//			pstmt = connection.prepareStatement(sql);
//
//			pstmt.setLong(1, no);
//			rs = pstmt.executeQuery();
//
//			if(rs.next()) {
//				String name = rs.getString(1);
//				String email = rs.getString(2);
//				String gender = rs.getString(3);
//
//				userVo = new UserVo();
//				userVo.setNo(no);
//				userVo.setName(name);
//				userVo.setEmail(email);
//				userVo.setGender(gender);
//			}
//
//		} catch (SQLException e) {
//			throw new UserRepositoryException("error :" + e);		
//		} finally {
//			try {
//				// 6. 자원정리
//				if (connection != null)
//					connection.close();
//				if(pstmt != null)
//					pstmt.close();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return userVo;
	}
	
	public int update(UserVo userVo) {
		//// 이렇게 작성되도 괜찮지만 동적 쿼리를 이용하여 작성할 것 ///////
//		int count = 0;
//		if("".equals(userVo.getPassword())) {
//			count = sqlSession.update("user.update1");
//		}
//		else {
//			count = sqlSession.update("user.update2");
//		}
		////////////////////////////////////////////////////
		
		return sqlSession.update("user.update", userVo);

//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			connection = dataSource.getConnection();
//
//			String sql = "update user set name = ?, password = ?, gender = ? where no = ?";
//			pstmt = connection.prepareStatement(sql);
//			
//			pstmt.setString(1, userVo.getName());
//			pstmt.setString(2, userVo.getPassword());
//			pstmt.setString(3, userVo.getGender());
//			pstmt.setLong(4, userVo.getNo());
//			
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			throw new UserRepositoryException(e.getMessage());	
//		} finally {
//			try {
//				// 6. 자원정리
//				if (connection != null)
//					connection.close();
//				if(pstmt != null)
//					pstmt.close();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}

//	private Connection getConnection() throws SQLException {
//		Connection connection = null;
//
//		try {
//			// 1. JDBC Driver(MyDriver) 로딩
//			Class.forName("org.mariadb.jdbc.Driver");
//
//			// 2. 연결하기
//			String url = "jdbc:mysql://192.168.1.104:3307/webdb";
//			connection = DriverManager.getConnection(url, "webdb", "webdb");
//			System.out.println("연결성공!");
//
//		} catch (ClassNotFoundException e) {
//			throw new UserRepositoryException(("드라이버 로딩 실패 : " + e));
//		}
//		return connection;
//	}
}
