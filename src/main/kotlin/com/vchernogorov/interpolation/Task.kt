package com.vchernogorov.interpolation

abstract class Task<T>(val a: Int, val b: Int, val e: Double, val V: Int, result: T) : com.vchernogorov.Task<T>(result) {
    constructor(task: Task<T>) : this(task.a, task.b, task.e, task.V, task.result)
}