package kr.co.ican.action.worker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;
import kr.co.ican.services.WorkerServiceImpl;
import kr.co.ican.vo.MemberVO;

public class CheckDuplicateAction implements CommandAction {

	private WorkerServiceImpl workerservice = WorkerServiceImpl.getInstance();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		
		String im_email = request.getParameter("ajaxeamil");
		String im_phone = request.getParameter("ajaxphone");
		String im_scnum = request.getParameter("ajaxscnum");
		
		MemberVO mvo = new MemberVO();
		
		mvo.setIm_email(im_email);
		mvo.setIm_phone(im_phone);
		mvo.setIm_scnum(im_scnum);
		
		boolean isS = workerservice.chkDuplicate(mvo);
		
		if(isS){
			return null;
		}else{
			return ns+"worker/addWorkers.jsp";
		}
	}
}
