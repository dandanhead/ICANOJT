package kr.co.ican.vo;

public class MemLicenseVO {

    private int iml_im_idx;
	private String iml_lname;
	private String iml_acudate;
	private String iml_organization;
	
	public MemLicenseVO() {
	}
	
	public MemLicenseVO(int iml_im_idx, String iml_lname, String iml_acudate, String iml_organization) {
		this.iml_im_idx = iml_im_idx;
		this.iml_lname = iml_lname;
		this.iml_acudate = iml_acudate;
		this.iml_organization = iml_organization;
	}
	
	public String getIml_acudate() {
		return iml_acudate;
	}

	public void setIml_acudate(String iml_acudate) {
		this.iml_acudate = iml_acudate;
	}

	public String getIml_organization() {
		return iml_organization;
	}

	public void setIml_organization(String iml_organization) {
		this.iml_organization = iml_organization;
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
		return "MemLicenseVO [iml_im_idx=" + iml_im_idx + ", iml_lname=" + iml_lname + ", iml_acudate=" + iml_acudate
				+ ", iml_organization=" + iml_organization + "]";
	}
}
