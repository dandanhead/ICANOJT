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
		<h2><b>Information</b></h2>
		<br>
		<form id="frm" method="post" enctype="multipart/form-data" >
			<div class="addform" style="float: left;">
				<div>
					<h5><strong style="color: red;">* 항목은 필수 입력 입니다.</strong></h5>
					<label>* Project Name</label>
					<input type="text" class="form-control" style="width: 40%;" name="ipl_pname" id="pname">
				</div>
				<br>
				<div>
					<label>* Client</label>
					<input type="text" class="form-control" style="width: 40%;" name="ipl_client" id="clientname">
				</div>
				<br>
				<div>
					<label>* Language AND Environment</label>
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
					<textarea rows="3" cols="100%;" class="form-control" style="resize: none; width: 40%" name="ipl_content" id="prjtcontent"></textarea>
				</div>
				<br>
				<div>
					<label>* Location</label>
					<br>
					<input type="text" style="width: 15%;" readonly="readonly" placeholder="PostCode" id="postcode" name="ipl_postcode">
					<a href="#none" id="findaddr" class="btn btn-default" style="width: 20%;">주소 찾기</a>
					<input type="text" class="form-control" style="width: 40%;" id="tempaddress" readonly="readonly" name="ipl_address">
					<input type="text" class="form-control" style="width: 40%;" id="detailaddress" placeholder="상세 주소" name="ipl_detailaddr">
				</div>
				<br>
				<div>
					<label>* Charge</label>
					<input type="text" class="form-control" style="width: 40%;" id="charger" name="ipl_charge">
				</div>
				<br>
				<div>
					<label>Number of Required Persons(Number Only)</label>
					<input type="text" class="form-control" style="width: 40%;"
					 onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' style='ime-mode:disabled;' id="reqnum" name="ipl_req_num">
				</div>
				<br>
				<h2><b>Date</b></h2>
				<br>
				<div>
					<label>* Start Date</label>
					<input type="text" class="form-control" id="startdate" style="width: 40%;" readonly="readonly" name="ipl_sdate">
				</div>
				<br>
				<div>
					<label>Expect End Date</label>
					<input type="text" class="form-control" id="expectdate" style="width: 40%;" readonly="readonly" name="ipl_exptdate">
				</div>
				<br>
				<h2><b>DOC</b></h2>
				<br>
				<div>
					<label>Document</label>
					<input type="file" class="form-control" style="width: 40%;" style="width: 40%;" name="ipl_doc" id="projectdoc">
				</div>
				<br>
				
				<div >
					<a class="btn btn-default" href="#none" style="width: 20%;" id="addProjectBtn">Submit</a>
					<a class="btn btn-default" href="goProject.do" style="width: 20%;">Return</a>
				</div>
			</div>
		</form>
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
});

//logout
$("#logout").click(function() {
		if(confirm("로그 아웃 하시겠습니까?")){
			location.href="logout.do";
		}
});

//주소 API(daum)
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

//숫자만 입력받기
function onlyNumber(event){
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
		return;
	else
		return false;
};
//숫자가아닌 문자 지우기, 또는 입력방지
function removeChar(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
		return;
	else
		event.target.value = event.target.value.replace(/[^0-9]/g, "");
};

$("#addProjectBtn").click(function() {
	//validation
	var chknum = 0; //체크박스 갯수 확인
	var pname = $.trim($("#pname").val()); // 프로젝트명
	var clientname = $.trim($("#clientname").val()); // 고객사
	var postcode = $.trim($("#postcode").val()); //우편번호 
	var tempaddress = $.trim($("#tempaddress").val()); //본 주소
	var detailaddress = $.trim($("#detailaddress").val()); // 상세주소
	var charger = $.trim($("#charger").val()); // charge
	var startdate = $.trim($("#startdate").val()); //start date
	//////////////////
	// null 허용 value
	var prjtcontent = $.trim($("#prjtcontent").val()); //상세내용
	var reqnum = $.trim($("#reqnum").val()); // 허용인원
	var expectdate = $.trim($("#expectdate").val()); // expect end date
	var projectdoc = $.trim($("#projectdoc").val());
	
	// 체크박스 개수 확인
 	$("input:checkbox[name='chklang']:checked").each(function() {
		chknum++;
	});
	//파일
	if(projectdoc == "" || projectdoc == null){
		alert("파일 선택 안함");
	}else{
		alert(projectdoc);
	}
	//call controller
/* 	$("#frm").attr({"target" : "_self" , "action" : "addProjectAf"}).submit(); */
});
</script>
</html>