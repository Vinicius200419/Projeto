# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.

-keep class com.receitas.models.** { *; }
-keep class com.receitas.database.** { *; }
-keep class androidx.room.** { *; }
-keep class com.squareup.picasso.** { *; }

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
