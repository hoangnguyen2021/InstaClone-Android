object Compose {
    const val composeVersion = "1.3.2"
    const val composeMaterial3 = "1.0.1"
    private const val hiltNavigationVersion = "1.0.0"
    private const val pagingComposeVersion = "1.0.0-rc1"

    const val animation = "androidx.compose.animation:animation:$composeVersion"
    const val compiler = "androidx.compose.compiler:compiler:$composeVersion"
    const val material3 = "androidx.compose.material3:material3:$composeMaterial3"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"

    const val navigation = "androidx.navigation:navigation-compose:${Navigation.version}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationVersion"
    const val activityCompose = "androidx.activity:activity-compose:${Activity.version}"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Lifecycle.version}"
    const val pagingCompose = "androidx.paging:paging-compose:$pagingComposeVersion"
}