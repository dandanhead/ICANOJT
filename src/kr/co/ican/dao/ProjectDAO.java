package kr.co.ican.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.ican.dbconn.GetDBConn;
import kr.co.ican.vo.ProjectVO;

public class ProjectDAO {

	private static ProjectDAO pdao;
	
	public static ProjectDAO getInstance(){
		
		if(pdao == null){
		
			pdao = new ProjectDAO();
		}
		
		return pdao;
	}
	
	public boolean addProject(ProjectVO pvo){
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int cnt = 1;
        int result = 0;
        try {
			conn = GetDBConn.getConnection();
			sql = " INSERT INTO ICAN_PROJECT_LIST"
				+ "  ( IPL_IDX, IPL_PNAME, IPL_SDATE, IPL_EPTDATE, IPL_EDATE, IPL_CONTENT, "
				+ "    IPL_DOC, IPL_CHARGE, IPL_CLIENT, IPL_ADDRESS, IPL_DETAILADDR , IPL_POSTCODE, IPL_SKILL) "
				+ " VALUES( PROJECT_LIST_SEQ.NEXTVAL, ? , ? , ? , NULL , ? , ? , ? , ? , ? , ? , ? , ? ) ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(cnt++, pvo.getIpl_pname());
			psmt.setString(cnt++, pvo.getIpl_sdate());
			psmt.setString(cnt++, pvo.getIpl_eptdate());
			psmt.setString(cnt++, pvo.getIpl_content());
			psmt.setString(cnt++, pvo.getIpl_doc());
			psmt.setString(cnt++, pvo.getIpl_charge());
			psmt.setString(cnt++, pvo.getIpl_client());
			psmt.setString(cnt++, pvo.getIpl_address());
			psmt.setString(cnt++, pvo.getIpl_detailaddr());
			psmt.setString(cnt++, pvo.getIpl_postcode());
			psmt.setString(cnt++, pvo.getIpl_skill());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
        
        return result > 0 ? true : false;
	}
	
	public int getTotalProjectCount(){
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int result= 0;
        
        try {
			conn = GetDBConn.getConnection();
			 sql = " SELECT NVL(COUNT(*), 0) AS CNT FROM ICAN_PROJECT_LIST ";
			 psmt = conn.prepareStatement(sql);
			 rs = psmt.executeQuery();
			 
				while (rs.next()) {
			   		 
					result = rs.getInt(1);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}

        return result;
		
	}
	
	public List<ProjectVO> getProjectList(ProjectVO pvo) throws SQLException{
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        
        List<ProjectVO> plist = new ArrayList<ProjectVO>();
        int cnt = 1;
        String sql = "";
        
        try {
			conn = GetDBConn.getConnection();
			sql = " SELECT "
				+ "         IPL_IDX, "
				+ "			IPL_PNAME, "
				+ "			IPL_SDATE, "
				+ "			IPL_EPTDATE, "
				+ "			IPL_EDATE, "
				+ "			IPL_CONTENT, "
				+ "			IPL_DOC, "
				+ "			IPL_CHARGE, "
				+ "			IPL_CLIENT, "
				+ "			IPL_ADDRESS, "
				+ "			IPL_DETAILADDR, "
				+ "			IPL_POSTCODE, "
				+ "			IPL_SKILL "
				+ " FROM ( "
				+ "			SELECT   "
				+ "					ROW_NUMBER() OVER (ORDER BY IPL_IDX) AS RNUM,   "
				+ "					IPL_IDX, IPL_PNAME ,"
				+ "					IPL_SDATE, IPL_EPTDATE, "
				+ "					IPL_EDATE, IPL_CONTENT, "
				+ "					IPL_DOC, IPL_CHARGE, "
				+ "					IPL_CLIENT, "
				+ "					IPL_ADDRESS, "
				+ "					IPL_DETAILADDR, "
				+ "					IPL_POSTCODE, "
				+ "					IPL_SKILL  "
				+ "			FROM  "
				+ "					ICAN_PROJECT_LIST )"
				+ " WHERE  RNUM BETWEEN ? AND ?  ";
			
			psmt =conn.prepareStatement(sql);
			psmt.setInt(cnt++, pvo.getStart());
			psmt.setInt(cnt++, pvo.getEnd());
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				cnt = 1;
				ProjectVO vo = new ProjectVO();
				
				vo.setIpl_idx(rs.getInt(cnt++));
				vo.setIpl_pname(rs.getString(cnt++));
				vo.setIpl_sdate(rs.getString(cnt++));
				vo.setIpl_eptdate(rs.getString(cnt++));
				vo.setIpl_edate(rs.getString(cnt++));
				vo.setIpl_content(rs.getString(cnt++));
				vo.setIpl_doc(rs.getString(cnt++));
				vo.setIpl_charge(rs.getString(cnt++));
				vo.setIpl_client(rs.getString(cnt++));
				vo.setIpl_address(rs.getString(cnt++));
				vo.setIpl_detailaddr(rs.getString(cnt++));
				vo.setIpl_postcode(rs.getString(cnt++));
				vo.setIpl_skill(rs.getString(cnt++));
				
				plist.add(vo);
				
			}
				
		} catch (Exception e) {
			e.getStackTrace();
			
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
        
        return plist;
		
	}
	
	public ProjectVO getProjectDetail(ProjectVO pvo){
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "";
        int cnt = 1;
        ProjectVO vo = new ProjectVO();
        
        try {
			conn = GetDBConn.getConnection();
			sql = " SELECT * FROM ICAN_PROJECT_LIST WHERE IPL_IDX = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(cnt++, pvo.getIpl_idx());
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				cnt = 1;
				vo.setIpl_idx(rs.getInt(cnt++));
				vo.setIpl_pname(rs.getString(cnt++));
				vo.setIpl_sdate(rs.getString(cnt++));
				vo.setIpl_eptdate(rs.getString(cnt++));
				vo.setIpl_edate(rs.getString(cnt++));
				vo.setIpl_content(rs.getString(cnt++));
				vo.setIpl_doc(rs.getString(cnt++));
				vo.setIpl_charge(rs.getString(cnt++));
				vo.setIpl_client(rs.getString(cnt++));
				vo.setIpl_address(rs.getString(cnt++));
				vo.setIpl_detailaddr(rs.getString(cnt++));
				vo.setIpl_postcode(rs.getString(cnt++));
				vo.setIpl_skill(rs.getString(cnt++));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			GetDBConn.close(conn, psmt, rs);
		}
        	
        return vo;
		
	}
}
