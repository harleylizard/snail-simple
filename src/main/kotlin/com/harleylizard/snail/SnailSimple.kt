package com.harleylizard.snail

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.gradle.api.Plugin
import org.gradle.api.Project
import soul.software.gladys.Gladys
import soul.software.gladys.GladysClient
import java.net.InetSocketAddress

class SnailSimple : Plugin<Project> {

    override fun apply(target: Project) {

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
            Dependency(
                latest.getAsJsonPrimitive("version").asString,
                latest.getAsJsonPrimitive("maven").asString
            )
        }
    }
}