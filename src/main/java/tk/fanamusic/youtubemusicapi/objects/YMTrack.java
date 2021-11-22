package tk.fanamusic.youtubemusicapi.objects;

public class YMTrack {

    private String videoId;

    public YMTrack(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getMixID() {
        return "RD" + videoId;
    }
}
