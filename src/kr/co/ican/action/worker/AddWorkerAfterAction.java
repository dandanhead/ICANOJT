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

import kr.co.ican.dao.WorkerDAO;
import kr.co.ican.dbconn.GetDBConn;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemSkillVO;
import kr.co.ican.vo.MemberVO;

@WebServlet("/adWorkerAfter")
public class AddWorkerAfterAction extends HttpServlet {
		
	private static final long serialVersionUID = 1L;

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
		WorkerDAO wdao = WorkerDAO.getInstance(); // 생성
		//vo 생성
		MemberVO mvo = new MemberVO(); // 사원 정보VO
		ExperienceVO evo = new ExperienceVO(); // 사원 경력 VO
		MemSkillVO msvo = new MemSkillVO(); // 사원 스킬 VO
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
		String chklang[] = request.getParameterValues("chklang"); // 사용가능한 언어 체크박스 배열 받기
		String chklicense[] = request.getParameterValues("chklicense");
		String im_dname = request.getParameter("im_dname"); //부서 코드 
		String im_auth = request.getParameter("im_auth"); // 직급 //int 변환
		String outsideperson = request.getParameter("outsideperson");
		// 경력사항
		String arrregi[] = request.getParameterValues("ime_regi_date");
		String arrexit[] = request.getParameterValues("ime_exit_date");
		String arrconame[] = request.getParameterValues("ime_coname");
		String arrauth[] = request.getParameterValues("ime_auth");
		String arrroll[] = request.getParameterValues("ime_roll");
		
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
		
		//DAO////////////////////////////////////
		//insert
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        int liccnt = 0, expcnt = 0;
        //checker
		boolean infoFlag = false, skillFlag = false, licFlag = false, basicFlag = false, expFlag = false; 
        //url
		String url = "";
        
		try {
			
			conn = GetDBConn.getConnection();
			// transaction start
			conn.setAutoCommit(false);
			
			//1 . 기본 정보 추가
			infoFlag = wdao.addWorkerInfo(mvo, conn, psmt, rs);
			
			//2. 스킬 집어넣기
			for (int idx = 0; idx < chklang.length; idx++) {
				
				String ims_is_sname = chklang[idx].trim();
				msvo.setIms_is_sname(ims_is_sname);
				skillFlag = wdao.addWorkerSkill(msvo,conn, psmt, rs);
				
				if(!skillFlag){
					break;
				}
			}
			//3. 자격증 집어넣기
			if(chklicense != null && chklicense.length > 0 ){
				liccnt++;
				for (int idx = 0; idx < chklicense.length; idx++) {
					
					String iml_lname = chklicense[idx].trim();
					mlvo.setIml_lname(iml_lname);
					
					licFlag = wdao.addWorkerLicense(mlvo , conn, psmt, rs);
					if(!licFlag){
						break;
					}
				}
			}
			// 4. 현재 회사 경력 추가 하기 
			// 기본 경력 추가
			basicFlag = wdao.basicWorkerExp(mvo, conn, psmt, rs);
			//4-1 .경력 사항에 따른 처리 (없으면 아이캔 자동생성, 있으면 입력받은거 + 아이캔 자동생성) 
			if(arrregi != null && arrexit != null && arrconame != null && arrauth != null && arrroll != null){
				
				expcnt++;
				
				for (int idx = 0; idx < arrregi.length; idx++) {
					
					evo.setIme_regi_date(arrregi[idx]);
					evo.setIme_exit_date(arrexit[idx]);
					evo.setIme_coname(arrconame[idx]);
					evo.setIme_auth(Integer.parseInt(arrauth[idx].trim()));
					evo.setIme_roll(arrroll[idx]);
					
					expFlag =wdao.addWorkerExp(evo , conn, psmt, rs);
					
					if(!expFlag){
						break;
					}
						
				}
			}
			
			// url chk
			if(liccnt != 0 && expcnt != 0){
				if(infoFlag && skillFlag && licFlag && basicFlag && expFlag){
					
					url = "goWorker.do";
					conn.commit();
					
				}else{
					url = "goError.do";
					conn.rollback();
				}
			}else if(liccnt == 0 && expcnt != 0){
				if(infoFlag && skillFlag && basicFlag && expFlag){
					
					url = "goWorker.do";
					conn.commit();
					
				}else{
					url = "goError.do";
					conn.rollback();
				}
			}else if(liccnt != 0 && expcnt == 0){
				if(infoFlag && skillFlag && licFlag && basicFlag){
					url = "goWorker.do";
					conn.commit();
				}else{
					url = "goError.do";
					conn.rollback();
				}
			}else{
				if(infoFlag && skillFlag && basicFlag){
					url = "goWorker.do";
					conn.commit();
				}else{
					url = "goError.do";
					conn.rollback();
				}
			}
			
			GetDBConn.close(conn, psmt, rs);
			
		}catch(SQLException e){
			
			url = "goError.do";
			e.printStackTrace();
		}
		
		response.sendRedirect(url);
	}

}
