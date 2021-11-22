import tk.fanamusic.youtubemusicapi.YoutubeMusicAPI;
import tk.fanamusic.youtubemusicapi.objects.YMPlaylistTrack;
import tk.fanamusic.youtubemusicapi.objects.streamingdata.YMStreamingFormat;
import tk.fanamusic.youtubemusicapi.request.requests.OpenRadioRequest;
import tk.fanamusic.youtubemusicapi.request.requests.VideoInfoRequest;

import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) throws Exception{

        YoutubeMusicAPI api = new YoutubeMusicAPI.Builder()
                .build();

        OpenRadioRequest.OpenRadioResponse response = api.request(new OpenRadioRequest("TFALdg7HUJU"));
        for (YMPlaylistTrack track : response.getTracks()) {
            System.out.println("https://music.youtube.com/watch?v=" + track.getVideoId() + "&list=" + track.getPlaylistId());
        }

        VideoInfoRequest.VideoInfoResponse videoInfo = api.request(new VideoInfoRequest("TFALdg7HUJU"));
        for (YMStreamingFormat format : videoInfo.getStreamingData().getAdaptiveFormats()) {
            if (format instanceof YMStreamingFormat.YMStreamingAdaptiveAudioFormat) {
                System.out.println("Download audio:  Bitrate: " + format.getBitrate() + "  URL: " + format.getUrl());
            }
        }
        System.out.println(videoInfo.getDetail().getTitle());
    }

    private static String input() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static String input(String message) {
        System.out.print(message);
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void print(Object... objects) {
        StringBuilder builder = new StringBuilder();
        for (Object object : objects) {
            builder.append(object);
        }
        System.out.println(builder.toString());
    }

}
