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
   * <p>
   * This method assumes that the file exists.
   * </p>
   *
   * @return File content
   * @throws IOException I/O error on file creation.
   */
  private String configRead() throws IOException {
    File configFile = new File("config/transcript.json");

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
   * @throws IOException Failed to read file.
   */
  private void configParse() throws IOException {
    config = new Gson().fromJson(configRead(), ConfigElement.class);
  }

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
   * Writes config to file.
   * </p>
   */
  public void reload() throws IOException {
    // Save config
    configWrite(config);
  }

  /**
   * <p>
   * Loads the file into the config element. Serves as an interface for
   * initialization.
   * </p>
   */
  public void configLoad() throws IOException {
    // Create file if it does not exist
    if (new File("config/transcript.json").createNewFile()) configWrite(config);
    else configParse();
  }
}
