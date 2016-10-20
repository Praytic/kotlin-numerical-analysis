class Task2 (val inputData: MutableMap<Double, Double> = mutableMapOf(),
             val result: MutableMap<Double, Double> = sortedMapOf()) : Runnable {

    override fun run() {
        val iterator = inputData.entries.iterator()
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
