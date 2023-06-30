package pe.codex.musicspot.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val HorizontalPadding = 12.dp





/*
* Font size
* */
val Small = 14.sp
val Small_2 = 18.sp
val Medium = 22.sp
val Large = 28.sp

/*
* Estos hay que incluirlo en el Theme de compose
* */

val SectionTitleStyle = TextStyle(
    fontSize = Medium,
    fontWeight = FontWeight.SemiBold
)

val ItemSectionStyle = TextStyle(
    fontSize = Small_2,
    fontWeight = FontWeight.Normal
)

val TitleScreenStyle = SpanStyle(
    fontSize = Large,
    fontWeight = FontWeight.SemiBold
)

val SubtitleScreenStyle = SpanStyle(
    fontSize = Small,
    fontWeight = FontWeight.Normal
)