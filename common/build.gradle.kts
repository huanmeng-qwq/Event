plugins {
    id("me.huanmeng.event.publish-conventions")
}

dependencies {
    compileOnly(libs.event.api)
    compileOnly(libs.event.method)
    compileOnly(libs.slf4j.api)
}