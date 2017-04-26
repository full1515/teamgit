function deletecheck() {
	if (document.boardform.passwd.value.replace(/\s/g, "") == "") {
		alert("암호를 입력해 주세요.");
		document.boardform.passwd.focus();
		return;
	}// if절 끝
	ok = confirm("삭제하시겠습니까?");

	if (ok) {
		document.boardform.menu.value = 'delete';
		document.boardform.submit();

	} else {
		return;
	}

}// deletecheck끝

function insertcheck() {
	with (document.boardform) {
		if (name.value.replace(/\s/g,"")=="") {
			alert("이름을 입력해 주세요");
			name.focus();
			return false;
		}

		if (email.value.replace(/\s/g,"")=="") {
			alert("이메일을 입력해 주세요");
			email.focus();
			return false;
		}

		if (title.value.replace(/\s/g,"")=="") {
			alert("제목을 입력해 주세요");
			title.focus();
			return false;
		}

		if (content.value.replace(/\s/g,"")=="") {
			alert("내용을 입력해 주세요");
			content.focus();
			return false;
		}

		if (passwd.value.replace(/\s/g,"")=="") {
			alert("비밀번호을 입력해 주세요");
			passwd.focus();
			return false;
		}

		if (passwd.value.search(/\W/)>=0) {
			alert("비밀번호는 알파벳과 숫자로 구성되어야 합니다.");
			passwd.value = ""
			passwd.focus();
			return false;
		}

		if (passwd.value.length < 4 || passwd.value.length > 8) {
			alert("비밀번호는 4자이상 8자이하로 작성되어야 합니다");
			passwd.value = ""
			passwd.focus();
			return false;
		}

	}// with절 끝

	document.boardform.menu.value ='insert';
	document.boardform.submit();

}// insertcheck끝

function updatecheck() {
	with (document.boardform) {
		if (name.value.replace(/\s/g, "") == "") {
			alert("이름을 입력해 주세요");
			name.focus();
			return false;
		}

		if (email.value.replace(/\s/g, "") == "") {
			alert("이메일을 입력해 주세요");
			email.focus();
			return false;
		}

		if (title.value.replace(/\s/g, "") == "") {
			alert("제목을 입력해 주세요");
			title.focus();
			return false;
		}

		if (passwd.value.replace(/\s/g, "") == "") {
			alert("비밀번호을 입력해 주세요");
			passwd.focus();
			return false;
		}

		if (passwd.value.search(/\W/) >= 0) {
			alert("비밀번호는 알파벳과 숫자로 구성되어야 합니다.");
			passwd.value = "";
			passwd.focus();
			return false;
		}

		if (passwd.value.length < 4 || passwd.value.length > 8) {
			alert("비밀번호는 4자이상 8자이하로 작성되어야 합니다");
			passwd.value = "";
			passwd.focus();
			return false;
		}

	}// with끝

	document.boardform.menu.value = 'update';
	document.boardform.submit();

	function s_word(val) {
		document.boardform.content.value += val;
	}

}// updatecheck끝

