package kr.co.ican.action.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;
import kr.co.ican.services.ProjectServiceImpl;
import kr.co.ican.vo.ProjectVO;

public class ProjectDetailAction implements CommandAction {
	
	private ProjectServiceImpl proservice = ProjectServiceImpl.getInstance();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		int ipl_idx = Integer.parseInt(request.getParameter("idx")); //사번 가져오기
		
		ProjectVO pvo = new ProjectVO();
//		ProjectJoinMemberVO pjvo = new ProjectJoinMemberVO();
		
		//vo settion
		pvo.setIpl_idx(ipl_idx);
		
		//1.해당 프로젝트 내용 가져오기
		pvo = proservice.getProjectDetail(pvo);
		 
		//2.해당 프로젝트 참여 인원 가져오기
		
		//set
		request.setAttribute("pvo", pvo);
		
		return ns + "project/projectDetail.jsp";
	}

}
