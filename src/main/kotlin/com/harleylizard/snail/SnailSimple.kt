package com.harleylizard.snail

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.ArtifactRepository
import soul.software.gladys.Gladys
import soul.software.gladys.GladysClient
import java.net.InetSocketAddress
import java.net.URI

class SnailSimple : Plugin<Project> {

    override fun apply(target: Project) {
        target.allprojects { project ->
            project.repositories.let {
                val central = it.mavenCentral()
                it.exclusiveMaven(central, "https://cursemaven.com/", "curse.maven")
                it.exclusiveMaven(central, "https://api.modrinth.com/maven", "maven.modrinth")
            }
        }
    }

    companion object {
        private val gson = Gson()

        val gladys get() = Gladys.build(InetSocketAddress(8082)) {
            it.add("fabric-loader", "net.fabricmc")
            it.add("fabric-api", "net.fabricmc.fabric-api")
            it.add("fabric-language-kotlin", "net.fabricmc")
        }

        fun latestVersion(client: GladysClient, loader: String, version: String, slug: String) = client.fromGladys(loader, version, slug)?.let { gson.fromJson(it, JsonObject::class.java) } ?.let {
            val latest = it.getAsJsonObject("latest")
            SnailDependency(
                latest.getAsJsonPrimitive("version").asString,
                latest.getAsJsonPrimitive("maven").asString
            )
        }

        fun RepositoryHandler.exclusiveMaven(repository: ArtifactRepository, uri: String, group: String) {
            exclusiveContent {
                it.forRepositories(repository, maven { maven ->
                    maven.url = URI(uri)
                })
                it.filter { filter ->
                    filter.includeGroup(group)
                }
            }
        }
    }
}