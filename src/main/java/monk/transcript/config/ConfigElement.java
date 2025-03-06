package monk.transcript.config;

import monk.transcript.Transcript;
import monk.transcript.alert.Alert;

import java.util.ArrayList;
import java.util.List;

public class ConfigElement {
  public final String version = Transcript.VERSION;
  /**
   * <p>
   * Keywords and phrases we're looking for.
   * </p>
   */
  public List<Alert> targets = new ArrayList<Alert>();

  public float pingVolume = 1.0F;

  public ConfigElement() {
  }

  public boolean addOverride(Alert alert) {
    if (targets.contains(alert)) return false;
    targets.add(alert);
    return true;
  }

  public boolean removePhrase(String target) {
    for (Alert alert : targets) {
      if (!alert.target.equals(target)) continue;
      targets.remove(alert);
      return true;
    }
    return false;
  }
}
