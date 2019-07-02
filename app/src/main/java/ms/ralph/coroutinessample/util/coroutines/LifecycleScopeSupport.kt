package ms.ralph.coroutinessample.util.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart

interface LifecycleScopeSupport {
    val scope: LifecycleScope

    fun bindLaunch(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = scope.bindLaunch(start, block)
}

