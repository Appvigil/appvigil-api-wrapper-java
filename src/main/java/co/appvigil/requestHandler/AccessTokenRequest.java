package co.appvigil.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import co.appvigil.exceptions.AccessTokenRequestException;
import co.appvigil.exceptions.ImproperKeyException;
import co.appvigil.requestModels.AccessToken;
import co.appvigil.requestUtils.Connection;
import co.appvigil.requestUtils.Constants;
import co.appvigil.requestUtils.Response;


public class AccessTokenRequest {
	
	private Connection connection;
    private String apiKey;
    private String apiSecret;

	
	public AccessTokenRequest(String apiKey,String apiSecret) {
		
		connection = new Connection();
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}

	private boolean validate(String...requestString){

		int i;		
		for(i=0;i<requestString.length;i++){

			switch(requestString[i].split(":")[0]){

			case "product_id":
			case "license_key":
			case "ttl":
			case "appvigil_server_key":
			case "appvigil_app_key":continue;
			default: return false;

			}
		}
		return true;
	}
	
	private List<NameValuePair> getParams(String[] requestString) {

		int i;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(i=0;i<requestString.length;i++){
			
			params.add(new BasicNameValuePair(requestString[i].split("=")[0], requestString[i].split("=")[1]));
			
		}
		return params;
		
	}



	public Response requestNewAccessToken(String...requestString) throws JSONException, ImproperKeyException, ClientProtocolException, IOException
	{
		if(requestString.length > 5){

			throw new ImproperKeyException();
		}
		else{

			if(validate(requestString)){

				List<NameValuePair> params = getParams(requestString);
				params.add(new BasicNameValuePair("api_key", apiKey));
				params.add(new BasicNameValuePair("api_secret", apiSecret));
				JSONObject jObj=connection.get(Constants.ACCESS_TOKEN_NEW, params);
				JSONObject response = null,meta;
				int code = 0;
				String message = null;
				Response responseData = new Response();
				
				response=(JSONObject) jObj.get("response");
				meta=(JSONObject) jObj.get("meta");
				code=meta.getInt("code");
				message=(String) response.get("message");
				responseData.setCode(code);
				responseData.setMessage(message);
				
				if (code==200)
				{
					AccessToken accessToken = new AccessToken();
					accessToken.setAccessTokenString(response.getString("access_token"));
					accessToken.setTimetoLive(response.getInt("ttl_in_seconds"));
					responseData.setRequestModel(accessToken);
					
				}
				return responseData;

			}
			else{
				throw new ImproperKeyException();
			}
		}


	}





	public Response renewAccessToken(AccessToken accessToken,String...timeToLive) throws JSONException, AccessTokenRequestException, ClientProtocolException, IOException
	{
	
		if(accessToken != null){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			
			if(timeToLive.length == 1){
				String key = timeToLive[0].split("=")[0];
				if(key == "new_ttl")
					params.add(new BasicNameValuePair(key, timeToLive[0].split("=")[1]));
			}
			JSONObject jObj=connection.get(Constants.ACCESS_TOKEN_RENEW, params);
			JSONObject response = null,meta;
			int code = 0;
			String message = null;
			Response responseData = new Response();
			response=(JSONObject) jObj.get("response");
			meta=(JSONObject) jObj.get("meta");
			code=meta.getInt("code");
			message=(String) response.get("message");
			responseData.setCode(code);
			responseData.setMessage(message);
			
			if(code==201){	

				accessToken.setAccessTokenString(response.getString("access_token"));
				accessToken.setTimetoLive(response.getInt("ttl_in_seconds"));
				responseData.setRequestModel(accessToken);

			} 

			return responseData;
		}
		else{
			throw new AccessTokenRequestException();
		}

	}
	
	

	public Response ViewAccessToken(AccessToken accessToken) throws JSONException, AccessTokenRequestException, ClientProtocolException, IOException
	{
	
		if(accessToken != null){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));	
			JSONObject jObj=connection.get(Constants.ACCESS_TOKEN_VIEW, params);
			JSONObject response = null,meta;
			int code = 0;
			String message = null;
			Response responseData = new Response();
			response=(JSONObject) jObj.get("response");
			meta=(JSONObject) jObj.get("meta");
			code=meta.getInt("code");
			message=(String) response.get("message");
			responseData.setCode(code);
			responseData.setMessage(message);
			
			if(code==200){	

				accessToken.setAccessTokenString(response.getString("access_token"));
				accessToken.setTimetoLive(response.getInt("ttl_in_seconds"));
				accessToken.setIssueDateTime(response.getString("issue_date_time"));
				responseData.setRequestModel(accessToken);

			} 

			return responseData;
		}
		else{
			throw new AccessTokenRequestException();
		}

	}


	public Response flushAccessToken(AccessToken accessToken) throws JSONException, AccessTokenRequestException, ClientProtocolException, IOException
	{
		
		if(accessToken != null){
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));

			JSONObject jObj=connection.get(Constants.ACCESS_TOKEN_FLUSH, params);
			JSONObject response = null,meta;
			int code = 0;
			String message = null;	
			Response responseData = new Response();
			response=(JSONObject) jObj.get("response");
			meta=(JSONObject) jObj.get("meta");
			code=meta.getInt("code");
			message=(String) response.get("message");			
			responseData.setCode(code);
			responseData.setMessage(message);

			if(code==202)
			{
				responseData.setRequestModel(null);
			}
			
			return responseData;
		}
		else{
			throw new AccessTokenRequestException();
		}

	}



}
