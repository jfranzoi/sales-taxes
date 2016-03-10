package my.projects.salestaxes.dummies;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileContent {

  private File source;
  private List<String> lines;

  public FileContent(File source) {
    this.source = source;
    this.lines = new ArrayList<String>();
  }

  public FileContent append(String line) {
    lines.add(line);
    return this;
  }

  public void save() throws Exception {
    try (FileWriter output = new FileWriter(source)) {
      for (String line : lines) {
        output.write(line);
        output.write(System.lineSeparator());
      }
    }
  }

}
