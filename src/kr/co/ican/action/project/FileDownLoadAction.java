package kr.co.ican.action.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/filedown")
public class FileDownLoadAction extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("요청되는지 보자 get");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//upload 디렉토리
		String saveDir = req.getServletContext().getRealPath("/uploadfile");
		//파일 이름
		String filename = req.getParameter("filename");
		//full path
		String path = saveDir +"/"+filename;
		//파일 객체 생성
		File file = new File(path);
		//MIMETYPE 설정
		String mimeType = getServletContext().getMimeType(file.toString());
		
		if(mimeType == null)
		{
			resp.setContentType("application/octet-stream");
		}
		//다운로드용 파일명 설정
		String downName = null;
		if(req.getHeader("user-agent").indexOf("MSIE") == -1)
		{
			downName = new String(filename.getBytes("UTF-8"), "8859_1");
		}
		else
		{
			downName = new String(filename.getBytes("EUC-KR"), "8859_1");
		}
		//강제 다운로드
		resp.setHeader("Content-Disposition","attachment;filename=\"" + downName + "\";");
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = resp.getOutputStream();
		
		byte b [] = new byte[1024];
		int data = 0;
		
		while((data=(fileInputStream.read(b, 0, b.length))) != -1)
		{
			servletOutputStream.write(b, 0, data);
		}
		
		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();
	}
}
