package com.example.moviesapp.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Composable function to create a shimmer effect for UI elements.
 * This effect is commonly used as a placeholder animation while loading content.
 *
 * @param modifier Modifier for customizing the size, shape, or layout of the shimmer effect.
 */
@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    // Creates a transition that runs infinitely for the shimmer animation
    val transition = rememberInfiniteTransition()

    // Animates a float value from 0f to 1f continuously, creating the shimmer motion
    val offset by transition.animateFloat(
        initialValue = 0f, // Starting value for the animation
        targetValue = 1f, // Ending value for the animation
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing) // Smooth animation with a linear easing curve
        )
    )

    // Canvas is used to draw the shimmer gradient effect
    Canvas(modifier = modifier) {
        drawRect(
            // Brush creates a linear gradient with two colors for the shimmer effect
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.Gray.copy(alpha = 0.3f), // Starting color with lower opacity
                    Color.Gray.copy(alpha = 0.7f)  // Ending color with higher opacity
                ),
                start = Offset(-size.width + size.width * offset, 0f), // Start position of the gradient
                end = Offset(size.width * offset, 0f) // End position of the gradient
            ),
            size = size // Size of the canvas
        )
    }
}