package com.spindrift.atg.manifest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.*;

import static org.junit.Assert.assertTrue;

public class ManifestWriterTest {

  private Path tempDir;

  @Before
  public void setUp() throws Exception {
    tempDir = Files.createTempDirectory("test");
  }

  @Ignore //Due to dependency on a runtime gradle project we can't test this
  @Test
  public void write_manifestCreated() throws Exception {
    Path path = Paths.get(tempDir.toString(),"META-INF","MANIFEST.MF");
    Manifest manifest = new Manifest(path);
    ManifestWriter.write(manifest, null);
    assertTrue(path.toFile().exists());
  }
}
