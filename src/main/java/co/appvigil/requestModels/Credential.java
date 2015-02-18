package co.appvigil.requestModels;

import co.appvigil.requestUtils.Model;


public class Credential extends Model{

	private AccessToken accessToken;
	private String credentialId;
	private String username;
	private String password;
	
	public AccessToken getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}
	public String getCredentialId() {
		return credentialId;
	}
	public void setCredentialId(String credentialId) {
		this.credentialId = credentialId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
