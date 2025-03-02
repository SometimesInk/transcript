package monk.transcript.config;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.text.Format;

public class ConfigCommand extends CommandBase {
  @Override
  public String getCommandName() {
    return "transcript";
  }

  @Override
  public String getCommandUsage(ICommandSender sender) {
    return "something";
  }

  @Override
  public void processCommand(ICommandSender sender, String[] args) throws CommandException {
    // Many things
  }

  @Override
  public int getRequiredPermissionLevel() {
    return 0;
  }
}
