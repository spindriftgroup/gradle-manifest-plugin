package com.spindrift.atg.manifest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ManifestCleaner {

  public static void clean(Manifest manifest) {
    Path path = manifest.getPath();
    try {
      Files.deleteIfExists(path);
    } catch (IOException e) {
      System.err.println(e);
    }
    deleteParentDirectory(path);
  }

  private static void deleteParentDirectory(Path path) {
    Path parent = path.getParent();
    try {
      Files.deleteIfExists(parent);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

}
