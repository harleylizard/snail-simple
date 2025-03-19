package com.harleylizard.snail.configurations

import com.harleylizard.snail.Snail
import org.gradle.api.artifacts.dsl.DependencyHandler
import soul.software.gladys.GladysClient

class NeoForgeConfigurations(
    private val version: String,
    private val client: GladysClient,
    private val dependencies: DependencyHandler) : Configurations {

    override fun implementation(slug: String) {
        Snail.latestVersion(client, NEO_FORGE, version, slug)?.let {
            dependencies.add("implementation", it.artifact)
        }
    }

    override fun include(slug: String) {
        Snail.latestVersion(client, NEO_FORGE, version, slug)?.let {
            dependencies.add("include", it.artifact)
        }
    }

    override fun runtimeOnly(slug: String) {
        Snail.latestVersion(client, NEO_FORGE, version, slug)?.let {
            dependencies.add("runtimeOnly", it.artifact)
        }
    }

    override fun compileOnly(slug: String) {
        Snail.latestVersion(client, NEO_FORGE, version, slug)?.let {
            dependencies.add("compileOnly", it.artifact)
        }
    }

    companion object {
        private const val NEO_FORGE = "neoforge"

    }
}