package co.appvigil.requestUtils;

public class CodeConstants{


	public static class SuccessCodes{

		public static final int SUCCESS = 200;
		public static final int	ACCESS_TOKEN_EXTENDED = 201;
		public static final int ACCESS_TOKEN_FLUSHED = 202;
		public static final int UPLOAD_SUCCESS = 203;
		public static final int SCAN_STARTED = 204;
		public static final int DIGEST_SUCCESS = 205;

	}

	public static class ErrorCodes{

		public static final int KEY_REQUIRED 			= 101;
		public static final int ACCESSTOKEN_NOT_GENERATE	= 102;
		public static final int INVALID_SECRET 		= 103;
		public static final int VALIDATION_ERROR 	 		= 104;
		public static final int INVALID_SECRET_LEN 	= 105;
		public static final int SPECIALCHAR_IN_KEY 	= 106;
		public static final int LIMIT_CROSS 			= 107;
		public static final int INVALID_TOKEN_LEN 	= 108;
		public static final int SPECIALCHAR_IN_TOKEN 	= 109;
		public static final int TOKEN_EXPIRED 		= 110;
		public static final int INVALID_TOKEN 		= 111;
		public static final int INVALID_UPLOAD_ID_TYPE = 112;
		public static final int INVALID_UPLOAD_ID 	= 113;
		public static final int INVALID_UPLOAD_FILE 	= 114;
		public static final int UPLOAD_ERROR 			= 115;
		public static final int INVALID_SCAN_ID_TYPE 	= 116;
		public static final int INVALID_SCAN_ID 		= 117;
		public static final int UN_AUTH_LAUNCH 		= 118;
		public static final int SYSTEM_DB_ERROR       = 119;
		public static final int UPLOAD_LIST_EMPTY     = 120;
		public static final int UPLOAD_ID_IN_PROCESS  = 121;
		public static final int INVALID_API_SECRET  = 122;
		public static final int INVALID_API_LEN  = 123;
		public static final int UN_AUTH_UPLOADID  = 124;
		public static final int UN_AUTH_SCANID  = 125;
		public static final int INTERNAL_ERROR_GEN_ACCESSTOKEN  = 126;
		public static final int INTERNAL_ERROR_RENEW  = 127;
		public static final int FLUSHED  = 128;
		public static final int INTERNAL_ERROR_FLUSH  = 129;
		public static final int INTERNAL_ERROR_FILE_UPLOAD = 130;
		public static final int INTERNAL_ERROR_REG_UPLOADID = 131;
		public static final int UPLOAD_EMPTY = 132;
		public static final int INTERNAL_ERROR_SCAN_QUEUE = 133;
		public static final int SCAN_EMPTY = 134;
		public static final int KEY_SECRET_EMPTY = 135;
		public static final int TOKEN_EMPTY = 136;
		public static final int TOKEN_FILE_EMPTY = 137;
		public static final int UPLOAD_ID_EMPTY = 138;
		public static final int TOKEN_SCANID_EMPTY = 139;
		public static final int USER_SUSPENDED = 140;
		public static final int SCAN_LIMIT_CROSSED = 141;

		public static final int SPECIALCHAR_IN_UPLOAD_REF = 142;
		public static final int INVALID_UPLOAD_REF_LEN = 143;
		public static final int SPECIALCHAR_IN_SCAN_REF = 144;
		public static final int INVALID_SCAN_REF_LEN = 145;
		public static final int APP_DIGEST_NOT_EXIST = 146;

		public static final int FIELD_MISSING = 147;
		public static final int EMAIL_REQUIRED = 148;
		public static final int NAME_REQUIRED = 149;
		public static final int CONTACT_REQUIRED = 150;
		public static final int EMAIL_NOT_VALID = 151;
		public static final int EMAIL_NOT_INSERT_LOGIN_TABLE = 152;

		public static final int USER_INSERT_ERROR = 153;
		public static final int USER_PREFERENCE_INSERT_ERROR = 154;
		public static final int USER_PLAN_INSERT_ERROR = 155;
		public static final int EMAIL_ALREADY_EXIST = 156;
		public static final int EMAIL_NOT_EXIST = 157;
		public static final int NAME_NOT_UPDATE = 158;

		public static final int USER_PLAN_EXPIRED = 159;
		public static final int UPLOAD_LIMIT_CROSSED = 160;
		public static final int USER_ID_NULL = 161;
		public static final int SERVER_SCAN_LIMIT_CROSSED = 162;

		public static final int ACCESSTOKEN_REQUIRED = 163;
		public static final int USERNAME_REQUIRED = 164;
		public static final int PASSWORD_REQUIRED = 165;
		public static final int SPECIALCHAR_IN_CREDENTIALS = 166;
		public static final int NUMBER_IN_USERNAME = 167;
		public static final int CREDENTIALS_TOO_LONG = 168;
		public static final int INTERNAL_ERROR_INSERT_CREDENTIAL = 169;
		public static final int SPECIALCHAR_IN_CREDENTIAL_REF = 170;
		public static final int CREDENTIAL_REF_LENGTH_INVALID = 171;
		public static final int CREDENTIAL_REF_NOT_EXIST = 172;
		public static final int INTERNAL_ERROR_UPDATE_CREDENTIAL = 173;
		public static final int UPDATE_FIELD_REQUIRED = 174;
		public static final int CREDENTIAL_REF_REQUIRED = 175;
		public static final int INTERNAL_ERROR_DELETE_CREDENTIAL = 176;
		public static final int CREDENTIAL_USER_ALREADY_EXIST = 177;
		public static final int GIVEN_CREDENTIALS_NOT_VALID = 178;
		public static final int APK_FILE_SIZE_EXCEEDED = 179;
		public static final int UNAUTHORIZED_TO_GENERATE_ACCESS_TOKEN = 180;
		public static final int PLAN_SCAN_LIMIT_CROSSED = 181;

		public static final int ECLIPSE_LICENSE_REQUIRED = 182;
		public static final int ECLIPSE_PRODUCT_ID_REQUIRED = 183;
		public static final int API_SECRET_REQUIRED = 184;
		public static final int API_KEY_REQUIRED = 185;
		public static final int INVALID_ECLIPSE_PRODUCT = 186;
		public static final int INVALID_ECLIPSE_LICENSE = 187;
		public static final int ECLIPSE_LICENSE_LENGTH_MISMACH = 188;
		public static final int NOT_UPDATE_LAST_VALIDATED_ON = 189;
		public static final int USER_AGENT_NOT_RECOGNIZE = 190;
		public static final int INSUFFICIENT_PARAMETER = 191;
		public static final int INCORRECT_PARAMETER = 192;
		public static final int REQUEST_LIMIT_CROSSED = 193;

		public static final int UNAUTHORIZED_ACCESS = 194;
		public static final int INVALID_DEVICE_ID = 195;
		public static final int NAME_NOT_VALID = 196;

		public static final int TTL_NOT_VALID = 197;
		public static final int APP_DIGEST_NOT_VALID = 198;
		public static final int INVALID_APP = 199;
		public static final int CREDENTIAL_LIST_EMPTY = 1101;

		public static final int INVALID_SCAN_STATUS = 401;

	}

}
