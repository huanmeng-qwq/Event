package me.huanmeng.event

import net.kyori.event.EventBus
import net.kyori.event.SimpleEventBus
import net.kyori.event.method.MethodScanner
import net.kyori.event.method.MethodSubscriptionAdapter
import net.kyori.event.method.SimpleMethodSubscriptionAdapter
import net.kyori.event.method.asm.ASMEventExecutorFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractASMEventManager<E : Any, L : Any, A : Annotation>(
    classLoader: ClassLoader,
    eventClass: Class<E>,
    listenerClass: Class<L>,
    annotationClass: Class<A>,
    logger: Logger,
    methodScanner: MethodScanner<L>,
) : AbstractCommonEventManager<E, L, A>(eventClass, listenerClass, annotationClass, logger, methodScanner) {
    override val eventBus: EventBus<E> = SimpleEventBus(eventClass)
    override val methodSubscription: MethodSubscriptionAdapter<L> by lazy {
        SimpleMethodSubscriptionAdapter(
            eventBus,
            ASMEventExecutorFactory(classLoader),
            methodScanner
        )
    }

    constructor(
        classLoader: ClassLoader,
        eventClass: Class<E>,
        listenerClass: Class<L>,
        annotationClass: Class<A>,
        logger: Logger
    ) : this(
        classLoader,
        eventClass,
        listenerClass,
        annotationClass,
        logger,
        MethodScannerImpl(annotationClass)
    )

    constructor(
        classLoader: ClassLoader,
        eventClass: Class<E>,
        listenerClass: Class<L>,
        annotationClass: Class<A>
    ) : this(
        classLoader,
        eventClass,
        listenerClass,
        annotationClass,
        LoggerFactory.getLogger("EventManager"),
    )
}