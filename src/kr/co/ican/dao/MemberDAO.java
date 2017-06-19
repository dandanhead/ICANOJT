package kr.co.ican.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kr.co.ican.dbconn.GetDBConn;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemberVO;

public class MemberDAO {
	
	private static MemberDAO memdao;

	public static MemberDAO getInstance() {
		
		if (memdao == null) {
			
			memdao = new MemberDAO();
		}
		return memdao;
	}

	public MemberVO logininfo(MemberVO lvo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        MemberVO vo = new MemberVO();
        
        int cnt = 1;
        int is = 0;
        
		try {
            // DB conn
            conn = GetDBConn.getConnection();
            
            String sql = " SELECT * "
            		   + " FROM ICAN_MEMBER WHERE IM_IDX = ? AND IM_PW = ? ";
            
            psmt = conn.prepareStatement(sql);
            psmt.setInt(cnt++, lvo.getIm_idx());
            psmt.setString(cnt++, lvo.getIm_pw());
            rs =psmt.executeQuery(); 
            
            
            
            while (rs.next()) {
            	cnt = 1;
            	vo.setIm_idx(rs.getInt(cnt++));
            	vo.setIm_pw(rs.getString(cnt++));
            	vo.setIm_dname(rs.getString(cnt++));
            	vo.setIm_name(rs.getString(cnt++));
            	vo.setIm_phone(rs.getString(cnt++));
            	vo.setIm_email(rs.getString(cnt++));
            	vo.setIm_resign(rs.getInt(cnt++));
            	vo.setIm_status(rs.getInt(cnt++));
            	vo.setIm_scnum(rs.getString(cnt++));
            	vo.setIm_address(rs.getString(cnt++));
            	vo.setIm_detailaddr(rs.getString(cnt++));
            	vo.setIm_postcode(rs.getString(cnt++));
            	vo.setIm_auth(rs.getInt(cnt++));
            
            	is++;
            }
 
        } catch (Exception e) {
 
            System.err.println("Oracle Database Connection Something Problem.");
 
            System.out.println(e.getMessage());
 
            e.printStackTrace();
            	
            
 
        } finally {
            //DB close
            GetDBConn.close(conn, psmt, rs);
		}
		
	   if(is == 0){
       		return null;
       		
       }else{
    	   return vo;
       }
	}

	public MemberVO findID(MemberVO lvo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        MemberVO mvo = new MemberVO();
        
        int cnt = 1;
        int is = 0;
        
        try {
        	
        	conn = GetDBConn.getConnection();
        	
        	 String sql = " select im_idx "
             	        + " from ican_member where im_name = ? and im_scnum = ? ";
             
             psmt = conn.prepareStatement(sql);
             psmt.setString(cnt++, lvo.getIm_name());
             psmt.setString(cnt++, lvo.getIm_scnum());
             rs =psmt.executeQuery();
             
             cnt = 1;
             while (rs.next()) {
            	 mvo.setIm_idx(rs.getInt(cnt++));
             	is++;
             }
			
		} catch (Exception e) {
            System.err.println("Oracle Database Connection Something Problem.");
            
            System.out.println(e.getMessage());
 
            e.printStackTrace();
		}finally {
			
			GetDBConn.close(conn, psmt, rs);
		}
		
        if(is == 0){
        	return null;
        }else{
        	return mvo;
        }
        
	}

	public MemberVO findPW(MemberVO lvo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        MemberVO mvo = new MemberVO();
        
        int cnt = 1;
        int is = 0;
        
        try {
        	
        	conn = GetDBConn.getConnection();
        	
        	 String sql = " select im_pw "
             	        + " from ican_member where im_name = ? and im_scnum = ? and im_email = ? ";
             
             psmt = conn.prepareStatement(sql);
             psmt.setString(cnt++, lvo.getIm_name());
             psmt.setString(cnt++, lvo.getIm_scnum());
             psmt.setString(cnt++, lvo.getIm_email());
             rs =psmt.executeQuery();
             
             cnt = 1;
             while (rs.next()) {
            	 mvo.setIm_pw(rs.getString(cnt++));
             	is++;
             }
			
		} catch (Exception e) {
            System.err.println("Oracle Database Connection Something Problem.");
            
            System.out.println(e.getMessage());
 
            e.printStackTrace();
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
		
        if(is == 0){
        	return null;
        }else{
        	return mvo;
        }
	}

	public ExperienceVO getExperience(MemberVO lvo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
        ResultSet rs = null;
        ExperienceVO evo = new ExperienceVO();
        
        int cnt = 1;
        int is = 0;
        try {
			conn = GetDBConn.getConnection();
			
			 String sql = " select * from ican_mem_exp where ime_exit_date is null and ime_im_idx = ? ";
			 
			 psmt = conn.prepareStatement(sql);
			 
			 psmt.setInt(1, lvo.getIm_idx());
			 
			 rs = psmt.executeQuery();
			 
			 cnt = 1;
			 while (rs.next()) {
			
				evo.setIme_im_idx(rs.getInt(cnt++));
				evo.setIme_regi_date(rs.getString(cnt++));
				evo.setIme_exit_date(rs.getString(cnt++));
				evo.setIme_coname(rs.getString(cnt++));
				evo.setIme_auth(rs.getInt(cnt++));
				
				is++;
			}
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			GetDBConn.close(conn, psmt, rs);
		}
        
        if(is == 0){
        	return null;
        }else{
        	return evo;
        }
        
	}
	
}
