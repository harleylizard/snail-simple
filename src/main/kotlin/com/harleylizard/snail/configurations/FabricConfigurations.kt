package com.harleylizard.snail.configurations

import com.harleylizard.snail.Snail
import org.gradle.api.artifacts.dsl.DependencyHandler
import soul.software.gladys.GladysClient

class FabricConfigurations(
    private val version: String,
    private val client: GladysClient,
    private val dependencies: DependencyHandler) : Configurations {

    override fun implementation(slug: String) {
        Snail.latestVersion(client, FABRIC, version, slug)?.let {
            dependencies.add("modImplementation", it.artifact)
        }
    }

    override fun include(slug: String) {
        Snail.latestVersion(client, FABRIC, version, slug)?.let {
            dependencies.add("modInclude", it.artifact)
        }
    }

    override fun runtimeOnly(slug: String) {
        Snail.latestVersion(client, FABRIC, version, slug)?.let {
            dependencies.add("modRuntimeOnly", it.artifact)
        }
    }

    override fun compileOnly(slug: String) {
        Snail.latestVersion(client, FABRIC, version, slug)?.let {
            dependencies.add("modCompileOnly", it.artifact)
        }
    }

    companion object {
        private const val FABRIC = "fabric"

    }
}