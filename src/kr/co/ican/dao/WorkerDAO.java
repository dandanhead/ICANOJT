package kr.co.ican.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.ican.dbconn.GetDBConn;
import kr.co.ican.interfaces.WorkerInterface;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemLicenseVO;
import kr.co.ican.vo.MemSkillVO;
import kr.co.ican.vo.MemberVO;
import kr.co.ican.vo.ProjectVO;

public class WorkerDAO implements WorkerInterface{
	
	private static WorkerDAO wdao;

	public static WorkerDAO getInstance() {
		
		if (wdao == null) {
			
			wdao = new WorkerDAO();
		}
		
		return wdao;
	}

	@Override
	public boolean addWorkerSkill(MemSkillVO msvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		 String sql = "";
	        int cnt = 1;
	        int result = 0;
	        
	        try {
				sql = " INSERT INTO ICAN_MEM_SKILL(IMS_IS_SNAME, IMS_IM_IDX) VALUES( ?, MEMBER_SEQ.CURRVAL ) ";
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(cnt++, msvo.getIms_is_sname());
				
				result = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	        
			return result > 0 ? true : false;
	}

	@Override
	public boolean addWorkerExp(ExperienceVO evo, Connection conn, PreparedStatement psmt, ResultSet rs) {
			String sql = "";
	        int cnt = 1;
	        int result = 0;
	        try {
	        	System.out.println("DAO(경험치 삽입 ) = "+evo.toString());
				sql = " INSERT INTO ICAN_MEM_EXP(IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL) "
					+ " VALUES(MEMBER_SEQ.CURRVAL , ? , ? , ?, ? , ?) ";

				psmt = conn.prepareStatement(sql);
				psmt.setString(cnt++, evo.getIme_regi_date());
				psmt.setString(cnt++, evo.getIme_exit_date());
				psmt.setString(cnt++, evo.getIme_coname());
				psmt.setInt(cnt++, evo.getIme_auth());
				psmt.setString(cnt++, evo.getIme_roll());
				
				result = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	        
			return result > 0 ? true : false;
	}

	@Override
	public boolean basicWorkerExp(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
			String sql = "";
	        int cnt = 1;
	        int result = 0;
	        try {
				if("타업체인력".equals(mvo.getIm_dname())){
					sql = " INSERT INTO ICAN_MEM_EXP(IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL) "
							+ " VALUES(MEMBER_SEQ.CURRVAL , SYSDATE, NULL, ? , ? , '미배정') ";
					
					psmt = conn.prepareStatement(sql);
					psmt.setString(cnt++, mvo.getOutsideperson());
					psmt.setInt(cnt++, mvo.getIm_auth());
				}else{
					sql = " INSERT INTO ICAN_MEM_EXP(IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL) "
							+ " VALUES(MEMBER_SEQ.CURRVAL , SYSDATE, NULL, '아이캔매니지먼트(주)', ? , '미배정') ";

					psmt = conn.prepareStatement(sql);
					psmt.setInt(cnt++, mvo.getIm_auth());
				}
				
				result = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return result > 0 ? true : false;
	}

	@Override
	public boolean addWorkerLicense(MemLicenseVO mlvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		 String sql = "";
	        int cnt = 1;
	        int result = 0;
	        try {
				sql = " INSERT INTO ICAN_MEM_LICENSE(IML_IM_IDX, IML_LNAME) "
					+ " VALUES( MEMBER_SEQ.CURRVAL , ? ) ";
				
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(cnt++, mlvo.getIml_lname());
				
				result = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return result > 0 ? true : false;
	}

	@Override
	public boolean chkDuplicate(MemberVO mvo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        int result= 0;
        int cnt = 1;
        String sql = "";
        
        try {
        	conn = GetDBConn.getConnection();
        	sql = "  SELECT * FROM ICAN_MEMBER WHERE IM_PHONE = ? OR IM_EMAIL = ? OR IM_SCNUM = ?  ";
        	psmt = conn.prepareStatement(sql);
        	psmt.setString(cnt++, mvo.getIm_phone());
        	psmt.setString(cnt++, mvo.getIm_email());
        	psmt.setString(cnt++, mvo.getIm_scnum());
        	
        	rs =psmt.executeQuery();
        	
        	while (rs.next()) {
				result++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
        
		return result > 0 ? true : false ;
	}

	@Override
	public List<MemberVO> getWorkerList(MemberVO mvo) throws SQLException{
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        List<MemberVO> list = new ArrayList<MemberVO>();
        
        int cnt = 1;
       	conn = GetDBConn.getConnection();
       	 String sql = "";
       
       	// 첫 시작 & 전체 검색 
       	sql = " SELECT IM_IDX, IM_NAME, IM_DNAME, IM_PHONE , IM_AUTH, IM_STATUS , YEAR#, MONTH# "
       		+ " FROM( "
       		+ "       SELECT "
       		+ "              ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, "
       		+ "              IM_IDX, "
       		+ "              IM_NAME, "
       		+ "              IM_DNAME, "
       		+ "              IM_PHONE, "
       		+ "              IM_AUTH, "
       		+ "              IM_STATUS, "
       		+ "              TRUNC(DATETERM / 12) AS YEAR#, "
       		+ "              TRUNC(MONTHS_BETWEEN (SYSDATE, ADD_MONTHS (MINDATE, 12 * TRUNC (DATETERM / 12)))) AS MONTH#"
       		+ "  	  FROM "
       		+ "             ICAN_MEMBER IM LEFT JOIN ("
       		+ "                                       SELECT "
       		+ "                                               IME_IM_IDX,"
       		+ "                                               MIN(IME_REGI_DATE) AS MINDATE, "
       		+ "                                               MONTHS_BETWEEN (SYSDATE, MIN(IME_REGI_DATE)) AS DATETERM "
       		+ "                                       FROM ICAN_MEM_EXP group by IME_IM_IDX "
       		+ "                                       ) IME"
       		+ "              ON IM.IM_IDX = IME.IME_IM_IDX "
       		+ "       WHERE "
       		+ "               IM_RESIGN = 0 "
       		+ "       )"
       		+ " WHERE RNUM BETWEEN ? AND ? ";
       	
    	psmt = conn.prepareStatement(sql);
       	psmt.setInt(1, mvo.getStart());
       	psmt.setInt(2, mvo.getEnd());
 
       	rs = psmt.executeQuery();
  	   
	    while (rs.next()) {
	   		cnt = 1;
	       	MemberVO vo = new MemberVO();
	       	vo.setIm_idx(rs.getInt(cnt++));
	       	vo.setIm_name(rs.getString(cnt++));
	       	vo.setIm_dname(rs.getString(cnt++));
	       	vo.setIm_phone(rs.getString(cnt++));
	       	vo.setIm_auth(rs.getInt(cnt++));
	       	vo.setIm_status(rs.getInt(cnt++));
	       	vo.setExpYear(rs.getInt(cnt++));
	       	vo.setExpMonth(rs.getInt(cnt++));
	       	
			list.add(vo);
	     }
	    
	    GetDBConn.close(conn, psmt, rs);
        return list;
	
	}
	
	@Override
	public int getWorkerCount(MemberVO mvo) throws SQLException {
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        conn = GetDBConn.getConnection();
        
        int result= 0;
        
        String sql = "";
        sql = " SELECT NVL(COUNT(*), 0) AS CNT FROM ICAN_MEMBER WHERE IM_RESIGN = 0 ";
		psmt = conn.prepareStatement(sql);
		
		rs = psmt.executeQuery();
		
		while (rs.next()) {
	   		 
			result = rs.getInt(1);
		}
        return result;
	}

	@Override
	public boolean addWorkerInfo(MemberVO mvo , Connection conn, PreparedStatement psmt, ResultSet rs) { // 사원 기본정보 넣기
        String sql = "";
        int cnt = 1;
        int result = 0;
        try {
			sql = " INSERT INTO ICAN_MEMBER(IM_IDX, IM_PW, IM_DNAME, IM_NAME, IM_PHONE, IM_EMAIL, IM_RESIGN, IM_STATUS, IM_SCNUM, IM_ADDRESS, IM_DETAILADDR, IM_POSTCODE, IM_AUTH) "
				 +" VALUES(MEMBER_SEQ.NEXTVAL, ? , ? , ? , ? , ? , 0 , 0 , ? , ? , ? , ? , ?)";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(cnt++, mvo.getIm_pw()); // 패스워드
			psmt.setString(cnt++, mvo.getIm_dname()); // 부서이름
			psmt.setString(cnt++, mvo.getIm_name()); //이름
			psmt.setString(cnt++, mvo.getIm_phone()); //전화번호
			psmt.setString(cnt++, mvo.getIm_email()); //이메일
			psmt.setString(cnt++, mvo.getIm_scnum()); //주번
			psmt.setString(cnt++, mvo.getIm_address()); //집주소
			psmt.setString(cnt++, mvo.getIm_detailaddr());
			psmt.setString(cnt++, mvo.getIm_postcode());
			psmt.setInt(cnt++, mvo.getIm_auth()); // 직급
			
			result = psmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result > 0 ? true : false;
	}

	@Override
	public MemberVO getMemberDetail(MemberVO mvo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int cnt = 1;
        
        try {
			conn = GetDBConn.getConnection();
			sql = " SELECT IM_IDX, IM_PW, IM_DNAME, IM_NAME, IM_PHONE, IM_EMAIL, IM_STATUS, IM_SCNUM, IM_ADDRESS,IM_DETAILADDR,IM_POSTCODE, IM_AUTH FROM ICAN_MEMBER WHERE IM_IDX = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, mvo.getIm_idx());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				mvo.setIm_idx(rs.getInt(cnt++));
				mvo.setIm_pw(rs.getString(cnt++));
				mvo.setIm_dname(rs.getString(cnt++));
				mvo.setIm_name(rs.getString(cnt++));
				mvo.setIm_phone(rs.getString(cnt++));
				mvo.setIm_email(rs.getString(cnt++));
				mvo.setIm_status(rs.getInt(cnt++));
				mvo.setIm_scnum(rs.getString(cnt++));
				mvo.setIm_address(rs.getString(cnt++));
				mvo.setIm_detailaddr(rs.getString(cnt++));
				mvo.setIm_postcode(rs.getString(cnt++));
				mvo.setIm_auth(rs.getInt(cnt++));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
        
		return mvo;
	}

	@Override
	public List<MemSkillVO> getMemberSkills(MemSkillVO svo) {
		

		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int cnt = 1;
        List<MemSkillVO> mslist = new ArrayList<MemSkillVO>();
        
        try {
			conn = GetDBConn.getConnection();
			sql = " SELECT * FROM ICAN_MEM_SKILL WHERE IMS_IM_IDX = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, svo.getIms_im_idx());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				MemSkillVO msvo = new MemSkillVO();
				msvo.setIms_is_sname(rs.getString(cnt++));
				msvo.setIms_im_idx(rs.getInt(cnt++));
				mslist.add(msvo);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
		
		return mslist;
	}

	@Override
	public List<MemLicenseVO> getMemberLicenses(MemLicenseVO licvo) {
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int cnt = 1;
        List<MemLicenseVO> liclist = new ArrayList<MemLicenseVO>();
        
        try {
			conn = GetDBConn.getConnection();
			sql = " SELECT * FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, licvo.getIml_im_idx());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				MemLicenseVO lvo = new MemLicenseVO();
				lvo.setIml_im_idx(rs.getInt(cnt++));
				lvo.setIml_lname(rs.getString(cnt++));
				
				liclist.add(lvo);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
		return liclist;
	}

	@Override
	public String getRegiDate(MemberVO mvo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int cnt = 1;
        String result = "";
        
        try {
			conn = GetDBConn.getConnection();
			sql = " SELECT TRUNC(IME_REGI_DATE) FROM ICAN_MEM_EXP WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NULL ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, mvo.getIm_idx());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				result = rs.getString(cnt++);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
		return result;
	}

	@Override
	public List<ExperienceVO> getMemberExperiences(ExperienceVO evo) {
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int cnt = 1;
        List<ExperienceVO> elist = new ArrayList<ExperienceVO>();
        
        try {
			conn = GetDBConn.getConnection();
			sql = " SELECT IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL "
				+ " FROM (SELECT ROW_NUMBER() OVER (ORDER BY IME_REGI_DATE DESC) AS RNUM, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH , IME_ROLL"
				+ "       FROM ICAN_MEM_EXP "
				+ "       WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NOT NULL) "
				+ " WHERE RNUM BETWEEN ? AND ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, evo.getIme_im_idx());
			psmt.setInt(2, evo.getStart());
			psmt.setInt(3, evo.getEnd());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				ExperienceVO expvo = new ExperienceVO();
				expvo.setIme_regi_date(rs.getString(cnt++));
				expvo.setIme_exit_date(rs.getString(cnt++));
				expvo.setIme_coname(rs.getString(cnt++));
				expvo.setIme_auth(rs.getInt(cnt++));
				expvo.setIme_roll(rs.getString(cnt++));
				
				elist.add(expvo);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
		return elist;
	}
	
	@Override
	public int getTotalHistory(ExperienceVO evo) {
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        int result= 0;
        
        try {
			conn = GetDBConn.getConnection();
	        
	        String sql = "";
	        sql = " SELECT NVL(COUNT(*) , 0) AS CNT FROM ICAN_MEM_EXP WHERE IME_IM_IDX = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, evo.getIme_im_idx());
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
		   		 
				result = rs.getInt(1);
			}
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}
	
	@Override
	public boolean mailDuplChk(MemberVO mvo) {
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        int result= 0;
        int cnt = 1;
        String sql = "";
        
        try {
        	conn = GetDBConn.getConnection();
        	sql = "  SELECT * FROM ICAN_MEMBER WHERE IM_EMAIL = ? ";
        	psmt = conn.prepareStatement(sql);
        	psmt.setString(cnt++, mvo.getIm_email());
        	
        	rs =psmt.executeQuery();
        	
        	while (rs.next()) {
				result++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
        
		return result > 0 ? true : false ;
	}

	@Override
	public boolean phoneDuplChk(MemberVO mvo) {
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        int result= 0;
        int cnt = 1;
        String sql = "";
        
        try {
        	conn = GetDBConn.getConnection();
        	sql = "  SELECT * FROM ICAN_MEMBER WHERE IM_PHONE = ? ";
        	psmt = conn.prepareStatement(sql);
        	psmt.setString(cnt++, mvo.getIm_phone());
        	
        	rs =psmt.executeQuery();
        	
        	while (rs.next()) {
				result++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
        
		return result > 0 ? true : false ;
	}

	
	@Override
	public boolean updateWorkerInfo(MemberVO mvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
			sql = " UPDATE ICAN_MEMBER SET IM_PW = ? , IM_DNAME = ? , IM_PHONE = ?, IM_EMAIL = ? ,IM_ADDRESS = ?, IM_DETAILADDR = ? ,"
				+ " IM_POSTCODE = ? , IM_AUTH = ?   WHERE IM_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, mvo.getIm_pw());
			psmt.setString(cnt++, mvo.getIm_dname());
			psmt.setString(cnt++, mvo.getIm_phone());
			psmt.setString(cnt++, mvo.getIm_email());
			psmt.setString(cnt++, mvo.getIm_address());
			psmt.setString(cnt++, mvo.getIm_detailaddr());
			psmt.setString(cnt++, mvo.getIm_postcode());
			psmt.setInt(cnt++, mvo.getIm_auth());
			psmt.setInt(cnt++, mvo.getIm_idx());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result > 0 ? true : false;
	}
	
	@Override
	public boolean preupdateWorkerSkill(MemSkillVO msvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
        	
			sql = " DELETE FROM ICAN_MEM_SKILL WHERE IMS_IM_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, msvo.getIms_im_idx());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result > 0 ? true : false;
	}
	@Override
	public boolean updateWorkerSkill(MemSkillVO msvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
			sql = " INSERT INTO ICAN_MEM_SKILL(IMS_IS_SNAME, IMS_IM_IDX) VALUES( ?, ? ) ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, msvo.getIms_is_sname());
			psmt.setInt(cnt++, msvo.getIms_im_idx());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result > 0 ? true : false;
	}

	@Override
	public boolean updateWorkerExp(ExperienceVO evo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
			sql = " INSERT INTO ICAN_MEM_EXP(IME_IM_IDX, IME_REGI_DATE, IME_EXIT_DATE, IME_CONAME, IME_AUTH, IME_ROLL) "
				+ " VALUES(? , ? , ? , ?, ? , ?) ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, evo.getIme_im_idx());
			psmt.setString(cnt++, evo.getIme_regi_date());
			psmt.setString(cnt++, evo.getIme_exit_date());
			psmt.setString(cnt++, evo.getIme_coname());
			psmt.setInt(cnt++, evo.getIme_auth());
			psmt.setString(cnt++, evo.getIme_roll());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
        
		return result > 0 ? true : false;
	}

	@Override
	public boolean updateWorkerLicense(MemLicenseVO mlvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		 String sql = "";
	        int cnt = 1;
	        int result = 0;
	        try {
				sql = " INSERT INTO ICAN_MEM_LICENSE(IML_IM_IDX, IML_LNAME) "
					+ " VALUES( ? , ? ) ";
				
				
				psmt = conn.prepareStatement(sql);
				psmt.setInt(cnt++, mlvo.getIml_im_idx());
				psmt.setString(cnt++, mlvo.getIml_lname());
				
				result = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return result > 0 ? true : false;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean preupdateWorkerExp(ExperienceVO evo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
			sql = " DELETE FROM ICAN_MEM_EXP WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NOT NULL ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, evo.getIme_im_idx());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result > 0 ? true : false;
	}

	@Override
	public boolean preupdateWorkerLicense(MemLicenseVO mlvo, Connection conn, PreparedStatement psmt, ResultSet rs) {
		String sql = "";
        int cnt = 1;
        int result = 0;
        try {
        	
        	System.out.println("preLicensedelete DAO mlvo toString = "+mlvo.toString());
        	
			sql = " DELETE FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mlvo.getIml_im_idx());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result > 0 ? true : false;
	}
	
	@Override
	public boolean chkExistLicence(MemLicenseVO mlvo) {
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        int result= 0;
        int cnt = 1;
        String sql = "";
        
        try {
        	conn = GetDBConn.getConnection();
        	sql = "  SELECT * FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = ? ";
        	psmt = conn.prepareStatement(sql);
        	psmt.setInt(cnt++, mlvo.getIml_im_idx());
        	
        	rs =psmt.executeQuery();
        	
        	while (rs.next()) {
				result++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
        
		return result > 0 ? true : false ;
	}

	@Override
	public boolean chkExistExp(ExperienceVO evo) {
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        int result= 0;
        int cnt = 1;
        String sql = "";
        
        try {
        	conn = GetDBConn.getConnection();
        	sql = "  SELECT * FROM ICAN_MEM_EXP WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NOT NULL ";
        	psmt = conn.prepareStatement(sql);
        	psmt.setInt(cnt++, evo.getIme_im_idx());
        	
        	rs =psmt.executeQuery();
        	
        	while (rs.next()) {
				result++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
        
		return result > 0 ? true : false ;
	}

@Override
	public boolean resignWorker(MemberVO mvo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		PreparedStatement psmts = null;
	    ResultSet rs = null;
	    
	    int result= 0;
	    int nextresult = 0;
        int cnt = 1;
        String sql = "";
        String nextsql = "";
        
        
	    try {
	    	conn = GetDBConn.getConnection();
			conn.setAutoCommit(false);
			
			
			sql = " UPDATE ICAN_MEMBER SET IM_RESIGN = 1 WHERE IM_IDX = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, mvo.getIm_idx());
			result = psmt.executeUpdate(); 
			psmt.close();
			
			nextsql = " UPDATE ICAN_MEM_EXP SET IME_EXIT_DATE = SYSDATE WHERE IME_IM_IDX = ? AND IME_EXIT_DATE IS NULL ";
			psmts = conn.prepareStatement(nextsql);
			cnt = 1;
			psmts.setInt(cnt++, mvo.getIm_idx());
			nextresult = psmts.executeUpdate();
			
			if((result > 0) && (nextresult> 0)){
				
				conn.commit();
				conn.setAutoCommit(true);
				return true;
				
			}else{
				conn.rollback();
				return false;
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
			
		}finally {
			GetDBConn.close(conn, psmts, rs);
		}
		
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<ProjectVO> getMemberProjects(ProjectVO pvo) {
		//TODO 아직안만들었어요!!
		return null;
	}
}
