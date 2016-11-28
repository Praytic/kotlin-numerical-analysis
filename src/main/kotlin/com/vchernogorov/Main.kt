package com.vchernogorov

import com.vchernogorov.interpolation.Task3

fun main(args: Array<String>){
    val task3 = Task3(1, 10, 1e-6, 6)
    task3.run()

    for ((x, y) in task3.result) {
        println("x = $x ; f(x) = $y")
    }
}