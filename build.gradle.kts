// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(notation = libs.plugins.android.application) apply false
    alias(notation = libs.plugins.jetbrains.kotlin.android) apply false
    alias(notation = libs.plugins.kotlin.serialization) apply false
    alias(notation = libs.plugins.ksp) apply false
    alias(notation = libs.plugins.safe.args) apply false
}