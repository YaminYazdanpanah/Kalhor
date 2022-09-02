
object Dependencies {

    const val ANDROIDX_APPCOMPACT = "androidx.appcompat:appcompat:${Version.ANDROIDX_APPCOMPACT}"
    const val ANDROIDX_ACTIVITY = "androidx.activity:activity-ktx:${Version.ANDROIDX_ACTIVITY}"
    const val ANDROIDX_CORE = "androidx.core:core-ktx:${Version.ANDROIDX_CORE}"
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"

    // Hilt
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Version.HILT}"
    const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:${Version.HILT_NAVIGATION}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Version.HILT_COMPILER}"

    // Startup
    const val STARTUP = "androidx.startup:startup-runtime:${Version.STARTUP}"

    // Coroutine
    const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINE}"
    const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINE}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"
    const val LIFECYCLE_SAVEDSTATE = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.LIFECYCLE}"

    // Compose
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Version.COMPOSE}"
    const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime:${Version.COMPOSE}"
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Version.COMPOSE}"
    const val COMPOSE_FOUNDATION_LAYOUT = "androidx.compose.foundation:foundation-layout:${Version.COMPOSE}"
    const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:${Version.COMPOSE}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Version.COMPOSE}"
    const val COMPOSE_MATERIAL_ICONS = "androidx.compose.material:material-icons-extended:${Version.COMPOSE}"
    const val COMPOSE_ANIMATION = "androidx.compose.animation:animation:${Version.COMPOSE}"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:1.1.1"
    const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:1.1.1"
    const val COMPOSE_RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata:${Version.COMPOSE}"

    // Accompanist
    const val ACCOMPANIST_PAGER = "com.google.accompanist:accompanist-pager:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_SWIPE_REFRESH = "com.google.accompanist:accompanist-swiperefresh:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_INSETS = "com.google.accompanist:accompanist-insets:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_SYSTEM_UI_CONTROLLER = "com.google.accompanist:accompanist-systemuicontroller:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_FLOW_LAYOUT = "com.google.accompanist:accompanist-flowlayout:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_PERMISSIONS = "com.google.accompanist:accompanist-permissions:${Version.ACCOMPANIST}"

    // Navigation
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Version.NAVIGATION_COMPOSE}"

    // Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
    const val RETROFIT_CONVERTER_SCALARS = "com.squareup.retrofit2:converter-scalars:${Version.RETROFIT}"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Version.LOGGING_INTERCEPTOR}"

    // Timber
    const val TIMBER = "com.jakewharton.timber:timber:${Version.TIMBER}"

    // Language
    const val LINGVER = "com.github.YarikSOffice:lingver:${Version.LINGVER}"

    // Security Crypto
    const val SECURITY_CRYPTO = "androidx.security:security-crypto:${Version.SECURITY_CRYPTO}"

    // Coil
    const val COIL = "com.github.skydoves:landscapist-coil:${Version.COIL}"
    const val COIL_KT = "io.coil-kt:coil-compose:${Version.COIL_KT}"
    const val COIL_SVG = "io.coil-kt:coil-svg:${Version.COIL_KT}"

    // Snapper
    const val SNAPPER = "dev.chrisbanes.snapper:snapper:${Version.SNAPPER}"

    // Serialization
    const val KOTLIN_X_SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.KOTLIN_X_SERIALIZATION}"
    const val KOTLIN_SERIALIZATION_CONVERTOR = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Version.KOTLIN_SERIALIZATION_CONVERTOR}"

    // DataStore
    const val DATA_STORE = "androidx.datastore:datastore-preferences:${Version.DATA_STORE}"

    // Test
    const val JUNIT = "junit:junit:${Version.JUNIT}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Version.ANDROIDX_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Version.ROBOLECTRIC}"
    const val ANDROIDX_TEST_EXT = "androidx.test.ext:junit-ktx:${Version.ANDROIDX_TEST_EXT}"
    const val MOCKK_ANDROID = "io.mockk:mockk-android:${Version.MOCKK}"
    const val MOCKK_AGENT_JVM = "io.mockk:mockk-agent-jvm:${Version.MOCKK}"
    const val MOCKK = "io.mockk:mockk:${Version.MOCKK}"
    const val MOCK_WEBSERVER = "com.squareup.okhttp3:mockwebserver:${Version.MOCK_WEBSERVER}"
    // Makes it possible to use context in the local tests using robolectric 4+
    const val ANDROIDX_TEST_CORE = "androidx.test:core:${Version.ANDROIDX_TEST_CORE}"
    const val KOTLINX_COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINE}"
    const val COMPOSE_UI_JUNIT4 ="androidx.compose.ui:ui-test-junit4:${Version.COMPOSE}"
    const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Version.COMPOSE}"

}