package pe.codex.musicspot.ui.components

import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.codex.musicspot.ui.bottomNavItems
import pe.codex.musicspot.ui.theme.DarkBlue
import pe.codex.musicspot.ui.theme.GrayDark
import pe.codex.musicspot.ui.theme.Green40
import pe.codex.musicspot.ui.theme.MusicSpotTheme
import pe.codex.musicspot.ui.theme.MusicTheme

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, onClickNavigation: (String) -> Unit) {
    var selectedItem by remember {
        mutableStateOf(0)
    }

    Surface(
        color = MusicTheme.colors.background,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .selectableGroup()
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
        ) {
            bottomNavItems.forEachIndexed { index, navItem ->
                BottomNavItem(
                    modifier = Modifier.fillMaxSize(),
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        onClickNavigation(navItem.title.name)
                    },
                    icon = {
                        MusicIcon(
                            painter = painterResource(id = navItem.icon),
                            contentDescription = navItem.title.name,
                            modifier = it
                        )
                    },
                    colors = BottomItemDefaults.colors(
                        selectedIconColor = MusicTheme.colors.primary,
                        unselectedIconColor = MusicTheme.colors.secondary,
                        indicatorColor = MusicTheme.colors.background
                    ),
                    generateShadow = true
                )
            }
        }
    }

}

@Composable
fun MusicIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier,
    tint: Color = LocalNavBarContentColor.current
) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

/**
 * To use correctly generation of shadow user modifier lamda parameter
 */
@Composable
fun RowScope.BottomNavItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    colors: BottomItemColors = BottomItemDefaults.colors(),
    generateShadow: Boolean = false,
    icon: @Composable (Modifier) -> Unit
) {

    val styledIcon = @Composable {
        val iconColor by colors.iconColor(selected = selected)
        Box(modifier = Modifier, contentAlignment = Alignment.Center) {
            CompositionLocalProvider(LocalNavBarContentColor provides iconColor) {
                icon(
                    Modifier.size(if (selected) 24.dp else 22.dp)
                )
                if (generateShadow && selected && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    icon(
                        Modifier
                            .size(28.dp)
                            .blur(12.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    )

                }
            }
        }
    }

    Box(
        modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = true,
                role = Role.Tab,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            )
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        styledIcon()
        if (selected)
            Column(Modifier.fillMaxHeight()) {
                Spacer(
                    modifier = Modifier
                        .width(28.dp)
                        .height(4.dp)
                        .clip(
                            RoundedCornerShape(bottomStart = 3.dp, bottomEnd = 3.dp)
                        )
                        .background(MusicTheme.colors.primary)
                )
            }
    }

}

val LocalNavBarContentColor = compositionLocalOf { Color.White }

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    MusicSpotTheme() {
        BottomNavigationBar() {}
    }
}

object BottomItemDefaults {
    @Composable
    fun colors(
        selectedIconColor: Color = Green40,
        indicatorColor: Color = DarkBlue,
        unselectedIconColor: Color = GrayDark,
    ): BottomItemColors = BottomItemColors(
        selectedIconColor = selectedIconColor,
        selectedIndicatorColor = indicatorColor,
        unselectedIconColor = unselectedIconColor,
    )
}

@Stable
class BottomItemColors internal constructor(
    private val selectedIconColor: Color,
    private val unselectedIconColor: Color,
    private val selectedIndicatorColor: Color
) {
    @Composable
    fun iconColor(selected: Boolean): State<Color> {
        return animateColorAsState(
            targetValue = if (selected) selectedIconColor else unselectedIconColor,
            animationSpec = tween(100)
        )
    }
}