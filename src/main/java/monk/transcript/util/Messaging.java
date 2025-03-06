package monk.transcript.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Messaging {
  public static void sendMessage(String s) {
    ChatComponentText message = new ChatComponentText(ChatFormatting.DARK_GRAY + "[" + ChatFormatting.WHITE +
        "TRANSCRIPT" + ChatFormatting.DARK_GRAY + "] ");
    message.appendSibling(new ChatComponentText(s));
    Minecraft.getMinecraft().thePlayer.addChatMessage(message);
  }
}
