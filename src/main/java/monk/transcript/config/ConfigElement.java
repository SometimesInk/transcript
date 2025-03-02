package monk.transcript.config;

import monk.transcript.alert.Alert;

import java.util.ArrayList;
import java.util.List;

public class ConfigElement {
  /**
   * <p>
   * Keywords and phrases we're looking for.
   * </p>
   */
  public List<Alert> targets = new ArrayList<Alert>();

  public ConfigElement(List<Alert> targets) {
    this.targets = targets;
  }

  public ConfigElement() {
  }
}
