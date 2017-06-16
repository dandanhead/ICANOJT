package kr.co.ican.action.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.co.ican.controller.CommandAction;
import kr.co.ican.dao.MemberDAO;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemberVO;

public class LoginAction implements CommandAction{

	MemberDAO memdao = MemberDAO.getInstance();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		
		String getidx = request.getParameter("im_idx");
		int idx = Integer.parseInt(getidx);
		String getpw = request.getParameter("im_pw");
		
		MemberVO vo = new MemberVO();
		ExperienceVO evo = new ExperienceVO();
		
		vo = memdao.logininfo(new MemberVO(idx, getpw));
		
		if(vo == null){
			return ns+"login/loginFailed.jsp";
		}else{
			
			HttpSession session = request.getSession();
			
			evo = memdao.getExperience(vo);
			
			session.setAttribute("exp", evo); //입사일 정보 가져오기
			session.setAttribute("login", vo); // 로그인 정보 세션 저장
			session.setAttribute("isLogin", 1); // 로그인 상태 세션 변경
			
			return ns+"main/main.jsp";
		}
    }

}
	
