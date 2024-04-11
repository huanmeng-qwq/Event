plugins {
    id("me.huanmeng.event.publish-conventions")
}

dependencies {
    api(project(":event-common"))
    compileOnly(libs.slf4j.api)
}