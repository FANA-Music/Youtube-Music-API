package tk.fanamusic.youtubemusicapi;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.*;
import tk.fanamusic.youtubemusicapi.request.YMRequest;
import tk.fanamusic.youtubemusicapi.request.YMResponse;

import java.util.Objects;

public class YoutubeMusicAPI {

    protected final OkHttpClient httpClient;
    private final String key;
    private final String baseUrl;
    private final Gson gson = new Gson();

    public static class Builder {
        private String key = "AIzaSyC9XL3ZjWddXya6X74dJoCTL-WEYFDNX30";
        private String baseUrl = "https://music.youtube.com/youtubei/v1/";

        public Builder() {
        }

        public Builder setBaseURL(String baseUrl) { this.baseUrl = baseUrl; return this; }
        public Builder setKey(String key) { this.key = key; return this; }

        public YoutubeMusicAPI build() {
            return new YoutubeMusicAPI(key, baseUrl);
        }

    }

    protected YoutubeMusicAPI(String key, String baseUrl) {
        this.key = key;
        this.baseUrl = baseUrl;
        this.httpClient = new OkHttpClient.Builder()
                .build();
    }

    public <ResponseParser extends YMResponse> ResponseParser request(YMRequest<ResponseParser> request) {
        JsonElement body = request.getBody();
        if (!body.getAsJsonObject().has("context")) {
            JsonObject contextObject = new JsonObject();
            JsonObject clientObject = new JsonObject();
            if (request.isRequiredAndroid()) {
                clientObject.addProperty("clientName", "ANDROID");
                clientObject.addProperty("clientVersion", "16.20");
            } else {
                clientObject.addProperty("clientName", "WEB_REMIX");
                clientObject.addProperty("clientVersion", "1.20210804.00.00");
            }
            contextObject.add("client", clientObject);
            body.getAsJsonObject().add("context", contextObject);
        }
        Request req = new Request.Builder()
                .url(baseUrl + request.getRequestURL())
                .post(RequestBody.create(
                        gson.toJson(body), MediaType.parse("application/json")))
                .build();
        boolean hasOrigin = req.header("Origin") != null;
        boolean hasKey = req.url().queryParameter("key") != null;
        Request out = req;
        if (!hasKey) {
            out = out.newBuilder()
                    .url(req.url().newBuilder().setQueryParameter("key", getKey()).build())
                    .build();
        }
        if (!hasOrigin) {
            out = out.newBuilder()
                    .header("Origin", "http://music.youtube.com")
                    .build();
        }
        Call call = httpClient.newCall(out);
        try {
            Response response = call.execute();
            return request.newResponse(gson.fromJson(Objects.requireNonNull(response.body()).string(), JsonElement.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getKey() {
        return key;
    }
}
