package com.vchernogorov.matrix

class DoubleMatrix(val rows: Array<DoubleArray>) {
    constructor(): this(arrayOf())
    constructor(size: Int): this(Array<DoubleArray>(size, { DoubleArray(size) }))
    constructor(matrix: DoubleMatrix): this(matrix.rows)

    operator fun get(index: Int): DoubleArray {
        return rows[index]
    }

    operator fun set(indexLine: Int, value: DoubleArray) {
        rows[indexLine] = value
    }

    fun swapRows(vector: DoubleArray, rowIndex1: Int, rowIndex2: Int): DoubleMatrix {
        var currentIndex = rowIndex2
        val tempLine = this[rowIndex1]
        val tempVector = vector[rowIndex1]

        var element = this[rowIndex1][rowIndex1]
        var swapIndex = currentIndex
        while (currentIndex < vector.size) {
            if (element < Math.abs(this[rowIndex1][currentIndex])) {
                element = Math.abs(this[rowIndex1][currentIndex])
                swapIndex = currentIndex
            }
            currentIndex++
        }

        this[rowIndex1] = this[swapIndex]
        vector[rowIndex1] = vector[swapIndex]

        this[swapIndex] = tempLine
        vector[swapIndex] = tempVector
        return this
    }
}