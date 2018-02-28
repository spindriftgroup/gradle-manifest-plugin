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

import com.spindrift.atg.manifest.*
import org.gradle.api.*
import java.nio.file.*

class ATGManifestPlugin implements Plugin<Project> {

  public static final PLUGIN_EXTENSION_NAME = "atgManifest"
  public static final GENERATE_TASK_NAME = "generateModuleManifest"
  public static final GENERATE_TASK_DESCRIPTION = "Generates the Oracle Commerce (ATG) module manifest."
  public static final CLEAN_TASK_NAME = "cleanModuleManifest"
  public static final CLEAN_TASK_DESCRIPTION = "Deletes the Oracle Commerce (ATG) module manifest."
  public static final TASK_GROUP = "ATG"

  @Override
  void apply(Project project) {

    project.extensions."${PLUGIN_EXTENSION_NAME}" = new DSLManifest(getManifestFilePath(project));

    addCleanModuleManifestTask(project)
    addGenerateModuleManifestTask(project)
    //TODO add dependency on clean if configured after evaluate

  }

  private void addCleanModuleManifestTask(Project project) {
    Task task = project.getTasks().create(CLEAN_TASK_NAME)
    task.setDescription(CLEAN_TASK_DESCRIPTION)
    task.setGroup(TASK_GROUP)
    task.doLast {
      ManifestCleaner.clean(getManifest(project))
    }
  }

  private void addGenerateModuleManifestTask(Project project) {
    Task task = project.getTasks().create(GENERATE_TASK_NAME)
    task.setDescription(GENERATE_TASK_DESCRIPTION)
    task.setGroup(TASK_GROUP)
    project.afterEvaluate {
      configureBuildAttributes(project);
    }
    task.doLast {
      ManifestConfigurator.configure(getManifest(project))
      ManifestWriter.write(getManifest(project), project)
    }
  }

  private Manifest getManifest(Project project) {
    (Manifest) project.extensions."${PLUGIN_EXTENSION_NAME}"
  }

  private Path getManifestFilePath(Project project) {
    Path path = FileSystems.getDefault()
        .getPath("${project.projectDir.getAbsolutePath()}/${ManifestConstants.MANIFEST_DIR_NAME}",
        ManifestConstants.MANIFEST_FILE_NAME)
    path
  }

  private void configureBuildAttributes(Project project) {
    BuildAttributeConfigurator configurator = new BuildAttributeConfigurator()
    getManifest(project).setModuleFullName(configurator.getModuleFullName(project))
    getManifest(project).setBuildNumber(configurator.getBuildNumber(project))
    getManifest(project).setBuiltBy(configurator.getBuiltBy(project))
    getManifest(project).setBuildVersion(configurator.getBuildVersion(project))
    getManifest(project).setImplementationVersion(configurator.getImplementationVersion(project))
    getManifest(project).setBuildTimestamp(configurator.getBuildTimestamp(project))
  }


}