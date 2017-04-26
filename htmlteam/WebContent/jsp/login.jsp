<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>

<body>
	<h4>로그인</h4>
	<form action="loginresult.jsp" method="post">
		<table>
			<tr>
				<td><label for="id"> <input type="text"
						placeholder="아이디 or 이메일" id="id" name="id">
				</label></td>
				<td rowspan="2"><a href="main.html" target="_blank"><img
						src="images/login.png" alt="로그인버튼"></a></td>
			</tr>

			<tr>
				<td><label for="pw"><input type="password" id="pw" name="pw"
						placeholder="비밀번호"></label></td>
			</tr>
		</table>
		<br> <label for="login"><input type="checkbox" id="login">로그인
			상태 유지</label> <label for="idregister"><input type="checkbox"
			id="idregister" name="idregister">아이디 저장</label>
		<p>개인정보보호를 위해 개인PC에서 사용해주세요.</p>
		<br>
		<table>
			<tr>
				<td><label> <input type="button" value="아이디찾기">
				</label></td>
				<td><label> <input type="button" value="비밀번호 찾기">
				</label></td>
				<td><label> <a href="register.html" target="_blank"><input
							type="button" value="회원가입"></a>
				</label></td>
			</tr>
		</table>
	</form>
</body>
</html>