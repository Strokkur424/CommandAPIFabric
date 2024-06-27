plugins {
    id("quiet-fabric-loom")
}

version = "9.6.0-SNAPSHOT"
group = "dev.jorel"
description = "Fabric port of the CommandAPI"

val minecraftVersion = "1.20.6"

dependencies {
    minecraft("com.mojang", "minecraft", minecraftVersion)
    mappings(loom.officialMojangMappings())

    modImplementation(include("net.kyori", "adventure-platform-fabric", "5.14.0"))
    implementation("dev.jorel:commandapi-core:9.5.1")
}

tasks {
    jar {
        from("LICENSE")
    }
    remapJar {
        archiveFileName.set("${project.name}-mc$minecraftVersion-${project.version}.jar")
    }
}