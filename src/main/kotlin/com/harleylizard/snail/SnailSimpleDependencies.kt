package com.harleylizard.snail

import com.harleylizard.snail.configurations.Configurations
import com.harleylizard.snail.configurations.FabricConfigurations
import com.harleylizard.snail.configurations.ForgeConfigurations
import com.harleylizard.snail.configurations.NeoForgeConfigurations
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyCollector
import soul.software.gladys.GladysClient

fun Project.fabric(unit: Configurations.() -> Unit) {
    SnailSimple.gladys.spawn {
        FabricConfigurations(it, this).also(unit)
    }
}

fun Project.neoForge(unit: Configurations.() -> Unit) {
    SnailSimple.gladys.spawn {
        NeoForgeConfigurations(it, this).also(unit)
    }
}

fun Project.forge(unit: Configurations.() -> Unit) {
    SnailSimple.gladys.spawn {
        ForgeConfigurations(it, this).also(unit)
    }
}

fun DependencyCollector.add(dependency: SnailDependency) {
    add(dependency.artifact)
}

fun DependencyCollector.fabric(unit: DependencyConfiguration.() -> Unit) {
    SnailSimple.gladys.spawn {
        DependencyConfiguration(FabricConfigurations.FABRIC, it, this).also(unit)
    }
}

fun DependencyCollector.forge(unit: DependencyConfiguration.() -> Unit) {
    SnailSimple.gladys.spawn {
        DependencyConfiguration(ForgeConfigurations.FORGE, it, this).also(unit)
    }
}

fun DependencyCollector.neoForge(unit: DependencyConfiguration.() -> Unit) {
    SnailSimple.gladys.spawn {
        DependencyConfiguration(NeoForgeConfigurations.NEO_FORGE, it, this).also(unit)
    }
}

class DependencyConfiguration(private val loader: String, private val client: GladysClient, private val collector: DependencyCollector) {

    fun dependencyOf(version: String, slug: String) {
        SnailSimple.latestVersion(client, loader, version, slug)?.let(collector::add)
    }
}

