package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.douzone.mysite.vo.UserVo;

public class UserRepository {

	public boolean insert(UserVo UserVo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			connection = getConnection();

			String sql = "insert into user values (null, ?, ?, ?, ?, now())";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, UserVo.getName());
			pstmt.setString(2, UserVo.getEmail());
			pstmt.setString(3, UserVo.getPassword());
			pstmt.setString(4, UserVo.getGender());

			int count = pstmt.executeUpdate();
			// 5. 성공 여부
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 6. 자원정리
				if (connection != null)
					connection.close();
				if(pstmt != null)
					pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo userVo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql =
				"select no, name" +
				"  from user" + 
				" where email = ?" +
				"   and password = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return userVo;
	}

	public UserVo findByNo(Long no) {
		UserVo userVo = null;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql = "select name, email, gender from user where no = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				String name = rs.getString(1);
				String email = rs.getString(2);
				String gender = rs.getString(3);

				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setGender(gender);
			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 6. 자원정리
				if (connection != null)
					connection.close();
				if(pstmt != null)
					pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userVo;
	}
	
	public void update(UserVo userVo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();

			String sql = "update user set name = ?, password = ?, gender = ? where no = ?";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getGender());
			pstmt.setLong(4, userVo.getNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 6. 자원정리
				if (connection != null)
					connection.close();
				if(pstmt != null)
					pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			// 1. JDBC Driver(MyDriver) 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("연결성공!");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} 
		return connection;
	}
}
