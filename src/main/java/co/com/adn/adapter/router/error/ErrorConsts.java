package co.com.adn.adapter.router.error;

public class ErrorConsts {

    public ErrorConsts() {
        super();
    }


    /**
     * Error Service Constants
     **/
    public static final String CUST_INFO_SERVICE_CODE = "CUSI";

    /**
     * Error Code Constants
     */
    public static final String GENERIC_ERROR = "-9999";
    public static final String PIPE = "|";

    /**
     * General Error Constants
     */
    public static final String DEFAULT_CODE_BAD_REQUEST = "E0400";
    public static final String DEFAULT_CODE_NOT_FOUND = "E0404";
    public static final String DEFAULT_CODE_ERROR = "";

    public static final String DEFAULT_ERROR_CODE_DOCUMENT_TYPE = CUST_INFO_SERVICE_CODE + "0001" + PIPE + "1";
    public static final String DEFAULT_ERROR_MESSAGE_DOCUMENT_TYPE = "No se encontró el tipo de documento.";
}
