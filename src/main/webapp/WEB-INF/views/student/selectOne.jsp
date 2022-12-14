<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 실습</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
<style>
div#student-container{text-align:center;}
table.tbl-student{margin:10px auto;border:1px solid; border-collapse:collapse;}
table.tbl-student th,table.tbl-student td{
	border:1px solid;
	padding:5px;
	line-height: 2em; /*input태그로 인해 cell이 높이가 길어져 border 두줄현상방지 */
}
table.tbl-student th{text-align:right;}
table.tbl-student td{text-align:left;}
table.tbl-student tr:last-of-type td:first-child{text-align:center;}
</style>
</head>
<body>
	<div id="student-container">
		<h2>학생정보 검색</h2>
		<p>등록된 학생 수는 <span id="totalCount">${totalCount }</span>명입니다.</p>
		<form name="studentSearchFrm">
			<table class="tbl-student">
				<tr>
					<th>학생번호</th>
					<td>
						<input type="number" name="no" value="" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
		
		<hr />
		
        <h2>학생 정보 수정</h2>
        <form name="studentUpdateFrm">
	        <table class="tbl-student">
	            <tr>
	                <th>학생번호</th>
	                <td>
	                    <input type="number" name="no" required readonly/>
	                </td>
	            </tr>
	            <tr>
	                <th>학생이름</th>
	                <td>
	                    <input type="text" name="name" required/>
	                </td>
	            </tr>
	            <tr>
	                <th>학생전화번호</th>
	                <td>
	                    <input type="tel" name="tel"  required/>
	                </td>
	            </tr>
	            <tr>
	                <td colspan="2">
	                    <input type="button" value="수정" onclick="updateStudent();"/>
	                    <input type="button" value="삭제" onclick="deleteStudent();" /> <!-- 현재 페이지 reload처리하기 -->
	                </td>
	            </tr>
	        </table>
       </form>
	</div>
<script>
document.studentSearchFrm.addEventListener('submit', (e) => {
	e.preventDefault();
	
	const no = e.target.no.value;
	
	$.ajax({
		url : "${pageContext.request.contextPath}/student/student.do",
		data : {no},
		success(student) {
			const frm = document.studentUpdateFrm;
			if(student) {
				const {no, name, tel} = student;
				frm.no.value = no;
				frm.name.value = name;
				frm.tel.value = tel;
			} else {
				alert("조회된 학생이 없습니다.");
				frm.reset();
			}
		},
		error : console.log
	});
});
const updateStudent = (e) => {
	const frm = document.studentUpdateFrm;
	const no = frm.no.value;
	const name = frm.name.value;
	const tel = frm.tel.value;
	console.log({no});
	console.log({name});
	console.log({tel});
	$.ajax({
		url : "${pageContext.request.contextPath}/student/updateStudent.do",
		data : {no, name, tel},
		method : "POST",
		success(response) {
			alert("회원정보가 수정되었습니다.");
		},
		error : console.log
	});
};

const deleteStudent = (e) => {
	const no = document.studentUpdateFrm.no.value;
	
	if(confirm("해당 학생정보를 정말로 삭제하시겠습니까?")) {
		$.ajax({
			url : "${pageContext.request.contextPath}/student/deleteStudent.do",
			data : {no},
			method : "POST",
			success(response) {
				alert(`\${no}번 학생정보가 삭제 되었습니다.`);
				location.reload();
			},
			error : console.log
		});
	}
};
</script>
</body>
</html>
