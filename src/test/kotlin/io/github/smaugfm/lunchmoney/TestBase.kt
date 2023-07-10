package io.github.smaugfm.lunchmoney

import org.junit.jupiter.api.BeforeAll
import reactor.tools.agent.ReactorDebugAgent

open class TestBase {
    companion object {
        @BeforeAll
        @JvmStatic
        fun initReactorTools() {
            ReactorDebugAgent.init()
        }
    }
}
