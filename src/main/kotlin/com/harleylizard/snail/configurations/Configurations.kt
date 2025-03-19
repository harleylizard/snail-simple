package com.harleylizard.snail.configurations

sealed interface Configurations {

    fun implementation(slug: String)

    fun include(slug: String)

    fun runtimeOnly(slug: String)

    fun compileOnly(slug: String)
}