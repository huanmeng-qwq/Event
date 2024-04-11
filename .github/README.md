## Event

A library for quickly creating event managers based on [kyori/event](https://github.com/KyoriPowered/event)

#### Gradle

build.gradle.kts

```kotlin
implementation("com.huanmeng-qwq:event-core:1.0.3")
// implementation("com.huanmeng-qwq:event-core-asm:1.0.3")
```

<details>

<summary>build.gradle</summary>

```groovy
implementation 'com.huanmeng-qwq:event-core:1.0.3'
```

</details>

#### Maven

<details>
<summary>pom.xml</summary>

```xml

<dependency>
    <groupId>com.huanmeng-qwq</groupId>
    <artifactId>event-core</artifactId>
    <version>1.0.3</version>
</dependency>
        <!--<dependency>
            <groupId>com.huanmeng-qwq</groupId>
            <artifactId>event-core-asm</artifactId>
            <version>1.0.3</version>
        </dependency>-->
```

</details>


#### Usage

```java
class SimpleEventManager extends AbstractEventManager<Event, Listener, EventHandler> {
    public SimpleEventManager() {
        super(Event.class, Listener.class, EventHandler.class);
    }
    // ...
}

class SimpleASMEventManager extends AbstractASMEventManager<Event, Listener, EventHandler> {
    public SimpleASMEventManager(ClassLoader classLoader) {
        super(classLoader, Event.class, Listener.class, EventHandler.class);
    }
    // ...
}
```