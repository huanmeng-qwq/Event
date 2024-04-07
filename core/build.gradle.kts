plugins {
    id("me.huanmeng.event.publish-conventions")
}

dependencies {
    api(project(":event-common"))
    compileOnly(libs.event.api)
    compileOnly(libs.event.method)
    compileOnly(libs.slf4j.api)
}