import org.gnome.gtk.Test;
import tk.fanamusic.youtubemusicapi.YoutubeMusicAPI;
import tk.fanamusic.youtubemusicapi.classes.YMPlaylistTrack;
import tk.fanamusic.youtubemusicapi.request.requests.OpenRadioRequest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) throws Exception{
        InputStream headerIn = new FileInputStream("key.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(headerIn));
        String header = "";
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            header += line + "\n";
        }
        print("Key: ", header);

        YoutubeMusicAPI api = new YoutubeMusicAPI.Builder(header)
                .build();
        OpenRadioRequest.OpenRadioResponse response = api.request(new OpenRadioRequest("mcPkJKkqeUg"));
        for (YMPlaylistTrack track : response.getTracks()) {
            System.out.println("https://music.youtube.com/watch?v=" + track.getVideoId() + "&list=" + track.getPlaylistId());
        }
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
