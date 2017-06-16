package kr.co.ican.action.errpage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ican.controller.CommandAction;

public class GoErrPageAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String ns = kr.co.ican.help.Helps.NS; //Name Space
		
		return ns+"error/error.jsp";
	}

}
