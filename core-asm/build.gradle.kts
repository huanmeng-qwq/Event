plugins {
    id("me.huanmeng.event.publish-conventions")
}

dependencies {
    api(project(":event-core")) {
        exclude(group = "net.kyori", module = "event-method")
    }
    api(libs.event.method.asm)
    compileOnly(libs.slf4j.api)
}