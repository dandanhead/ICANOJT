package kr.co.ican.action.worker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;
import kr.co.ican.dao.WorkerDAO;
import kr.co.ican.vo.MemberVO;

public class MailCheckDuplicateAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		WorkerDAO wdao = WorkerDAO.getInstance(); // 생성
		
		String im_email = request.getParameter("ajaxeamil");
		MemberVO mvo = new MemberVO();
		mvo.setIm_email(im_email);
		boolean isS = wdao.mailDuplChk(mvo);
		
		if(isS){
			return null;
		}else{
			return ns+"worker/updateWorkers.jsp";
		}
	}

}
