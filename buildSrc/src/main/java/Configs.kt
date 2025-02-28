object Configs {
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0
    private const val versionQualifier = "alpha1"

    private fun generateVersionCode(): Int {
        return versionMajor * 10000 + versionMinor * 100 + versionPatch
    }

    private fun generateVersionName(): String {
        return "$versionMajor.$versionMinor.$versionPatch"
    }

    const val Id = "com.dienty.demo"
    val VersionCode = generateVersionCode()
    val VersionName = generateVersionName()
    const val MinSdk = 21
    const val TargetSdk = 32
    const val CompileSdk = 32
    const val AndroidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"
    val FreeCompilerArgs = listOf(
        "-Xjvm-default=all",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlin.Experimental",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview"
    )

    val FreeCoroutineCompilerArgs = listOf(
        "-Xjvm-default=all",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlin.Experimental",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview"
    )
}