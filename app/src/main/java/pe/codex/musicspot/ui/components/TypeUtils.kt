package pe.codex.musicspot.ui.components

fun String.limitCharacters(max: Int): String {
    return if (length < max) this else take(max-3).plus("...")
}