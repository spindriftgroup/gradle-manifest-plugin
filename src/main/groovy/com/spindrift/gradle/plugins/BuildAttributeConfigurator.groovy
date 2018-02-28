package com.spindrift.gradle.plugins

import org.gradle.api.Project
import static com.spindrift.gradle.plugins.ATGManifestPlugin.PLUGIN_EXTENSION_NAME

class BuildAttributeConfigurator {

  static String getModuleFullName(Project project) {

    def moduleFullName = (project.getAt(PLUGIN_EXTENSION_NAME).moduleFullName) ?: ""

    if (!moduleFullName) {
      if (project == project.rootProject)
        moduleFullName = project.rootProject.name
      else
        moduleFullName = "${project.rootProject.name} - ${project.name}"
    }

    moduleFullName
  }

  static String getBuildNumber(Project project) {
    def manifest = project.getAt(PLUGIN_EXTENSION_NAME)

    String buildNumber = (manifest.buildNumber) ?:
        (manifest.inheritFromRoot) ?
            project.rootProject.getAt(PLUGIN_EXTENSION_NAME).buildNumber : ''
    if (!buildNumber && project.rootProject.hasProperty('buildNumber'))
      buildNumber = project.rootProject.ext.buildNumber
    if (!buildNumber)
      buildNumber = "(dev build by ${System.getenv('USER')})"

    buildNumber
  }

  static String getBuiltBy(Project project) {
    def manifest = project.getAt(PLUGIN_EXTENSION_NAME)

    String builtBy = (manifest.builtBy) ?:
        (manifest.inheritFromRoot) ?
            project.rootProject.getAt(PLUGIN_EXTENSION_NAME).builtBy : ''
    if (!builtBy && project.rootProject.hasProperty('builtBy'))
      builtBy = project.rootProject.ext.builtBy
    if (!builtBy)
      builtBy = 'Gradle'

    builtBy
  }

  static String getBuildVersion(Project project) {
    def manifest = project.getAt(PLUGIN_EXTENSION_NAME)

    def buildVersion = (manifest.buildVersion) ?:
        (manifest.inheritFromRoot) ?
            (project.rootProject.getAt(PLUGIN_EXTENSION_NAME).buildVersion) : ''
    if (!buildVersion)
      buildVersion = "${project.rootProject.version}"

    buildVersion
  }

  static String getImplementationVersion(Project project) {
    def manifest = project.getAt(PLUGIN_EXTENSION_NAME)

    def implementationVersion = (manifest.implementationVersion) ?:
        (manifest.inheritFromRoot) ?
            project.rootProject.getAt(PLUGIN_EXTENSION_NAME).implementationVersion : ''
    if (!implementationVersion)
      implementationVersion = "${getBuildVersion(project)}-${getBuildNumber(project)}"

    implementationVersion
  }

  static Date getBuildTimestamp(Project project) {
    def manifest = project.getAt(PLUGIN_EXTENSION_NAME)

    def buildTimestamp = (manifest.buildTimestamp) ?:
        (manifest.inheritFromRoot) ?
            project.rootProject.getAt(PLUGIN_EXTENSION_NAME).buildTimestamp : null
    if (!buildTimestamp)
      buildTimestamp = new Date()

    buildTimestamp
  }

}
