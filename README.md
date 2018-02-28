# gradle-manifest-plugin
Generates Oracle Commerce (ATG) module manifests

Usage
=====
Build script snippet for new plugin format
```$xslt
    plugins {
        id 'com.spindrift.atg-manifest' version '1.0.0'
    }
```
Build script snippet for use in all Gradle versions
```$xslt
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "com.spindrift:atg-manifest:1.0.0"
  }
}

apply plugin: "com.spindrift.atg-manifest"
``` 

Custom Tasks
============

`generateModuleManifest` - Generates the Oracle Commerce module's manifest for the module  
`cleanModuleManifest` - Deletes the Oracle Commerce module's manifest

Default Configuration
=====================

- ...  
- ...  

Optional Configuration
======================

Property | Default Value | Description  
-------- | ------------- | -----------  
... | `...` | ...    

Example configuration overrides
===============================

```$xslt
  atgManifest { s
    moduleName 'MyModule'
    moduleFullName 'MyModule - Commerce Extensions'
    requiredModules ['DCS','SomeOtherModule']
  }
```

Build Notes
===========

1. Maven local installation  
`gradle or gradle publishToMavenlocal`  
2. Publishing to Plugin portal  
`gradle clean build -Prelease=true publishPlugins`  
3. Publishing to Bintray JCenter  
`gradle clean build -Prelease=true bintrayUpload`  

Versions
========

See CHANGELOG
