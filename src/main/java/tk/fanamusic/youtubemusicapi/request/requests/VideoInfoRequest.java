package tk.fanamusic.youtubemusicapi.request.requests;

import com.google.gson.*;
import lombok.Getter;
import tk.fanamusic.youtubemusicapi.objects.streamingdata.YMStreamingData;
import tk.fanamusic.youtubemusicapi.objects.YMVideoDetail;
import tk.fanamusic.youtubemusicapi.objects.streamingdata.YMStreamingFormat;
import tk.fanamusic.youtubemusicapi.request.YMRequest;
import tk.fanamusic.youtubemusicapi.request.YMResponse;

public class VideoInfoRequest extends YMRequest<VideoInfoRequest.VideoInfoResponse> {
    @Override
    public boolean isRequiredAndroid() {
        return true;
    }

    private String videoId;

    public VideoInfoRequest(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String getRequestURL() {
        return "player";
    }

    @Override
    public JsonElement getBody() {
        JsonObject object = new JsonObject();
        if (videoId != null) object.addProperty("videoId", videoId);
        return object;
    }

    @Override
    public VideoInfoResponse newResponse(JsonElement response) {
        return new VideoInfoResponse(response);
    }

    @Getter
    public static class VideoInfoResponse extends YMResponse {

        private YMVideoDetail detail;
        private YMStreamingData streamingData;

        public VideoInfoResponse(JsonElement response) {
            super(response);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(YMStreamingFormat.class, new YMStreamingFormat.YMStreamingFormatDeserializer(super.gson))
                    .create();
            JsonObject object = response.getAsJsonObject();
            detail = gson.fromJson(object.get("videoDetails"), YMVideoDetail.class);
            streamingData = gson.fromJson(object.get("streamingData"), YMStreamingData.class);
        }
    }
}
