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

@WebServlet("/updateWorker")
public class WorkerUpdateAfterAction extends HttpServlet{

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
		WorkerDAO wdao = WorkerDAO.getInstance(); // 생성
		
		//vo 생성
		MemberVO mvo = new MemberVO(); // 사원 정보VO
		ExperienceVO evo = new ExperienceVO(); // 사원 경력 VO
		MemSkillVO msvo = new MemSkillVO(); // 사원 스킬 VO
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
		String im_detailaddr = request.getParameter("im_detailaddr");
		String chklang[] = request.getParameterValues("chklang"); // 사용가능한 스킬 체크박스 배열 받기
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
		// chekcer
		String changlic = request.getParameter("licflag");
		String changeskill = request.getParameter("skillflag");
		String changexp = request.getParameter("expflag");
		
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
		
		msvo.setIms_im_idx(memidx);
		mlvo.setIml_im_idx(memidx);
		evo.setIme_im_idx(memidx);
		
		//DAO////////////////////////////////////
		//insert
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        //flag
        boolean infoFlag = false;
        boolean expFlag = false;
        boolean expdel = false; 
        boolean licdel = false; 
        boolean skilldel =false; 
        boolean updskill = false; 
        boolean updlic = false;
        
        //url
		String url = "";
        
        try {
        	
			conn = GetDBConn.getConnection();
			// transaction start
			conn.setAutoCommit(false);
			//1 . 기본 정보 추가
			infoFlag = wdao.updateWorkerInfo(mvo, conn, psmt, rs);
			System.out.println("1.기본 정보 추가");
			//2. 스킬 집어넣기
			if("1".equals(changeskill)){
				// 스킬 지우고
				skilldel = wdao.preupdateWorkerSkill(msvo, conn, psmt, rs); ////////////// 쿼리 
				System.out.println("스킬이 바뀌었으니 스킬을 지우고");
				for (int idx = 0; idx < chklang.length; idx++) {
					// 스킬 체크 만큼 insert
					String ims_is_sname = chklang[idx].trim();
					msvo.setIms_is_sname(ims_is_sname);
					updskill = wdao.updateWorkerSkill(msvo, conn, psmt, rs); ////////////////// 쿼리 
					System.out.println("새로운 스킬을 insert 반복!");
					if(!updskill || !skilldel){
						break;
					}
				}
			}else{
				skilldel = true;
				updskill = true;
			}
			//3. 자격증 집어넣기
			if("1".equals(changlic)){
				//자격증 있는지 체크
				boolean exsitLicense = wdao.chkExistLicence(mlvo);
				
				if(exsitLicense){
					licdel = wdao.preupdateWorkerLicense(mlvo, conn, psmt, rs); //// 자격증 지우기 쿼리 //////////////////////////
				}else{
					licdel = true;
				}
				if(chklicense != null && chklicense.length > 0 ){
					
					for (int idx = 0; idx < chklicense.length; idx++) {
						
						String iml_lname = chklicense[idx].trim();
						
						mlvo.setIml_lname(iml_lname);
						
						updlic = wdao.updateWorkerLicense(mlvo, conn, psmt, rs); /////////////// 쿼리 
						System.out.println("새로운 자격증을 insert 반복!");
						if(!updlic || !licdel){
							break;
						}
					}
				}else{
					updlic = true;
				}
			}else{
				licdel = true;
				updlic = true;
			}
			
			// 4. 현재 회사 경력 추가 하기 
			// 경력 다 삭제 (현재 회사 빼고)
			if("1".equals(changexp)){
				//경력 체크
				boolean chkExp = wdao.chkExistExp(evo);
				if(chkExp){
					expdel = wdao.preupdateWorkerExp(evo, conn, psmt, rs); ///////////// 쿼리  delete
				}else{
					expdel = true;
				}
				
				if(arrregi != null && arrexit != null && arrconame != null && arrauth != null && arrroll != null){
					for (int idx = 0; idx < arrregi.length; idx++) {
						
						evo.setIme_regi_date(arrregi[idx]);
						evo.setIme_exit_date(arrexit[idx]);
						evo.setIme_coname(arrconame[idx]);
						evo.setIme_auth(Integer.parseInt(arrauth[idx].trim()));
						evo.setIme_roll(arrroll[idx]);
						expFlag =wdao.updateWorkerExp(evo, conn, psmt, rs); ////////////// 쿼리 
						System.out.println("경력넣은 만큼 insert 반복!");
						if(!expFlag || !expdel){
							break;
						}
					}
				}else{
					expFlag = true;
				}
				
			}else{
				expFlag = true;
				expdel = true;
			}
			//url
			System.out.println("infoFlag = " + infoFlag + " , expFlag = " + expFlag + " , expdel = " + expdel + " , licdel = " + licdel + " , skilldel =" + skilldel);
			System.out.print(" , updskill = " + updskill + " , updlic = " + updlic);
			if(!infoFlag || !expFlag || !expdel || !licdel || !skilldel || !updskill || !updlic){
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
