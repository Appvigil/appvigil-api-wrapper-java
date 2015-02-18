package co.appvigil.requestUtils;

import java.util.List;

public class Response {
	
	private Model requestModel;
	private int code;
	private String message;
	private List<Model> requestModels;
	
	public Model getRequestModel() {
		return requestModel;
	}
	public void setRequestModel(Model requestModel) {	
		this.requestModel = requestModel;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Model> getRequestModels() {
		return requestModels;
	}
	public void setRequestModels(List<Model> requestModels) {
		this.requestModels = requestModels;
	}
	
	public void setRequestModels(Model requestModel) {
		this.requestModels.add(requestModel);
	}	

}
