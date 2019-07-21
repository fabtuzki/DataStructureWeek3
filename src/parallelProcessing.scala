import scala.collection.mutable.ArrayBuffer

object parallelProcessing {
  //Things to create:
  //an array of tuple
  //siftup/siftdown, insert, extract max for binary heap min tree
  //a variable to remember what extracted
  //an array to remember what is extracted.

  def main(args: Array[String]): Unit = {
    val numThread = 3

    val arrayTask = Array(0, 0, 1, 1, 2, 2, 3, 3, 4)

    val output = parallelProcessing(numThread, arrayTask)


    output.foreach(x => println(x))


  }


  def parallelProcessing(numberOfThread: Int, arrayOfTask: Array[Int]): Array[ppUnit] = {
    /*Step to do :
    * Set up the beginning priority queue for number of thread
    * do extract max
    * save information for current time and current thread
    * insert new node with value time added & thread
    * loop through the array of time
    */
    val tree = Array.range(0, numberOfThread).map(x => ppUnit(0, x))
    val arrayOutput = new ArrayBuffer[ppUnit]
    var currentThread = 0
    var currentTime = 0
    val pqTuple = new priorityQueueTuple2(tree)
    for (i <- 0 until arrayOfTask.length) {

      val result = pqTuple.extractMax()
      println("extract max result : " + result)

      pqTuple.tree.foreach(x => println("print element inside tree after extract max : " + x))
      currentThread = result.thread
      currentTime = result.time
      arrayOutput.append(result)
      println("update the element : " + ppUnit(currentTime + arrayOfTask(i), currentThread))
      pqTuple.insertPQ(ppUnit(currentTime + arrayOfTask(i), currentThread))
      pqTuple.tree.foreach(x => println("print tree after insert : " + x))

    }

    arrayOutput.toArray
  }


}

