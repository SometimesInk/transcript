package monk.transcript.event;

import monk.transcript.alert.Alert;
import monk.transcript.alert.AlertCheckResult;
import monk.transcript.alert.AlertHandler;
import monk.transcript.config.ConfigHandler;
import monk.transcript.util.Messaging;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class EventChat {
  @SubscribeEvent
  public void onChat(ClientChatReceivedEvent event) {
    List<Alert> matches = new ArrayList<Alert>();

    for (Alert alert : ConfigHandler.getInstance().configGet().targets) {
      if (!event.message.getUnformattedText().contains(alert.target)) continue;
      matches.add(alert);
    }
    if (matches.isEmpty()) return;

    // Check alerts
    AlertCheckResult output = AlertHandler.getInstance().check(event.message.getFormattedText(), matches);
    if (output.overrideMessage && output.message != null) {
      event.setCanceled(true);
      Messaging.sendMessage(output.message);
    }
  }
}
