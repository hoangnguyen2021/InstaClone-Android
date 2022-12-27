package myapp.hoang.core.coroutines

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    val io: CoroutineContext
    val main: CoroutineContext
    val default: CoroutineContext
}