package com.skoove.challenge.data.utils.extension

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*


fun MutablePreferences.putString(key: String, value: String?, defaultValue: String? = null) {
    val strValue = value ?: defaultValue
    strValue?.let { this[stringPreferencesKey(key)] = it }
}

fun MutablePreferences.putInt(key: String, value: Int?, defaultValue: Int? = null) {
    val intValue = value ?: defaultValue
    intValue?.let { this[intPreferencesKey(key)] = it }
}

fun MutablePreferences.putLong(key: String, value: Long?, defaultValue: Long? = null) {
    val lngValue = value ?: defaultValue
    lngValue?.let { this[longPreferencesKey(key)] = it }
}

fun MutablePreferences.putBoolean(key: String, value: Boolean?, defaultValue: Boolean? = null) {
    val boolValue = value ?: defaultValue
    boolValue?.let { this[booleanPreferencesKey(key)] = it }
}

fun MutablePreferences.putFloat(key: String, value: Float?, defaultValue: Float? = null) {
    val floatValue = value ?: defaultValue
    floatValue?.let { this[floatPreferencesKey(key)] = it }
}

fun MutablePreferences.putDouble(key: String, value: Double?, defaultValue: Double? = null) {
    val doubleValue = value ?: defaultValue
    doubleValue?.let { this[doublePreferencesKey(key)] = it }
}

fun Preferences.getStringOrNull(key: String): String? {
    return this[stringPreferencesKey(key)]
}

fun Preferences.getIntOrNull(key: String): Int? {
    return this[intPreferencesKey(key)]
}

fun Preferences.getLongOrNull(key: String): Long? {
    return this[longPreferencesKey(key)]
}

fun Preferences.getBooleanOrNull(key: String): Boolean? {
    return this[booleanPreferencesKey(key)]
}

fun Preferences.getFloatOrNull(key: String): Float? {
    return this[floatPreferencesKey(key)]
}

fun Preferences.getDoubleOrNull(key: String): Double? {
    return this[doublePreferencesKey(key)]
}

fun Preferences.containsString(key: String): Boolean {
    return this.contains(stringPreferencesKey(key))
}

fun Preferences.containsInt(key: String): Boolean {
    return this.contains(intPreferencesKey(key))
}

fun Preferences.containsLong(key: String): Boolean {
    return this.contains(longPreferencesKey(key))
}

fun Preferences.containsBoolean(key: String): Boolean {
    return this.contains(booleanPreferencesKey(key))
}

fun Preferences.containsFloat(key: String): Boolean {
    return this.contains(floatPreferencesKey(key))
}

fun Preferences.containsDouble(key: String): Boolean {
    return this.contains(doublePreferencesKey(key))
}

suspend fun DataStore<Preferences>.clear() {
    this.edit { it.clear() }
}


