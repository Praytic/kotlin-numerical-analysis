package com.vchernogorov.interpolation

class Task1(a: Int, b: Int, e: Double, V: Int, result: MutableMap<Double, Double> = mutableMapOf()) :
        Task<MutableMap<Double, Double>>(a, b, e, V, result) {
    constructor() : this(0, 0, 0.0, 0)

    override fun run() {
        for (i in a..b step 1) {
            var count = 1
            var res = 1.0
            while (Math.abs(f(res, count * 2, i)) > e) {
                res += f(res, count * 2, i)
                count++
            }
            result.put(i.toDouble(), res);
        }
    }

    fun f(previous: Double, count: Int, x: Int): Double {
        return previous * -1.0 * V * V * x * x / ((count - 1) * count)
    }
}
