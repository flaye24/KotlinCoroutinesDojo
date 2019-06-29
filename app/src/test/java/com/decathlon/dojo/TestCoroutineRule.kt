package com.decathlon.dojo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class TestCoroutineRule : TestWatcher() {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    override fun finished(description: Description?) {
        super.finished(description)

        Dispatchers.resetMain()

    }

    override fun starting(description: Description?) {
        super.starting(description)

        Dispatchers.setMain(testCoroutineDispatcher)
    }
}