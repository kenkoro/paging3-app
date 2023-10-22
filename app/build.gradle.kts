plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.devtools.ksp")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.kenkoro.paging3"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.kenkoro.paging3"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.3"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  val daggerHilt = "2.48.1"
  implementation("com.google.dagger:dagger-android:$daggerHilt")
  implementation("com.google.dagger:hilt-android:$daggerHilt")
  ksp("com.google.dagger:hilt-android-compiler:$daggerHilt")
  kspAndroidTest("com.google.dagger:hilt-android-compiler:$daggerHilt")

  val room = "2.6.0"
  implementation("androidx.room:room-runtime:$room")
  implementation("androidx.room:room-ktx:$room")
  annotationProcessor("androidx.room:room-compiler:$room")
  ksp("androidx.room:room-compiler:$room")

  val coil = "2.4.0"
  val paging3 = "3.2.1"
  val retrofit2 = "2.9.0"
  val okhttp3 = "5.0.0-alpha.11"
  implementation("io.coil-kt:coil-compose:$coil")
  implementation("androidx.paging:paging-runtime-ktx:$paging3")
  implementation("androidx.paging:paging-compose:$paging3")
  implementation("com.squareup.retrofit2:retrofit:$retrofit2")
  implementation("com.squareup.retrofit2:converter-moshi:$retrofit2")
  implementation("com.squareup.okhttp3:logging-interceptor:$okhttp3")

  val coreKtx = "1.12.0"
  val lifecycleRuntimeKtx = "2.6.2"
  val activityCompose = "1.8.0"
  val hiltNavigationCompose = "1.0.0"
  val lifecycle = "2.6.2"
  implementation("androidx.core:core-ktx:$coreKtx")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeKtx")
  implementation("androidx.activity:activity-compose:$activityCompose")
  implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationCompose")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle")

  implementation(platform("androidx.compose:compose-bom:2023.10.01"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")

  val junit = "4.13.2"
  val extJUnit = "1.1.5"
  val espresso = "3.5.1"
  val truth = "1.1.5"
  testImplementation("junit:junit:$junit")
  testImplementation("com.google.truth:truth:$truth")
  androidTestImplementation("androidx.test.ext:junit:$extJUnit")
  androidTestImplementation("androidx.test.espresso:espresso-core:$espresso")
  androidTestImplementation("com.google.truth:truth:$truth")

  androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
}