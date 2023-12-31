package pe.codex.musicspot.ui.components

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

fun Modifier.vectorShadow(
    path: Path,
    x: Dp,
    y: Dp,
    radius: Dp
) = composed(
    inspectorInfo = {
        name = "vectorShadow"
        value = path
        value = x
        value = y
        value = radius
    },
    factory = {

        val paint = remember {
            Paint()
        }

        val frameworkPaint = remember {
            paint.asFrameworkPaint()
        }

        val color = Color.DarkGray
        val dx: Float
        val dy: Float
        val radiusInPx: Float

        with(LocalDensity.current) {
            dx = x.toPx()
            dy = y.toPx()
            radiusInPx = radius.toPx()
        }


        drawBehind {

            this.drawIntoCanvas {

                val transparent = color
                    .copy(alpha = 0f)
                    .toArgb()

                frameworkPaint.color = transparent

                frameworkPaint.setShadowLayer(
                    radiusInPx,
                    dx,
                    dy,
                    color
                        .copy(alpha = .7f)
                        .toArgb()
                )

                it.drawPath(path, paint)


            }
        }
    }
)


fun Modifier.badgeLayout() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // based on the expectation of only one line of text
        val minPadding = placeable.height / 4

        val width = maxOf(placeable.width + minPadding, placeable.height)
        layout(width, placeable.height) {
            placeable.place((width - placeable.width) / 2, 0)
        }
    }