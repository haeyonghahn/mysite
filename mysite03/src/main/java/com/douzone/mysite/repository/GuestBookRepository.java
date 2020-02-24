package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestBookVo;

@Repository
public class GuestBookRepository {
	
	public List<GuestBookVo> findAll() {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<GuestBookVo> list = new ArrayList<>();

		try {
			connection = getConnection();

			String sql = "select no, name, contents, password, reg_date from guestbook order by reg_date desc";
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String password = rs.getString(4);
				Timestamp regDate = rs.getTimestamp(5);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setPassword(password);
				vo.setRegDate(regDate);
				
				list.add(vo);
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
		return list;
	}

	public int insert(GuestBookVo GuestBookVo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			connection = getConnection();

			String sql = "insert into guestbook values (null, ?, ?, ?, now())";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, GuestBookVo.getName());
			pstmt.setString(2, GuestBookVo.getContents());
			pstmt.setString(3, GuestBookVo.getPassword());
			int count = pstmt.executeUpdate();

			// 5. 성공 여부
			result = count;

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
	
	public boolean delete(Long no) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			connection = getConnection();

			String sql = "delete from guestbook where no = ?";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setLong(1, no);
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
	
	public String find(Long no) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String password = null;
		
		
		try {
			connection = getConnection();

			String sql = "select password from guestbook where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				password = rs.getString(1);
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
		return password;
	}
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			// 1. JDBC Driver(MyDriver) 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.1.104:3307/webdb";
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
