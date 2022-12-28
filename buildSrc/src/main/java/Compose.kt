object Compose {
    const val composeVersion = "1.3.2"
    private const val composeMaterial3 = "1.0.1"
    private const val hiltNavigationVersion = "1.0.0"
    private const val pagingComposeVersion = "1.0.0-rc1"
    private const val coilComposeVersion = "2.2.2"

    const val animation = "androidx.compose.animation:animation:$composeVersion"
    const val compiler = "androidx.compose.compiler:compiler:$composeVersion"
    const val material3 = "androidx.compose.material3:material3:$composeMaterial3"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"

    const val navigation = "androidx.navigation:navigation-compose:${Navigation.version}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationVersion"
    const val activityCompose = "androidx.activity:activity-compose:${Activity.version}"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Lifecycle.version}"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Lifecycle.lifecycleRuntimeComposeVersion}"
    const val pagingCompose = "androidx.paging:paging-compose:$pagingComposeVersion"

    const val coil = "io.coil-kt:coil-compose:$coilComposeVersion"
}