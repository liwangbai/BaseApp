package com.wpy.utils.ext

fun CharSequence?.withDefault(placeholder: CharSequence): CharSequence {
    return this?.takeIf { it.isNotBlank() } ?: placeholder
}

fun CharSequence?.isNotNullOrBlank(): Boolean {
    return this?.isNotBlank() ?: false
}
