package monk.transcript.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Messaging {

  public static void sendMessage(ChatComponentText chatComponentText) {
    ChatComponentText message = new ChatComponentText(ChatFormatting.DARK_GRAY + "[" + ChatFormatting.WHITE +
        "TRANSCRIPT" + ChatFormatting.DARK_GRAY + "] ");
    message.appendSibling(chatComponentText);
    Minecraft.getMinecraft().thePlayer.addChatMessage(message);
  }
}
