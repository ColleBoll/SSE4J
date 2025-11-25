package org.collebol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class SpotifyInfo {
    public static String getSpotifyNow() {
        try {
            Process process = Runtime.getRuntime().exec(
                    "powershell \"Get-Process -Name Spotify | ForEach-Object { $_.MainWindowTitle }\""
            );
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String title = reader.readLine();
            if (Objects.equals(title, "Spotify Premium")) {
                title = null;
            }
            return title;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
