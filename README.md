# SSE4J – SteelSeries Engine for Java

**SSE4J** is a Java library for communicating with SteelSeries devices via the SteelSeries Engine. It allows you to register “games” and display information on device screens.  

## Requirements
- **Java**: version 25 or higher

## SSE4J-Spotify

> [!WARNING]
> **Note:** SSE4J-Spotify currently only works on **Windows**.

SSE4J-Spotify is a Java app for displaying Spotify details on SteelSeries OLED screens.  
You can download the `.jar` from the [releases](https://github.com/ColleBoll/SSE4J/releases).

### Usage

#### Display on all devices
This will display the currently playing artist and song on **all connected SteelSeries OLED screens**:

```bash
java -jar SSE4J-Spotify-file.jar
```

#### Display on a specific device
```bash
java -jar SSE4J-Spotify-file.jar -device=DEVICE_NAME
```

Replace `DEVICE_NAME` with the device you want to display Spotify information on. Available devices:

- **RIVAL** – Mouse  
- **APEX** – Keyboard  
- **ARCTIS** – Headset  
- **GAMEDAC** – Audio controller for Arctis Nova Pro
