package co.appvigil.requestModels;
import co.appvigil.requestUtils.Model;

public class AccessToken extends Model{

	private String accessToken;
	private int timetoLive;
	private String issueDateTime;

	public String getAccessTokenString() {
		return accessToken;
	}

	public void setAccessTokenString(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getTimetoLive() {
		return timetoLive;
	}

	public void setTimetoLive(int timetoLive) {
		this.timetoLive = timetoLive;
	}

	public String getIssueDateTime() {
		return issueDateTime;
	}

	public void setIssueDateTime(String issueDateTime) {
		this.issueDateTime = issueDateTime;
	}
	
	

	
}

