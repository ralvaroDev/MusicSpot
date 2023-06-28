package pe.codex.musicspot.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.core.view.WindowCompat

val LocalExtendedColors = staticCompositionLocalOf { lightColorMusicScheme() }

/*private val DarkColorScheme = MusicColorScheme(

)*/

private val LightColorScheme = lightColorMusicScheme(
    textColor = Color.White,
    background = DarkBlue,
    onBackground = LightBlue,
    primary = Green40,
    secondary = GrayDark,
    secondaryVariant = GrayLight
)


@Composable
fun MusicSpotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val extendedColors = when {
        darkTheme -> LightColorScheme
        //DarkColorScheme
        else -> LightColorScheme
    }
    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = extendedColors.background.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    darkTheme
            }
        }

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = {
                ProvideTextStyle(
                    value = TextStyle(color = extendedColors.textColor),
                    content = content
                )
            }
        )
    }

}

object MusicTheme {
    val colors: MusicColorScheme
        @Composable
        get() = LocalExtendedColors.current
}

data class MusicColorScheme(
    val textColor: Color,
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val secondary: Color,
    val secondaryVariant: Color,
    val transparent: Color
)

fun lightColorMusicScheme(
    textColor: Color = Color.White,
    background: Color = DarkBlue,
    onBackground: Color = LightBlue,
    primary: Color = Green40,
    secondary: Color = GrayDark,
    secondaryVariant: Color = GrayLight,
    transparent: Color = Color.Transparent
): MusicColorScheme =
    MusicColorScheme(
        textColor = textColor,
        background = background,
        onBackground = onBackground,
        primary = primary,
        secondary = secondary,
        secondaryVariant = secondaryVariant,
        transparent = transparent
    )

//TODO POR EJEMPLO AHORITA MI NAV BAR TIENE UNOS COLORES PERO ESTA LINKEADO A UNO EN ESPECIFICO, QUE
// PASA SI LUEGO YO QUIERO CAMBIAR SOLO ESTE, DEBERIA HABER UNA CAPA APARTE DE PERSONALIZACION,
// DONDE ESTE TENGA POR DEFECTO, Y SI LE PONGO RECIEN SE SOBRE ESCRIBE

/*
* TODO EL LOCAL PROVIDER QUE DEBERIA COMO QUE CREARSE TODO UN MUNDO DE COMPONENTES ENTEROS Y QUE ESTOS DENTRO USEN UN PROVIDER CUSTON
*  PORQUE ASI LUEGO CUANDO USEN UN COMPONENTE ESTE INTERNAMENTE VA A LLAMAR A ESE PROVIDER Y OBTENDRA LOS COLORES,
*  LA CAPA DE SEPARACION ADICIONAL SERIA GENERAR PROVIDER PARA CADA COMPONENTE O PAQUETE DE COMPONENTES, YA QUE ASI ESTOS LLAMARAN DENTRO A UN VALOR,
*  ENTONCES EL TEMA DEBERIA TENER PUROS KEYS PARA QUE ASI YA LUEGO CADA UNO DE ESTOS PROVIDERS DEFINAN CUAL KEY CHAPA
* */