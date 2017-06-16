<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--bootstrap  -->
<jsp:include page="../layout/bootstrap.jsp"/>
<!-- Daum Adress API Lib  -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
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
			<br>
			<div>
				<label>Client</label>
				<input type="text" class="form-control" style="width: 40%;">
			</div>
			<br>
			<div>
				<label>Language AND Environment</label>
				<br>
				<table>
					<tr>
						<td><input type="checkbox" value="Java" name="chklang" class="chklang">&nbsp;Java&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="jQuery" name="chklang" class="chklang">&nbsp;jQuery&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="JSP" name="chklang" class="chklang">&nbsp;JSP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="Ajax" name="chklang" class="chklang">&nbsp;Ajax&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="PHP" name="chklang" class="chklang">&nbsp;PHP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td><input type="checkbox" value="CSS" name="chklang" class="chklang">&nbsp;CSS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="HTML" name="chklang" class="chklang">&nbsp;HTML&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="C" name="chklang" class="chklang">&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="C++" name="chklang" class="chklang">&nbsp;C++&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="C#" name="chklang" class="chklang">&nbsp;C#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td><input type="checkbox" value="Ruby" name="chklang" class="chklang">&nbsp;Ruby&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="Object C" name="chklang" class="chklang">&nbsp;Object C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="Swift" name="chklang" class="chklang">&nbsp;Swift&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="Spring" name="chklang" class="chklang">&nbsp;Spring&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="Oracle" name="chklang" class="chklang">&nbsp;Oracle&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td><input type="checkbox" value="MsSql" name="chklang" class="chklang">&nbsp;MsSql&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><input type="checkbox" value="MySql" name="chklang" class="chklang">&nbsp;MySql&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
				</table>
			</div>
			<br>
			<div>
				<label>Contents</label>
				<textarea rows="3" cols="100%;" class="form-control" style="resize: none; width: 40%"></textarea>
			</div>
			<br>
			<div>
				<label>Location</label>
				<br>
				<input type="text" style="width: 15%;" readonly="readonly" placeholder="PostCode" id="postcode">
				<a href="#none" id="findaddr" class="btn btn-default" style="width: 20%;">주소 찾기</a>
				<input type="text" class="form-control" style="width: 40%;" id="tempaddress" readonly="readonly">
				<input type="text" class="form-control" style="width: 40%;" id="detailaddress">
			</div>
			<br>
			<div>
				<label>Charge</label>
				<input type="text" class="form-control" style="width: 40%;">
			</div>
			<br>
			<div>
				<label>Number of Required Persons</label>
				<input type="text" class="form-control" style="width: 40%;">
			</div>
			<br>
			<div>
				<label>Start Date</label>
				<input type="text" class="form-control" id="startdate" style="width: 40%;" readonly="readonly">
			</div>
			<br>
			<div>
				<label>Expect End Date</label>
				<input type="text" class="form-control" id="expectdate" style="width: 40%;" readonly="readonly">
			</div>
			<br>
			<div>
				<label>End Date</label>
				<input type="text" class="form-control" id="enddate" style="width: 40%;" readonly="readonly">
			</div>
			<br>
			<div>
				<label>Document</label>
				<input type="file" class="form-control" style="width: 40%;" style="width: 40%;">
			</div>
			<br>
			<div >
				<a class="btn btn-default" href="projectManage.jsp" style="width: 20%;">Submit</a>
				<a class="btn btn-default" href="projectManage.jsp" style="width: 20%;">Return</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

//datepicker 설정
$(function() {
    $( "#startdate" ).datepicker({
    	dateFormat:'yy-mm-dd',
        changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달' 
    });
    $( "#expectdate" ).datepicker({
    	dateFormat:'yy-mm-dd',
        changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달' 
    });
    $( "#enddate" ).datepicker({
    	dateFormat:'yy-mm-dd',
        changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달' 
    });
});

//logout
$("#logout").click(function() {
		if(confirm("로그 아웃 하시겠습니까?")){
			location.href="logout.do";
		}
});

//주소 API
$("#findaddr").click(function() {
	new daum.Postcode({
        oncomplete: function(data) {
        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('tempaddress').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('detailaddress').focus();
        }
    }).open();
});

</script>
</html>