package kr.co.ican.services;

import java.sql.SQLException;
import java.util.List;

import kr.co.ican.vo.ProjectVO;

public interface ProjectService {
	
	public boolean addProject(ProjectVO pvo);
	public int getTotalProjectCount();
	public List<ProjectVO> getProjectList(ProjectVO pvo) throws SQLException;
	public ProjectVO getProjectDetail(ProjectVO pvo);
}
