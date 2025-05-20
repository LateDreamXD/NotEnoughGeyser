plugins {
    id("geyser.publish-conventions")
    id("io.freefair.lombok")
}

tasks {
    processResources {
        // Spigot, BungeeCord, Velocity, Fabric, ViaProxy, NeoForge
        filesMatching(listOf("plugin.yml", "bungee.yml", "velocity-plugin.json", "fabric.mod.json", "viaproxy.yml", "META-INF/neoforge.mods.toml")) {
            expand(
                "id" to "geyser",
                "name" to "NotEnoughGeyser",
                "version" to project.version,
                "description" to project.description,
                "url" to "https://github.com/LateDreamXD/NotEnoughGeyser",
                "author" to "LateDream & GeyserMC"
            )
        }
    }
}
