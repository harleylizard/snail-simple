plugins {
    id("java")
    kotlin("jvm") version "2.1.0"
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.harleylizard"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.soulsoftware.dev/releases")
    maven("https://maven.msrandom.net/repository/root/")
    maven("https://maven.msrandom.net/repository/cloche/")
}

dependencies {
    implementation("soul.software:gladys:1.4-SNAPSHOT")
    compileOnly("soul.software:gladys:1.4-SNAPSHOT")

    implementation("com.google.code.gson:gson:2.11.0")
    implementation("fabric-loom:fabric-loom.gradle.plugin:1.9-SNAPSHOT")

    implementation("earth.terrarium:cloche:0.8.6")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("snail") {
            id = "com.harleylizard.snail-simple"
            implementationClass = "com.harleylizard.snail.SnailSimple"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "soulsoftwareincPublic"
            url = uri("https://maven.soulsoftware.dev/releases")
            credentials {
                username = project.findProperty("soulsoftwareincPrivateUsername") as String? ?: System.getenv("SOULSOFTWAREINC_PRIVATE_USERNAME")
                password = project.findProperty("soulsoftwareincPrivatePassword") as String? ?: System.getenv("SOULSOFTWAREINC_PRIVATE_PASSWORD")
            }
            authentication {
                create("basic", BasicAuthentication::class.java)
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = "com.harleylizard"
            artifactId = "snail-simple"
            version = "1.0-SNAPSHOT"
            from(components["kotlin"])
        }

    }
}