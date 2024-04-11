plugins {
    id("me.huanmeng.event.publish-conventions")
}

dependencies {
    api(libs.event.api)
    api(libs.event.method)
    compileOnly(libs.slf4j.api)
}