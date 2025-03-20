package com.harleylizard.snail.configurations

import com.harleylizard.snail.SnailSimple
import org.gradle.api.Project
import soul.software.gladys.GladysClient

class NeoForgeConfigurations(private val client: GladysClient, private val project: Project) : Configurations {
    private val version get() = "todo"

    private val dependencies = project.dependencies

    override fun implementation(slug: String) {
        getProject(slug)?.let {
            dependencies.add("implementation", it.artifact)
        }
    }

    override fun include(slug: String) {
        getProject(slug)?.let {
            dependencies.add("include", it.artifact)
        }
    }

    override fun runtimeOnly(slug: String) {
        getProject(slug)?.let {
            dependencies.add("runtimeOnly", it.artifact)
        }
    }

    override fun compileOnly(slug: String) {
        getProject(slug)?.let {
            dependencies.add("compileOnly", it.artifact)
        }
    }

    private fun getProject(slug: String) = SnailSimple.latestVersion(client, NEO_FORGE, version, slug)

    companion object {
        const val NEO_FORGE = "neoforge"

    }
}