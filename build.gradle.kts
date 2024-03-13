plugins {
    `java-library`
    `kotlin-dsl`
    `maven-publish`
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
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
                pom {
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
}
