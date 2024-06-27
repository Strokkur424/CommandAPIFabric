plugins {
    id("quiet-fabric-loom")
}

version = "9.6.0-SNAPSHOT"
group = "dev.jorel"
description = "Fabric port of the CommandAPI"

val minecraft_version: String by project

dependencies {
    minecraft("com.mojang", "minecraft", minecraft_version)
    mappings(loom.officialMojangMappings())

    modImplementation(include("net.kyori", "adventure-platform-fabric", "5.14.0"))
    implementation("dev.jorel:commandapi-core:9.5.1")
}

tasks {
    jar {
        from("LICENSE")
    }
    remapJar {
        archiveFileName.set("${project.name}-mc$minecraft_version-${project.version}.jar")
    }
}