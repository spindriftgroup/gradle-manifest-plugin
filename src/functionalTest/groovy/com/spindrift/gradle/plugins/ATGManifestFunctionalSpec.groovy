package com.spindrift.gradle.plugins

import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class ATGManifestFunctionalSpec extends Specification {
    @Rule final TemporaryFolder testProjectDir = new TemporaryFolder()
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
        def result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withArguments('tasks')
            .withPluginClasspath()
            .build()

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
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments('tasks')
                .withPluginClasspath()
                .build()

        then:
        result.output.contains(ATGManifestPlugin.CLEAN_TASK_NAME)
        result.output.contains(ATGManifestPlugin.CLEAN_TASK_DESCRIPTION)
        result.task(":tasks").outcome == SUCCESS
    }
}
