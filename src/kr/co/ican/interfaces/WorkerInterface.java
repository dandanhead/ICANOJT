package kr.co.ican.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemSkillVO;
import kr.co.ican.vo.MemberVO;
import kr.co.ican.vo.ProjectVO;

public interface WorkerInterface {
	
	//Unique key Checker
	public boolean chkDuplicate(MemberVO mvo);
	
	public int getWorkerCount(MemberVO mvo) throws SQLException;
	public List<MemberVO> getWorkerList(MemberVO mvo) throws SQLException;
	//사원 추가 set
	public boolean addWorkerInfo(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs); //사원 기본정보 입력 사번 sequence 자동 생성
	public boolean addWorkerSkill(MemSkillVO msvo, Connection conn, PreparedStatement psmt, ResultSet rs); //위에 기본정보 입력후 사번 가지고 와야함  currval 사용
	public boolean addWorkerExp(ExperienceVO evo,  Connection conn ,PreparedStatement psmt, ResultSet rs); // 위에 기본정보 입력 후 사번 가지고 와야함 currval 사용
	public boolean basicWorkerExp(MemberVO mvo , Connection conn , PreparedStatement psmt, ResultSet rs); // 사원 기본경력 추가 
	public boolean addWorkerLicense(MemLicenseVO mlvo , Connection conn ,PreparedStatement psmt, ResultSet rs);
	//Detail
	public MemberVO getMemberDetail(MemberVO mvo); // 사원 기본정보
	public List<MemSkillVO> getMemberSkills(MemSkillVO svo); // 사원 스킬들
	public List<ExperienceVO> getMemberExperiences(ExperienceVO evo); // 사원 경력사항
	public int getTotalHistory(ExperienceVO evo); // 해당 사원 경력 개수
	public List<MemLicenseVO> getMemberLicenses(MemLicenseVO licvo); // 자격증 가져오기
	public String getRegiDate(MemberVO mvo);
	public List<ProjectVO> getMemberProjects(ProjectVO pvo); // 사원 프로젝트 history
	//update check
	public boolean mailDuplChk(MemberVO mvo);
	public boolean phoneDuplChk(MemberVO mvo);
	//Exist
	public boolean chkExistLicence(MemLicenseVO mlvo);
	public boolean chkExistExp(ExperienceVO evo);
	//update
	public boolean updateWorkerInfo(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs);
	
	public boolean preupdateWorkerSkill(MemSkillVO msvo, Connection conn, PreparedStatement psmt, ResultSet rs);
	public boolean updateWorkerSkill(MemSkillVO msvo, Connection conn, PreparedStatement psmt, ResultSet rs);
	public boolean preupdateWorkerExp(ExperienceVO evo, Connection conn, PreparedStatement psmt, ResultSet rs);
	public boolean updateWorkerExp(ExperienceVO evo, Connection conn, PreparedStatement psmt, ResultSet rs);
	public boolean preupdateWorkerLicense(MemLicenseVO mlvo, Connection conn, PreparedStatement psmt, ResultSet rs);
	public boolean updateWorkerLicense(MemLicenseVO mlvo, Connection conn, PreparedStatement psmt, ResultSet rs);
	
	//resign
	public boolean resignWorker(MemberVO mvo);

	

}
