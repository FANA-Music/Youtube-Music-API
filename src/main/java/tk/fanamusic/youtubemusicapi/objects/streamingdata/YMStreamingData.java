package tk.fanamusic.youtubemusicapi.objects.streamingdata;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class YMStreamingData {

    private long createdTime = System.currentTimeMillis();

    @SerializedName("expiresInSeconds") private int expiresInSeconds;
    @SerializedName("formats") private List<YMStreamingFormat> formats;
    @SerializedName("adaptiveFormats") private List<YMStreamingFormat> adaptiveFormats;

    public long getExpireUnitTime() {
        return createdTime + expiresInSeconds*1000L;
    }

    public YMStreamingData() {
    }
}
