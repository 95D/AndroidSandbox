package com.example.gear.ui.shape

import android.graphics.PointF
import androidx.annotation.FloatRange
import androidx.core.graphics.times
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import kotlin.math.cos
import kotlin.math.sin

fun getGearShape(
    numTeeth: Int,
    @FloatRange(from = 0.001) teethRadius: Float,
    @FloatRange(from = 0.001) wheelRadius: Float,
    centerX: Float,
    centerY: Float,
    toothRounding: CornerRounding = CornerRounding.Unrounded
): RoundedPolygon = RoundedPolygon(
    vertices = getGearVertices(
        numVertices = numTeeth,
        wheelRadius = wheelRadius,
        teethRadius = teethRadius,
        centerX = centerX,
        centerY = centerY
    ),
    perVertexRounding = cornerRoundings(
        toothRounding = toothRounding,
        numVertices = numTeeth
    )
)

private fun cornerRoundings(
    toothRounding: CornerRounding,
    numVertices: Int
): List<CornerRounding> =
    listOf(
        CornerRounding.Unrounded,
        toothRounding,
        toothRounding,
        CornerRounding.Unrounded,
    ) * numVertices

private operator fun List<CornerRounding>.times(another: Int): List<CornerRounding> {
    var result = emptyList<CornerRounding>()
    for (i in 0 until another) {
        result = result + this
    }
    return result
}

private fun getGearVertices(
    numVertices: Int,
    teethRadius: Float,
    wheelRadius: Float,
    centerX: Float,
    centerY: Float
): FloatArray {
    val result = FloatArray(numVertices * 8)
    var arrayIndex = 0
    for (i in 0 until numVertices) {
        val radiusList = listOf(
            wheelRadius,
            teethRadius,
            teethRadius,
            wheelRadius
        )
        for (j in 0 until 4) {
            val pointIndex = (i * 4 + j).toFloat()
            val vertex = radialToCartesian(
                radiusList[j],
                (Math.PI.toFloat() * 2) / (numVertices * 4) * pointIndex
            ) + PointF(centerX, centerY)
            result[arrayIndex++] = vertex.x
            result[arrayIndex++] = vertex.y
        }
    }
    return result
}

internal fun radialToCartesian(
    radius: Float,
    angleRadians: Float,
    center: PointF = PointF(0f, 0f)
): PointF = directionVector(angleRadians) * radius + center

internal fun directionVector(angleRadians: Float): PointF =
    PointF(cos(angleRadians), sin(angleRadians))

internal operator fun PointF.plus(another: PointF): PointF =
    PointF(x + another.x, y + another.y)

internal operator fun PointF.times(another: Float): PointF =
    PointF(x * another, y * another)
