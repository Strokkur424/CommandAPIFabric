plugins {
    val indraVersion = "3.1.3"
    id("net.kyori.indra") version indraVersion
    id("quiet-fabric-loom")
}

version = "9.6.0-SNAPSHOT"
group = "dev.jorel"
description = "Fabric port of the CommandAPI"

val minecraftVersion = "1.20.6"

dependencies {
    minecraft("com.mojang", "minecraft", minecraftVersion)
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc", "fabric-loader", "0.15.11")
    modImplementation("net.fabricmc.fabric-api", "fabric-api", "0.100.3+1.21")

    annotationProcessor("org.apache.logging.log4j", "log4j-core", "2.23.1")

    val jlineVersion = "3.26.2"
    implementation(include("org.jline", "jline", jlineVersion))
    implementation(include("org.jline", "jline-terminal-jansi", jlineVersion))

    implementation(include("org.fusesource.jansi", "jansi", "2.4.1"))

    modImplementation(include("net.kyori", "adventure-platform-fabric", "5.14.0"))

    implementation(transitiveInclude("org.spongepowered:configurate-hocon:4.1.2")!!)

    compileOnly("org.checkerframework", "checker-qual", "3.44.0")

    implementation(include("net.fabricmc", "mapping-io", "0.6.1"))
}

indra {
    javaVersions().target(21)
}

tasks {
    processResources {
        val props = mapOf(
            "name" to "CommandAPIFabric",
            "description" to project.description,
            "version" to project.version,
            "githubUrl" to "https://github.com/jpenilla/better-fabric-console"
        )
        inputs.properties(props)
        filesMatching("fabric.mod.json") {
            expand(props)
        }
    }
    jar {
        from("LICENSE")
    }
    remapJar {
        archiveFileName.set("${project.name}-mc$minecraftVersion-${project.version}.jar")
    }
}