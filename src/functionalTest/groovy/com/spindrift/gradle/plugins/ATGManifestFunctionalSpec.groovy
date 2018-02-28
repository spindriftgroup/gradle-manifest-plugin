/**
 * Copyright (C) 2012-2018 Spindrift B.V. All Rights Reserved
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spindrift.gradle.plugins

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.text.SimpleDateFormat

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class ATGManifestFunctionalSpec extends Specification {
  @Rule
  final TemporaryFolder testProjectDir = new TemporaryFolder()
  File buildFile

  def setup() {
    buildFile = testProjectDir.newFile('build.gradle')
    buildFile << """
        plugins {
            id 'com.spindrift.atg-manifest'
        }
    """
  }

  def "Generate module manifest task listed in tasks output"() {
    given:
    buildFile << """
            atgManifest {}
        """

    when:
    def result = runGradleTasksListing()

    then:
    result.output.contains(ATGManifestPlugin.GENERATE_TASK_NAME)
    result.output.contains(ATGManifestPlugin.GENERATE_TASK_DESCRIPTION)
    result.task(":tasks").outcome == SUCCESS
  }

  def "Clean module manifest task listed in tasks output"() {
    given:
    buildFile << """
        atgManifest {}
    """

    when:
    def result = runGradleTasksListing()

    then:
    result.output.contains(ATGManifestPlugin.CLEAN_TASK_NAME)
    result.output.contains(ATGManifestPlugin.CLEAN_TASK_DESCRIPTION)
    result.task(":tasks").outcome == SUCCESS
  }

  def "Delete module manifest when exists"() {
    given:
    buildFile << """
        atgManifest {}
    """

    and:
    File manifestDir = new File(testProjectDir.root, "META-INF")
    manifestDir.mkdirs()
    manifestDir.exists()
    File manifestFile = new File(manifestDir, "MANIFEST.MF")
    manifestFile.createNewFile()
    manifestFile.exists()

    when:
    def result = runCleanModuleManifestWithTrace()

    then:
    !manifestFile.exists()
    !manifestDir.exists()
  }

  def "Delete module manifest when not exists is silent"() {
    given:
    buildFile << """
        atgManifest {}
    """

    when:
    def result = runCleanModuleManifestWithTrace()

    then:
    result.task(":cleanModuleManifest").outcome == SUCCESS
  }


  def "Generate module manifest creates a manifest file"() {
    File manifestDir
    File manifestFile

    given:
    buildFile << """
        atgManifest {}
    """

    when:
    def result = runGenerateModuleManifestWithTrace()

    and:
    manifestDir = new File(testProjectDir.root, "META-INF")
    manifestFile = getGeneratedManifest()

    then:
    manifestDir.exists()
    manifestFile.exists()

  }


  def "Generated module manifest contains default manifest attribute"() {
    File manifestFile

    given:
    buildFile << """
        atgManifest {}
    """

    when:
    def result = runGenerateModuleManifestWithTrace()

    and:
    manifestFile = getGeneratedManifest()

    then:
    manifestFile.exists()
    manifestFile.text.contains("Manifest-Version: 1.0")

  }

  def "Generated module manifest contains module name"() {
    File manifestFile

    given:
    buildFile << """
        atgManifest {
          moduleFullName 'My Module - Commerce'
        }
    """

    when:
    def result = runGenerateModuleManifestWithTrace()

    and:
    manifestFile = getGeneratedManifest()

    then:
    manifestFile.text.contains("ATG-Product-Full: My Module - Commerce")

  }

  def "Generated module manifest contains required modules"() {
    File manifestFile

    given:
    buildFile << """
        atgManifest {
          moduleFullName 'My Module - Commerce'
          requiredModules = ['DCS','SomeOtherModule']
        }
    """

    when:
    def result = runGenerateModuleManifestWithTrace()

    and:
    manifestFile = getGeneratedManifest()

    then:
    manifestFile.text.contains("ATG-Required: DCS SomeOtherModule")

  }

  def "Manifest contains default build properties"() {
    File manifestFile
    String user = System.getenv('USER')

    given:
    buildFile << """
        atgManifest {
          buildVersion '2.1.0'
        }
    """

    when:
    def result = runGenerateModuleManifestWithTrace()

    and:
    manifestFile = getGeneratedManifest()

    then:
    manifestFile.text.contains("ATG-Build: (dev build by " +user + ")")
    manifestFile.text.contains("ATG-Version: 2.1.0")
    manifestFile.text.contains("Built-Date: ")
    manifestFile.text.contains("Built-By: Gradle")
    manifestFile.text.contains("Implementation-Version: 2.1.0-(dev build by ")

  }

  def "Manifest contains development config path if not release build"() {
    File manifestFile
    String user = System.getenv('USER')

    given:
    buildFile << """
        atgManifest {
        }
    """

    when:
    def result = runGenerateModuleManifestWithTrace()

    and:
    manifestFile = getGeneratedManifest()

    then:
    manifestFile.text.contains("ATG-Config-Path: config")

  }


  private BuildResult runGradleTasksListing() {
    runGradleTaskWithStackTrace('tasks')
  }

  private File getGeneratedManifest() {
    new File("${testProjectDir.root}/META-INF", "MANIFEST.MF")
  }

  private BuildResult runCleanModuleManifestWithTrace() {
    runGradleTaskWithStackTrace('cleanModuleManifest')
  }

  private BuildResult runGenerateModuleManifestWithTrace() {
    runGradleTaskWithStackTrace('generateModuleManifest')
  }

  private BuildResult runGradleTaskWithStackTrace(String method) {
    GradleRunner.create()
        .withProjectDir(testProjectDir.root)
        .withArguments(method, '-s')
        .withPluginClasspath()
        .build()
  }


}
