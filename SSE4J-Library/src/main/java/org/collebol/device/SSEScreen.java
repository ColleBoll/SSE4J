package org.collebol.device;

import org.json.JSONObject;

public class SSEScreen extends SSEDevice {

    public SSEScreen(String deviceType) {
        super(deviceType);
    }

    @Override
    public JSONObject createHandler(JSONObject... frames) {
        JSONObject handler = new JSONObject()
            .put("device-type", getDeviceType())
            .put("mode", "screen")
            .put("zone", "one")
            .put("datas", frames);
        setHandler(handler);
        return handler;
    }
}
