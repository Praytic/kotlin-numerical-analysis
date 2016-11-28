package com.vchernogorov.interpolation

class Task2(a: Int, b: Int, e: Double, V: Int, result: MutableMap<Double, Double> = mutableMapOf(),
            var task1: Task1 = { val task = Task1(a, b, e, V); task.run(); task }.invoke()) :
        Task<MutableMap<Double, Double>>(a, b, e, V, result) {
    constructor() : this(0, 0, 0.0, 0)

    override fun run() {
        val iterator = task1.result.entries.iterator()
        var firstElement = iterator.next()
        while (iterator.hasNext()) {
            val secondElement = iterator.next()
            val (x0, y0) = firstElement
            val (x1, y1) = secondElement
            val x = x0 + (x1 - x0) / 2
            val y = f(x, x0, y0, x1, y1)
            result.put(x, y)
            firstElement = secondElement
        }
    }

    fun f(x: Double, x0: Double, y0: Double, x1: Double, y1: Double): Double {
        val y = y0 + ((y1 - y0) / (x1 - x0)) * (x - x0)
        return y
    }
}
