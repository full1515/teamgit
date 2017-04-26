package board;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

//DBCP를 이용한 테이블 board처리 데이터베이스 연동 자바빈즈 프로그램
public class BoardDBCP {
//데이터베이스 연결관련 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private DataSource ds = null;
	
	//JDBC드라이버 로드 메소드
	public BoardDBCP(){
		
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//BoardDBCP끝
	
	//데이터베이스 연결 메소드
	public void connect(){
		try {
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//connect메소드끝
	
	//데이터베이스 연결 해제 메소드
	public void disconnect(){
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//if절끝
		
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//if절끝
	}//disconnect끝
	
	//게시판의 모든 레코드를 반환 메서드
	public Vector<BoardEntity> getBoardList(){
		connect();
		Vector<BoardEntity> list = new Vector<BoardEntity>();
		String SQL = "select no, name, passwd, title, email, regdate, content from board";
		try {
			pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardEntity brd = new BoardEntity();
				brd.setNo(rs.getInt("no"));
				brd.setName(rs.getString("name"));
				brd.setPasswd(rs.getString("passwd"));
				brd.setTitle(rs.getString("title"));
				brd.setEmail(rs.getString("email"));
				brd.setRegdate(rs.getTimestamp("regdate"));
				brd.setContent(rs.getString("content"));
				//리스트에 추가
				list.add(brd);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return list;
		
	}//getBoardList메소드끝
	
	//주 키 no의 레코드를 반환하는 메서드
	public BoardEntity getBoard(int no){
		connect();
		String SQL = "select no, name, passwd, title, email, regdate, content from board where no =?";
		BoardEntity brd = new BoardEntity();
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			brd.setNo(rs.getInt("no"));
			brd.setName(rs.getString("name"));
			brd.setPasswd(rs.getString("passwd"));
			brd.setTitle(rs.getString("title"));
			brd.setEmail(rs.getString("email"));
			brd.setRegdate(rs.getTimestamp("regdate"));
			brd.setContent(rs.getString("content"));
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return brd;
	
	}//getboard끝
	
	//게시물 등록 메서드
	public boolean insertDB(BoardEntity board){
		boolean success = false;
		connect();
		String sql = "insert into board(no,name,passwd,title, email, content) values(board_seq.nextval,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getPasswd());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getEmail());
			pstmt.setString(5, board.getContent());
			pstmt.executeQuery();
			success = true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return success;
		
	}//게시물등록 메서드 끝
	
	//데이터 수정을 위한 메서드
	public boolean updateDB(BoardEntity board){
		boolean success = false;
		connect();
		String sql = "update board set name=?,title=?, email=?, content=? where no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			//인자로 받은 board객체를 이용해 사용자가 수정한 값을 가져와 SQL문 완성
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getEmail());
			pstmt.setString(4, board.getContent());
			pstmt.setInt(5, board.getNo());
			pstmt.executeQuery();
			int rowUdt = pstmt.executeUpdate();
			//System.out.println(rowUdt);
			if(rowUdt == 1) success = true;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return success;
	}//삭제 메서드 끝
	
	//게시물 삭제를 위한 메서드
	public boolean deleteDB(int no){
		boolean success = false;
		connect();
		String sql = "delete from board where no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			//인자로 받은 주 키인 no값을 이용해 삭제
			pstmt.setInt(1,no);
			pstmt.executeUpdate();
			success = true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return success;
	}//삭제 메서드 끝
		
	//데이터베이스에서 인자인 no와 passwd가 일치하는지 검사하는 메서드
	public boolean isPasswd(int no, String passwd){
		boolean success = false;
		connect();
		String sql = "select passwd from board where no=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String orgPasswd = rs.getString(1);
			if (passwd.equals(orgPasswd))success = true;
			System.out.println(success);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return success;
		
	}//isPasswd메서드끝
	
}//클래스 끝
