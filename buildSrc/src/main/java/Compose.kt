object Compose {
    const val version = "1.3.2"
    private const val material3Version = "1.0.1"
    private const val hiltNavigationVersion = "1.0.0"
    private const val pagingVersion = "1.0.0-rc1"
    private const val coilVersion = "2.2.2"
    private const val stateEventsVersion = "1.2.3"

    const val animation = "androidx.compose.animation:animation:$version"
    const val compiler = "androidx.compose.compiler:compiler:$version"
    const val material3 = "androidx.compose.material3:material3:$material3Version"
    const val runtime = "androidx.compose.runtime:runtime:$version"
    const val ui = "androidx.compose.ui:ui:$version"
    const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"

    const val navigation = "androidx.navigation:navigation-compose:${Navigation.version}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationVersion"
    const val activity = "androidx.activity:activity-compose:${Activity.version}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Lifecycle.version}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${Lifecycle.lifecycleRuntimeComposeVersion}"
    const val pagingCompose = "androidx.paging:paging-compose:$pagingVersion"

    const val coil = "io.coil-kt:coil-compose:$coilVersion"

    const val stateEvents = "com.github.leonard-palm:compose-state-events:$stateEventsVersion"
}