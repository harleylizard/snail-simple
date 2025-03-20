package com.harleylizard.snail.configurations

import org.gradle.api.Project
import soul.software.gladys.GladysClient

class ForgeConfigurations(private val client: GladysClient, private val project: Project) : Configurations {

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

    companion object {
        const val FORGE = "forge"

    }
}