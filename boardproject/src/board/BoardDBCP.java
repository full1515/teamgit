package board;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

//DBCP�� �̿��� ���̺� boardó�� �����ͺ��̽� ���� �ڹٺ��� ���α׷�
public class BoardDBCP {
//�����ͺ��̽� ������� ���� ����
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private DataSource ds = null;
	
	//JDBC����̹� �ε� �޼ҵ�
	public BoardDBCP(){
		
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//BoardDBCP��
	
	//�����ͺ��̽� ���� �޼ҵ�
	public void connect(){
		try {
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//connect�޼ҵ峡
	
	//�����ͺ��̽� ���� ���� �޼ҵ�
	public void disconnect(){
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//if����
		
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//if����
	}//disconnect��
	
	//�Խ����� ��� ���ڵ带 ��ȯ �޼���
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
				//����Ʈ�� �߰�
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
		
	}//getBoardList�޼ҵ峡
	
	//�� Ű no�� ���ڵ带 ��ȯ�ϴ� �޼���
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
	
	}//getboard��
	
	//�Խù� ��� �޼���
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
		
	}//�Խù���� �޼��� ��
	
	//������ ������ ���� �޼���
	public boolean updateDB(BoardEntity board){
		boolean success = false;
		connect();
		String sql = "update board set name=?,title=?, email=?, content=? where no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			//���ڷ� ���� board��ü�� �̿��� ����ڰ� ������ ���� ������ SQL�� �ϼ�
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
	}//���� �޼��� ��
	
	//�Խù� ������ ���� �޼���
	public boolean deleteDB(int no){
		boolean success = false;
		connect();
		String sql = "delete from board where no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			//���ڷ� ���� �� Ű�� no���� �̿��� ����
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
	}//���� �޼��� ��
		
	//�����ͺ��̽����� ������ no�� passwd�� ��ġ�ϴ��� �˻��ϴ� �޼���
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
		
	}//isPasswd�޼��峡
	
}//Ŭ���� ��
