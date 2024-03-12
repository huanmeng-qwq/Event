package me.huanmeng.event

import net.kyori.event.EventBus
import net.kyori.event.SimpleEventBus
import net.kyori.event.method.MethodHandleEventExecutorFactory
import net.kyori.event.method.MethodScanner
import net.kyori.event.method.MethodSubscriptionAdapter
import net.kyori.event.method.SimpleMethodSubscriptionAdapter
import org.slf4j.Logger

/**
 * 2024/3/13<br>
 * Event<br>
 * @author huanmeng_qwq
 */
abstract class AbstractEventManager<E : Any, L : Any, A : Annotation>(
    eventClass: Class<E>,
    listenerClass: Class<L>,
    annotationClass: Class<A>,
    methodScanner: MethodScanner<L> = MethodScannerImpl(annotationClass),
    logger: Logger
) : AbstractCommonEventManager<E, L, A>(eventClass, listenerClass, annotationClass, methodScanner, logger) {
    final override val eventBus: EventBus<E> = SimpleEventBus(eventClass)
    override val methodSubscription: MethodSubscriptionAdapter<L> = SimpleMethodSubscriptionAdapter(
        eventBus,
        MethodHandleEventExecutorFactory(),
        methodScanner
    )
}