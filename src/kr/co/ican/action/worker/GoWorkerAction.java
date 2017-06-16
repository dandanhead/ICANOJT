package kr.co.ican.action.worker;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.ican.controller.CommandAction;
import kr.co.ican.dao.WorkerDAO;
import kr.co.ican.vo.MemberVO;

public class GoWorkerAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		WorkerDAO wdao = WorkerDAO.getInstance(); // 생성
		MemberVO mvo = new MemberVO();
		
		// search
		String s_category = request.getParameter("s_category");
		String s_keyword = request.getParameter("s_keyword");
		mvo.setS_category(s_category);
		mvo.setS_keyword(s_keyword);
		
		//어떤 검색인지 판단하기
		String wsearch = request.getParameter("wsearch");
		
		//페이징 처리
		String pgn = request.getParameter("pageNumber"); // 현재 페이지 받아오기 , null 일경우 0
		int pageNumber = castChange(pgn); // 현제 페이지 번호, null 일경우 0
		mvo.setPageNumber(pageNumber);
		int sn=mvo.getPageNumber(); // 현재 페이지 번호
		int start=(sn)*mvo.getRecordCountPerPage() + 1; // rownum 의 시작
		int end=(sn+1)*mvo.getRecordCountPerPage(); // rownum의 끝
		int recordCountperPage = mvo.getRecordCountPerPage();
		mvo.setStart(start); // rownum 의 시작
		mvo.setEnd(end); // rownum의 끝
		mvo.setRecordCountPerPage(recordCountperPage);
		
		//DAO
		int totalRecordCount=wdao.getWorkerCount(mvo); //총 게시글 수
		List<MemberVO> memlist = wdao.getWorkerList(mvo); // 글목록 카운트 쿼리는 성공했으니 리스트 가져오면 끝~~

		// 가져온 리스트를 화면으로 가져가기
		request.setAttribute("wsearch", wsearch);// 어떤 검색인지 판단하기
		request.setAttribute("workerlist", memlist); // 사원 리스트 가져오기
		request.setAttribute("pageNumber", sn); // 현재 페이지 번호
		request.setAttribute("pageCountPerScreen", 10); // 페이징 버튼 수
		request.setAttribute("recordCountPerPage", recordCountperPage); // 한 페이지당 보여줄 글의 갯수
		request.setAttribute("totalRecordCount", totalRecordCount); // 총 사원의 수
		request.setAttribute("s_category", s_category); // 상세검색 카테고리
		request.setAttribute("s_keyword", s_keyword); // 상세검색 검색어
		
		return ns+"worker/workerManage.jsp";
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
