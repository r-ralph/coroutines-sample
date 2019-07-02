package ms.ralph.coroutinessample

import kotlinx.coroutines.delay
import java.util.Date

class MainUseCase {
    suspend fun sleepAndReturnTime(sleepMillis: Long): String {
        delay(sleepMillis)
        return Date().toString()
    }
}
