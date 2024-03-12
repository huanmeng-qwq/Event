package me.huanmeng.event

import net.kyori.event.method.MethodScanner
import java.lang.reflect.Method

/**
 * 2024/3/13<br>
 * Event<br>
 * @author huanmeng_qwq
 */
class MethodScannerImpl<L, E : Annotation>(
    private val annotation: Class<E>,
    private val priority: ((E) -> Int) = { -1 }
) : MethodScanner<L> {

    override fun shouldRegister(listener: L & Any, method: Method): Boolean {
        return method.isAnnotationPresent(annotation)
    }

    override fun postOrder(listener: L & Any, method: Method): Int {
        return priority(method.getAnnotation(annotation))
    }

    override fun consumeCancelledEvents(listener: L & Any, method: Method): Boolean {
        return false
    }
}