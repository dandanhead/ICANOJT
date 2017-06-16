package kr.co.ican.interfaces;

import kr.co.ican.vo.ExperienceVO;
import kr.co.ican.vo.MemberVO;

public interface MemberInterface {

	public MemberVO logininfo(MemberVO lvo);
	public MemberVO findID(MemberVO lvo);
	public MemberVO findPW(MemberVO lvo);
	public ExperienceVO getExperience(MemberVO lvo);
}
