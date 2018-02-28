package com.spindrift.atg.manifest

import org.gradle.api.Project

class ManifestAttributeWriter {

  static void writeAttributes(Project project, String manifestFilePath, Map attributes) {
    project.ant.manifest(file: "${manifestFilePath}") {
      attributes.each { key, value ->
        attribute(name: key, value: value)
      }
    }
  }

}
