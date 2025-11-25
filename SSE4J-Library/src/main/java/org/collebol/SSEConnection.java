package org.collebol;

import org.collebol.device.SSEDevice;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SSEConnection {

    private final SSE4J instance;

    private HttpClient sseClient = null;
    private String sseAddress = "";
    private HttpRequest sseRequest = null;
    private boolean connected = false;

    public SSEConnection(SSE4J instance) {
        this.instance = instance;
        connectToSSE();
    }

    public void registerGame() {
        JSONObject game = new JSONObject();
        game.put("game", instance.getGameName());
        game.put("game_display_name", instance.getGameName());
        game.put("developer", "ColleBol");
        executeRequest(game, "/game_metadata");
    }

    public void removeGame() {
        JSONObject game = new JSONObject();
        game.put("game", instance.getGameName());
        executeRequest(game, "/remove_game");
    }

    public void bindHandler(String eventName, SSEDevice device) throws Exception {
        JSONObject event = new JSONObject();
        event.put("game", instance.getGameName());
        event.put("event", eventName);
        event.put("min_value", device.getMinValue());
        event.put("max_value", device.getMaxValue());
        event.put("value_optional", true);
        event.put("icon_id", device.getIcon().getId());
        event.put("handlers", new JSONArray().put(device.getHandler()));
        executeRequest(event, "/bind_game_event");
    }

    public void sendEvent(String eventName, JSONObject data) {
        JSONObject event = new JSONObject();
        event.put("game", instance.getGameName());
        event.put("event", eventName);
        event.put("data", data);
        executeRequest(event, "/game_event");
    }

    private void executeRequest(JSONObject data, String param) {
        String dataParams = data.toString();
        try {
            if (!connected) return;

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(sseAddress + param))
                    .timeout(Duration.ofMillis(500))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(dataParams))
                    .build();

            if (instance.showConsoleDetails()) {
                IO.println(dataParams);
            }

            HttpResponse<String> response = sseClient.send(req, HttpResponse.BodyHandlers.ofString());

            if (response != null) {
                if (instance.showConsoleDetails()) {
                    IO.println("Request response: " + response.body());
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void connectToSSE() {
        String jsonAddress = "";

        // get coreProps.json and extract the address from that file
        // windows
        try {
            String corePropsFileName = System.getenv("PROGRAMDATA") + "\\SteelSeries\\SteelSeries Engine 3\\coreProps.json";
            BufferedReader coreProps = new BufferedReader(new FileReader(corePropsFileName));
            jsonAddress = coreProps.readLine();
            IO.println("coreProps.json out: " + jsonAddress);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            IO.println("coreProps.json file not found!");
        } catch (IOException e) {
            e.printStackTrace();
            IO.println("Something happened when reading coreProps.json!");
        }

        // if the address in coreProps.json is found we open a connection
        try {
            if (!jsonAddress.isEmpty()) {
                JSONObject obj = new JSONObject(jsonAddress);
                sseAddress = "http://" + obj.getString("address");
            }

            //client
            sseClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofMillis(10))
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();
            //request
            sseRequest = HttpRequest.newBuilder()
                    .uri(URI.create(sseAddress))
                    .timeout(Duration.ofMillis(10))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            connected = true;

        } catch (JSONException e) {
            e.printStackTrace();
            IO.println("Something went wrong creating JSONObject from coreProps.json!");
        }
    }
}
