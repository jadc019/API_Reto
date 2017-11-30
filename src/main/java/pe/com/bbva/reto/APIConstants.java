package pe.com.bbva.reto;

public class APIConstants {
	
	public static final String IND_SI = "S";
	public static final String IND_NO = "N";
	
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_CT_FORM = "application/x-www-form-urlencoded";
	public static final String HEADER_CT_FORM_UTF8 = HEADER_CT_FORM + ";charset=UTF-8"; 
	public static final String CT_CHARSET_UTF8 = ";charset=UTF-8";
	public static final String HEADER_CT_JSON = "application/json"; 
	public static final String HEADER_CT_JSON_UTF8 = HEADER_CT_JSON + CT_CHARSET_UTF8;
	
	public static final String CODE_OK 			= "0";
	public static final String CODE_ERROR 		= "1";
	public static final String CODE_VALIDATION 	= "2";

	public static final int HTTP_STATUS_CODE_OK 				= 200;
	public static final int HTTP_STATUS_CODE_CREATED 			= 201;
	public static final int HTTP_STATUS_CODE_BAD_REQUEST		= 400;
	public static final int HTTP_STATUS_CODE_NOT_FOUND 			= 404;
	public static final int HTTP_STATUS_CODE_INTERNAL_ERROR 	= 500;
	
	public static final String PU_RETOBBVA = "retobbvaPU";
}
