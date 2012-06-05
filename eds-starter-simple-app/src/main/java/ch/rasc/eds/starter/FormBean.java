package ch.rasc.eds.starter;

public class FormBean {

	private String osName;

	private String osVersion;

	private int availableProcessors;

	private String remarks;

	public String getOsName() {
		return osName;
	}

	public void setOsName(final String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(final String osVersion) {
		this.osVersion = osVersion;
	}

	public int getAvailableProcessors() {
		return availableProcessors;
	}

	public void setAvailableProcessors(final int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "FormBean [osName=" + osName + ", osVersion=" + osVersion + ", availableProcessors="
				+ availableProcessors + ", remarks=" + remarks + "]";
	}

}
