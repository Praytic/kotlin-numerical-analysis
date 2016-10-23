package com.vchernogorov

abstract class Task<T>(val a: Int, val b: Int, val e: Double, val V: Int,
                    var result: T) : Runnable {
    constructor(task: Task<T>) : this(task.a, task.b, task.e, task.V, task.result)
}