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
<jsp:include page="../layout/header.jsp"/> <!--header  -->
<div id="notice" class="layer">
	<hr>
	<div id="pagetitle" style="width: 20%;">
		<h4><b>Workers Management</b></h4>
	</div>
	<hr> <!-- 공통 css -->
</div>
<div class="layer">
	<div class="contentsDetail">
		<div>
			<label>Project Name</label>
			<input type="text" class="form-control" readonly="readonly" value="Amore">
		</div>
		<div>
			<label>Client</label>
			<input type="text" class="form-control" readonly="readonly" value="AmorePercipic">
		</div>
		<div>
			<label>Language AND Environment</label>
				<textarea rows="3" cols="100%;" class="form-control" style="resize: none;" readonly="readonly">JAVA, jQuery, Spring, Ajax, Mybaits </textarea>
		</div>
		<div>
			<label>Content</label>
			<textarea rows="3"  class="form-control" style="resize: none;" readonly="readonly">Amore Project in Seoul</textarea>
		</div>
		<div>
			<label>Location</label>
			<input type="text" class="form-control"  readonly="readonly" value="Seoul">
		</div>
		<div>
			<label>Charge</label>
			<input type="text" class="form-control"  readonly="readonly" value="Kim gil-dong">
		</div>
		<div>
			<label>Start Date</label>
			<div class="row">
			  <div class="col-xs-3">
			  	<input type="text" class="form-control" readonly="readonly" value="2017">
			  </div>
			  <div class="col-xs-3">
			    <input type="text" class="form-control" readonly="readonly" value="04">
			  </div>
			  <div class="col-xs-3">
			   <input type="text" class="form-control" readonly="readonly" value="01">
			  </div>
			</div>
		</div>
		<div>
			<label>Expect End Date</label>
			<div class="row">
			  <div class="col-xs-3">
				<input type="text" class="form-control" readonly="readonly" value="2017">
			  </div>
			  <div class="col-xs-3">
			    <input type="text" class="form-control" readonly="readonly" value="08">
			  </div>
			  <div class="col-xs-3">
			  	<input type="text" class="form-control" readonly="readonly" value="01">
			  </div>
			</div>
		</div>
		<div>
			<label>End Date</label>
			<div class="row">
			  <div class="col-xs-3">
			    <input type="text" class="form-control" readonly="readonly" value="-">
			  </div>
			  <div class="col-xs-3">
			  	<input type="text" class="form-control" readonly="readonly" value="-">
			  </div>
			  <div class="col-xs-3">
			  	<input type="text" class="form-control" readonly="readonly" value="-">
			  </div>
			</div>
		</div>
		<div>
			<label>Document</label>
			<input type="text" class="form-control" value="fileName" readonly="readonly">
			<button type="button" class="btn btn-default">Download</button>
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
</script>
</html>