import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object parallelProcessing {
  //Things to create:
  //an array of tuple
  //siftup/siftdown, insert, extract max for binary heap min tree
  //a variable to remember what extracted
  //an array to remember what is extracted.

  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("C:\\Users\\Jade Phung\\Documents\\homework2\\week2_priority_queues_and_disjoint_sets\\2_job_queue\\tests\\08")
      .getLines().toArray

    val arrayTask = source(1).split(" ").map(_.toLong)

    val numThread = source(0).split(" ")(0).toInt

    val output = parallelProcessing(numThread, arrayTask)

    val outputReal = Source.fromFile("C:\\Users\\Jade Phung\\Documents\\homework2\\week2_priority_queues_and_disjoint_sets\\2_job_queue\\tests\\08.a")
      .getLines().toArray

    for (k <- 0 until output.length) {
      println("output is : " + output(k).thread + " " + output(k).time)
      if (outputReal(k) == output(k).thread + " " + output(k).time) {
        println("true")
      } else {
        println("false")
      }
    }


  }


  def parallelProcessing(numberOfThread: Int, arrayOfTask: Array[Long]): Array[ppUnit] = {
    /*Step to do :
    * Set up the beginning priority queue for number of thread
    * do extract max
    * save information for current time and current thread
    * insert new node with value time added & thread
    * loop through the array of time
    */
    val tree = arrayOfTask.slice(0, numberOfThread).zipWithIndex.map(x => ppUnit(x._1, x._2))
    val arrayOutput = new ArrayBuffer[ppUnit]
    for (j <- 0 until numberOfThread) {

      arrayOutput.append(ppUnit(0, j))
    }
    var currentThread = 0
    var currentTime = 0L
    val pqTuple = new priorityQueueTuple2(tree)
    pqTuple.buildHeap(numberOfThread)
    for (i <- numberOfThread until arrayOfTask.length) {

      val result = pqTuple.extractMax()
      //      println("extract max result : " + result)

      //      pqTuple.tree.foreach(x => println("print element inside tree after extract max : " + x))
      currentThread = result.thread
      currentTime = result.time
      arrayOutput.append(result)
      //      println("update the element : " + ppUnit(currentTime + arrayOfTask(i), currentThread))
      pqTuple.insertPQ(ppUnit(currentTime + arrayOfTask(i), currentThread))
      //      pqTuple.tree.foreach(x => println("print tree after insert : " + x))

    }

    arrayOutput.toArray
  }


}

