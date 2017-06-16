<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--bootstrap  -->
<jsp:include page="../layout/bootstrap.jsp"/> 
<!--tool tip  -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="top" class="layer"><img alt="no img" src="img/top.png" style="width: 100%;">
</div>
<form id="frm">
	<div id="notice" class="layer">
		<hr>
		<div id="pagetitle" style="width: 20%;">
			<h4><b>Workers Management</b></h4>
		</div>
		<hr> <!-- 공통 css -->
		<table style="width: 100%; margin: auto;" class="table table-striped">
			<thead>
				<tr>
					<td>
						<select class="form-control">
							<option selected="selected">전체</option>
							<option>Available</option>
							<option>Not Available</option>
						</select>
					</td>
				</tr>
				<tr style="background-color: #aaa">
					<td>No.</td>
					<td>Name</td>
					<td>Department</td>
					<td>Position</td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
			<colgroup>
				<col width="10%;">
				<col width="15%;">
				<col width="15%;">
				<col width="15%;">
				<col width="15%;">
				<col width="15%;">
				<col width="15%;">
			</colgroup>
				<tr>
					<td>0001</td>
					<td>
						<a href="workerDetail.jsp" title="Java, jQuery, JSTL, EL , Ajax">일지매</a>
					</td>
					<td>대표이사</td>
					<td>대표이사</td>
					<td>
						<select class="form-control">
							<option selected="selected">Manager</option>
							<option>Developer</option>
						</select>
					</td>
					<td><a href="#none" class="btn btn-default">Add</a></td>
				</tr>
				<tr>
					<td>0002</td>
					<td><a href="workerDetail.jsp" title="Spring, 전자정부 프레임워크, 자바, 제이쿼리, 제이에스티엘, 파이썬, R, 루비, 마이플랫폼">이지매</a></td>
					<td>전무이사</td>
					<td>전무이사</td>
					<td>
						<select class="form-control">
							<option selected="selected">Manager</option>
							<option>Developer</option>
						</select>
					</td>
					<td><a href="#none" class="btn btn-default">Add</a></td>
				</tr>
				<tr>
					<td>0003</td>
					<td><a href="workerDetail.jsp">삼지매</a></td>
					<td>SI사업부</td>
					<td>사원</td>
					<td>
						<select class="form-control">
							<option selected="selected">Manager</option>
							<option>Developer</option>
						</select>
					</td>
					<td><a href="#none" class="btn btn-default">Add</a></td>
				</tr>
				<tr>
					<td>0004</td>
					<td><a href="workerDetail.jsp">사지매</a></td>
					<td>SI사업부</td>
					<td>사원</td>
					<td>
						<select class="form-control">
							<option selected="selected">Manager</option>
							<option>Developer</option>
						</select>
					</td>
					<td><a href="#none" class="btn btn-default">Add</a></td>
				</tr>
				<tr>
					<td>0005</td>
					<td><a href="workerDetail.jsp">오지매</a></td>
					<td>SI사업부</td>
					<td>사원</td>
					<td>
						<select class="form-control">
							<option selected="selected">Manager</option>
							<option>Developer</option>
						</select>
					</td>
					<td><a href="#none" class="btn btn-default">Add</a></td>
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
		      <a href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
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
					<option selected="selected">Name</option>
					<option>Department</option>
					<option>No.</option>
					<option>License</option>
				</select>
			</td>
			<td>
				<input type="text" class="form-control">
			</td>
			<td>
				<a class="btn btn-default">Search</a>
			</td>
		</tr>
	</table>
	<br>
	<div class="layer" style="float: left;">
		<label>Added Member</label>
		<br>
		<p>
			<a>육지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp; 
			<a>칠지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a>팔지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a>구지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a>십지매&nbsp;&nbsp;<img alt="no img" src="img/xicon.png" style="width: 15px; height: 15px;"></a>&nbsp;&nbsp;&nbsp;&nbsp;	
		</p>
		<div>
			<a href="#none" class="btn btn-default" style="width: 30%;">추가 하기</a>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$( function() {
    $( document ).tooltip();
  } );
</script>
</html>