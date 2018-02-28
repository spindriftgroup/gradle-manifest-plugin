package com.spindrift.atg.manifest;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManifestConfiguratorTest {
  private Manifest manifest;

  private void assertHasKey(String key) {
    assertTrue(manifest.getConfiguredAttributes().containsKey(key));
  }

  @Before
  public void setUp() throws Exception {
    Path dir = Files.createTempDirectory("META-INF");
    Path path = Files.createTempFile(dir,"MANIFEST","MF");
    manifest = new Manifest(path);
  }

  @Test
  public void configure_moduleFullName() throws Exception {
    manifest.setModuleFullName("My Custom Module");
    ManifestConfigurator.configure(manifest);
    String key =  "ATG-Product-Full";
    assertHasKey(key);
    assertEquals(manifest.getConfiguredAttributes().get(key),manifest.getModuleFullName());
  }

  @Test
  public void configure_noModule() throws Exception {
    ManifestConfigurator.configure(manifest);
    String key =  "ATG-Required";
    assertHasKey(key);
    assertEquals(manifest.getConfiguredAttributes().get(key),"");
  }

  @Test
  public void configure_modulesConfiguredWithSpaces() throws Exception {
    List<String> modules = new ArrayList<>();
    modules.add("DCS");
    modules.add("Catalogs");
    modules.add("Custom.Base");
    manifest.setRequiredModules(modules);
    ManifestConfigurator.configure(manifest);
    String key =  "ATG-Required";
    assertHasKey(key);
    assertEquals(manifest.getConfiguredAttributes().get(key),"DCS Catalogs Custom.Base");
  }

  @Test
  public void configureBuiltByConfigured() throws Exception {
    manifest.setBuiltBy("someone");
    ManifestConfigurator.configure(manifest);
    String key =  "Built-By";
    assertHasKey(key);
    assertEquals("someone",manifest.getConfiguredAttributes().get(key));
  }

  @Test
  public void configure_ATGVersionMatchesBuildVersion() throws Exception {
    manifest.setBuildVersion("2.1.0");
    ManifestConfigurator.configure(manifest);
    String key =  "ATG-Version";
    assertHasKey(key);
    assertEquals("2.1.0",manifest.getConfiguredAttributes().get(key));
  }

  @Test
  public void configure_developmentConfigPath() throws Exception {
    manifest.setConfigPath("config");
    ManifestConfigurator.configure(manifest);
    String key = "ATG-Config-Path";
    assertHasKey(key);
    assertEquals("config",manifest.getConfiguredAttributes().get(key));
  }
}
