package tk.fanamusic.youtubemusicapi.request;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class YMResponse {

    private JsonElement response;
    protected final Gson gson;
    public YMResponse(JsonElement response) {
        this.response = response;
        this.gson = new Gson();
    }

    public JsonElement getResponse() {
        return response;
    }
}
