package com.harleylizard.snail

import com.harleylizard.snail.configurations.Configurations
import com.harleylizard.snail.configurations.FabricConfigurations
import com.harleylizard.snail.configurations.ForgeConfigurations
import com.harleylizard.snail.configurations.NeoForgeConfigurations
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

fun Project.fabric(unit: Configurations.() -> Unit) {
    SnailSimple.gladys.spawn {
        FabricConfigurations(it, this).also(unit)
    }
}

fun DependencyHandler.neoForge(version: String, unit: Configurations.() -> Unit) {
    SnailSimple.gladys.spawn {
        NeoForgeConfigurations(version, it, this).also(unit)
    }
}

fun DependencyHandler.forge(version: String, unit: Configurations.() -> Unit) {
    SnailSimple.gladys.spawn {
        ForgeConfigurations(version, it, this).also(unit)
    }
}
