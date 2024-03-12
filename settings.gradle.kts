plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

sequenceOf(
    "common",
    "core",
    "core-asm"
).forEach { path ->
    val name = if (path.contains("/")) {
        path.replace('/', '-')
    } else path
    include("event-$name")
    project(":event-$name").projectDir = File(path)
}


rootProject.name = "Event"

