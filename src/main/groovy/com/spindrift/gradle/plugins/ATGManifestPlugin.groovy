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

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

/**
 * Provides configuration for the ATG Manifest generator plugin
 * All properties can be set directly or using its DSL equivalent
 * e.g.
 * <code>
 * atgManifest {
 *   moduleName='myModuleName' //Direct setting
 *   moduleName 'myModuleName' //Optional DSL style setting
 * }
 * </code>
 *
 * @author hallatech
 *
 */
class ATGManifestPlugin implements Plugin<Project> {

    public static final PLUGIN_EXTENSION_NAME = "atgManifest"
    public static final GENERATE_TASK_NAME = "generateModuleManifest"
    public static final GENERATE_TASK_DESCRIPTION = "Generates the Oracle Commerce (ATG) module manifest."
    public static final CLEAN_TASK_NAME = "cleanModuleManifest"
    public static final CLEAN_TASK_DESCRIPTION = "Deletes the Oracle Commerce (ATG) module manifest."
    public static final TASK_GROUP = "ATG"

    @Override
    void apply(Project project) {

        project.extensions."${PLUGIN_EXTENSION_NAME}" = new ATGManifestPluginExtension()

        println "ATG Manifest Plugin applied!"

        addGenerateModuleManifestTask(project)
        addCleanModuleManifestTask(project)

    }

    private void addGenerateModuleManifestTask(Project project) {

        Task task = project.getTasks().create(GENERATE_TASK_NAME)
        task.setDescription(GENERATE_TASK_DESCRIPTION)
        task.setGroup(TASK_GROUP)

    }

    private void addCleanModuleManifestTask(Project project) {
        Task task = project.getTasks().create(CLEAN_TASK_NAME)
        task.setDescription(CLEAN_TASK_DESCRIPTION)
        task.setGroup(TASK_GROUP)
    }
}