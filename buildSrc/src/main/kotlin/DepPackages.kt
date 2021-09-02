import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementBasicAndroid(){
//    android  kotlin core
    add("implementation", Dependencies.Androidx.CoreKtx)
//    android  Appcompat
    add("implementation", Dependencies.Androidx.AppCompat)
//    android  Material
    add("implementation", Dependencies.Androidx.Material)
//    android  ConstraintLayout
    add("implementation", Dependencies.Androidx.ConstraintLayout)
}

fun DependencyHandler.implementAndroidX(){
    add("implementation", Dependencies.DependencyInjection.daggerHilt)
    add("kapt", Dependencies.DependencyInjection.daggerHiltCompiler)
    add("implementation", Dependencies.WebKit.webKit)
    add("implementation", Dependencies.Lifecycle.viewmodelKtx)
    add("implementation", Dependencies.Lifecycle.lifecycleRuntime)
    add("implementation",Dependencies.Coroutine.coroutineCore)
    add("implementation",Dependencies.Coroutine.coroutineAndroid)
    add("implementation",Dependencies.SwipeRefreshLayout.swiperefreshlayout)
    add("implementation",Dependencies.Storage.RoomRuntime)
    add("kapt", Dependencies.Storage.RoomCompiler)
    add("implementation",Dependencies.Storage.RoomKtx)
    add("implementation",Dependencies.Storage.RoomPaging3)
    add("implementation", Dependencies.Storage.paging)
}

fun DependencyHandler.implementTest() {
    add("testImplementation", Dependencies.Test.junit)
}

fun DependencyHandler.implementAndroidTest() {
    add("androidTestImplementation", Dependencies.AndroidTest.espresso)
    add("androidTestImplementation", Dependencies.AndroidTest.extJunitKtx)

}

//fun DependencyHandler.implementShared() {
//    // desugar
//    add("implementation", Dependencies.DeSugar.DeSugar)
//    // kotlin
//    add("implementation", Dependencies.Kotlin.kotlin)
//    // coroutine
//    add("implementation", Dependencies.Coroutine.coroutineCore)
//    add("implementation", Dependencies.Coroutine.coroutineAndroid)
//    add("implementation", Dependencies.Coroutine.coroutineTest)
//    // dependency injection
//    add("implementation", Dependencies.DependencyInjection.dagger)
//    add("kapt", Dependencies.DependencyInjection.daggerCompiler)
//    add("implementation", Dependencies.DependencyInjection.daggerHilt)
//    add("kapt", Dependencies.DependencyInjection.daggerHiltCompiler)
//    add("implementation", Dependencies.DependencyInjection.androidHilt)
//    add("implementation", Dependencies.DependencyInjection.androidHiltViewModel)
//    add("kapt", Dependencies.DependencyInjection.androidHiltCompiler)
//}
//
//
//fun DependencyHandler.implementCompose() {
//    add("implementation", Dependencies.Compose.composeUi)
//    add("implementation", Dependencies.Compose.composeUiTooling)
//    add("implementation", Dependencies.Compose.composeCompiler)
//    add("implementation", Dependencies.Compose.composeMaterial)
//    add("implementation", Dependencies.Compose.composeFoundation)
//    add("implementation", Dependencies.Compose.composeAnimation)
//    add("implementation", Dependencies.Compose.composeLivedata)
//    add("implementation", Dependencies.Image.coil)
//}
//

//fun DependencyHandler.implementTest() {
//    add("testImplementation", Dependencies.Test.junit)
//    add("testImplementation", Dependencies.Test.truth)
//    add("testImplementation", Dependencies.Test.archCore)
//    add("testImplementation", Dependencies.Test.mockk)
//}
//
//fun DependencyHandler.implementAndroidTest() {
//    add("testImplementation", Dependencies.Test.junit)
//    add("testImplementation", Dependencies.Test.truth)
//    add("testImplementation", Dependencies.AndroidTest.testCore)
//    add("testImplementation", Dependencies.AndroidTest.extJunitKtx)
//    add("testImplementation", Dependencies.AndroidTest.extTruth)
//    add("testImplementation", Dependencies.AndroidTest.espresso)
//    add("testImplementation", Dependencies.DependencyInjection.daggerHiltTesting)
//    add("kaptAndroidTest", Dependencies.DependencyInjection.daggerHiltCompiler)
//}
