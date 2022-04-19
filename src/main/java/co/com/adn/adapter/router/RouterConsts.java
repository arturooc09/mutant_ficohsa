package co.com.adn.adapter.router;

public class RouterConsts {

    public RouterConsts() {
        super();
    }

    public static final String INITIAL_PATH = "/";
    public static final String CSRF_PATH = "/csrf";
    public static final String CONTROLLER_PATH = "/mutant/v1";
    public static final String MUTANT_PATH = "/mutant";
    public static final String STATS_PATH = "/stats";


     /**
     * Request Headers
     */
    public static final String HEADER_SESSION_ID = "session_id";
    public static final String HEADER_CHANNEL_ID = "channel-id";
    public static final String HEADER_APPLICATION = "application";
    public static final String HEADER_TIMESTAMP = "timestamp";

    public static final String ADN_ERROR_SIZE_MATRIX = "1-INF";
    public static final String ADN_ERROR_DESC_MATRIX = "La matriz no tiene el tamaño minimo requerido";

    public static final String ADN_ERROR_SIZE_MATRIX2 = "2-INF";
    public static final String ADN_ERROR_DESC_MATRIX2 = "La matriz no es NXN";

    /**
     * Swagger Documentation
     */
    public static final String SWAG_API_TAGS = "adn-ms";
    public static final String SWAG_OPERATION_VALUE = "Validates if a person is mutant ot not.";
    public static final String SWAG_OPERATION_NOTES = "Service that validates the adn of person.";
    public static final String SWAG_RESPONSE_200 = "Returns an http code 200 if the person is a mutant.";
    public static final String SWAG_RESPONSE_400 = "Some input parameter is missing.";
    public static final String SWAG_RESPONSE_404 = "Resource not found.";
    public static final String SWAG_RESPONSE_403 = "Not mutant.";
    public static final String SWAG_RESPONSE_422 = "Malformed data";
    public static final String SWAG_RESPONSE_500 = "Unknown error";
    public static final String DEFAULT_VALUE = "";


}
