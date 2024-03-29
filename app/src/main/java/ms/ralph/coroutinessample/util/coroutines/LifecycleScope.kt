package ms.ralph.coroutinessample.util.coroutines

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlin.coroutines.CoroutineContext

class LifecycleScope(
    private val lifecycleOwner: LifecycleOwner,
    coroutineScope: CoroutineScope = AndroidScope
) : LifecycleObserver, CoroutineScope {

    init {
        coroutineScope.coroutineContext[Job]
            ?.let { throw IllegalArgumentException("A Context with Job already passed") }

        lifecycleOwner.lifecycle.addObserver(this)
    }

    private val mJob = SupervisorJob()

    override val coroutineContext: CoroutineContext = (coroutineScope + mJob).coroutineContext

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        mJob.cancelChildren()
        lifecycleOwner.lifecycle.removeObserver(this)
    }

    fun bindLaunch(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(coroutineContext, start, block)
}
