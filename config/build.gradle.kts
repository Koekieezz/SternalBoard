plugins {
    id("project.publishing-conventions")
}

dependencies {
    compileOnly(libs.spigot)
    api ("net.elytrium:serializer:1.1.1")
}