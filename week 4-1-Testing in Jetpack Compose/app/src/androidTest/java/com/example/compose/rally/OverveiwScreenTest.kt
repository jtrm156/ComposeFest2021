package com.example.compose.rally

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.example.compose.rally.ui.overview.OverviewBody
import com.example.compose.rally.ui.overview.RallyApp
import org.junit.Rule
import org.junit.Test

class OverviewScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun overviewScreen_alertsDisplayed() {
        composeTestRule.setContent {
            OverviewBody()
        }

        composeTestRule
            .onNodeWithText("Alerts")
            .assertIsDisplayed()
    }

    @Test
    fun rallyTopAppBarTest_clickTabs(){
        var currentScreen:RallyScreen = RallyScreen.Overview // 현재 상태
        composeTestRule.setContent {
            RallyApp(currentScreen){ screen-> currentScreen = screen }
        }

        // 모든 탭을 순회하면서 클릭 하고 현재 상태를 확인한다.
        RallyScreen.values().forEach { screen->
            composeTestRule
                .onNodeWithContentDescription(screen.name)
                .performClick()
            assert(currentScreen == screen)
        }
        Thread.sleep(5000)
    }
}