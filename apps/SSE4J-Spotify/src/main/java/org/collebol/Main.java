package org.collebol;

import org.collebol.device.SSEDevice;
import org.collebol.device.SSEScreen;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static SSE4J game = new SSE4J("SSE4J-SPOTIFY");
    public static String device = null;

    static void main(String[] args) throws Exception {
        for (String arg : args) {
            if (arg.startsWith("-device=")) {
                device = arg.substring("-device=".length());
            }
        }

        Main main = new Main();
        game.createConnection();
        game.getConnection().registerGame();
        main.buildSpotifyDetails();
        main.sendSpotifyDetails();
    }

    public void buildSpotifyDetails() throws Exception {
        SSEScreen screen;
        switch (device) {
            case "RIVAL" -> screen = new SSEScreen(SSEDevice.RIVAL_SCREEN);
            case "APEX" -> screen = new SSEScreen(SSEDevice.APEX_SCREEN);
            case "ARCTIS" -> screen = new SSEScreen(SSEDevice.ARCTIS_SCREEN);
            case "GAMEDAC" -> screen = new SSEScreen(SSEDevice.GAMEDAC_SCREEN);
            case null -> screen = new SSEScreen(SSEDevice.SCREEN);
            default -> throw new IllegalStateException("Unexpected value: " + device);
        }
        JSONObject lines = new JSONObject()
                .put("lines", new JSONArray()
                        .put(new JSONObject()
                                .put("has-text", true)
                                .put("context-frame-key", "title")
                        )
                        .put(new JSONObject()
                                .put("has-text", true)
                                .put("context-frame-key", "song")
                        )
                        .put(new JSONObject()
                                .put("has-progress-bar", true)
                        )
                );

        screen.createHandler(lines);
        game.getConnection().bindHandler("SHOW_SONG", screen);
    }

    public void sendSpotifyDetails() throws Exception {
        int pos = 0;
        while (true) {
            String spotify = SpotifyInfo.getSpotifyNow();
            if (spotify != null) {
                String song = rotate16(spotify, pos);
                game.getConnection().sendEvent("SHOW_SONG",
                        new JSONObject()
                                .put("value", 100) //progress bar value :)
                                .put("frame", new JSONObject()
                                        .put("title", "SPOTIFY")
                                        .put("song", song)
                                        .put("numbericalvalue", 88)
                                )
                );
                pos++;
                Thread.sleep(300);
            }
        }
    }

    public String rotate16(String text, int position) {
        final int WIDTH = 16;

        String padded = text + "      ";

        StringBuilder frame = new StringBuilder();

        for (int i = 0; i < WIDTH; i++) {
            int index = (position + i) % padded.length();
            frame.append(padded.charAt(index));
        }

        return frame.toString();
    }
}
