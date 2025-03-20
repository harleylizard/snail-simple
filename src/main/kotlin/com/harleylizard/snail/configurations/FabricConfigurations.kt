package com.harleylizard.snail.configurations

import com.harleylizard.snail.SnailSimple
import net.fabricmc.loom.util.Constants
import org.gradle.api.Project
import soul.software.gladys.GladysClient

class FabricConfigurations(private val client: GladysClient, private val project: Project) : Configurations {
    private val version get() = project.minecraft.dependencies.first().version ?: "1.21.4"

    private val dependencies = project.dependencies

    override fun implementation(slug: String) {
        getProject(slug)?.let {
            dependencies.add("modImplementation", it.artifact)
        }
    }

    override fun include(slug: String) {
        getProject(slug)?.let {
            dependencies.add("modInclude", it.artifact)
        }
    }

    override fun runtimeOnly(slug: String) {
        getProject(slug)?.let {
            dependencies.add("modRuntimeOnly", it.artifact)
        }
    }

    override fun compileOnly(slug: String) {
        getProject(slug)?.let {
            dependencies.add("modCompileOnly", it.artifact)
        }
    }

    private fun getProject(slug: String) = SnailSimple.latestVersion(client, FABRIC, version, slug)

    companion object {
        const val FABRIC = "fabric"

        private val Project.minecraft get() = configurations.getByName(Constants.Configurations.MINECRAFT)

    }
}