package com.vchernogorov

class Task1 (val a: Int, val b: Int, val e: Double, val V: Int,
             val result: MutableMap<Double, Double> = mutableMapOf()) : Runnable {

    override fun run() {
        for (i in a..b step 1) {
            var count = 1
            var res = 1.0
            while (Math.abs(f(res, count * 2, i)) > e) {
                res += f(res, count * 2, i)
                count++
            }
//            println("f($i) = $res: $count")
            result.put(i.toDouble(), res);
        }
    }

    fun f(previous: Double, count: Int, x: Int): Double {
        return previous * -1.0 * V * V * x * x / ((count - 1) * count)
    }
}
