package com.harleylizard.snail.configurations

import com.harleylizard.snail.Dependency

sealed interface Configurations {

    fun implementation(slug: String)

    fun include(slug: String)

    fun runtimeOnly(slug: String)

    fun compileOnly(slug: String)
}