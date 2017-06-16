<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<jsp:include page="../layout/header.jsp"/> <!--header  -->>
<form id="frm">
<div class="layer">
	<hr>
	<div id="pagetitle" style="width: 20%;">
		<h4><b>Project Management</b></h4>
	</div>
	<hr> <!-- 공통 css -->
<table style="width: 100%; margin: auto;" class="table table-striped">
			<thead>
				<tr>
					<td>
						<select class="form-control">
							<option selected="selected">전체</option>
							<option>Done</option>
							<option>Undone</option>
							<option>Deleted</option>
						</select>
					</td>
				</tr>
				<tr style="background-color: #aaa">
					<td>No.</td>
					<td>Project Name(sorting▼)</td>
					<td>Start Date(sorting▼)</td>
					<td>Expect End Date(sorting▼)</td>
					<td>End Date(sorting▼)</td>
				</tr>
			</thead>
			<tbody>
			<colgroup>
				<col width="10%;">
				<col width="45%;">
				<col width="15%">
				<col width="15%">
				<col width="15%">
			</colgroup>
				<tr>
					<td>0010</td>
					<td><a href="projectDetail.jsp">아모레 퍼시픽 Project</a></td>
					<td>2017.01.01</td>
					<td>2017.06.01</td>
					<td>-</td>
				</tr>
			</tbody>
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
		      <a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
	</div>
</form>
<div id="selectDetail" class="layer">
	<table style="width: 100%;">
		<colgroup>
			<col width="10%;">
			<col width="15%;">
			<col width="10%;">
			<col width="auto;">
		</colgroup>
		<tr>
			<td>
				<select class="form-control">
					<option selected="selected">Project Name</option>
					<option>Client</option>
				</select>
			</td>
			<td>
				<input type="text" class="form-control">
			</td>
			<td>
				<a class="btn btn-default">Search</a>
			</td>
			<td style="float: right;">
				<a class="btn btn-default" href="goAddProject.do" >add Project</a>
			</td>
		</tr>
	</table>
</div>
</body>
<script type="text/javascript">
	$("#logout").click(function() {
			if(confirm("로그 아웃 하시겠습니까?")){
				location.href="logout.do";
			}
	});
</script>
</html>