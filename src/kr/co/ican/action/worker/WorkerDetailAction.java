package kr.co.ican.action.worker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.ican.controller.CommandAction;
import kr.co.ican.services.WorkerServiceImpl;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;

public class WorkerDetailAction implements CommandAction {

	private WorkerServiceImpl workerservice = WorkerServiceImpl.getInstance();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		String idx = request.getParameter("idx"); //사번 가져오기
		String expy = request.getParameter("expy");
		String expm = request.getParameter("expm");
		
		ExperienceVO evo = new ExperienceVO();
		MemberVO mvo = new MemberVO();
		MemLicenseVO licvo = new MemLicenseVO();
		//페이징 처리
		String pgn = request.getParameter("pageNumber"); // 현재 페이지 받아오기 , null 일경우 0
		int pageNumber = castChange(pgn); // 현제 페이지 번호, null 일경우 0
		evo.setPageNumber(pageNumber);
		int sn=evo.getPageNumber(); // 현재 페이지 번호
		int start=(sn)*evo.getRecordCountPerPage() + 1; // rownum 의 시작
		int end=(sn+1)*evo.getRecordCountPerPage(); // rownum의 끝
		int recordCountperPage = evo.getRecordCountPerPage();
		evo.setStart(start); // rownum 의 시작
		evo.setEnd(end); // rownum의 끝
		evo.setRecordCountPerPage(recordCountperPage);
		
		int im_idx = Integer.parseInt(idx.trim());
		// vo setting
		mvo.setIm_idx(im_idx); 
		evo.setIme_im_idx(im_idx);
		licvo.setIml_im_idx(im_idx);
//		svo.setIms_im_idx(im_idx);
		
		//1. 기본 정보 가져오기
		mvo = workerservice.getMemberDetail(mvo); 
		//2. 스킬 리스트 가져오기
//		List<MemSkillVO> slist = new ArrayList<MemSkillVO>();
//		slist = workerservice.getMemberSkills(svo);
		//3, 경력 가져오기
		List<ExperienceVO> elist = new ArrayList<ExperienceVO>();
		elist = workerservice.getMemberExperiences(evo);
		int totalRecordCount = workerservice.getTotalHistory(evo);
		//4. 라이센스 가져오기
		List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();
		liclist = workerservice.getMemberLicenses(licvo);
		//5. 입사일 가져오기
		String regidate = workerservice.getRegiDate(mvo);
		//6. 생년월일 , 나이 , 성별  계산
		String stnum = mvo.getIm_scnum().substring(0 , 6); // 850910
		String agenum = stnum.substring(0, 2);
		String ednum = mvo.getIm_scnum().substring(7, 14); // 1691817
		String gennum = ednum.substring(0, 1);
		//7. 경력
		String experience = "";
		
		if("0".equals(expy) && "0".equals(expm)){
			experience = "-";
		}else if ("0".equals(expy) && !"0".equals(expm)) {
			experience = expm + "개월";
		}else if(!"0".equals(expy) && "0".equals(expm)){
			experience = expy + "년";
		}else{
			experience = expy + "년 " + expm + "개월"; 
		}
		
		//나이
		int gendernum = Integer.parseInt(gennum);
		if(gendernum < 3){
			agenum = "19" + agenum;
		}else{
			agenum = "20" + agenum;
		}
		int yage = Integer.parseInt(agenum);
		int yy = Calendar.getInstance().get(Calendar.YEAR);
		int age = yy - yage;
		
		request.setAttribute("age", age);
		//성별
		if((gendernum % 2) == 0){
			request.setAttribute("gender", "F");
		}else{
			request.setAttribute("gender", "M");
		}
		
		//각 리스트 와 기본정보들
		request.setAttribute("expy", expy);
		request.setAttribute("expm", expm);
		request.setAttribute("idx", im_idx);
		request.setAttribute("experience", experience);
		request.setAttribute("expy", expy);
		request.setAttribute("expm", expm);
		request.setAttribute("regiDate", regidate);
		request.setAttribute("mvo", mvo);
//		request.setAttribute("slist", slist);
		request.setAttribute("elist", elist);
		request.setAttribute("liclist", liclist);
		
		request.setAttribute("pageNumber", sn); // 현재 페이지 번호
		request.setAttribute("pageCountPerScreen", 5); // 페이징 버튼 수
		request.setAttribute("recordCountPerPage", recordCountperPage); // 한 페이지당 보여줄 글의 갯수
		request.setAttribute("totalRecordCount", totalRecordCount); // 총 갯수
		
		return ns+"worker/workerDetail.jsp";
		
	}
	
	private int castChange (String str){
		
		int result;
		
		if(str == null || "".equals(str)){
			result = 0;
		}else{
			result = Integer.parseInt(str);
		}
		return result;
	}

}
