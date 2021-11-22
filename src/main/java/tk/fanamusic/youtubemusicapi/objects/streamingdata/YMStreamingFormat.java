package tk.fanamusic.youtubemusicapi.objects.streamingdata;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tk.fanamusic.youtubemusicapi.objects.YMRange;

import java.lang.reflect.Type;

@Getter
@AllArgsConstructor
public class YMStreamingFormat {

    @SerializedName("itag") private int itag;
    @SerializedName("url") private String url;
    @SerializedName("mimeType") private String mimeType;
    @SerializedName("bitrate") private int bitrate;
    @SerializedName("lastModified") private String lastModified;
    @SerializedName("contentLength") private String contentLength;
    @SerializedName("quality") private String quality;
    @SerializedName("averageBitrate") private int averageBitrate;
    @SerializedName("projectionType") private String projectionType;
    @SerializedName("approxDurationMs") private String approxDurationMs;

    public YMStreamingFormat() {}


    @Getter public static class YMStreamingAdaptiveFormat extends YMStreamingFormat {
        @SerializedName("initRange") private YMRange initRange;
        @SerializedName("indexRange") private YMRange indexRange;
    }
    @Getter public static class YMStreamingAdaptiveVideoFormat extends YMStreamingAdaptiveFormat {
        @SerializedName("width") private int width;
        @SerializedName("height") private int height;
        @SerializedName("fps") private int fps;
        @SerializedName("qualityLabel") private String qualityLabel;
    }
    @Getter public static class YMStreamingAdaptiveAudioFormat extends YMStreamingAdaptiveFormat {
        @SerializedName("audioQuality") private String audioQuality;
        @SerializedName("audioSampleRate") private String audioSampleRate;
        @SerializedName("audioChannels") private String audioChannels;
    }
    @Getter public static class YMStreamingVideoFormat extends YMStreamingFormat {

        @SerializedName("width") private int width;
        @SerializedName("height") private int height;
        @SerializedName("fps") private int fps;
        @SerializedName("qualityLabel") private String qualityLabel;
        @SerializedName("audioQuality") private String audioQuality;
        @SerializedName("audioSampleRate") private String audioSampleRate;
        @SerializedName("audioChannels") private String audioChannels;
    }


    public static class YMStreamingFormatDeserializer implements JsonDeserializer<YMStreamingFormat> {

        protected Gson gson;
        public YMStreamingFormatDeserializer(Gson gson) {
            this.gson = gson;
        }


        @Override
        public YMStreamingFormat deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement.isJsonObject()) {
                JsonObject object = jsonElement.getAsJsonObject();
                if (object.has("initRange")) {
                    if (object.has("fps")) {
                        return gson.fromJson(jsonElement, YMStreamingAdaptiveVideoFormat.class);
                    } else {
                        return gson.fromJson(jsonElement, YMStreamingAdaptiveAudioFormat.class);
                    }
                } else {
                    return gson.fromJson(jsonElement, YMStreamingVideoFormat.class);
                }
            }
            return new YMStreamingFormat();
        }
    }

}
