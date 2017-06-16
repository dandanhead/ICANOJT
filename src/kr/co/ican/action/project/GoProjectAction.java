package kr.co.ican.action.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;

public class GoProjectAction implements CommandAction{
	
	

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		request.setCharacterEncoding("UTF-8"); // utf-8 charset
		//DAO 에서 리스트 가져오기
		
		
		return ns+"project/projectManage.jsp";
	}
	
	

}
