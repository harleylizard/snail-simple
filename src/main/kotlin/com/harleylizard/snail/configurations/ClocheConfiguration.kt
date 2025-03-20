package com.harleylizard.snail.configurations

import com.harleylizard.snail.SnailSimple
import com.harleylizard.snail.add
import earth.terrarium.cloche.api.target.MinecraftTarget
import soul.software.gladys.GladysClient

class ClocheConfiguration <T : Any> (
    private val loader: String,
    private val client: GladysClient,
    private val target: MinecraftTarget<T>) : Configurations {

    private val version get() = target.minecraftVersion.orNull ?: "1.21.4"

    override fun implementation(slug: String) {
        getProject(slug)?.let {
            target.dependencies { dependencies ->
                dependencies.modImplementation.add(it)
            }
        }
    }

    override fun include(slug: String) {
        getProject(slug)?.let {
            target.include.add(it)
        }
    }

    override fun runtimeOnly(slug: String) {
        getProject(slug)?.let {
            target.dependencies { dependencies ->
                dependencies.modRuntimeOnly.add(it)
            }
        }
    }

    override fun compileOnly(slug: String) {
        getProject(slug)?.let {
            target.dependencies { dependencies ->
                dependencies.modCompileOnly.add(it)
            }
        }
    }

    private fun getProject(slug: String) = SnailSimple.latestVersion(client, loader, version, slug)
}