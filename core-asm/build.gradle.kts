dependencies {
    api(project(":event-core")) {
        exclude(group = "net.kyori", module = "event-method")
    }
    compileOnlyApi(libs.event.method.asm)
}