package com.spindrift.atg.manifest;

import java.util.HashMap;
import java.util.Map;

public class ManifestConfigurator {


  static void configure(Manifest manifest) {
    manifest.setConfiguredAttributes(generateAttributes(manifest));
  }

  private static Map<String,String> generateAttributes(Manifest manifest) {

    Map<String,String> attributes = new HashMap<>();
    attributes.putAll(getCoreAttributes(manifest));
    return attributes;

  }

  private static Map<String,String> getCoreAttributes(Manifest manifest) {

    Map<String,String> coreAttributes = new HashMap<>();

    coreAttributes.put("Built-By",manifest.getBuiltBy());
    coreAttributes.put("Built-Date",manifest.getBuildTimestamp().toString());
    coreAttributes.put("Implementation-Version",manifest.getImplementationVersion());
    coreAttributes.put("ATG-Required",getModulesAsString(manifest));
    coreAttributes.put("ATG-Config-Path",manifest.getConfigPath());
    //        "ATG-Config-Path":configPath,
////        "ATG-Class-Path":classpathJars,
////        "ATG-Client-Class-Path":classpathJars,
    coreAttributes.put("ATG-Version",manifest.getBuildVersion());
    coreAttributes.put("ATG-Build",manifest.getBuildNumber());
    coreAttributes.put("ATG-Product-Full",manifest.getModuleFullName());

    return coreAttributes;

  }

  private static String getModulesAsString(Manifest manifest) {
    String modules = "";
    for (int i=0; i < manifest.getRequiredModules().size(); i++) {
      if (i>0)
        modules += " ";
      modules += manifest.getRequiredModules().get(i);
    }

    return modules;
  }



}
