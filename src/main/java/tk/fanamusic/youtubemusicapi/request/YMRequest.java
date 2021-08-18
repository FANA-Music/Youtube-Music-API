package tk.fanamusic.youtubemusicapi.request;

import com.google.gson.JsonElement;

public abstract class YMRequest<Response extends YMResponse> {

    public String getRequestMethod() {
        return "POST";
    }
    public abstract String getRequestURL();
    public abstract JsonElement getBody();
    public abstract Response newResponse(JsonElement response);

}
