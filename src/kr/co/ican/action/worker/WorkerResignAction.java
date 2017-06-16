package kr.co.ican.action.worker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;
import kr.co.ican.dao.WorkerDAO;
import kr.co.ican.vo.MemberVO;

public class WorkerResignAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		
		WorkerDAO wdao = WorkerDAO.getInstance();
		
		String idx = request.getParameter("idx");
		int im_idx = Integer.parseInt(idx); // 사번
		
		boolean resignresult = false;
		
		
		MemberVO mvo = new MemberVO();
		mvo.setIm_idx(im_idx);
		
		resignresult = wdao.resignWorker(mvo);
		
		if(resignresult){
			
			return ns+"worker/workerOut.jsp";
		}else{
			return "goError.do";
		}
		
	}

}
