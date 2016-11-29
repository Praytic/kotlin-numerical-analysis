package com.vchernogorov.differential_equations

import com.vchernogorov.Task

class Task9 (val a: Double, val b: Double, val iterations: Int, val V: Int) : Task<DoubleArray>(DoubleArray(iterations)) {

    val xs = DoubleArray(iterations)
    val expected = DoubleArray(iterations)
    val diff = DoubleArray(iterations)

    override fun run() {
        xs[0] = a
        xs.indices.forEach { i -> xs[i] = if (i == 0) a else xs[i - 1] + (b - a) / (iterations - 1) }
        expected.indices.forEach { i -> expected[i] = getYPrecise(xs[i]) }
        result[0] = V + 2.0
        for (i in 1..result.size - 1) {
            val h = xs[i] - xs[i - 1]
            result[i] = getYEuler(xs[i - 1], result[i - 1], h)
            result[i] = getYModifiedEuler(xs[i - 1], xs[i], result[i - 1], h)
            result[i] = getPredictCorrector(xs[i - 1], xs[i], result[i - 1], h)
        }
        result.indices.forEach { i -> diff[i] = Math.abs(result[i] - expected[i]) }
    }

    fun getYPrecise(x: Double): Double {
        return V * Math.pow(x, 3.0) + Math.pow(x, 2.0) + x
    }

    fun getYEuler(x: Double, y: Double, h: Double): Double {
        return y + h * getF(x, y)
    }

    fun getYModifiedEuler(x1: Double, x2: Double, y: Double, h: Double): Double {
        val x = (x1 + x2) / 2
        val yNew = y + h / 2 * getF(x1, y)
        return y + h * getF(x, yNew)
    }

    fun getPredictCorrector(x1: Double, x2: Double, y1: Double, h: Double): Double {
        val y2 = getYModifiedEuler(x1, x2, y1, h)
        return y1 + h * ((getF(x1, y1) + getF(x2, y2)) / 2)
    }

    fun getF(x: Double, y: Double): Double {
        return V * Math.pow(x, 3.0) + (3 * V + 1) * Math.pow(x, 2.0) + 3 * x + 1.0 - y
    }
}
