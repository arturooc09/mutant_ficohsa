package co.com.adn.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static co.com.adn.adapter.router.RouterConsts.DEFAULT_VALUE;
import static com.google.common.base.Preconditions.checkNotNull;

public final class JsonUtils {

    public static String mapJsonToString(final ObjectMapper objectMapper, final Object obj) {
        checkNotNull(obj);
        String objJson;

        try {
            objJson = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            objJson = DEFAULT_VALUE;
        }

        return objJson;
    }
}
