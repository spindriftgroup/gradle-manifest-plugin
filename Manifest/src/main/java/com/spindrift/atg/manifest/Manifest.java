package com.spindrift.atg.manifest;

import java.nio.file.Path;
import java.util.*;

public class Manifest {
  private Path path;
  private Map configuredAttributes;
  private String moduleName;
  private String moduleFullName;
  private boolean generateDependsOnClean = true;
  private List<String> requiredModules = new ArrayList<>();
  private boolean inheritFromRoot = true;
  private String builtBy = "";
  private Date buildTimestamp = new Date();
  private String implementationVersion = "";
  private String buildVersion = "";
  private String buildNumber = "";
  private String configPath = "";
  private String devConfigPath = "config";
  private String releaseConfigPath = "config/config.jar";
  private String releaseConfigPathProperty = "releaseBuild";


  public Manifest(Path path) {
    this.path = path;
  }

  public Path getPath() {
    return path;
  }

  public Map getConfiguredAttributes() {
    if (configuredAttributes == null)
      return new HashMap();
    else
      return configuredAttributes;
  }

  protected void setConfiguredAttributes(Map configuredAttributes) {
    this.configuredAttributes = configuredAttributes;
  }

  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public void setModuleFullName(String moduleFullName) {
    this.moduleFullName = moduleFullName;
  }
  public String getModuleFullName() {
    return this.moduleFullName;
  }

  public void setPath(Path path) {
    this.path = path;
  }

  public boolean isGenerateDependsOnClean() {
    return generateDependsOnClean;
  }

  public void setGenerateDependsOnClean(boolean generateDependsOnClean) {
    this.generateDependsOnClean = generateDependsOnClean;
  }

  public List<String> getRequiredModules() {
    return requiredModules;
  }

  public void setRequiredModules(List<String> requiredModules) {
    this.requiredModules = requiredModules;
  }

  public boolean isInheritFromRoot() {
    return inheritFromRoot;
  }

  public void setInheritFromRoot(boolean inheritFromRoot) {
    this.inheritFromRoot = inheritFromRoot;
  }

  public String getBuiltBy() {
    return builtBy;
  }

  public void setBuiltBy(String builtBy) {
    this.builtBy = builtBy;
  }

  public Date getBuildTimestamp() {
    return buildTimestamp;
  }

  public void setBuildTimestamp(Date buildTimestamp) {
    this.buildTimestamp = buildTimestamp;
  }

  public String getImplementationVersion() {
    return implementationVersion;
  }

  public void setImplementationVersion(String implementationVersion) {
    this.implementationVersion = implementationVersion;
  }

  public String getBuildVersion() {
    return buildVersion;
  }

  public void setBuildVersion(String buildVersion) {
    this.buildVersion = buildVersion;
  }

  public String getBuildNumber() {
    return buildNumber;
  }

  public void setBuildNumber(String buildNumber) {
    this.buildNumber = buildNumber;
  }

  public String getConfigPath() {
    return configPath;
  }

  public void setConfigPath(String configPath) {
    this.configPath = configPath;
  }

  public String getDevConfigPath() {
    return devConfigPath;
  }

  public void setDevConfigPath(String devConfigPath) {
    this.devConfigPath = devConfigPath;
  }

  public String getReleaseConfigPath() {
    return releaseConfigPath;
  }

  public void setReleaseConfigPath(String releaseConfigPath) {
    this.releaseConfigPath = releaseConfigPath;
  }

  public String getReleaseConfigPathProperty() {
    return releaseConfigPathProperty;
  }

  public void setReleaseConfigPathProperty(String releaseConfigPathProperty) {
    this.releaseConfigPathProperty = releaseConfigPathProperty;
  }

}
