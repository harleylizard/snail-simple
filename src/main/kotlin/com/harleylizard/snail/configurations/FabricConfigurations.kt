package com.harleylizard.snail.configurations

import com.harleylizard.snail.SnailSimple
import net.fabricmc.loom.util.Constants
import org.gradle.api.Project
import soul.software.gladys.GladysClient

class FabricConfigurations(private val client: GladysClient, private val project: Project) : Configurations {
    private val version get() = project.configurations.getByName(Constants.Configurations.MINECRAFT).dependencies.first().version ?: "1.21.4"

    private val dependencies = project.dependencies

    override fun implementation(slug: String) {
        SnailSimple.latestVersion(client, FABRIC, version, slug)?.let {
            dependencies.add("modImplementation", it.artifact)
        }
    }

    override fun include(slug: String) {
        SnailSimple.latestVersion(client, FABRIC, version, slug)?.let {
            dependencies.add("modInclude", it.artifact)
        }
    }

    override fun runtimeOnly(slug: String) {
        SnailSimple.latestVersion(client, FABRIC, version, slug)?.let {
            dependencies.add("modRuntimeOnly", it.artifact)
        }
    }

    override fun compileOnly(slug: String) {
        SnailSimple.latestVersion(client, FABRIC, version, slug)?.let {
            dependencies.add("modCompileOnly", it.artifact)
        }
    }

    companion object {
        private const val FABRIC = "fabric"

    }
}