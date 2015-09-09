name := {
  println("I love my name!")
  "parallel"
}

scalaVersion := "2.11.7"

version := "1.0.0"

// TASK EXECUTION:
val taskA = taskKey[String]("taskA, consists of tasks B and C")
val taskB = taskKey[String]("taskB, takes 2 seconds")
val taskC = taskKey[String]("taskC, takes 2 seconds")

taskA := {
  val b = taskB.value
  val c = taskC.value
  "Task A completed with " + b + " and " + c
}

taskB := {
  Thread.sleep(2000)
  "Task B completed"
}

taskC := {
//  taskB.value
  Thread.sleep(2000)
  "Task C completed"
}






// SETTINGS DEPENDENT ON CONFIGURATION
val dependentSettings = settingKey[String]("A setting key being different in compile and test")

dependentSettings := "global"

dependentSettings in Test := "only in test"

dependentSettings in Compile := "only in compile"

val printSettings = taskKey[Unit]("Determines the current git commit SHA")

printSettings := {
    println("SETTING: " + dependentSettings.value)
}

printSettings in Test := {
  println("SETTING: " + (dependentSettings in Test).value)
}

//printSettings in Compile := {
//  println("SETTING: " + (dependentSettings in Compile).value)
//}







// VERY SIMPLE PROCESS
val gitHeadCommitSha = taskKey[String]("Determines the current git commit SHA")

gitHeadCommitSha := Process("git rev-parse HEAD").lines.head
//same as
//gitHeadCommitSha := {
//  "git rev-parse HEAD" !!
//}

val combinedProcess = taskKey[Int]("Process combined from two")

combinedProcess := {
  ("cd process" #&& "ls -la") !
}