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
	<div class="layer" style="margin-left: 30%;">
		<div class="addform" style="float: left;">
			<div>
				<label>ID</label>
				<input type="text" class="form-control" style="width: 40%;" readonly="readonly" value="inputYourID">
			</div>
			<div>
				<label>PW</label>
				<input type="password" class="form-control" style="width: 40%;" readonly="readonly" value="abcdefghi">
			</div>
			<div>
				<label>Name</label>
				<input type="text" class="form-control" style="width: 40%;" readonly="readonly" value="hong gil-dong">
			</div>
			<div>
				<label>Sex</label> 
				<div>
					<label class="radio-inline"> 
						<input type="radio"	name="sexradio" id="radiomale" value="male" checked="checked" disabled="disabled">
							M
					</label>
					<label class="radio-inline"> 
						<input type="radio" name="sexradio" id="radiofemale" value="female" disabled="disabled">
							F
					</label>
				</div>
			</div>
			<div>
				<label>Social Num</label>
				<div class="row">
				  <div class="col-xs-3">
				    <input type="text" class="form-control" placeholder="앞 자리" value="877777" readonly="readonly">
				  </div>
				  <div class="col-xs-3">
				    <input type="text" class="form-control" placeholder="뒷 자리" value="1111111" readonly="readonly">
				  </div>
				</div>
			</div>
			<div>
				<label>Phone</label>
				<div class="row">
				  <div class="col-xs-3">
				    <input type="text" class="form-control" value="010" readonly="readonly">
				  </div>
				  <div class="col-xs-3">
				    <input type="text" class="form-control" value="1111" readonly="readonly">
				  </div>
				  <div class="col-xs-3">
				    <input type="text" class="form-control" value="1010" readonly="readonly">
				  </div>
				</div>
			</div>
			<div>
				<label>E-mail</label>
				<input type="email" class="form-control" readonly="readonly" value="aaaa@naver.com">
			</div>
			<div>
				<label>Department</label>
				<select class="form-control" disabled="disabled">
						<option>대표이사</option>
						<option>전무 이사</option>
						<option>관리팀</option>
						<option selected="selected">SI사업부</option>
						<option>SO사업부</option>
						<option>SW사업부</option>
						<option>Freelancer</option>
				</select>
			</div>
			<div>
				<label>Authority</label>
				<select class="form-control" disabled="disabled">
						<option>Employee</option>
						<option selected="selected">Manager</option>
				</select>
			</div>
			<div>
				<label>License</label>
				<textarea rows="2" style="resize: none; width: 100%"  class="form-control" placeholder="Input your Licence" readonly="readonly"></textarea>
			</div>
			<div>
				<label>Language</label>
				<textarea rows="2" style="resize: none; width: 100%"  class="form-control" placeholder="input available Language & Environment" readonly="readonly"></textarea>
			</div>
			<div>
				<label>Profile Image</label>
				<input type="file" class="form-control">
			</div>
			<div >
				<a class="btn btn-default" href="mypageUpdate.jsp" style="width: 40%;">Fix</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
/* $(document).ready(function() {
	var date = new Date();
	var year = date.getFullYear();
	var selectValueY = document.getElementById("birthdayYear");
	var optionIndex = 0;
	
	for(var i=year-100;i<=year;i++){

		selectValueY.add(new Option(i,i),optionIndex++);                        
	}
	
	optionIndex = 0;
	var selectValueM = document.getElementById("birthdayMonth"); 
	for(var i=1;i<=12;i++){

			selectValueM.add(new Option(i,i),optionIndex++);
	}
	
	optionIndex = 0;	
	var selectValueD = document.getElementById("birthdayDay");
	for(var i=1;i<=31;i++){

			selectValueD.add(new Option(i,i),optionIndex++);
	}

}) */

	$("#logout").click(function() {
			if(confirm("로그 아웃 하시겠습니까?")){
				location.href="logout.do";
			}
	});
</script>
</html>