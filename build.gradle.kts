import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `java-library`
    `kotlin-dsl`
}

allprojects {
    group = "com.huanmeng-qwq"
    version = "1.0.3"
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

    java {
        withSourcesJar()
        withJavadocJar()
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlin {
        compilerOptions.jvmTarget = JvmTarget.JVM_1_8
    }

    tasks.javadoc {
        if (JavaVersion.current().isJava9Compatible) {
            (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
        }
    }
}

