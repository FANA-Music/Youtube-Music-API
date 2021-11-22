package tk.fanamusic.youtubemusicapi.request.requests;

import com.google.gson.*;
import tk.fanamusic.youtubemusicapi.objects.YMPlaylistTrack;
import tk.fanamusic.youtubemusicapi.request.YMRequest;
import tk.fanamusic.youtubemusicapi.request.YMResponse;

import java.util.ArrayList;
import java.util.List;

public class OpenRadioRequest extends YMRequest<OpenRadioRequest.OpenRadioResponse> {

    private String videoId = null;

    public OpenRadioRequest(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String getRequestURL() {
        return "next";
    }

    @Override
    public JsonElement getBody() {
        JsonObject object = new JsonObject();
        if (videoId != null) object.addProperty("videoId", videoId);
        return object;
    }

    @Override
    public OpenRadioResponse newResponse(JsonElement response) {
        return new OpenRadioResponse(response);
    }

    public static class OpenRadioResponse extends YMResponse {

        public OpenRadioResponse(JsonElement response) {
            super(response);
            System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(response));
        }

        public List<YMPlaylistTrack> getTracks() {
            List<YMPlaylistTrack> out = new ArrayList<>();
            JsonElement response = getResponse();
            JsonObject jsonObject = response.getAsJsonObject();
            JsonObject contents = jsonObject.get("contents").getAsJsonObject();
            JsonObject singleColumnMusicWatchNextResultsRenderer = contents.get("singleColumnMusicWatchNextResultsRenderer").getAsJsonObject();
            JsonObject tabbedRenderer = singleColumnMusicWatchNextResultsRenderer.get("tabbedRenderer").getAsJsonObject();
            JsonObject watchNextTabbedResultsRenderer = tabbedRenderer.get("watchNextTabbedResultsRenderer").getAsJsonObject();
            JsonArray tabs = watchNextTabbedResultsRenderer.get("tabs").getAsJsonArray();
            for (JsonElement tab : tabs) {
                JsonObject tabRenderer = tab.getAsJsonObject().getAsJsonObject("tabRenderer");
                if (tabRenderer.get("title").getAsString().equals("Up next")) {
                    JsonArray tracks = tabRenderer.getAsJsonObject("content").getAsJsonObject("musicQueueRenderer").getAsJsonObject("content").getAsJsonObject("playlistPanelRenderer").getAsJsonArray("contents");
                    for (JsonElement track : tracks) {
                        JsonObject watchEndpoint = track.getAsJsonObject().getAsJsonObject("playlistPanelVideoRenderer").getAsJsonObject("navigationEndpoint").getAsJsonObject("watchEndpoint");
                        YMPlaylistTrack ymTrack = new YMPlaylistTrack(watchEndpoint.get("videoId").getAsString(), watchEndpoint.get("playlistId").getAsString());
                        out.add(ymTrack);
                    }
                }
            }
            return out;
        }
    }
}
