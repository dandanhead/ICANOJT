package kr.co.ican.services;

import kr.co.ican.dao.MemberDAO;
import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	private static MemberServiceImpl memservice;

	public static MemberServiceImpl getInstance() {
		
		if (memservice == null) {
			
			memservice = new MemberServiceImpl();
		}
		return memservice;
	}
	
	private MemberDAO memdao = MemberDAO.getInstance();
		
	@Override
	public MemberVO logininfo(MemberVO lvo) {
		
		return memdao.logininfo(lvo);
	}

	@Override
	public MemberVO findID(MemberVO lvo) {
		return memdao.findID(lvo);
	}

	@Override
	public MemberVO findPW(MemberVO lvo) {
		return memdao.findPW(lvo);
	}

	@Override
	public ExperienceVO getExperience(MemberVO lvo) {
		// TODO Auto-generated method stub
		return null;
	}

}
