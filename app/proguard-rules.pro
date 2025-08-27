# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep line numbers for debugging stack traces
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Preserve annotations
-keepattributes *Annotation*

# Keep generic signatures
-keepattributes Signature

# Keep inner classes information
-keepattributes InnerClasses

# Keep enum methods
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Parcelable classes
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
}

# Serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep Jetpack Compose
-keep class androidx.compose.** { *; }
-keep class androidx.compose.runtime.** { *; }
-dontwarn androidx.compose.**

# Keep Compose destinations generated code
-keep class wwon.seokk.abandonedpets.ui.** { *; }

# Keep data classes and model classes
-keep class wwon.seokk.abandonedpets.data.** { *; }
-keep class wwon.seokk.abandonedpets.domain.entity.** { *; }

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel { *; }
-keep @dagger.hilt.android.lifecycle.HiltViewModel class * { *; }

# Keep ViewModels
-keep class **.*ViewModel { *; }
-keep class **.*ViewModel$* { *; }

# Keep Retrofit and OkHttp
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class com.squareup.okhttp3.** { *; }
-dontwarn retrofit2.**
-dontwarn okhttp3.**

# Keep Gson
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Keep Room database entities
-keep class wwon.seokk.abandonedpets.data.local.entities.** { *; }

# Keep API response/request classes
-keep class wwon.seokk.abandonedpets.data.remote.model.** { *; }

# Keep Coil image loading
-keep class coil.** { *; }
-dontwarn coil.**

# Keep Orbit MVI
-keep class org.orbitmvi.orbit.** { *; }
-dontwarn org.orbitmvi.orbit.**

# Keep Coroutines
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

# Keep AboutLibraries
-keep class com.mikepenz.aboutlibraries.** { *; }
-dontwarn com.mikepenz.aboutlibraries.**

# Keep Navigation Component
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

# Keep Paging 3
-keep class androidx.paging.** { *; }
-dontwarn androidx.paging.**

# Keep Material 3 components
-keep class androidx.compose.material3.** { *; }
-dontwarn androidx.compose.material3.**

# Keep custom nav types
-keep class wwon.seokk.abandonedpets.ui.*NavType { *; }

# Keep splash screen
-keep class androidx.core.splashscreen.** { *; }
-dontwarn androidx.core.splashscreen.**

# Keep preferences
-keep class wwon.seokk.abandonedpets.util.** { *; }

# Keep custom theme and UI
-keep class wwon.seokk.abandonedpets.ui.theme.** { *; }
-keep class wwon.seokk.abandonedpets.ui.common.** { *; }

# Suppress warnings for optional dependencies
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**