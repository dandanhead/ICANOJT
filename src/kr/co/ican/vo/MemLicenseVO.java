package kr.co.ican.vo;

public class MemLicenseVO {

    private int iml_im_idx;
	private String iml_lname;
	
	public MemLicenseVO() {
	}
	
	public MemLicenseVO(int iml_im_idx, String iml_lname) {
		super();
		this.iml_im_idx = iml_im_idx;
		this.iml_lname = iml_lname;
	}
	public int getIml_im_idx() {
		return iml_im_idx;
	}
	public void setIml_im_idx(int iml_im_idx) {
		this.iml_im_idx = iml_im_idx;
	}
	public String getIml_lname() {
		return iml_lname;
	}
	public void setIml_lname(String iml_lname) {
		this.iml_lname = iml_lname;
	}
	
	@Override
	public String toString() {
		return "MemLicenseVO [iml_im_idx=" + iml_im_idx + ", iml_lname=" + iml_lname + "]";
	}
}
