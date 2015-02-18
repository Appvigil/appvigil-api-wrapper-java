package co.appvigil.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import co.appvigil.exceptions.CredentialRequestException;
import co.appvigil.requestModels.AccessToken;
import co.appvigil.requestModels.Credential;
import co.appvigil.requestUtils.Connection;
import co.appvigil.requestUtils.Constants;
import co.appvigil.requestUtils.Response;



public class CredentialRequest {


	private AccessToken accessToken;
	private Connection connection;

	public CredentialRequest(AccessToken accessToken) {

		this.accessToken = accessToken;
		connection = new Connection();
	}

	public Response createCredential(String username,String password) throws ClientProtocolException, IOException, CredentialRequestException{

		if(username.length() >=1 && password.length() >= 1){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			JSONObject jObj=connection.get(Constants.CREDENTIAL_REQUEST, params);
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

			if(code==208){	

				Credential credential = new Credential();
				credential.setAccessToken(accessToken);
				credential.setCredentialId(response.getString("credential_id"));
				credential.setPassword(password);
				credential.setUsername(username);
				responseData.setRequestModel(credential);

			} 
			return responseData;
		}
		else{
			throw new CredentialRequestException();
		}
	}

	public Response deleteCredential(Credential credential) throws CredentialRequestException, ClientProtocolException, IOException{


		if(credential != null){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			params.add(new BasicNameValuePair("credential_id", credential.getCredentialId()));

			JSONObject jObj=connection.get(Constants.CREDENTIAL_DELETE, params);
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

			if(code==210){	

				responseData.setRequestModel(null);
			}

			return responseData;

		}
		else{
			throw new CredentialRequestException();
		}

	}



	public Response updateCredential(Credential credential,String username,String password) throws CredentialRequestException, ClientProtocolException, IOException{


		if(credential != null){


			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			params.add(new BasicNameValuePair("credential_id", credential.getCredentialId()));
			JSONObject jObj=connection.get(Constants.CREDENTIAL_UPDATE, params);
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
			if(code==209){	

				credential.setAccessToken(accessToken);
				credential.setCredentialId(response.getString("credential_id"));
				credential.setPassword(password);
				credential.setUsername(username);
				responseData.setRequestModel(credential);

			}
			return responseData;
		}
		else{
			throw new CredentialRequestException();
		}
	}


	public Response listCredentials() throws CredentialRequestException, ClientProtocolException, IOException{


		if(accessToken != null){


			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			JSONObject jObj=connection.get(Constants.CREDENTIAL_LIST, params);
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

				int count = 0;

				while(true){

					try{
						JSONObject indivResponse = (JSONObject) response.get(String.valueOf(count));
						Credential credential = new Credential();
						credential.setCredentialId(indivResponse.getString("credential_id"));
						credential.setUsername(indivResponse.getString("username"));
						credential.setPassword(indivResponse.getString("password"));
						credential.setAccessToken(accessToken);
						responseData.setRequestModels(credential);
					}
					catch(Exception e){
						break;
					}
					count = count + 1;
				}

			}
			return responseData;
		}
		else{
			throw new CredentialRequestException();
		}
	}


}



