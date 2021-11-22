package tk.fanamusic.youtubemusicapi.objects;

import com.google.gson.annotations.SerializedName;

public class YMRange {
    @SerializedName("start") private String start;
    @SerializedName("end") private String end;

    public long getStart() {
        return Long.parseLong(start);
    }
    public long getEnd() {
        return Long.parseLong(end);
    }
}
