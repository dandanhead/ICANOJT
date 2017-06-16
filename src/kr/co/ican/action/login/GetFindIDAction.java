package kr.co.ican.action.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;
import kr.co.ican.dao.MemberDAO;
import kr.co.ican.vo.MemberVO;

public class GetFindIDAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		
//		response.setContentType("text/xml;charset=UTF-8");
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		MemberDAO mdao = MemberDAO.getInstance();
		
		
		String getfnum = request.getParameter("f_num");
		String getenum = request.getParameter("e_num");
		
		String getName= request.getParameter("im_name");
		String getSNum = getfnum + "-" + getenum;
		
		MemberVO lvo = new MemberVO();
		
		lvo.setIm_name(getName);
		lvo.setIm_scnum(getSNum);;
		
		MemberVO vo = new MemberVO();
		
		vo = mdao.findID(lvo);
		
		
		if(vo == null){
			request.setAttribute("result", null);
			return ns+"login/getResultID.jsp";
			
		}else{
			request.setAttribute("result", vo);
			return ns+"login/getResultID.jsp";
		}
	}
}
