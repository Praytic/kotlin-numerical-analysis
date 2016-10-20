fun main(args: Array<String>){
    val task1 = Task1(1, 10, 1e-6, 6)
    task1.run()
    val task2 = Task2(task1.result)
    task2.run()
    val task3 = Task3(task1.result, task2.result)
    task3.run()

    for ((x, y) in task3.result) {
        println("x = $x ; Ln(x) = $y")
    }
}