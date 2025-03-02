package monk.transcript;

import monk.transcript.alert.Alert;
import monk.transcript.config.ConfigCommand;
import monk.transcript.config.ConfigElement;
import monk.transcript.config.ConfigHandler;
import monk.transcript.event.EventChat;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;

@Mod(modid = Transcript.MODID, version = Transcript.VERSION)
public class Transcript {
  public static final String MODID = "transcript";
  public static final String VERSION = "1.0";

  @EventHandler
  public void init(FMLInitializationEvent event) {
    // Register events
    MinecraftForge.EVENT_BUS.register(new EventChat());

    // Register commands
    ClientCommandHandler.instance.registerCommand(new ConfigCommand());
  }

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    // Initialize config
    ConfigHandler.getInstance().configLoad();

    ConfigElement newConfig = ConfigHandler.getInstance().configGet();
    newConfig.targets.add(new Alert("mavis", Collections.singletonList(Alert.Type.HIGHLIGHT_ITALIC)));

    ConfigHandler.getInstance().configSet(newConfig);
  }
}