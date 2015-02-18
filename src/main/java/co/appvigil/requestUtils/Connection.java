package co.appvigil.requestUtils;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


public class Connection {

	//private static Log logger=LogFactory.getLog(Connection.class);

	private static String getUrl(String resource)
	{
		return Constants.PROTO + Constants.HOST + Constants.VERSION + resource;
	}

	public JSONObject post(String resource,String accessToken,String appName,String appLocation,String appDigest) throws ClientProtocolException, IOException {


		InputStream inputStream = null;
		JSONObject responseJSONObject = null;
		String responseJsonString = "";		

		StringBody accessTokenString = new StringBody(accessToken);
		StringBody appNameString = new StringBody(appName);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(getUrl(resource));
		MultipartEntity Entity = new MultipartEntity();

		if (appLocation != null)
			Entity.addPart("app", new FileBody(new File(appLocation)));

		if(appDigest !=null)
			Entity.addPart("app_digest", new StringBody(appDigest));		

		Entity.addPart("access_token", accessTokenString);	
		Entity.addPart("app_name", appNameString);	
		httpPost.setEntity(Entity);		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();

		inputStream = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream, "iso-8859-1"), 8);

		StringBuilder stringBuilder = new StringBuilder();
		String line = null;

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line + "\n");
		}

		inputStream.close();
		reader.close();
		responseJsonString = stringBuilder.toString();
		responseJSONObject = new JSONObject(responseJsonString);		
		return responseJSONObject;

	}


	public JSONObject get(String resource, List<NameValuePair> params) throws ClientProtocolException, IOException {

		InputStream inputStream = null;
		JSONObject responseJSONObject = null;
		String responseJsonString = "";
		String URL = getUrl(resource);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String paramString = URLEncodedUtils.format(params, "utf-8");
		URL += "?" + paramString;
		HttpGet httpGet = new HttpGet(URL);
		httpGet.setHeader(HttpHeaders.USER_AGENT, "JAVA_CLI");
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		inputStream = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream, "iso-8859-1"), 8);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}

		inputStream.close();
		reader.close();
		responseJsonString = sb.toString();
		responseJSONObject = new JSONObject(responseJsonString);
		return responseJSONObject;

	}


















}
