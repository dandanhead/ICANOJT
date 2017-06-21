package kr.co.ican.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kr.co.ican.dao.WorkerDAO;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemberVO;
import kr.co.ican.vo.ProjectVO;

public class WorkerServiceImpl implements WorkerService {

	
	private static WorkerServiceImpl workerservice;

	public static WorkerServiceImpl getInstance() {
		
		if (workerservice == null) {
			
			workerservice = new WorkerServiceImpl();
		}
		
		return workerservice;
	}
	
	private WorkerDAO wdao = WorkerDAO.getInstance();
	
	@Override
	public boolean chkDuplicate(MemberVO mvo) {
		
		return wdao.chkDuplicate(mvo);
	}

	@Override
	public int getWorkerCount(MemberVO mvo) throws SQLException {
		
		return wdao.getWorkerCount(mvo);
	}

	@Override
	public List<MemberVO> getWorkerList(MemberVO mvo) throws SQLException {
		
		return wdao.getWorkerList(mvo);
	}

	@Override
	public boolean addWorkerInfo(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		return wdao.addWorkerInfo(mvo, conn, psmt, rs);
	}


	@Override
	public boolean addWorkerExp(ExperienceVO evo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		return wdao.addWorkerExp(evo, conn, psmt, rs);
	}

	@Override
	public boolean basicWorkerExp(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		return wdao.basicWorkerExp(mvo, conn, psmt, rs);
	}

	@Override
	public boolean addWorkerLicense(MemLicenseVO mlvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		return wdao.addWorkerLicense(mlvo, conn, psmt, rs);
	}

	@Override
	public MemberVO getMemberDetail(MemberVO mvo) {
		
		return wdao.getMemberDetail(mvo);
	}

	@Override
	public List<ExperienceVO> getMemberExperiences(ExperienceVO evo) {
		
		return wdao.getMemberExperiences(evo);
	}

	@Override
	public int getTotalHistory(ExperienceVO evo) {
		
		return wdao.getTotalHistory(evo);
	}

	@Override
	public List<MemLicenseVO> getMemberLicenses(MemLicenseVO licvo) {
		
		return wdao.getMemberLicenses(licvo);
	}

	@Override
	public String getRegiDate(MemberVO mvo) {
		
		return wdao.getRegiDate(mvo);
	}

	@Override
	public List<ProjectVO> getMemberProjects(ProjectVO pvo) {
		
		return wdao.getMemberProjects(pvo);
	}

	@Override
	public boolean mailDuplChk(MemberVO mvo) {
		
		return wdao.mailDuplChk(mvo);
	}

	@Override
	public boolean phoneDuplChk(MemberVO mvo) {
		
		return wdao.phoneDuplChk(mvo);
	}

	@Override
	public boolean chkExistLicence(MemLicenseVO mlvo) {
		
		return wdao.chkExistLicence(mlvo);
	}

	@Override
	public boolean chkExistExp(ExperienceVO evo) {
		
		return wdao.chkExistExp(evo);
	}

	@Override
	public boolean updateWorkerInfo(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		return wdao.updateWorkerInfo(mvo, conn, psmt, rs);
	}

	@Override
	public boolean preupdateWorkerExp(ExperienceVO evo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		return wdao.preupdateWorkerExp(evo, conn, psmt, rs);
	}

	@Override
	public boolean updateWorkerExp(ExperienceVO evo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		return wdao.updateWorkerExp(evo, conn, psmt, rs);
	}

	@Override
	public boolean preupdateWorkerLicense(MemLicenseVO mlvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		return wdao.preupdateWorkerLicense(mlvo, conn, psmt, rs);
	}

	@Override
	public boolean updateWorkerLicense(MemLicenseVO mlvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		return wdao.updateWorkerLicense(mlvo, conn, psmt, rs);
	}

	@Override
	public boolean resignWorker(MemberVO mvo) {
		
		return wdao.resignWorker(mvo);
	}

}
