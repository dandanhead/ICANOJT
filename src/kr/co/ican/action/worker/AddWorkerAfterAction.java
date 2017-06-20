package kr.co.ican.action.worker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.ican.dbconn.GetDBConn;
import kr.co.ican.services.WorkerServiceImpl;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;

@WebServlet("/adWorkerAfter")
public class AddWorkerAfterAction extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
	private WorkerServiceImpl workerservice = WorkerServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addWorkersInfo(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addWorkersInfo(req, resp);
	}
	
	
	private void addWorkersInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		
		//vo 생성
		MemberVO mvo = new MemberVO(); // 사원 정보VO
		ExperienceVO evo = new ExperienceVO(); // 사원 경력 VO
		MemLicenseVO mlvo = new MemLicenseVO(); //사원 자격증 VO
		// 기본사항 
		String im_pw = request.getParameter("im_pw"); // 패스워드
		String im_name = request.getParameter("im_name"); // 이름
		String im_scnum = request.getParameter("im_scnum"); // 주민등록번호
		String im_phone = request.getParameter("im_phone"); //전화번호
		String im_email = request.getParameter("im_email"); // 이메일
		String im_postcode = request.getParameter("im_postcode");
		String im_address = request.getParameter("im_address"); // 집주소
		String im_detailaddr = request.getParameter("im_detailaddr");
		String im_dname = request.getParameter("im_dname"); //부서 코드 
		String im_auth = request.getParameter("im_auth"); // 직급 //int 변환
		String im_skill = request.getParameter("im_skill"); // 스킬
		String outsideperson = request.getParameter("outsideperson"); //타업체 인력
		// 경력사항
		String arrregi[] = request.getParameterValues("ime_regi_date");
		String arrexit[] = request.getParameterValues("ime_exit_date");
		String arrconame[] = request.getParameterValues("ime_coname");
		String arrauth[] = request.getParameterValues("ime_auth");
		String arrroll[] = request.getParameterValues("ime_roll");
		// 자격증
		String arrlicname[] = request.getParameterValues("iml_lname");
		String arracqdate[] = request.getParameterValues("iml_acudate");
		String arrorg[] = request.getParameterValues("iml_organization");
		//vo setting
		mvo.setOutsideperson(outsideperson);
		mvo.setIm_pw(im_pw);
		mvo.setIm_name(im_name);
		mvo.setIm_scnum(im_scnum);
		mvo.setIm_phone(im_phone);
		mvo.setIm_email(im_email);
		mvo.setIm_address(im_address);
		mvo.setIm_detailaddr(im_detailaddr);
		mvo.setIm_postcode(im_postcode);
		mvo.setIm_dname(im_dname);
		mvo.setIm_auth(Integer.parseInt(im_auth));
		mvo.setIm_skill(im_skill);
		
		//workerservice////////////////////////////////////
		//insert
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        //checker
		boolean infoFlag = false, licFlag = false, basicFlag = false, expFlag = false; 
        //url
		String url = "";
        
		try {
			
			conn = GetDBConn.getConnection();
			// transaction start
			conn.setAutoCommit(false);
			
			//1 . 기본 정보 추가
			infoFlag = workerservice.addWorkerInfo(mvo, conn, psmt, rs);
			
			//2.  자격증 집어넣기
			if(arrlicname != null && arracqdate != null && arrorg != null){
				for (int idx = 0; idx < arrlicname.length; idx++) {
					
					String iml_lname = arrlicname[idx].trim();
					String iml_acqdate = arracqdate[idx].trim();
					String iml_organization = arrorg[idx].trim();
					mlvo.setIml_lname(iml_lname);
					mlvo.setIml_acqdate(iml_acqdate);
					mlvo.setIml_organization(iml_organization);
					
					licFlag = workerservice.addWorkerLicense(mlvo , conn, psmt, rs);
					if(!licFlag){
						break;
					}
				}
			}else{
				licFlag = true;
			}
			//3. 현재 회사 경력 추가 하기 
			basicFlag = workerservice.basicWorkerExp(mvo, conn, psmt, rs);
			//4 .경력 사항에 따른 처리 
			if(arrregi != null && arrexit != null && arrconame != null && arrauth != null && arrroll != null){
				
				for (int idx = 0; idx < arrregi.length; idx++) {
					
					evo.setIme_regi_date(arrregi[idx]);
					evo.setIme_exit_date(arrexit[idx]);
					evo.setIme_coname(arrconame[idx]);
					evo.setIme_auth(Integer.parseInt(arrauth[idx].trim()));
					evo.setIme_roll(arrroll[idx]);
					
					expFlag =workerservice.addWorkerExp(evo , conn, psmt, rs);
					
					if(!expFlag){
						break;
					}
				}
			}else{
				
				expFlag = true;
			}
			
			// url chk
			if(infoFlag && licFlag &&  basicFlag && expFlag){
				url = "goWorker.do";
				conn.commit();
			}else{
				url = "goError.do";
				conn.rollback();
			}
			
			GetDBConn.close(conn, psmt, rs);
			
		}catch(SQLException e){
			
			url = "goError.do";
			e.printStackTrace();
		}
		
		response.sendRedirect(url);
	}

}
