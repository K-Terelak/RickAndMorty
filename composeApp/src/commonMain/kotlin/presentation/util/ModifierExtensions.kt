package presentation.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.shimmerLoadingAnimation(
    isLoading: Boolean = true,
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
    color: Color? = null,
): Modifier {
    if (!isLoading) {
        return this
    } else {
        return composed {
            val shimmerColor = color ?: Color.LightGray

            val shimmerColors =
                listOf(
                    shimmerColor.copy(alpha = 0.3f),
                    shimmerColor.copy(alpha = 0.5f),
                    shimmerColor.copy(alpha = 1.0f),
                    shimmerColor.copy(alpha = 0.5f),
                    shimmerColor.copy(alpha = 0.3f),
                )
            val transition = rememberInfiniteTransition(label = "shimmer transition")

            val translateAnimation =
                transition.animateFloat(
                    initialValue = 0f,
                    targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
                    animationSpec =
                        infiniteRepeatable(
                            animation =
                                tween(
                                    durationMillis = durationMillis,
                                    easing = LinearEasing,
                                ),
                            repeatMode = RepeatMode.Restart,
                        ),
                    label = "Shimmer loading animation",
                )
            this.background(
                brush =
                    Brush.linearGradient(
                        colors = shimmerColors,
                        start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                        end = Offset(x = translateAnimation.value, y = angleOfAxisY),
                    ),
            )
        }
    }
}
