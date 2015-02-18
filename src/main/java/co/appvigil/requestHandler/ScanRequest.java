package co.appvigil.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import co.appvigil.exceptions.ScanRequestException;
import co.appvigil.requestModels.AccessToken;
import co.appvigil.requestModels.Scan;
import co.appvigil.requestModels.Upload;
import co.appvigil.requestUtils.Connection;
import co.appvigil.requestUtils.Constants;
import co.appvigil.requestUtils.Response;



public class ScanRequest {


	private AccessToken accessToken;
	private Connection connection;

	public ScanRequest(AccessToken accessToken) {

		this.accessToken = accessToken;
		connection = new Connection();
	}



	private Map<String,String> validateScanStringAndGetResultMap(String[] scanArgs) {

		Map<String,String> argMap = new HashMap<String, String>();
		if(scanArgs != null){
			int i;
			for(i=0;i<scanArgs.length;i++){

				String key = scanArgs[0].split("=")[0];
				String value = scanArgs[0].split("=")[1];

				if(key != null & value != null){

					switch(key){
					case "credential_id":
					case "scan_type":argMap.put(key, value);
					break;
					default: break;
					}
				}
			}
			return argMap;
		}
		return null;
	}



	public Response startScan(Upload upload,String...scanString) throws JSONException, ScanRequestException, ClientProtocolException, IOException
	{
		if(upload != null && upload.getUploadId().length() == 40){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			params.add(new BasicNameValuePair("upload_id",upload.getUploadId()));

			if(scanString.length > 2){
				throw new ScanRequestException();
			}


			if( validateScanStringAndGetResultMap(scanString) != null && validateScanStringAndGetResultMap(scanString).get("credential_id") != null ){
				params.add(new BasicNameValuePair("credential_id",validateScanStringAndGetResultMap(scanString).get("credential_id")));
			}

			if(validateScanStringAndGetResultMap(scanString) != null && validateScanStringAndGetResultMap(scanString).get("scan_type") != null){
				params.add(new BasicNameValuePair("scan_type", validateScanStringAndGetResultMap(scanString).get("scan_type")));
			}

			JSONObject jObj=connection.get(Constants.SCAN_START, params);
			JSONObject response = null,meta;
			int code = 0;
			String message;
			Response responseData = new Response();
			response=(JSONObject) jObj.get("response");
			meta=(JSONObject) jObj.get("meta");
			code=meta.getInt("code");
			message=response.getString("message");
			responseData.setCode(code);
			responseData.setMessage(message);


			if(code==204)
			{
				Scan scan = new Scan();
				scan.setAccessToken(accessToken);
				scan.setUpload(upload);
				scan.setScanId(response.getString("scan_id"));
				responseData.setRequestModel(scan);

			}
			return responseData;
		}
		else{
			throw new ScanRequestException();
		}
	}


	public Response scanStatus(Scan scan) throws ScanRequestException, ClientProtocolException, IOException
	{

		if(scan != null && scan.getScanId().length() == 60){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			params.add(new BasicNameValuePair("scan_id",scan.getScanId()));
			JSONObject jObj=connection.get(Constants.SCAN_STATUS, params);
			JSONObject response = null,meta;
			int code = 0;
			String message;
			Response responseData = new Response();
			response=(JSONObject) jObj.get("response");
			meta=(JSONObject) jObj.get("meta");
			code=meta.getInt("code");
			message=response.getString("message");
			responseData.setCode(code);
			responseData.setMessage(message);
			if(code == 200){
				
				scan.setScan_status(response.getString("scan_status"));
				scan.setScan_date_time(response.getString("scan_date_time"));
				scan.setFinish_date_time(response.getString("finish_date_time"));
				scan.setScan_status(response.getString("scan_status"));
				
				if(scan.getScan_status().equals("Finished"))
					scan.setReport_url(response.getString("report_url"));

			}

			return responseData;
		}
		else{
			throw new ScanRequestException();
		}

	}


	public Response listScan(int statusType) throws ScanRequestException, ClientProtocolException, IOException
	{

		if(accessToken != null){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			params.add(new BasicNameValuePair("status_type",String.valueOf(statusType)));
			JSONObject jObj=connection.get(Constants.SCAN_LIST, params);
			JSONObject response = null,meta;
			int code = 0;
			String message;
			Response responseData = new Response();
			response=(JSONObject) jObj.get("response");
			meta=(JSONObject) jObj.get("meta");
			code=meta.getInt("code");
			message=response.getString("message");
			responseData.setCode(code);
			responseData.setMessage(message);

			if(code == 200){				

				int count = 0;

				while(true){

					try{

						JSONObject indivResponse = (JSONObject) response.get(String.valueOf(count));
						Scan scan = new Scan();
						scan.setScanId(indivResponse.getString("scan_id"));
						scan.setScan_status(indivResponse.getString("scan_status"));
						scan.setScan_date_time(indivResponse.getString("scan_date_time"));
						scan.setAccessToken(accessToken);
						scan.setFinish_date_time(indivResponse.getString("finish_date_time"));
						Upload upload = new Upload();
						JSONObject uploadResponse = (JSONObject) indivResponse.get("upload_details");
						upload.setUploadId(uploadResponse.getString("upload_id"));
						upload.setAppName(uploadResponse.getString("file_name"));
						upload.setUploadDateTime(uploadResponse.getString("upload_on"));
						scan.setUpload(upload);
						responseData.setRequestModels(scan);
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
			throw new ScanRequestException();
		}

	}


	public Response stopScan(Scan scan) throws ScanRequestException, ClientProtocolException, IOException
	{

		if(scan != null){

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("access_token", accessToken.getAccessTokenString()));
			params.add(new BasicNameValuePair("scan_id",scan.getScanId()));
			JSONObject jObj=connection.get(Constants.SCAN_STOP, params);
			JSONObject response = null,meta;
			int code = 0;
			String message;
			Response responseData = new Response();
			response=(JSONObject) jObj.get("response");
			meta=(JSONObject) jObj.get("meta");
			code=meta.getInt("code");
			message=response.getString("message");
			responseData.setCode(code);
			responseData.setMessage(message);

			if(code == 200){

				responseData.setRequestModel(null);
			}

			return responseData;
		}
		else{
			throw new ScanRequestException();
		}		


	}



}
