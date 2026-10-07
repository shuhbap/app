package com.zolvex.studio.data.model

enum class LayerType { TEXT, SHAPE, IMAGE }

enum class ShapeKind { RECTANGLE, ROUNDED_RECTANGLE, CIRCLE }

/** One editable object on a canvas. Coordinates are in canvas units, not screen pixels. */
data class Layer(
    val id: String,
    val type: LayerType,
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val text: String = "",
    val fontSize: Float = 48f,
    val fontFamily: String = "Poppins-Bold",
    val color: String = "#FFFFFF",
    val shape: ShapeKind = ShapeKind.RECTANGLE,
    val cornerRadius: Float = 0f,
    val rotation: Float = 0f,
    val opacity: Float = 1f,
    val visible: Boolean = true,
    val locked: Boolean = false,
    val zIndex: Int = 0
)

data class Template(
    val id: String,
    val name: String,
    val category: String,
    val width: Int,
    val height: Int,
    val background: String,
    val isPremium: Boolean,
    val layers: List<Layer>
)
