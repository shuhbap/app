package com.zolvex.studio.data.repository

import com.zolvex.studio.data.model.Layer
import com.zolvex.studio.data.model.LayerType
import com.zolvex.studio.data.model.ShapeKind
import com.zolvex.studio.data.model.Template

interface TemplateRepository {
    fun getTemplates(): List<Template>
    fun getCategories(): List<String>
    fun getTemplate(id: String): Template?
}

class LocalTemplateRepository : TemplateRepository {

    override fun getTemplates(): List<Template> = templates

    override fun getCategories(): List<String> = templates.map { it.category }.distinct()

    override fun getTemplate(id: String): Template? = templates.firstOrNull { it.id == id }

    private companion object {

        fun text(
            id: String, value: String, x: Float, y: Float, w: Float, h: Float,
            size: Float, color: String = "#FFFFFF", z: Int = 2, rotation: Float = 0f
        ) = Layer(
            id = id, type = LayerType.TEXT, x = x, y = y, width = w, height = h,
            text = value, fontSize = size, color = color, zIndex = z, rotation = rotation
        )

        fun shape(
            id: String, kind: ShapeKind, x: Float, y: Float, w: Float, h: Float,
            color: String, radius: Float = 0f, z: Int = 1, rotation: Float = 0f, opacity: Float = 1f
        ) = Layer(
            id = id, type = LayerType.SHAPE, x = x, y = y, width = w, height = h,
            color = color, shape = kind, cornerRadius = radius, zIndex = z,
            rotation = rotation, opacity = opacity
        )

        fun image(id: String, x: Float, y: Float, w: Float, h: Float, z: Int = 1) = Layer(
            id = id, type = LayerType.IMAGE, x = x, y = y, width = w, height = h,
            color = "#292929", zIndex = z
        )

        fun template(
            id: String, name: String, category: String, w: Int, h: Int,
            bg: String, premium: Boolean, vararg layers: Layer
        ) = Template(id, name, category, w, h, bg, premium, layers.toList())

        val templates: List<Template> = listOf(
            template(
                "template_001", "Premium Sale", "Marketing", 1080, 1350, "#101010", true,
                shape("l1", ShapeKind.CIRCLE, 560f, -120f, 700f, 700f, "#8B5CF6", opacity = 0.9f, z = 0),
                text("l2", "SALE", 80f, 420f, 920f, 300f, 220f),
                text("l3", "UP TO 70% OFF", 80f, 740f, 920f, 100f, 64f, "#B794F6"),
                shape("l4", ShapeKind.ROUNDED_RECTANGLE, 340f, 980f, 400f, 120f, "#FFFFFF", 60f),
                text("l5", "SHOP NOW", 340f, 1010f, 400f, 70f, 44f, "#101010", 3)
            ),
            template(
                "template_002", "Eid Offer", "Festival", 1080, 1350, "#0B2A1F", false,
                shape("l1", ShapeKind.CIRCLE, 340f, 120f, 400f, 400f, "#F5D76E", z = 0),
                shape("l2", ShapeKind.CIRCLE, 410f, 120f, 400f, 400f, "#0B2A1F", z = 1),
                text("l3", "EID OFFER", 100f, 640f, 880f, 150f, 110f, "#F5D76E", 3),
                text("l4", "Celebrate with 30% off", 100f, 840f, 880f, 80f, 48f),
                text("l5", "Eid Mubarak", 100f, 1180f, 880f, 80f, 44f, "#F5D76E")
            ),
            template(
                "template_003", "Restaurant Promo", "Food", 1080, 1080, "#1A0F0A", false,
                image("l1", 60f, 60f, 960f, 560f),
                text("l2", "TASTE THE DIFFERENCE", 80f, 680f, 920f, 200f, 90f),
                text("l3", "Fresh. Hot. Delivered.", 80f, 900f, 920f, 70f, 44f, "#FF9F43"),
                shape("l4", ShapeKind.ROUNDED_RECTANGLE, 80f, 970f, 360f, 70f, "#FF9F43", 35f)
            ),
            template(
                "template_004", "Instagram Quote", "Social Media", 1080, 1080, "#14101F", false,
                shape("l1", ShapeKind.RECTANGLE, 90f, 90f, 12f, 900f, "#8B5CF6", z = 0),
                text("l2", "Make it simple, but significant.", 140f, 300f, 820f, 400f, 78f),
                text("l3", "- ZOLVEX", 140f, 900f, 820f, 60f, 36f, "#B794F6")
            ),
            template(
                "template_005", "Fashion Sale", "Marketing", 1080, 1350, "#F2EDE4", true,
                image("l1", 540f, 0f, 540f, 1350f),
                text("l2", "NEW SEASON", 60f, 220f, 460f, 200f, 70f, "#111111"),
                text("l3", "FASHION SALE", 60f, 460f, 460f, 260f, 92f, "#8B5CF6"),
                text("l4", "Flat 50% off", 60f, 1100f, 460f, 70f, 44f, "#111111")
            ),
            template(
                "template_006", "Business Advertisement", "Business", 1080, 1080, "#0D1B2A", false,
                shape("l1", ShapeKind.RECTANGLE, 0f, 0f, 1080f, 14f, "#8B5CF6", z = 0),
                text("l2", "GROW YOUR BUSINESS", 80f, 260f, 920f, 300f, 100f),
                text("l3", "Smart solutions for modern brands", 80f, 620f, 920f, 100f, 46f, "#A0A0A0"),
                shape("l4", ShapeKind.ROUNDED_RECTANGLE, 80f, 840f, 420f, 100f, "#8B5CF6", 20f),
                text("l5", "Contact Us", 80f, 862f, 420f, 60f, 40f, "#FFFFFF", 3)
            ),
            template(
                "template_007", "YouTube Thumbnail", "Social Media", 1280, 720, "#120A1F", false,
                image("l1", 700f, 60f, 520f, 600f),
                text("l2", "WATCH THIS", 50f, 150f, 620f, 160f, 110f, "#FFE066"),
                text("l3", "BEFORE YOU BUY", 50f, 340f, 620f, 120f, 70f),
                shape("l4", ShapeKind.ROUNDED_RECTANGLE, 50f, 520f, 300f, 80f, "#EF4444", 16f)
            ),
            template(
                "template_008", "Birthday", "Events", 1080, 1350, "#2B1055", false,
                shape("l1", ShapeKind.CIRCLE, 120f, 160f, 160f, 160f, "#FF6FB5", z = 0),
                shape("l2", ShapeKind.CIRCLE, 820f, 260f, 120f, 120f, "#FFE066", z = 0),
                text("l3", "HAPPY", 80f, 460f, 920f, 200f, 150f),
                text("l4", "BIRTHDAY", 80f, 680f, 920f, 200f, 150f, "#FF6FB5"),
                text("l5", "Make a wish!", 80f, 1000f, 920f, 80f, 52f, "#FFE066")
            ),
            template(
                "template_009", "Wedding", "Events", 1080, 1920, "#F7F1E8", true,
                shape("l1", ShapeKind.ROUNDED_RECTANGLE, 70f, 70f, 940f, 1780f, "#F7F1E8", 40f, z = 0),
                text("l2", "WE ARE GETTING MARRIED", 140f, 520f, 800f, 200f, 56f, "#8A6D3B"),
                text("l3", "Aisha & Rahul", 140f, 800f, 800f, 220f, 120f, "#3B2F1E"),
                text("l4", "12 December 2026", 140f, 1160f, 800f, 80f, 48f, "#8A6D3B")
            ),
            template(
                "template_010", "Ramadan", "Festival", 1080, 1920, "#0A0F2C", false,
                shape("l1", ShapeKind.CIRCLE, 280f, 220f, 520f, 520f, "#F5D76E", z = 0),
                shape("l2", ShapeKind.CIRCLE, 380f, 220f, 520f, 520f, "#0A0F2C", z = 1),
                text("l3", "RAMADAN KAREEM", 90f, 900f, 900f, 260f, 110f, "#F5D76E", 3),
                text("l4", "Wishing you peace and blessings", 90f, 1240f, 900f, 120f, 46f)
            )
        )
    }
}
