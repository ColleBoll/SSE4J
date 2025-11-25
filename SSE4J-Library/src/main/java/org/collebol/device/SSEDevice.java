package org.collebol.device;

import org.collebol.SSEIcon;
import org.json.JSONObject;

public abstract class SSEDevice {

    public final static String SCREEN = "screened";
    public final static String RIVAL_SCREEN = "screened-128x36";
    public final static String APEX_SCREEN = "screened-128x40";
    public final static String ARCTIS_SCREEN = "screened-128x48";
    public final static String GAMEDAC_SCREEN = "screened-128x52";

    private final String deviceType;

    private int minValue = 0;
    private int maxValue = 100;
    private SSEIcon icon = SSEIcon.DEFAULT;
    private JSONObject handler = null;

    public SSEDevice(String deviceType) {
        this.deviceType = deviceType;
    }

    public abstract JSONObject createHandler(JSONObject... frames);

    public String getDeviceType() {
        return deviceType;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public SSEIcon getIcon() {
        return icon;
    }

    public void setIcon(SSEIcon icon) {
        this.icon = icon;
    }

    public JSONObject getHandler() throws Exception {
        if (handler == null) throw new Exception("You have to create the handler first before getting it!");
        return handler;
    }

    public void setHandler(JSONObject handler) {
        this.handler = handler;
    }
}
