package kr.co.ican.action.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;
import kr.co.ican.services.MemberServiceImpl;
import kr.co.ican.vo.MemberVO;

public class GetFindPWAction implements CommandAction{

	private MemberServiceImpl memservice = MemberServiceImpl.getInstance();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		
//		response.setContentType("text/xml;charset=UTF-8");
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		
		String getfnum = request.getParameter("f_num");
		String getenum = request.getParameter("e_num");
		String getSNum = getfnum + "-" + getenum;
		String getName= request.getParameter("im_name");
		String getMail = request.getParameter("im_email");
		
		
		MemberVO lvo = new MemberVO();
		
		lvo.setIm_name(getName);
		lvo.setIm_scnum(getSNum);
		lvo.setIm_email(getMail);
		
		MemberVO vo = new MemberVO();
		
		vo = memservice.findPW(lvo);
		
		
		if(vo == null){
			request.setAttribute("result", null);
			return ns+"login/getResultPW.jsp";
			
		}else{
			request.setAttribute("result", vo);
			return ns+"login/getResultPW.jsp";
		}
	}
}
