package kr.co.ican.help;


public class Helps {

	public static final String NS = "/WEB-INF/views/";
	
	
	public String selectAuthority(int num){
		String result = "";
		if(num == 0){
			result = "Developer";
		}else{
			result = "Manager";
		}
		
		return result;
	}
	
	public String yymmdd(String ymd){
		
		String result = "";
		
		if("".equals(ymd) || ymd == null){
			return result;
		}else{
			result = ymd.substring(0, 10);
			return result;
		}
	}
}
