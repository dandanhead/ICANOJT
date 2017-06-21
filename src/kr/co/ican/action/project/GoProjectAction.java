package kr.co.ican.action.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;
import kr.co.ican.services.ProjectServiceImpl;
import kr.co.ican.vo.ProjectVO;

public class GoProjectAction implements CommandAction{
	
	private ProjectServiceImpl proservice = ProjectServiceImpl.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		ProjectVO pvo = new ProjectVO();
		//DAO 에서 리스트 가져오기
		
		//페이징 처리
		String pgn = request.getParameter("pageNumber"); // 현재 페이지 받아오기 , null 일경우 0
		int pageNumber = castChange(pgn); // 현제 페이지 번호, null 일경우 0
		pvo.setPageNumber(pageNumber);
		int sn=pvo.getPageNumber(); // 현재 페이지 번호
		int start=(sn)*pvo.getRecordCountPerPage() + 1; // rownum 의 시작
		int end=(sn+1)*pvo.getRecordCountPerPage(); // rownum의 끝
		int recordCountperPage = pvo.getRecordCountPerPage();
		pvo.setStart(start); // rownum 의 시작
		pvo.setEnd(end); // rownum의 끝
		pvo.setRecordCountPerPage(recordCountperPage);
		
		int totalRecordCount = proservice.getTotalProjectCount();
		List<ProjectVO> plist = proservice.getProjectList(pvo);
		
		// 가져온 리스트를 화면으로 가져가기
		request.setAttribute("plist", plist); // 사원 리스트 가져오기
		request.setAttribute("pageNumber", sn); // 현재 페이지 번호
		request.setAttribute("pageCountPerScreen", 10); // 페이징 버튼 수
		request.setAttribute("recordCountPerPage", recordCountperPage); // 한 페이지당 보여줄 글의 갯수
		request.setAttribute("totalRecordCount", totalRecordCount); // 총 사원의 수
		
		
		return ns+"project/projectManage.jsp";
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
