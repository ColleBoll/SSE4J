package org.collebol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class SpotifyInfo {
    public static String getSpotifyNow() {
        try {
            Process process = Runtime.getRuntime().exec(
                    "powershell \"Get-Process -Name Spotify | ForEach-Object { $_.MainWindowTitle }\""
            );
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> title = reader.readAllLines();
            if (title.contains("Spotify Premium")) {
                return null;
            }
            return title.get(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
