name := "parallel"

version := "1.0"

scalaVersion := "2.11.7"

val taskA = taskKey[String]("taskA, consists of tasks B and C")
val taskB = taskKey[String]("taskB, takes 5 seconds")
val taskC = taskKey[String]("taskC, takes 5 seconds")

taskA := {
  val b = taskB.value
  val c = taskC.value
  "task A completed"
}

taskB := {
  Thread.sleep(5000)
  "task B completed"
}

taskC := {
  Thread.sleep(5000)
  "task C completed"
}