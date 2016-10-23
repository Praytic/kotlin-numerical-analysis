package com.vchernogorov

class Task3 (a: Int, b: Int, e: Double, V: Int, result: MutableMap<Double, Double> = mutableMapOf(),
             var task2: Task2 = { val task = Task2(a, b, e, V); task.run(); task }.invoke()) :
        Task<MutableMap<Double, Double>>(a, b, e, V, result) {
    constructor() : this(0, 0, 0.0, 0)

    override fun run() {
        for ((x, y) in task2.result) {
            result.put(x, f(x))
        }
    }

    fun f(x: Double): Double {
        var result = 0.0
        for ((xk, yk) in task2.result) {
            var yk2 = yk
            for ((xi, yi) in task2.result) {
                if (xk != xi) {
                    yk2 *= (x - xi) / (xk - xi)
                }
            }
            result += yk2
        }
        return result
    }
}
