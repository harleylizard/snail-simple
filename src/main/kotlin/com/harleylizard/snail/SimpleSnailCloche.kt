package com.harleylizard.snail

import com.harleylizard.snail.configurations.*
import earth.terrarium.cloche.api.target.FabricTarget
import earth.terrarium.cloche.api.target.ForgeTarget
import earth.terrarium.cloche.api.target.NeoforgeTarget

fun FabricTarget.snail(unit: Configurations.() -> Unit) {
    SnailSimple.gladys.spawn {
        ClocheConfiguration(FabricConfigurations.FABRIC, it, this).also(unit)
    }
}

fun ForgeTarget.snail(unit: Configurations.() -> Unit) {
    SnailSimple.gladys.spawn {
        ClocheConfiguration(ForgeConfigurations.FORGE, it, this).also(unit)
    }
}

fun NeoforgeTarget.snail(unit: Configurations.() -> Unit) {
    SnailSimple.gladys.spawn {
        ClocheConfiguration(NeoForgeConfigurations.NEO_FORGE, it, this).also(unit)
    }
}