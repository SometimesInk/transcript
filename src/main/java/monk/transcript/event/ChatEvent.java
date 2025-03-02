package monk.transcript.event;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatEvent {
    /**
     * Reads chat messages to then parse them.
     */
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        event.setCanceled(true);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Message: " + message));
    }
}
