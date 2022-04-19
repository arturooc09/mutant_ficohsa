package co.com.adn.utils.json;

public class JsonParseException extends RuntimeException {
    private static final long serialVersionUID = 99620802121525743L;

    public JsonParseException(Exception e) {

        super(e);
    }
}
