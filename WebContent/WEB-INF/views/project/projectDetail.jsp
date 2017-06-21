<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value ="utf-8"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--bootstrap  -->
<jsp:include page="../layout/bootstrap.jsp"/> 
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../login/doLogout.jsp"></jsp:include> <!--로그아웃체크  -->
<jsp:include page="../layout/header.jsp"/> <!--header  -->
<div id="notice" class="layer">
	<hr>
	<div id="pagetitle" style="width: 20%;">
		<h4><b>Project Management</b></h4>
	</div>
	<hr> <!-- 공통 css -->
</div>
<div class="layer">
	<div class="contentsDetail" style="width: 60%; margin: auto;">
		<div>
			<label>Project Name</label>
			<input type="text" class="form-control" readonly="readonly" value="${pvo.ipl_pname}">
		</div>
		<div>
			<label>Client</label>
			<input type="text" class="form-control" readonly="readonly" value="${pvo.ipl_client}">
		</div>
		<div>
			<label>Language AND Environment</label>
			<input type="text" class="form-control" readonly="readonly" value="${pvo.ipl_skill }">
		</div>
		<div>
			<label>Content</label>
			<textarea rows="3"  class="form-control" style="resize: none;" readonly="readonly">${pvo.ipl_content}</textarea>
		</div>
		<div>
			<label>Location</label>
			<input type="text" class="form-control"  readonly="readonly" value="(${pvo.ipl_postcode})${pvo.ipl_address}${pvo.ipl_detailaddr}">
		</div>
		<div>
			<label>Charge</label>
			<input type="text" class="form-control"  readonly="readonly" value="${pvo.ipl_charge}">
		</div>
		<div>
			<label>Start Date</label>
			<input type="text" class="form-control" readonly="readonly" value="${fn:substring(pvo.ipl_sdate, 0 , 10)}" style="width: 20%;">
		</div>
		<div>
			<label>Expect End Date</label>
			<input type="text" class="form-control" readonly="readonly" value="${fn:substring(pvo.ipl_eptdate, 0 , 10)}" style="width: 20%;">
		</div>
		<div>
			<label>End Date</label>
			<input type="text" class="form-control" readonly="readonly" value="${fn:substring(pvo.ipl_edate, 0 , 10)}" style="width: 20%;">
		</div>
		<div>
			<label>Document</label>
			<input type="button" onclick="fileDowns('${pvo.ipl_idx}','${pvo.ipl_doc}')" value="${pvo.ipl_doc}">
		</div>
		<br>
		<div >
			<a class="btn btn-default" href="projectManageFix.jsp" style="width: 40%;">Fix</a>
			<a class="btn btn-default" href="projectManage.jsp" style="width: 40%;">Return</a>
		</div>
	</div>
	<div class="contentsDetail" style="margin-left: 50px;">
		<div>
			<label>Members</label>
		</div>
		<table class="table table-striped">
			<thead>
			<tr>
				<th>No</th>
				<th>Department</th>
				<th>Name</th>
				<th>Phone</th>
				<th>E-Mail</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td>0432</td>
					<td>SI사업부</td>
					<td>김영란</td>
					<td>010-0000-0000</td>
					<td>daum@naver.com&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;" class="delmem"></td>
				</tr>
				<tr>
					<td>0432</td>
					<td>SI사업부</td>
					<td>김영란</td>
					<td>010-0000-0000</td>
					<td>daum@naver.com&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;" class="delmem"></td>
				</tr>
				<tr>
					<td>0432</td>
					<td>SI사업부</td>
					<td>김영란</td>
					<td>010-0000-0000</td>
					<td>daum@naver.com&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;" class="delmem"></td>
				</tr>
				<tr>
					<td>0432</td>
					<td>SI사업부</td>
					<td>김영란</td>
					<td>010-0000-0000</td>
					<td>daum@naver.com&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;" class="delmem"></td>
				</tr>
				<tr>
					<td>0432</td>
					<td>SI사업부</td>
					<td>김영란</td>
					<td>010-0000-0000</td>
					<td>daum@naver.com&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;" class="delmem"></td>
				</tr>
				<tr>
					<td>0432</td>
					<td>SI사업부</td>
					<td>김영란</td>
					<td>010-0000-0000</td>
					<td>daum@naver.com&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;" class="delmem"></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5" style="text-align: right;">
						<button type="button" class="btn btn-default" id="addMember">Add Member</button>
					<td>
				</tr>
			</tfoot>
		</table>
		<nav style="text-align: center;">
		  <ul class="pagination">
		    <li>
		      <a href="#" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <li><a href="#">1</a></li>
		    <li><a href="#">2</a></li>
		    <li><a href="#">3</a></li>
		    <li><a href="#">4</a></li>
		    <li><a href="#">5</a></li>
		    <li><a href="#">6</a></li>
		    <li><a href="#">7</a></li>
		    <li><a href="#">8</a></li>
		    <li><a href="#">9</a></li>
		    <li><a href="#">10</a></li>
		    <li>
		      <a href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
	</div>
</div>
<form name="filedownForm" action="filedown" method="post">
	<input type="hidden" name="filename"  value=""/>
	<input type="hidden" name="idx"  value=""/>
</form>
</body>
<script type="text/javascript">
	$(".delmem").click(function() {
		if(confirm("해당 member를 제외 하시겠습니까?")){
			alert("해당 member 삭제!");
		}
	})
	$("#addMember").click(function() {
		var url = "addProjectMember.jsp";  
      	window.open(url, "_blank", "width = 1024, height = 860, left = 200, top = 100, status = no, scrollbars = yes");
	})
	$("#logout").click(function() {
			if(confirm("로그 아웃 하시겠습니까?")){
				location.href="logout.do";
			}
	});
	
	function fileDowns(idx, filename) {
		alert(filename);
		alert(idx);
		var doc = document.filedownForm;
		doc.filename.value = filename;
		doc.idx.value = idx;
		doc.submit();
	}
</script>
</html>