plugins {
    id("me.huanmeng.event.publish-conventions")
}

dependencies {
    api(project(":event-core"))
    compileOnly(libs.event.api)
    compileOnly(libs.event.method.asm)
    compileOnly(libs.slf4j.api)
}