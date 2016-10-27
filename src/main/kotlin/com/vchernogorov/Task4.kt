package com.vchernogorov

class Task4 (val x: Double, a: Int, b: Int, e: Double, V: Int, result: Double = 0.0,
             var task2: Task2 = { val task = Task2(a, b, e, V); task.run(); task }.invoke()) :
        Task<Double>(a, b, e, V, result) {
    constructor(x: Double) : this(x, 0, 0, 0.0, 0)

    val fs = mutableMapOf<Double, Double>()
    val fi = arrayOf<DoubleArray>()
    val xs = task2.result.values.toDoubleArray()
    val n = xs.size

    override fun run() {
        result = newton(x);
    }

    fun f() {
        for (i in 0..n) {
            for (j in 0..n) {
                if (j == 0) {
                    
                }
            }
        }
    }

    fun newton(x: Double): Double {
        var multiplier = 1.0
        for (i in 0..n) {
            result += fi[0][i]
            multiplier *= (x - xs[i])
        }
        return result
    }
}
