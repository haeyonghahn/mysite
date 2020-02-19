package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {

	public List<BoardVo> findAll() {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BoardVo> list = new ArrayList<>();

		try {
			connection = getConnection();

			String sql = "select board.no, title, name, hit, board.reg_date, user_no, g_no, o_no, depth, state "
					+ 	 "from board, user "
					+    "where board.user_no = user.no "
					+ 	 "order by g_no desc, o_no asc";
			
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				Timestamp regDate = rs.getTimestamp(5);
				Long userNo = rs.getLong(6);
				int gNo = rs.getInt(7);
				int oNo = rs.getInt(8);
				int depth = rs.getInt(9);
				String state = rs.getString(10);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setState(state);

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
	
	public List<BoardVo> find(String kwd) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BoardVo> list = new ArrayList<>();

		try {
			connection = getConnection();

			String sql = "select board.no, title, contents, name, hit, board.reg_date, user_no, g_no, o_no, depth, state "
					+ 	 "from board, user "
					+    "where board.user_no = user.no "
					+	 "and title like ? "
					+ 	 "order by g_no desc, o_no asc";
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%"+kwd+"%");
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String name = rs.getString(4);
				int hit = rs.getInt(5);
				Timestamp regDate = rs.getTimestamp(6);
				Long userNo = rs.getLong(7);
				int gNo = rs.getInt(8);
				int oNo = rs.getInt(9);
				int depth = rs.getInt(10);
				String state = rs.getString(11);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setName(name);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setState(state);

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

	public BoardVo viewContents(Long no) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		BoardVo vo = null;

		try {
			connection = getConnection();

			String sql = "select title, contents, hit, user_no, g_no, o_no, depth from board where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				int hit = rs.getInt(3);
				Long userNo = rs.getLong(4);
				int gNo = rs.getInt(5);
				int oNo = rs.getInt(6);
				int dept = rs.getInt(7);

				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setUserNo(userNo);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(dept);

				vo.setNo(no);
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

		return vo;
	}
	
	public void countUp(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update board set hit = ? where no = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, vo.getHit() + 1);
			pstmt.setLong(2, vo.getNo());

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

	public void writeUpdate(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update board set title = ?, contents = ? where no = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

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

	public void write(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();

			String sql = "insert into board values (null, ?, ?, 0, now(), ifnull((select max(g_no) from board b)+1, 1), 1, 0, ?, ?)";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getState());
			pstmt.setLong(4, vo.getUserNo());

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
	
	public void replyWrite(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		int newDepth = vo.getDepth() + 1;
		
		try {
			connection = getConnection();

			String sql = "insert into board values (null, ?, ?, 0, now(), ?, ?, ?, 'no', ?)";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			
			pstmt.setInt(3, vo.getgNo());
			pstmt.setInt(4, vo.getoNo() + 1);
			pstmt.setInt(5, newDepth);
			pstmt.setLong(6, vo.getUserNo());

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
	
	public void replyUpdate(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update board set o_no = o_no + 1 where g_no = ? and o_no > ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, vo.getgNo());
			pstmt.setInt(2, vo.getoNo());
			
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
	
	public boolean delete(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			connection = getConnection();

			String sql = "update board set title = '', state = ? where no = ?";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, vo.getState());
			pstmt.setLong(2, vo.getNo());
			
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
