// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.theme.Theme
import ui.widgets.dashboard.DashboardScreen
import ui.widgets.util.LocalIsDarkMode


@Composable
@Preview
fun App() {
    CompositionLocalProvider(
        LocalIsDarkMode provides mutableStateOf(isSystemInDarkTheme())
    ) {
        Theme(
            useDarkTheme = LocalIsDarkMode.current.value
        ) {
            DashboardScreen()
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pharmacy Storage",
        icon = painterResource("Icons/capsules_64px.png"),
    ) {
        App()
    }
}
