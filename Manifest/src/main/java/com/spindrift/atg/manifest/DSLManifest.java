package com.spindrift.atg.manifest;

import java.nio.file.Path;
import java.util.Date;
import java.util.List;

public class DSLManifest extends Manifest {

  public DSLManifest(Path path) {
    super(path);
  }

  public String moduleName() {
    return getModuleName();
  }

  public void moduleName(String moduleName) {
    setModuleName(moduleName);
  }

  public void moduleFullName(String moduleFullName) {
    setModuleFullName(moduleFullName);
  }

  public String moduleFullName() {
    return getModuleFullName();
  }

  public List<String> requiredModules() {
    return getRequiredModules();
  }

  public void requiredModules(List<String> requiredModules) {
    setRequiredModules(requiredModules);
  }

  public String builtBy() {
    return getBuiltBy();
  }

  public void builtBy(String builtBy) {
    setBuiltBy(builtBy);
  }

  public Date buildTimestamp() {
    return getBuildTimestamp();
  }

  public void buildTimestamp(Date buildTimestamp) {
    setBuildTimestamp(buildTimestamp);
  }

  public String implementationVersion() {
    return getImplementationVersion();
  }

  public void implementationVersion(String implementationVersion) {
    setImplementationVersion(implementationVersion);
  }

  public String buildVersion() {
    return getBuildVersion();
  }

  public void buildVersion(String buildVersion) {
    setBuildVersion(buildVersion);
  }

  public String buildNumber() {
    return getBuildNumber();
  }

  public void buildNumber(String buildNumber) {
    setBuildNumber(buildNumber);
  }
}
