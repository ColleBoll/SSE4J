package org.collebol;

public class SSE4J {

    private final String gameName;
    private SSEConnection connection = null;
    private boolean showConsoleDetails = false;

    public SSE4J(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void createConnection() {
        if (connection != null) return;
        this.connection = new SSEConnection(this);
    }

    public SSEConnection getConnection() throws Exception {
        if (connection == null) throw new Exception("Before getting a connection you have to create one first!");
        return this.connection;
    }

    public boolean showConsoleDetails() {
        return showConsoleDetails;
    }

    public void setShowConsoleDetails(boolean details) {
        this.showConsoleDetails = details;
    }
}
