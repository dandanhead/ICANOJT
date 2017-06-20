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

@WebServlet("/updateWorker")
public class WorkerUpdateAfterAction extends HttpServlet{

	private WorkerServiceImpl workerservice = WorkerServiceImpl.getInstance();
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		updateWorkerInfo(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		updateWorkerInfo(req, resp);
	}
	
	private void updateWorkerInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		
		//vo 생성
		MemberVO mvo = new MemberVO(); // 사원 정보VO
		ExperienceVO evo = new ExperienceVO(); // 사원 경력 VO
		MemLicenseVO mlvo = new MemLicenseVO(); //사원 자격증 VO
		
		// 기본사항 
		String im_idx = request.getParameter("im_idx"); // 사번
		int memidx = Integer.parseInt(im_idx);
		String im_pw = request.getParameter("im_pw"); // 패스워드
		String im_name = request.getParameter("im_name"); // 이름
		String im_scnum = request.getParameter("im_scnum"); // 주민등록번호
		String im_phone = request.getParameter("im_phone"); //전화번호
		String im_email = request.getParameter("im_email"); // 이메일
		String im_postcode = request.getParameter("im_postcode");
		String im_address = request.getParameter("im_address"); // 집주소
		String im_skill = request.getParameter("im_skill"); //스킬
		String im_detailaddr = request.getParameter("im_detailaddr");
		String im_dname = request.getParameter("im_dname"); //부서 코드 
		String im_auth = request.getParameter("im_auth"); // 직급 //int 변환
		String outsideperson = request.getParameter("outsideperson");
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
		mvo.setIm_idx(memidx);
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
		
		mlvo.setIml_im_idx(memidx);
		evo.setIme_im_idx(memidx);
		
		//workerservice////////////////////////////////////
		//insert
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        //flag
        boolean infoFlag = false;
        boolean expFlag = false;
        boolean expdel = false; 
        boolean licdel = false; 
        boolean licFlag = false;
        
        //url
		String url = "";
        
        try {
			conn = GetDBConn.getConnection();
			// transaction start
			conn.setAutoCommit(false);
			//1 . 기본 정보 추가
			infoFlag = workerservice.updateWorkerInfo(mvo, conn, psmt, rs);
			//2. 자격증 집어넣기
			//2-1 자격증 다 지우기
			boolean exsitLicense = workerservice.chkExistLicence(mlvo);
			if(exsitLicense){
				licdel = workerservice.preupdateWorkerLicense(mlvo, conn, psmt, rs);
			}else{
				licdel = true;
			}
			//2-2 자격증 인서트 하기
			if(arrlicname != null && arracqdate != null && arrorg != null){
				for (int idx = 0; idx < arrlicname.length; idx++) {
					
					String iml_lname = arrlicname[idx].trim();
					String iml_acqdate = arracqdate[idx].trim();
					String iml_organization = arrorg[idx].trim();
					
					String acqdate = iml_acqdate.substring(0, 10);
					
					mlvo.setIml_lname(iml_lname);
					mlvo.setIml_acqdate(acqdate);
					mlvo.setIml_organization(iml_organization);
					
					licFlag = workerservice.updateWorkerLicense(mlvo , conn, psmt, rs);
					if(!licFlag){
						break;
					}
				}
			}else{
				licFlag = true;
			}
			// 3. 현재 회사 경력 추가 하기
			// 3-1. 경력 다 지우기
			boolean chkExp = workerservice.chkExistExp(evo);
			// 3-2. 경력 insert
			if(chkExp){
				expdel = workerservice.preupdateWorkerExp(evo, conn, psmt, rs); 
			}else{
				expdel = true;
			}
			
			if(arrregi != null && arrexit != null && arrconame != null && arrauth != null && arrroll != null){
				for (int idx = 0; idx < arrregi.length; idx++) {
					
					String subregi = arrregi[idx].substring(0,10);
					String subexit = arrexit[idx].substring(0, 10);
					
					evo.setIme_regi_date(subregi);
					evo.setIme_exit_date(subexit);
					evo.setIme_coname(arrconame[idx]);
					evo.setIme_auth(Integer.parseInt(arrauth[idx].trim()));
					evo.setIme_roll(arrroll[idx]);
					
					expFlag =workerservice.updateWorkerExp(evo, conn, psmt, rs); 
					if(!expFlag || !expdel){
						break;
					}
				}
			}else{
				expFlag = true;
			}
			
			//url
			System.out.println("infoFlag = " + infoFlag + " , expFlag = " + expFlag + " , expdel = " + expdel + " , licdel = " + licdel + " , licFlag = " + licFlag);
			
			if(!infoFlag || !expFlag || !expdel || !licdel || !licFlag){
				url = "goError.do";
				conn.rollback();
			}else{
				url = "goWorker.do";
				conn.commit();
			}
			
			GetDBConn.close(conn, psmt, rs);
						
		} catch (SQLException e) {
			url = "goError.do";
			e.printStackTrace();
		}
        
        response.sendRedirect(url);
		
	}
}
