package monk.transcript.config;

import monk.transcript.util.MethodResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConfigHandler {
  public static ConfigHandler INSTANCE = new ConfigHandler();
  private ConfigElement config = new ConfigElement();

  private ConfigHandler() {
  }

  public ConfigHandler getINSTANCE() {
    return INSTANCE;
  }

  public enum StateReadConfig {
    CONFIG_FILE_DOES_NOT_EXIST,
    SUCCESS
  }

  private MethodResult<List<String>, StateReadConfig> configRead() {
    File configFile = new File("/config/transcript.json");

    // Check for file existence
    if (!configFile.exists()) return new MethodResult<List<String>, StateReadConfig>(null,
        StateReadConfig.CONFIG_FILE_DOES_NOT_EXIST);

    List<String> fileContent = new ArrayList<String>();
    try {
      Scanner configReader = new Scanner(configFile);
      // Loop through file lines and append them to string
      while (configReader.hasNextLine()) {
        fileContent.add(configReader.nextLine());
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return new MethodResult<List<String>, StateReadConfig>(fileContent, StateReadConfig.SUCCESS);
  }

  protected void configParse() {

  }

  protected void configSave() {

  }

  private ConfigElement configSet(ConfigElement config) {
    this.config = config;

    // Save
    configSave();

    return this.config;
  }
}
