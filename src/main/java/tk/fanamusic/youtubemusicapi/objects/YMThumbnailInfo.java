package tk.fanamusic.youtubemusicapi.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class YMThumbnailInfo {

    @SerializedName("thumbnails") private List<YMThumbnail> thumbnails;

}


