package com.spindrift.atg.manifest;

import org.gradle.api.Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ManifestWriter {

  public static void write(Manifest manifest, Project project) {

    File manifestFilePath = manifest.getPath().toFile();
    createParentDirectory(manifest.getPath());

    ManifestAttributeWriter.writeAttributes(project,
        manifestFilePath.toString(),
        manifest.getConfiguredAttributes());

  }

  private static void createParentDirectory(Path path) {
    Path parentDir = path.getParent();
    if (!parentDir.toFile().exists()) {
      try {
        Files.createDirectory(parentDir);
      } catch (IOException e) {
        System.err.println(e);
      }
    }
  }
}
