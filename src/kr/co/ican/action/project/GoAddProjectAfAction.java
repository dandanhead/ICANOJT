package kr.co.ican.action.project;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/addProjectAf")
public class GoAddProjectAfAction extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addProjectAf(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addProjectAf(req, resp);
	}
	
	private void addProjectAf(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String saveDir = request.getServletContext().getRealPath("/uploadfile");
		
		//폴더 체크 후 폴더 생성
		File mkdir = new File(saveDir);
		if(!mkdir.exists()){
			mkdir.mkdirs();
		}
		int maxSize = 1024*1024*100; 
		String encType = "UTF-8";
		MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType, new DefaultFileRenamePolicy()); //upload 시점
		
		String ipl_pname = multipartRequest.getParameter("ipl_pname"); //프로젝트명
		String ipl_client = multipartRequest.getParameter("ipl_client"); //고객사
		String skills[] = multipartRequest.getParameterValues("chklang"); //사용 스킬
		String ipl_content = multipartRequest.getParameter("ipl_content"); //상세내용
		String ipl_postcode = multipartRequest.getParameter("ipl_postcode"); //우편번호
		String ipl_address = multipartRequest.getParameter("ipl_address"); // 주소
		String ipl_detailaddr = multipartRequest.getParameter("ipl_detailaddr"); //상세주소
		String ipl_charge = multipartRequest.getParameter("ipl_charge"); //charge
		String ipl_sdate = multipartRequest.getParameter("ipl_sdate"); //시작일
		String ipl_exptdate = multipartRequest.getParameter("ipl_exptdate"); //예상 종료일
		String ipl_doc = multipartRequest.getFilesystemName("ipl_doc"); // 파일
		String fullpath = saveDir + "/" + ipl_doc; // 파일 전체 경로
		
		System.out.println("fullpath : " + fullpath);
		System.out.println("파일명 : " +  ipl_doc);
		
		/*if(!("").equals(ipl_doc) || ipl_do){
			
			System.out.println("파일이름 : " + originalFileName); // 파일 이름이 있으면 파일 업로드 없으면 null return 
			File file = new File("ipl_doc"); // upload 시점 
			//<a href="upload/<%=fileName%>"><%=fileName%></a> 다운로드 시 폴더 url + 파일명으로 다운로드 가능
		}else{
			
		}*/
		
		//1. 프로젝트 list 에 프로젝트 정보 저장
		
		//2. 파일 있으면 파일 업로드
		//return
		
	}
	
}
