package kr.co.ican.action.worker;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.ican.controller.CommandAction;
import kr.co.ican.services.WorkerServiceImpl;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;

public class WorkerUpdateAction implements CommandAction {

	private WorkerServiceImpl workerservice = WorkerServiceImpl.getInstance();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		
		String idx = request.getParameter("idx");
		int im_idx = Integer.parseInt(idx);
		
		MemberVO mvo = new MemberVO();
		ExperienceVO evo = new ExperienceVO();
		MemLicenseVO licvo = new MemLicenseVO();
		
		mvo.setIm_idx(im_idx);
		evo.setIme_im_idx(mvo.getIm_idx());
		licvo.setIml_im_idx(mvo.getIm_idx());
		
		//1. 기본 정보 가져오기
		mvo = workerservice.getMemberDetail(mvo); 
		//2, 경력 가져오기
		List<ExperienceVO> elist = new ArrayList<ExperienceVO>();
		elist = workerservice.getMemberExperiences(evo);
		//3. 라이센스 가져오기
		List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();
		liclist = workerservice.getMemberLicenses(licvo);
		
		request.setAttribute("mvo", mvo);
		request.setAttribute("elist", elist);
		request.setAttribute("liclist", liclist);
		
		return ns+"/worker/workerUpdate.jsp";
	}
	
	

}
