package kr.co.ican.action.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;

public class GoMyPageAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String ns = kr.co.ican.help.Helps.NS; //Name Space
		
		return ns+"mypage/myPage.jsp";
	}

	
}
