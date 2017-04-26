<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 기본 예제 : 게시 목록 표시</title>
<style type="text/css">
body, table {
	font-size: 9pt;
	font-family: 굴림;
}

th, cl {
	color: #0000CC;
}
</style>
</head>
<body>
	<h2 align="center">게시판 목록 표시 프로그램</h2>
	<hr>
	<center>
	<%@ page
		import="java.util.Vector,board.BoardEntity,java.text.SimpleDateFormat"%>
	<jsp:useBean id="brddb" class="board.BoardDBCP" scope="page"></jsp:useBean>
	<%
		//게시 목록을 위한 배열리스트를 자바빈즈를 이용하여 확보
		Vector<BoardEntity> list = brddb.getBoardList();
		int counter = list.size();
		int row = 0;
		if (counter > 0) {
	%>
	<table width="800" border="0" cellpadding="1" cellspacing="3">
		<tr>
			<th class="cl">번호</th>
			<th class="cl">제목</th>
			<th class="cl">작성자</th>
			<th class="cl">작성일</th>
			<th class="cl">전자메일</th>
		</tr>
		<%
			//게시 등록일을 2010-3-15 10:33:21 형태로 출력하기 위한 클래스
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
				for (BoardEntity brd : list) {
					//홀짝으로 다르게 색상지정
					String color = "papaywhip";
					if (++row % 2 == 0)
						color = "white";
		%>
		<tr bgcolor="<%=color%>"
			onmouseover="this.style.backgroundColor='skyBlue'"
			onmouseout="this.style.backgroundColor='<%=color%>'">
			<!-- 수정과 삭제를 위한 링크로 no를 전송 -->
			<td align="center"><%=brd.getNo()%></td>
			<td align="left"><a href="editboard.jsp?no=<%=brd.getNo()%>"><%=brd.getTitle()%></a></td>
			<td align="center"><%=brd.getName()%></td>
			<!-- 게시 작성일을 2010-3-15 10:33:21 형태로 출력 -->
			<td align="center"><%=df.format(brd.getRegdate())%></td>
			<td align="center"><%=brd.getEmail()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
	<hr width="90%">
	<p>
		조회된 게시판 목록 수가
		<%=counter%>개 입니다.
	</p>
	</center>
	<hr>
	<center>
		<form name="form" method="post" action="editboard.jsp">
			<input type="submit" value="게시등록">
		</form>
	</center>
</body>
</html>