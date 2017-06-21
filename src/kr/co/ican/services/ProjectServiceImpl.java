package kr.co.ican.services;

import java.sql.SQLException;
import java.util.List;

import kr.co.ican.dao.ProjectDAO;
import kr.co.ican.vo.ProjectVO;

public class ProjectServiceImpl implements ProjectService {

	private static ProjectServiceImpl proservice;
	
	public static ProjectServiceImpl getInstance(){
		
		if(proservice == null){
			
			proservice = new ProjectServiceImpl();
		}
		
		return proservice;
	}
	
	private ProjectDAO pdao = ProjectDAO.getInstance();
	
	@Override
	public boolean addProject(ProjectVO pvo) {
		
		return pdao.addProject(pvo);
	}

	@Override
	public int getTotalProjectCount() {
		
		return pdao.getTotalProjectCount();
	}

	@Override
	public List<ProjectVO> getProjectList(ProjectVO pvo) throws SQLException {
		
		return pdao.getProjectList(pvo);
	}

	@Override
	public ProjectVO getProjectDetail(ProjectVO pvo) {
		
		return pdao.getProjectDetail(pvo);
	}
	
	
	
}
