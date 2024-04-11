package me.huanmeng.event

import net.kyori.event.EventBus
import net.kyori.event.SimpleEventBus
import net.kyori.event.method.MethodHandleEventExecutorFactory
import net.kyori.event.method.MethodScanner
import net.kyori.event.method.MethodSubscriptionAdapter
import net.kyori.event.method.SimpleMethodSubscriptionAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 2024/3/13<br>
 * Event<br>
 * @author huanmeng_qwq
 */
abstract class AbstractEventManager<E : Any, L : Any, A : Annotation>(
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
            MethodHandleEventExecutorFactory(),
            methodScanner
        )
    }

    constructor(eventClass: Class<E>, listenerClass: Class<L>, annotationClass: Class<A>, logger: Logger) : this(
        eventClass,
        listenerClass,
        annotationClass,
        logger,
        MethodScannerImpl(annotationClass)
    )

    constructor(eventClass: Class<E>, listenerClass: Class<L>, annotationClass: Class<A>) : this(
        eventClass,
        listenerClass,
        annotationClass,
        LoggerFactory.getLogger("EventManager"),
    )
}