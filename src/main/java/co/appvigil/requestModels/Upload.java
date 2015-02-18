package co.appvigil.requestModels;

import co.appvigil.requestUtils.Model;


public class Upload  extends Model{
	
	private AccessToken accessToken;
	private String uploadId;	
	private String appLocation;
	private String appName;
	private String appSize;
	private String uploadDateTime;
	
	public AccessToken getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	public String getAppLocation() {
		return appLocation;
	}
	public void setAppLocation(String appLocation) {
		this.appLocation = appLocation;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppSize() {
		return appSize;
	}
	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}
	public String getUploadDateTime() {
		return uploadDateTime;
	}
	public void setUploadDateTime(String uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}
	
	

}
