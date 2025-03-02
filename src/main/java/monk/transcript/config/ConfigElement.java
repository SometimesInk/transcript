package monk.transcript.config;

import java.util.ArrayList;
import java.util.List;

public class ConfigElement {
  public List<String> targets = new ArrayList<String>();
  
  public ConfigElement(List<String> targets) {
    this.targets = targets;
  }
  
  public ConfigElement() {
  }
}
