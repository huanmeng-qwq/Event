group = "com.huanmeng-qwq.event"
version = "1.0"

plugins {
    `java-library`
    `kotlin-dsl`
}

allprojects {
    repositories {
        mavenCentral()
    }
    apply(plugin = "java-library")
    apply(plugin = "kotlin")

    tasks.compileJava {
        options.encoding = "UTF-8"
        options.isIncremental = true
        options.compilerArgs.add("-parameters")
    }
    tasks.whenTaskAdded {
        if (name == "shadowJar") {
            tasks.build {
                dependsOn(this@whenTaskAdded)
            }
        }
    }
}
