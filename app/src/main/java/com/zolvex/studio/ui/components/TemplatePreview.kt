package com.zolvex.studio.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.zolvex.studio.data.model.LayerType
import com.zolvex.studio.data.model.ShapeKind
import com.zolvex.studio.data.model.Template
import com.zolvex.studio.utils.parseHexColor

/** Renders a template's editable layers scaled to fit. Canvas units -> screen pixels via one scale factor. */
@Composable
fun TemplatePreview(template: Template, modifier: Modifier = Modifier) {
    val measurer = rememberTextMeasurer()
    Canvas(
        modifier = modifier
            .aspectRatio(template.width.toFloat() / template.height.toFloat())
            .clip(RoundedCornerShape(8.dp))
    ) {
        val scale = size.width / template.width
        drawRect(color = parseHexColor(template.background))

        template.layers
            .filter { it.visible }
            .sortedBy { it.zIndex }
            .forEach { layer ->
                val topLeft = Offset(layer.x * scale, layer.y * scale)
                val layerSize = Size(layer.width * scale, layer.height * scale)
                val pivot = Offset(
                    topLeft.x + layerSize.width / 2f,
                    topLeft.y + layerSize.height / 2f
                )
                val color = parseHexColor(layer.color).copy(alpha = layer.opacity)

                rotate(degrees = layer.rotation, pivot = pivot) {
                    when (layer.type) {
                        LayerType.TEXT -> drawText(
                            textMeasurer = measurer,
                            text = layer.text,
                            topLeft = topLeft,
                            style = TextStyle(
                                color = color,
                                fontSize = (layer.fontSize * scale).toSp(),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            overflow = TextOverflow.Clip,
                            softWrap = true,
                            size = layerSize
                        )

                        LayerType.IMAGE -> drawRoundRect(
                            color = color,
                            topLeft = topLeft,
                            size = layerSize,
                            cornerRadius = CornerRadius(12f * scale)
                        )

                        LayerType.SHAPE -> when (layer.shape) {
                            ShapeKind.RECTANGLE -> drawRect(color, topLeft, layerSize)
                            ShapeKind.ROUNDED_RECTANGLE -> drawRoundRect(
                                color = color,
                                topLeft = topLeft,
                                size = layerSize,
                                cornerRadius = CornerRadius(layer.cornerRadius * scale)
                            )
                            ShapeKind.CIRCLE -> drawOval(color, topLeft, layerSize)
                        }
                    }
                }
            }
    }
}
