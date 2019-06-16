package com.decathlon.dojo.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * CoroutineDispatcherProvider for test purposes
 */
class TestCoroutineDispatcher : CoroutineDispatcherProvider() {
    override val main: CoroutineDispatcher = Dispatchers.Unconfined
    override val io: CoroutineDispatcher = Dispatchers.Unconfined
}