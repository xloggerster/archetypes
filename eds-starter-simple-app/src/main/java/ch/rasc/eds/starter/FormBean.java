package ch.rasc.eds.starter;

public class FormBean {

	private String osName;

	private String osVersion;

	private int availableProcessors;
	
	private String remarks;

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public int getAvailableProcessors() {
		return availableProcessors;
	}

	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "FormBean [osName=" + osName + ", osVersion=" + osVersion + ", availableProcessors="
				+ availableProcessors + ", remarks=" + remarks + "]";
	}

}
