import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks
import java.util._

case class ppUnit(time: Double, thread: Int)

object parallelProcessing {
  //Things to create:
  //an array of tuple
  //siftup/siftdown, insert, extract max for binary heap min tree
  //a variable to remember what extracted
  //an array to remember what is extracted.

  def main(args: Array[String]): Unit = {
    //    val time1 = System.currentTimeMillis()
    val input = scala.io.Source.stdin.getLines().toArray
    //    val input = Array("4", "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1")
    val arrayTask = input(1).split(" ").map(_.toDouble)
    val numThread = input(0).split(" ")(0).toInt
    //    val output = parallelProcessing(numThread, arrayTask)
    //    output.foreach(x => println(x.thread + " " + scala.math.round(x.time)))
    /*
        val time2 = System.currentTimeMillis()
        println(time2 - time1)
    */
    val javaOutput = javaPQParallelProcessing(numThread, arrayTask)
    javaOutput.foreach(x => println(x.thread + " " + scala.math.round(x.time)))


  }

  def javaPQParallelProcessing(numberOfThread: Int, arrayOfTask: Array[Double]): Array[ppUnit] = {
    if (numberOfThread >= arrayOfTask.length && !arrayOfTask.contains(0D)) {
      val specialOutput = Array.fill(arrayOfTask.length)(0).zipWithIndex.map(x => ppUnit(x._1, x._2))
      specialOutput
    } else {
      val javaPQ = new PriorityQueue[ppUnit](arrayOfTask.length, new NodeComparator)
      val arrayOutput = new ArrayBuffer[ppUnit]
      for (i <- 0 until numberOfThread) {
        javaPQ.add(ppUnit(0, i))
      }
      var currentThread = 0
      var currentTime = 0D
      for (i <- 0 until arrayOfTask.length) {
        if (arrayOfTask(i) > 0) {
          val result = javaPQ.poll()
          currentThread = result.thread
          currentTime = result.time
          arrayOutput.append(result)
          javaPQ.add(ppUnit(currentTime + arrayOfTask(i), currentThread))
        } else {
          arrayOutput.append(javaPQ.peek())
        }

      }

      arrayOutput.toArray
    }
  }


  class NodeComparator extends Comparator[ppUnit] {

    // Overriding compare()method of Comparator
    // for descending order of cgpa
    override def compare(node1: ppUnit, node2: ppUnit): Int = {

      if (node1.time < node2.time) {
        -1
      } else if (node1.time == node2.time && node1.thread < node2.thread) {
        -1
      } else {
        1
      }

      /*
            if (s1.cgpa < s2.cgpa)
              return 1
            else if (s1.cgpa > s2.cgpa)
              return -1
            return 0
      */
    }
  }


  def parallelProcessing(numberOfThread: Int, arrayOfTask: Array[Double]): Array[ppUnit] = {
    /*Step to do :
    * Set up the beginning priority queue for number of thread
    * do extract max
    * save information for current time and current thread
    * insert new node with value time added & thread
    * loop through the array of time
    */
    val tree = Array.fill(numberOfThread)(0).zipWithIndex.map(x => ppUnit(x._1, x._2))
    //          tree.foreach(x => println("tree " + x))
    val arrayOutput = new ArrayBuffer[ppUnit]
    var currentThread = 0
    var currentTime = 0D
    val pqTuple = new priorityQueueTupleThis(tree)
    pqTuple.buildHeap(numberOfThread)
    for (i <- 0 until arrayOfTask.length) {
      if (arrayOfTask(i) > 0) {
        val result = pqTuple.extractMax()
        //      println("extract max result : " + result)
        //      pqTuple.tree.foreach(x => println("print element inside tree after extract max : " + x))
        currentThread = result.thread
        currentTime = result.time
        arrayOutput.append(result)
        //      println("update the element : " + ppUnit(currentTime + arrayOfTask(i), currentThread))

        pqTuple.insertPQ(ppUnit(currentTime + arrayOfTask(i), currentThread))
      } else {
        arrayOutput.append(tree(0))
      }
      //      pqTuple.tree.foreach(x => println("print tree after insert : " + x))

    }
    arrayOutput.toArray


  }


  /*  def checkTaskZero(arr: Array[Double], numberOfThread: Int): (Int, Int) = {
      var checkFirstTask = 0
      var checkAllTask = 0
      val loopbreak = new Breaks
      loopbreak.breakable {
        for (i <- 0 until arr.length) {
          if (arr(i) != 0D) {
            checkAllTask += 1
            loopbreak.break()
          }
        }
      }
      loopbreak.breakable {
        for (i <- 0 until Array(numberOfThread, arr.length).min) {
          if (arr(i) != 0D) {
            checkFirstTask += 1
            loopbreak.break()
          }
        }

      }
      (checkFirstTask, checkAllTask)
    }*/


}


class priorityQueueTupleThis(var tree: Array[ppUnit]) {

  def buildHeap(size: Int): Unit = {
    for (i <- (0 to math.floor(size / 2).toInt).reverse) {
      siftDown(i)
    }
  }


  def extractMax(): ppUnit = {
    val result = tree(0)
    tree.update(0, tree(tree.length - 1))
    tree = tree.dropRight(1)
    siftDown(0)
    result
  }

  def insertPQ(node: ppUnit): Unit = {
    tree = tree :+ node
    siftUp(tree.length - 1)
  }

  def swap(a: Int, b: Int, arr: Array[ppUnit]): Unit = {
    val swap1 = arr(a)
    val swap2 = arr(b)
    arr.update(a, swap2)
    arr.update(b, swap1)
  }

  def siftUp(position: Int): Unit = {
    var updatedPosition = position
    while (updatedPosition > 0 && greaterPriority(tree(updatedPosition), tree(math.floor((updatedPosition - 1) / 2).toInt))) {
      swap(updatedPosition, math.floor((updatedPosition - 1) / 2).toInt, tree)
      updatedPosition = math.floor((updatedPosition - 1) / 2).toInt
    }
  }

  def siftDown(position: Int): Unit = {
    var minIndex = position
    //priority của cha > con thì false => ngược lại
    if (position * 2 + 1 <= (tree.length - 1) && !greaterPriority(tree(position), tree(position * 2 + 1))) {
      minIndex = position * 2 + 1
    }
    if (position * 2 + 2 <= (tree.length - 1) && !greaterPriority(tree(minIndex), tree(position * 2 + 2))) {
      minIndex = position * 2 + 2
    }
    if (minIndex != position) {
      swap(minIndex, position, tree)
    } else {
      return null
    }
    siftDown(minIndex)

  }

  def greaterPriority(node1: ppUnit, node2: ppUnit): Boolean = {
    if (node1.time < node2.time) {
      true
    } else if (node1.time == node2.time && node1.thread < node2.thread) {
      true
    } else {
      false
    }
  }


}