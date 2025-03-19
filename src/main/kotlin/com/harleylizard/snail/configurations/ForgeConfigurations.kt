package com.harleylizard.snail.configurations

import com.harleylizard.snail.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import soul.software.gladys.GladysClient

class ForgeConfigurations(
    private val version: String,
    private val client: GladysClient,
    private val dependencies: DependencyHandler) : Configurations {

    override fun implementation(slug: String) {
        TODO("Not yet implemented")
    }

    override fun include(slug: String) {
        TODO("Not yet implemented")
    }

    override fun runtimeOnly(slug: String) {
        TODO("Not yet implemented")
    }

    override fun compileOnly(slug: String) {
        TODO("Not yet implemented")
    }
}