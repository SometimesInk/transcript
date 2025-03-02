package monk.transcript.event;

import com.mojang.realmsclient.gui.ChatFormatting;
import monk.transcript.alert.Alert;
import monk.transcript.config.ConfigHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventChat {
  @SubscribeEvent
  public void onChat(ClientChatReceivedEvent event) {
    for (Alert alert : ConfigHandler.getInstance().configGet().targets) {
      if (!event.message.getUnformattedText().contains(alert.target)) return;

      String highlight;
      if (alert.types.contains(Alert.Type.HIGHLIGHT_STRIKETHROUGH)) highlight = ChatFormatting.STRIKETHROUGH.toString();
      if (alert.types.contains(Alert.Type.HIGHLIGHT_OBFUSCATED)) highlight = ChatFormatting.OBFUSCATED.toString();
      if (alert.types.contains(Alert.Type.HIGHLIGHT_UNDERLINE)) highlight = ChatFormatting.UNDERLINE.toString();
      if (alert.types.contains(Alert.Type.HIGHLIGHT_ITALIC)) highlight = ChatFormatting.ITALIC.toString();
      if (alert.types.contains(Alert.Type.HIGHLIGHT_BOLD)) highlight = ChatFormatting.BOLD.toString();
    }
  }
}
