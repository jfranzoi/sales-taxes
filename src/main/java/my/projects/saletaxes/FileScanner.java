package my.projects.saletaxes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileScanner implements Scanner {

  private File source;

  public FileScanner(File source) {
    this.source = source;
  }

  public List<String> lines() {
    return linesAt(source);
  }

  private List<String> linesAt(File file) {
    List<String> lines = new ArrayList<String>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (Exception e) {
    }

    return lines;
  }

}
