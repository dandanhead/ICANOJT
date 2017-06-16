package kr.co.ican.action.worker;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.ican.controller.CommandAction;
import kr.co.ican.dao.WorkerDAO;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemSkillVO;
import kr.co.ican.vo.MemberVO;

public class WorkerUpdateAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		
		String idx = request.getParameter("idx");
		int im_idx = Integer.parseInt(idx);
		
		MemberVO mvo = new MemberVO();
		MemSkillVO svo = new MemSkillVO();
		ExperienceVO evo = new ExperienceVO();
		MemLicenseVO licvo = new MemLicenseVO();
		
		mvo.setIm_idx(im_idx);
		svo.setIms_im_idx(mvo.getIm_idx());
		evo.setIme_im_idx(mvo.getIm_idx());
		licvo.setIml_im_idx(mvo.getIm_idx());
		
		WorkerDAO wdao = WorkerDAO.getInstance();
		
		//1. 기본 정보 가져오기
		mvo = wdao.getMemberDetail(mvo); 
		//2. 스킬 리스트 가져오기
		List<MemSkillVO> slist = new ArrayList<MemSkillVO>();
		slist = wdao.getMemberSkills(svo);
		//3, 경력 가져오기
		List<ExperienceVO> elist = new ArrayList<ExperienceVO>();
		elist = wdao.getMemberExperiences(evo);
		//4. 라이센스 가져오기
		List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();
		liclist = wdao.getMemberLicenses(licvo);
		
		request.setAttribute("mvo", mvo);
		request.setAttribute("slist", slist);
		request.setAttribute("elist", elist);
		request.setAttribute("liclist", liclist);
		
		return ns+"/worker/workerUpdate.jsp";
	}
	
	

}
