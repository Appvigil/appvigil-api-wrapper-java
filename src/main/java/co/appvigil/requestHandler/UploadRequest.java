package co.appvigil.requestHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import co.appvigil.exceptions.UploadRequestException;
import co.appvigil.requestModels.AccessToken;
import co.appvigil.requestModels.Upload;
import co.appvigil.requestUtils.Connection;
import co.appvigil.requestUtils.Constants;
import co.appvigil.requestUtils.Response;


public class UploadRequest {

	private Connection connection;
	private AccessToken accessToken;

	public UploadRequest(AccessToken accessToken) {

		connection = new Connection();
		this.accessToken = accessToken;
	}

	public Response newUpload(String appLocation,String...uploadString) throws NoSuchAlgorithmException , JSONException, ClientProtocolException, IOException
	{
		String appName = null,digest = "false";
		boolean isDigest = true;
		if(uploadString.length > 0){

			int i;

			for(i=0;i<uploadString.length;i++){

				String key = uploadString[i].split("=")[0];
				String value = uploadString[i].split("=")[1];
				if(key.equals("appName") && value.length() > 0)
					appName = uploadString[i].split("=")[1];

				if(key.equals("digest") && value.equals("false"))
					isDigest = false;
			}
		}

		if(appName == null)
			appName = (new File(appLocation)).getName();

		if(isDigest)
			digest = appDigest(appLocation,"SHA-256");
		


		JSONObject jObj=connection.post(Constants.UPLOAD_NEW,accessToken.getAccessTokenString(),appName,appLocation,digest);
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

		if(code==203||code==205)
		{
			Upload upload = new Upload();
			upload.setAccessToken(accessToken);
			upload.setAppLocation(appLocation);
			upload.setAppName(appName);
			upload.setUploadId(response.getString("upload_id"));
			responseData.setRequestModel(upload);

		}

		return responseData;	

	}


	public Response uploadList(String...uploadString) throws JSONException, UploadRequestException, ClientProtocolException, IOException
	{		
		if(accessToken != null){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if(uploadString.length > 0){

				int i;
				for(i=0;i<uploadString.length;i++){

					String key = uploadString[i].split("=")[0];
					String value = uploadString[i].split("=")[1];
					if(  (key.equals("count") && value != null && value.length() > 0) || (key.equals("this_ses") && (value.equals("true") || value.equals("false")))  )
						params.add(new BasicNameValuePair(key, value));			

				}
			}			

			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));

			JSONObject jObj=connection.get(Constants.UPLOAD_LIST, params);
			JSONObject response = null,meta;
			int code = 0;
			String message = null;
			Response responseData = new Response();
			response=(JSONObject) jObj.get("response");
			meta=(JSONObject) jObj.get("meta");
			code=meta.getInt("code");
			message=response.getString("message");
			responseData.setCode(code);
			responseData.setMessage(message);

			if(code==200){

				int count = 0;
				
				while(true){
					
					try{
						JSONObject indivResponse = (JSONObject) response.get(String.valueOf(count));
						Upload upload = new Upload();
						upload.setUploadId(indivResponse.getString("upload_id"));
						upload.setAppName(indivResponse.getString("app_name"));
						responseData.setRequestModels(upload);
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
			throw new UploadRequestException();
		}

	}

	public Response uploadDetails(Upload upload) throws JSONException, UploadRequestException, ClientProtocolException, IOException
	{		
		if(accessToken != null && upload != null){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			params.add(new BasicNameValuePair("upload_id",upload.getUploadId()));
			JSONObject jObj=connection.get(Constants.UPLOAD_DETAILS, params);
			JSONObject response = null,meta;
			int code = 0;
			String message = null;
			Response responseData = new Response();
			response=(JSONObject) jObj.get("response");
			meta=(JSONObject) jObj.get("meta");
			code=meta.getInt("code");
			message=response.getString("message");
			responseData.setCode(code);
			responseData.setMessage(message);

			if(code==200){

				upload.setAppSize(response.getString("app_size_in_bytes"));
				upload.setUploadDateTime(response.getString("upload_date_time"));
				responseData.setRequestModel(upload);
			}
			return responseData;
		}
		else{
			throw new UploadRequestException();
		}

	}



	private String appDigest(String file, String algorithm) throws NoSuchAlgorithmException
	{
		//convert the byte to hex format
		StringBuffer sb = new StringBuffer("");
		try {


			{
				MessageDigest md = null;
				try {
					md = MessageDigest.getInstance(algorithm);

				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				FileInputStream fis = new FileInputStream(new File(file));

				byte[] dataBytes = new byte[1024];


				int nread = 0; 

				while ((nread = fis.read(dataBytes)) != -1) {
					md.update(dataBytes, 0, nread);
				};

				byte[] mdbytes = md.digest();


				for (int i = 0; i < mdbytes.length; i++) {
					sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				fis.close();
			}



		} catch ( IOException ex) {
			//logger.error(
			//"Could not generate hash from file", ex);
			ex.printStackTrace();
		}

		return sb.toString(); 
	}

}
