object Compose {
    private const val bomVersion = "2023.01.00"

    const val version = "1.3.3"
    const val compilerVersion = "1.4.2"
    private const val material3Version = "1.0.1"
    private const val hiltNavigationVersion = "1.0.0"
    private const val pagingVersion = "1.0.0-rc1"
    private const val coilVersion = "2.2.2"
    private const val constraintLayoutVersion = "1.1.0-alpha07"
    private const val stateEventsVersion = "1.2.3"
    private const val composeCropperVersion = "0.2.4"
    private const val pagerIndicatorVersion = "2.2"

    const val bom = "androidx.compose:compose-bom:$bomVersion"

    const val animation = "androidx.compose.animation:animation"
    const val foundation = "androidx.compose.foundation:foundation"
    const val compiler = "androidx.compose.compiler:compiler:$compilerVersion"
    const val material3 = "androidx.compose.material3:material3"
    const val runtime = "androidx.compose.runtime:runtime"
    const val ui = "androidx.compose.ui:ui"
    const val uiUtil = "androidx.compose.ui:ui-util"
    const val uiTooling = "androidx.compose.ui:ui-tooling"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"

    const val navigation = "androidx.navigation:navigation-compose:${Navigation.version}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationVersion"
    const val activity = "androidx.activity:activity-compose:${Activity.version}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Lifecycle.version}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${Lifecycle.lifecycleRuntimeComposeVersion}"
    const val pagingCompose = "androidx.paging:paging-compose:$pagingVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:$constraintLayoutVersion"

    const val coil = "io.coil-kt:coil-compose:$coilVersion"
    const val coilVideo = "io.coil-kt:coil-video:$coilVersion"

    const val stateEvents = "com.github.leonard-palm:compose-state-events:$stateEventsVersion"
    const val composeCropper = "com.github.SmartToolFactory:Compose-Cropper:$composeCropperVersion"
    const val pagerIndicator = "com.github.talhatek:pager_indicator:$pagerIndicatorVersion"
}