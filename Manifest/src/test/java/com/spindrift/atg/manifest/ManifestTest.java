package com.spindrift.atg.manifest;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class ManifestTest {

  private Path dir;
  private Path path;
  private Manifest manifest;

  @Before
  public void setUp() throws Exception {
    dir = Files.createTempDirectory("META-INF");
    path = Files.createTempFile(dir,"MANIFEST","MF");
    manifest = new Manifest(path);
  }

  @Test
  public void setPath_getPath() throws Exception {
    assertEquals(path,manifest.getPath());
  }

  @Test
  public void configuredAttributes_notSet_isEmptyNotNull() throws Exception {
    String moduleName = "My custom module";
    manifest.setModuleFullName(moduleName);
    assertEquals(moduleName,manifest.getModuleFullName());
  }
}
