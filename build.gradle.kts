import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `java-library`
    `kotlin-dsl`
    `maven-publish`
    signing
}

allprojects {
    group = "com.huanmeng-qwq.event"
    version = "1.0"
    repositories {
        mavenCentral()
    }
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

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

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
                pom {
                    name = "Event"
                    groupId = group.toString()
                    artifactId = project.name
                    url = "https://github.com/huanmeng-qwq/Event"
                    description = "A highly customizable event handling and manager"
                    developers {
                        developer {
                            name = "huanmeng-qwq"
                            url = "https://github.com/huanmeng-qwq"
                            email = "huanmeng@huanmeng-qwq.com"
                        }
                    }
                    licenses {
                        license {
                            name = "MIT"
                            url = "https://opensource.org/licenses/MIT"
                        }
                    }
                    scm {
                        url = "https://github.com/huanmeng-qwq/Event"
                        connection = "scm:git:git@github.com/Event.git"
                        developerConnection = "scm:git:git@github.com:huanmeng-qwq/Event.git"
                        tag = "HEAD"
                    }
                }
            }
        }
        repositories {
            maven {
                name = "central"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }
            }
        }
    }

    signing {
        val key = System.getenv("MAVEN_GPG_PRIVATE_KEY") ?: findProperty("MAVEN_GPG_PRIVATE_KEY")?.toString()
        val pwd = System.getenv("MAVEN_GPG_PASSPHRASE") ?: findProperty("MAVEN_GPG_PASSPHRASE")?.toString()
        useInMemoryPgpKeys(
            key,
            pwd
        )
        sign(publishing.publications["maven"])
    }

    tasks.javadoc {
        if (JavaVersion.current().isJava9Compatible) {
            (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
        }
    }
}

