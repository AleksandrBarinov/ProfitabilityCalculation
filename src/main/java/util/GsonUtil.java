package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtil {

    private static final GsonUtil instance = new GsonUtil();

    public static GsonUtil getInstance() {
        return instance;
    }

    private static JsonParser parser = new JsonParser();

    public JsonObject getJsonObject(String jsonData){
        JsonObject responseJO;
        responseJO = parser.parse(jsonData).getAsJsonObject();
        return responseJO;
    }
}
