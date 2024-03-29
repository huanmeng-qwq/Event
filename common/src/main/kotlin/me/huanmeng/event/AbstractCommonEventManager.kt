package me.huanmeng.event

import net.kyori.event.EventBus
import net.kyori.event.PostResult
import net.kyori.event.method.MethodScanner
import net.kyori.event.method.MethodSubscriptionAdapter
import org.slf4j.Logger

/**
 * 2024/3/13<br>
 * Event<br>
 * @author huanmeng_qwq
 * @param E event class
 * @param L listener class
 * @param A event handler annotation class
 */
abstract class AbstractCommonEventManager<E : Any, L : Any, A : Annotation>(
    protected val eventClass: Class<E>,
    protected val listenerClass: Class<L>,
    protected val annotationClass: Class<A>,
    protected val methodScanner: MethodScanner<L> = MethodScannerImpl(annotationClass),
    protected val logger: Logger,
) {
    protected abstract val eventBus: EventBus<E>
    protected abstract val methodSubscription: MethodSubscriptionAdapter<L>

    protected abstract fun onRegister(listener: L)

    protected abstract fun onUnregister(listener: L)

    open fun callEvent(event: E): E {
        eventBus.post(event).apply {
            if (!wasSuccessful()) {
                doException(this)
            }
        }
        return event
    }

    protected open fun doException(postResult: PostResult) {
        logger.error("An error occurred while processing some listening events")
        postResult.exceptions().forEach { (eventSubscription, exception) ->
            logger.error("{}", eventSubscription.genericType(), exception)
        }
    }

    open fun register(listener: L) {
        onRegister(listener)
        methodSubscription.register(listener)
    }

    open fun unregister(listener: L) {
        onUnregister(listener)
        methodSubscription.unregister(listener)
    }
}