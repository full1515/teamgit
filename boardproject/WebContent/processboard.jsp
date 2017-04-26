<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 기본 예제 : 게시 등록 수정 삭제 처리</title>
</head>
<body>
	<!--게시의 등록,수정,삭제를 위한 자바빈즈 이용 선언-->
	<jsp:useBean id="brd" class="board.BoardEntity" scope="page"></jsp:useBean>
	<jsp:useBean id="brddb" class="board.BoardDBCP" scope="page"></jsp:useBean>
	<%
//한글 처리를 위해 문자인코딩 지정
request.setCharacterEncoding("UTF-8");
//등록(inser),수정(update),삭제(delete) 중 하나를 저장
String menu = request.getParameter("menu");
//등록 또는 수정 처리 모듈
if(menu.equals("delete") || menu.equals("update")){
	String no = request.getParameter("no");
	String passwd = request.getParameter("passwd");
	int nonum = Integer.parseInt(no);
	//데이터베이스 자바빈즈에 구현된 메소드 isPasswd()로
	//id와 암호가 일치하는지 검사
	if(!brddb.isPasswd(nonum, passwd)){
		%>
	<!-- 암호가 틀리면 이전 화면으로 이동 -->
	<script>
		alert("비밀번호가 다릅니다.");
		history.go(-1);//이전페이지로 이동
	</script>

	<% 
	}else{	
		if(menu.equals("delete")){
			//삭제를 위해 데이터베이스 자바빈즈에
			//구현된 메소드 deleteDB()실행
			brddb.deleteDB(nonum);
		} else if(menu.equals("update")){
			%>
	<!-- 수정 시 BoardEntity에 지정해야 하는 필드 id -->
	<jsp:setProperty name="brd" property="no" />
	<jsp:setProperty name="brd" property="name" />
	<jsp:setProperty name="brd" property="title" />
	<jsp:setProperty name="brd" property="email" />
	<jsp:setProperty name="brd" property="content" />

	<% 
		//수정을 위해 데이터베이스 자바빈즈에
		//구현된 메소드 updateDB()실행
		brddb.updateDB(brd);
		
		%>

	<%
		} //else if절끝
		%>

	<% 
		//기능 수행 후 다시 게시 목록 보기로 이동
		response.sendRedirect("listboard.jsp");
			}
		}else if(menu.equals("insert")){
		%>

	<!-- 등록시 BoardEntity에 지정해야 하는 필드 passwd -->
	<jsp:setProperty name="brd" property="name"/>
	<jsp:setProperty name="brd" property="title"/>
	<jsp:setProperty name="brd" property="email"/>
	<jsp:setProperty name="brd" property="content"/>
	<jsp:setProperty name="brd" property="passwd"/>
	
	<% 
	//등록을 위해 데이터베이스 자바빈즈에 구현된 메소드 insertDB()실행
	brddb.insertDB(brd);
	//기능 수행 후 다시 게시 목록 보기로 이동
	response.sendRedirect("listboard.jsp");
		}//else if절 끝
	%>

</body>
</html>