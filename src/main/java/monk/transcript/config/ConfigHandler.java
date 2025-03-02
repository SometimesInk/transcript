package monk.transcript.config;

import com.google.gson.Gson;
import monk.transcript.util.MethodResult;

import java.io.File;
import java.io.FileNotFoundException;
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
 * // Add a word to config
 * ConfigElement newConfig = ConfigHandler.getInstance().configGet();
 * newConfig.targets.put("Hello", ChatFormatting.BLUE);
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
   * The output state of the {@link ConfigHandler#configRead()} method.
   * </p>
   * <table>
   *   <tr>
   *     <th>State</th>
   *     <th>Meaning</th>
   *   </tr>
   *   <tr>
   *     <td>SUCCESS</td>
   *     <td>Successfully read file.</td>
   *   </tr>
   *   <tr>
   *     <td>FILE_NOT_FOUND</td>
   *     <td>Config file does not exist</td>
   *   </tr>
   * </table>
   *
   * @see ConfigHandler#configRead()
   */
  private enum StateReadConfig {
    /**
     * <p>
     * Successfully read file.
     * </p>
     *
     * @see StateReadConfig
     */
    SUCCESS,
    /**
     * <p>
     * Config file does not exist.
     * </p>
     *
     * @see StateReadConfig
     */
    FILE_NOT_FOUND
  }

  /**
   * <p>
   * Reads the config file and outputs its content.
   * </p>
   *
   * @return File content and the error stream.
   * @see StateReadConfig
   */
  private MethodResult<String, StateReadConfig> configRead() {
    File configFile = new File("/config/transcript.json");

    // Check for file existence
    if (!configFile.exists()) return new MethodResult<String, StateReadConfig>(null,
        StateReadConfig.FILE_NOT_FOUND);

    StringBuilder fileContent = new StringBuilder();
    try {
      Scanner configReader = new Scanner(configFile);
      // Loop through file lines and append them to string
      while (configReader.hasNextLine()) {
        fileContent.append(configReader.nextLine());
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return new MethodResult<String, StateReadConfig>(fileContent.toString(), StateReadConfig.SUCCESS);
  }

  /**
   * <p>
   * Writes the given config element to the file.
   * </p>
   *
   * @param config The config element to write.
   */
  private void configWrite(ConfigElement config) {
    // Write parsed config to file
    try {
      FileWriter configWriter = new FileWriter("/config/transcript.json");
      configWriter.write(new Gson().toJson(config));
      configWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * <p>
   * Assigns the config element from the config file.
   * </p>
   *
   * @param recursionLimit The amount of possible recursion calls left.
   */
  private void configParse(int recursionLimit) {
    MethodResult<String, StateReadConfig> configFile = configRead();

    if (configFile.stmerr == StateReadConfig.SUCCESS) {
      this.config = new Gson().fromJson(configRead().stmout, ConfigElement.class);
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
    configWrite(config);

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
    configParse(16);
  }
}
