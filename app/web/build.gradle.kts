import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  kotlin("multiplatform")
  alias(libs.plugins.compose.multiplatform)
  alias(libs.plugins.compose.compiler)
}

kotlin {
  js(IR) {
    browser()
    binaries.executable()
  }

  @OptIn(ExperimentalWasmDsl::class)
  wasmJs {
    browser()
    binaries.executable()
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":app"))
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(libs.compose.multiplatform.material3.windowsizeclass)
        implementation(libs.decompose)
        implementation(libs.decompose.compose)
        implementation(libs.decompose.router)
      }
    }

    val jsMain by getting {
      dependencies {
        implementation(libs.ktor.client.js)
      }
    }

    val wasmJsMain by getting {
      dependencies {
        implementation(libs.ktor.client.js)
      }
    }

  }
}
