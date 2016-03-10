package my.projects.saletaxes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternGroups {

  private Matcher matcher;

  public PatternGroups(String content, String expression) {
    Pattern pattern = Pattern.compile(expression);
    matcher = pattern.matcher(content);
  }

  public String at(String name) {
    matcher.matches();
    return matcher.group(name);
  }

}
