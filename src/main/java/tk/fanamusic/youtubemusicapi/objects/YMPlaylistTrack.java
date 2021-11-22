package tk.fanamusic.youtubemusicapi.objects;

public class YMPlaylistTrack extends YMTrack {

    private String playlistId;

    public YMPlaylistTrack(String videoId, String playlistId) {
        super(videoId);
        this.playlistId = playlistId;
    }

    public String getPlaylistId() {
        return playlistId;
    }
}
