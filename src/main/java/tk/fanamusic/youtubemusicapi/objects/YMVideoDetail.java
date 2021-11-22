package tk.fanamusic.youtubemusicapi.objects;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class YMVideoDetail {

    @SerializedName("videoId")  private String videoId;
    @SerializedName("title")  private String title;
    @SerializedName("lengthSeconds")  private String lengthSeconds;
    @SerializedName("channelId") private String channelId;
    @SerializedName("isOwnerViewing") private boolean ownerViewing;
    @SerializedName("isCrawlable") private boolean crawlable;
    @SerializedName("thumbnail") private YMThumbnailInfo thumbnailInfo;
    @SerializedName("averageRating") private float averageRating;
    @SerializedName("allowRatings") private boolean allowRatings;
    @SerializedName("viewCount") private String viewCount;
    @SerializedName("author") private String author;
    @SerializedName("isPrivate") private boolean videoPrivate;
    @SerializedName("isUnpluggedCorpus") private boolean unpluggedCorpus;
    @SerializedName("musicVideoType") private YMMusicVideoType musicVideoType;
    @SerializedName("isLiveContent") private boolean liveContent;

    public int getLengthSeconds() {
        return Integer.parseInt(lengthSeconds);
    }

    public int getViewCount() {
        return Integer.parseInt(viewCount);
    }

    public YMVideoDetail() {}


}
