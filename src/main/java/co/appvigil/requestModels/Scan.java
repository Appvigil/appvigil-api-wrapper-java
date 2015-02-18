package co.appvigil.requestModels;

import java.util.List;

import co.appvigil.requestUtils.Model;

public class Scan  extends Model{

	private AccessToken accessToken;
	private Upload upload;
	private List<Credential> credentials;
	private String scan_id;
	private String scan_status;
	private String scan_date_time;
	private String finish_date_time;
	private String report_url;
	
	public AccessToken getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}
	public Upload getUpload() {
		return upload;
	}
	public void setUpload(Upload upload) {
		this.upload = upload;
	}
	public List<Credential> getCredentials() {
		return credentials;
	}
	public void setCredentials(List<Credential> credentials) {
		this.credentials = credentials;
	}
	public void setCredentials(Credential credential) {
		this.credentials.add(credential);
	}
	public String getScanId() {
		return scan_id;
	}
	public void setScanId(String scan_id) {
		this.scan_id = scan_id;
	}
	public String getScan_status() {
		return scan_status;
	}
	public void setScan_status(String scan_status) {
		this.scan_status = scan_status;
	}
	public String getScan_date_time() {
		return scan_date_time;
	}
	public void setScan_date_time(String scan_date_time) {
		this.scan_date_time = scan_date_time;
	}
	public String getFinish_date_time() {
		return finish_date_time;
	}
	public void setFinish_date_time(String finish_date_time) {
		this.finish_date_time = finish_date_time;
	}
	public String getReport_url() {
		return report_url;
	}
	public void setReport_url(String report_url) {
		this.report_url = report_url;
	}

	
	
}
