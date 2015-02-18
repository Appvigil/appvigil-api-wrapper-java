package co.appvigil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.json.JSONException;

import co.appvigil.exceptions.ImproperKeyException;
import co.appvigil.exceptions.ScanRequestException;
import co.appvigil.requestHandler.AccessTokenRequest;
import co.appvigil.requestHandler.ScanRequest;
import co.appvigil.requestHandler.UploadRequest;
import co.appvigil.requestModels.AccessToken;
import co.appvigil.requestModels.Scan;
import co.appvigil.requestModels.Upload;
import co.appvigil.requestUtils.Response;


public class ScanLauncher {

	private static String apiKey;
	private static  String apiSecret;
	private static  String appLocation;
	private static  String appName;
	private static  String credentialIds;
	private  static String timeToLive;
	private  static String digestCheck;


	private static Options options = new Options();


	public static void main(String[] args) throws IOException, org.apache.commons.cli.ParseException, JSONException, ImproperKeyException, NoSuchAlgorithmException, ScanRequestException
	{
		parse(args);
		
		System.out.println("Requesting for the Access token. Please Wait......");
		AccessTokenRequest accessTokenHandler = new AccessTokenRequest(apiKey,apiSecret);
		Response response = accessTokenHandler.requestNewAccessToken();
		AccessToken accessToken = (AccessToken) response.getRequestModel();
		System.out.println("Access Token is: "+accessToken.getAccessTokenString());
		System.out.println("Access token has been generated. Please Wait for file to be uploaded to Appvigil Cloud......");
		UploadRequest uploadHandler = new UploadRequest(accessToken);
		System.out.println(".............................................................................................");
		response = uploadHandler.newUpload(appLocation);
		
		Upload upload = (Upload) response.getRequestModel();
		System.out.println("Your Upload Id is :"+upload.getUploadId());
		System.out.println("Your file has been uploaded and the scan is ready to be launched. Please Wait................");
		ScanRequest scanHandler = new ScanRequest(accessToken);
		response = scanHandler.startScan(upload);
		Scan scan = (Scan) response.getRequestModel();
		System.out.println("Your scan has been launched and the scan id is "+scan.getScanId());
		
		

	}
	// command line parser
	public static void parse(String[] args) throws org.apache.commons.cli.ParseException {

		options.addOption("K","api-key", true, "* Require the ApiKey before executing your jar");
		options.addOption("S","api-secret", true, "* Require the ApiSecret before executing your jar");
		options.addOption("L","app-loc", true, "* Require the AppLocation before start your scan");
		options.addOption("A","app-name", true, "Specify your own name to app");
		options.addOption("C","credential-id", true, "Specify test credential");
		options.addOption("t", "ttl", true, "(optional) value to set ttl(TIME TO LIVE) for access_token");
		options.addOption("h", "help", false, "Show this message");		
		options.addOption("d","disable_digest_check", false, "Disable the digest check");

		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;
		cmd = parser.parse(options, args);
		if (cmd.hasOption("h"))
			help();

		if (cmd.hasOption("d"))
			digestCheck = "false";

		if (cmd.hasOption("t"))
			timeToLive=cmd.getOptionValue("ttl");

		if (cmd.hasOption("api-key")) {
			apiKey=cmd.getOptionValue("api-key");

		} else {
			System.out.println("Missing api-key option");
			System.exit(1);
		}
		if (cmd.hasOption("api-secret")) {
			apiSecret=cmd.getOptionValue("api-secret");			

		} else {
			System.out.println("Missing api-secret option");
			System.exit(1);
		}
		if (cmd.hasOption("app-loc")) {
			appLocation=cmd.getOptionValue("app-loc");			

		} else {
			System.out.println("Missing app-loc option");
			System.exit(1);
		}

		if (cmd.hasOption("A"))
		{ appName=cmd.getOptionValue("app-name");

		}
		if (cmd.hasOption("C"))
		{ 
			credentialIds=cmd.getOptionValue("C");
			List<String> items = Arrays.asList(credentialIds.split("\\s*,\\s*"));
			for(int i=0;i<items.size();i++)
			{
				if(items.get(i).length()!=8)
				{
					System.out.println("credential_id must be of 8 character length");
					System.exit(0);

				}
				if(!(items.get(i).matches("[A-Za-z0-9]{8}")))
				{
					System.out.println("credential_id should not contain special characters");
					System.exit(0);
				}
			}

		}

	}


	private static void help() {
		HelpFormatter formater = new HelpFormatter();
		formater.printHelp("Main", options);
		System.exit(0);
	}
}
