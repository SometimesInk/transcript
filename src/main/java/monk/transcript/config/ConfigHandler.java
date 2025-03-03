package monk.transcript.config;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * <p>
 * This class handles the communication between the configuration file and the
 * config element object.
 * </p>
 * <p>
 * When using this singleton class, call the {@link ConfigHandler#configLoad()}
 * method to initialize the object. To access the config element, use the
 * {@link ConfigHandler#configGet()} method to read the value, and the
 * {@link ConfigHandler#configSet(ConfigElement)} method to set values.
 * </p>
 * <p>
 * When initializing this class using the {@link ConfigHandler#configLoad()}
 * method, call it in the
 * </p>
 *
 * <pre>
 *   {@code
 * // Initialize config
 * ConfigHandler.getInstance().configLoad();
 *
 * // Add new config element
 * ConfigElement newConfig = ConfigHandler.getInstance().configGet();
 * newConfig.targets.add(new Alert("Hello world!", Collections.singletonList(Alert.Type.PING)));
 * ConfigHandler.getInstance().configSet(newConfig);
 *   }
 * </pre>
 */
public class ConfigHandler {
  private static final ConfigHandler INSTANCE = new ConfigHandler();
  private ConfigElement config = new ConfigElement();

  private ConfigHandler() {
  }

  public static ConfigHandler getInstance() {
    return INSTANCE;
  }

  /**
   * <p>
   * Reads the config file and outputs its content.
   * </p>
   *
   * @return File content or NULL if it does not exist (in which case it is
   * created, yet not populated)
   * @throws IOException I/O error on file creation.
   */
  private String configRead() throws IOException {
    File configFile = new File("config/transcript.json");

    // Indirectly check for file existence by creating the file, if it does not
    //  exist, it will be created and return NULL, otherwise it will continue
    if (!configFile.createNewFile()) return null;

    // Read file contents
    StringBuilder fileContent = new StringBuilder();
    Scanner configReader = new Scanner(configFile);
    // Loop through file lines and append them to string
    while (configReader.hasNextLine()) fileContent.append(configReader.nextLine());

    return fileContent.toString();
  }

  /**
   * <p>
   * Writes the given config element to the file.
   * </p>
   *
   * @param config The config element to write.
   * @throws IOException Could not write to file.
   */
  private void configWrite(ConfigElement config) throws IOException {
    // Write parsed config to file
    FileWriter configWriter = new FileWriter("config/transcript.json");
    configWriter.write(new Gson().toJson(config));
    configWriter.close();
  }

  /**
   * <p>
   * Assigns the config element from the config file.
   * </p>
   *
   * @param recursionLimit The amount of possible recursion calls left.
   * @throws IOException Failed to read file.
   */
  private void configParse(int recursionLimit) throws IOException {
    String configFile = configRead();

    if (configFile != null) {
      this.config = new Gson().fromJson(configRead(), ConfigElement.class);
      return;
    }

    // In this case, file does not exist
    // Create file and call this method back for inspection safely
    configWrite(new ConfigElement());
    if (recursionLimit > 0) configParse(recursionLimit - 1);
    else {
      System.err.println("The 'configParse()' method in 'ConfigHandler' hit its recursion limit.");
      this.config = new ConfigElement();
    }
  }

// TODO: Cache values to remove the need to check the config file all the time
//  (although it would make the system non-hot-reloading)

  /**
   * <p>
   * Switches the config element to the given one.
   * </p>
   *
   * @param config Config element that will override the current one
   * @return The final config element for chaining.
   */
  public ConfigElement configSet(ConfigElement config) {
    this.config = config;

    // Save config
    try {
      configWrite(config);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return config; // For chaining
  }

  /**
   * <p>
   * Returns the current config element.
   * </p>
   */
  public ConfigElement configGet() {
    return this.config;
  }

  /**
   * <p>
   * Loads the file into the config element. Serves as an interface for
   * initialization.
   * </p>
   */
  public void configLoad() {
    try {
      configParse(16);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
