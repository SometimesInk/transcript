package monk.transcript.sound;

import monk.transcript.alert.Alert;
import monk.transcript.config.ConfigHandler;
import net.minecraft.client.Minecraft;

import java.util.List;

public class Pinging {
  public static void ping(String message, List<Alert> matches) {
    Minecraft.getMinecraft().thePlayer.playSound("transcript:ping",
        ConfigHandler.getInstance().configGet().pingVolume, 1.0F);
  }
}
