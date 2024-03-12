package me.huanmeng.event

import net.kyori.event.EventBus
import net.kyori.event.SimpleEventBus
import net.kyori.event.method.MethodScanner
import net.kyori.event.method.MethodSubscriptionAdapter
import net.kyori.event.method.SimpleMethodSubscriptionAdapter
import net.kyori.event.method.asm.ASMEventExecutorFactory
import org.slf4j.Logger

abstract class AbstractASMEventManager<E : Any, L : Any, A : Annotation>(
    classLoader: ClassLoader,
    eventClass: Class<E>,
    listenerClass: Class<L>,
    annotationClass: Class<A>,
    methodScanner: MethodScanner<L> = MethodScannerImpl(annotationClass),
    logger: Logger
) : AbstractCommonEventManager<E, L, A>(eventClass, listenerClass, annotationClass, methodScanner, logger) {
    final override val eventBus: EventBus<E> = SimpleEventBus(eventClass)
    override val methodSubscription: MethodSubscriptionAdapter<L> = SimpleMethodSubscriptionAdapter(
        eventBus,
        ASMEventExecutorFactory(classLoader),
        methodScanner
    )
}