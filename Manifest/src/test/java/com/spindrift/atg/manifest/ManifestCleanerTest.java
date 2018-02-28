package com.spindrift.atg.manifest;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertFalse;

public class ManifestCleanerTest {

  @Test
  public void manifestFileExists_deleteFile() throws Exception {
    Path dirPath = Files.createTempDirectory("META-INF");
    File manifestDir = dirPath.toFile();
    Path path = Files.createTempFile(dirPath,"MANIFEST",".MF");
    File manifestFile = path.toFile();

    Manifest manifest = new Manifest(path);

    ManifestCleaner manifestCleaner = new ManifestCleaner();
    manifestCleaner.clean(manifest);

    assertFalse(manifestFile.exists());
    assertFalse(manifestDir.exists());
  }

}
