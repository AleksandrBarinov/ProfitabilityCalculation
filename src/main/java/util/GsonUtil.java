package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtil {

    private static final GsonUtil instance = new GsonUtil();

    public static GsonUtil getInstance() {
        return instance;
    }

    private static JsonParser parser = new JsonParser();
    private static Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();

    public JsonObject getJsonObject(String jsonData){
        JsonObject responseJO;
        responseJO = parser.parse(jsonData).getAsJsonObject();
        return responseJO;
    }
}
