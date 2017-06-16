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
			<h4><b>Project Management</b></h4>
		</div>
		<hr> <!-- 공통 css -->
	</div>
<div class="layer" style="margin-left: 30%;">
		<div class="addform" style="float: left;">
			<div>
				<label>Project Name</label>
				<input type="text" class="form-control" style="width: 40%;">
			</div>
			<div>
				<label>Client</label>
				<input type="text" class="form-control" style="width: 40%;">
			</div>
			<div>
				<label>Language AND Environment</label>
				<br>
				<input type="checkbox" value="Java" name="chklang" class="chklang">&nbsp;Java&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="jQuery" name="chklang" class="chklang">&nbsp;jQuery&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="JSP" name="chklang" class="chklang">&nbsp;JSP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="Ajax" name="chklang" class="chklang">&nbsp;Ajax&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="PHP" name="chklang" class="chklang">&nbsp;PHP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<input type="checkbox" value="CSS" name="chklang" class="chklang">&nbsp;CSS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="HTML" name="chklang" class="chklang">&nbsp;HTML&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="C" name="chklang" class="chklang">&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="C++" name="chklang" class="chklang">&nbsp;C++&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="C#" name="chklang" class="chklang">&nbsp;C#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<input type="checkbox" value="Ruby" name="chklang" class="chklang">&nbsp;Ruby&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="Object C" name="chklang" class="chklang">&nbsp;Object C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="Swift" name="chklang" class="chklang">&nbsp;Swift&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="Spring" name="chklang" class="chklang">&nbsp;Spring&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="Oracle" name="chklang" class="chklang">&nbsp;Oracle&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<input type="checkbox" value="MsSql" name="chklang" class="chklang">&nbsp;MsSql&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="MySql" name="chklang" class="chklang">&nbsp;MySql&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<div>
				<label>Contents</label>
				<textarea rows="3" cols="100%;" class="form-control" style="resize: none;"></textarea>
			</div>
			<div>
				<label>Location</label>
				<input type="text" class="form-control" style="width: 40%;">
			</div>
			<div>
				<label>Charge</label>
				<input type="text" class="form-control" style="width: 40%;">
			</div>
			<div>
				<label>Number of Required Persons</label>
				<input type="text" class="form-control" style="width: 40%;">
			</div>
			<div>
				<label>Start Date</label>
				<div class="row">
				  <div class="col-xs-3">
				    <select id="projectStartYear" class="form-control">
					</select>
				  </div>
				  <div class="col-xs-3">
				    <select id=projectStartMonth class="form-control">
					</select>
				  </div>
				  <div class="col-xs-3">
				   <select id="projectStartDate" class="form-control">
					</select>
				  </div>
				</div>
			</div>
			<div>
				<label>Expect End Date</label>
				<div class="row">
				  <div class="col-xs-3">
				    <select id="projectExpectYear" class="form-control">
					</select>
				  </div>
				  <div class="col-xs-3">
				    <select id=projectExpectMonth class="form-control">
					</select>
				  </div>
				  <div class="col-xs-3">
				   <select id="projectExpectDate" class="form-control">
					</select>
				  </div>
				</div>
			</div>
			<div>
				<label>End Date</label>
				<div class="row">
				  <div class="col-xs-3">
				    <select id="projectEndYear" class="form-control">
					</select>
				  </div>
				  <div class="col-xs-3">
				    <select id=projectEndMonth class="form-control">
					</select>
				  </div>
				  <div class="col-xs-3">
				   <select id="projectEndDate" class="form-control">
					</select>
				  </div>
				</div>
			</div>
			<div>
				<label>Document</label>
				<input type="file" class="form-control">
			</div>
			<div >
				<a class="btn btn-default" href="projectManage.jsp" style="width: 40%;">Submit</a>
				<a class="btn btn-default" href="projectManage.jsp" style="width: 40%;">Return</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	var date = new Date();
	var year = date.getFullYear();
	
	var selectValueSTY = document.getElementById("projectStartYear");
	var selectValueEPY = document.getElementById("projectExpectYear");
	var selectValueEDY = document.getElementById("projectEndYear");
	
	var optionIndex = 0;
	
	for(var i=year-100;i<=year+10;i++){

		selectValueSTY.add(new Option(i,i),optionIndex++);
		selectValueEPY.add(new Option(i,i),optionIndex++);
		selectValueEDY.add(new Option(i,i),optionIndex++);
	}
	
	optionIndex = 0;
	var selectValueSTM = document.getElementById("projectStartMonth");
	var selectValueEPM = document.getElementById("projectExpectMonth");
	var selectValueEDM = document.getElementById("projectEndMonth");
	for(var i=1;i<=12;i++){

			selectValueSTM.add(new Option(i,i),optionIndex++);
			selectValueEPM.add(new Option(i,i),optionIndex++);
			selectValueEDM.add(new Option(i,i),optionIndex++);
	}
	
	optionIndex = 0;	
	var selectValueSTD = document.getElementById("projectStartDate");
	var selectValueEPD = document.getElementById("projectExpectDate");
	var selectValueEDD = document.getElementById("projectEndDate");
	for(var i=1;i<=31;i++){

			selectValueSTD.add(new Option(i,i),optionIndex++);
			selectValueEPD.add(new Option(i,i),optionIndex++);
			selectValueEDD.add(new Option(i,i),optionIndex++);
	}

})

	$("#logout").click(function() {
			if(confirm("로그 아웃 하시겠습니까?")){
				location.href="logout.do";
			}
	});
</script>
</html>