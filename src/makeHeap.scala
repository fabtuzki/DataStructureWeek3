import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.math

object makeHeap {
  var swapDetail = new ArrayBuffer[String]

  def main(args: Array[String]): Unit = {

    val inputTest = Source.fromFile("C:\\Users\\Jade Phung\\Documents\\homework2\\week2_priority_queues_and_disjoint_sets\\1_make_heap\\tests\\04").getLines().toArray
    val input = inputTest(1).split(" ").map(_.toInt)

    val outputTest = Source.fromFile("C:\\Users\\Jade Phung\\Documents\\homework2\\week2_priority_queues_and_disjoint_sets\\1_make_heap\\tests\\04.a").getLines().toArray
    val output = outputTest.drop(1)

    println(compareTest(input, output))
println(output.length)
    println(swapDetail.length)
    println(input.length)


  }

  def compareTest(input: Array[Int], output: Array[String]): Boolean = {

    var check = 0

    buildHeap(input.length, input)

    for (i <- 0 until output.length) {
      if (swapDetail(i) != output(i)) {
        println("error swapdetail: " + swapDetail(i))
        println("error output real: " + output(i))
        println("error at row : " + i)
        check += 1

      }


    }
    if (check > 0) {
      false
    } else {
      true
    }


  }

  def buildHeap(size: Int, tree: Array[Int]): Unit = {
    for (i <- (0 to math.floor(size / 2).toInt).reverse) {
      siftDown(i, tree)

    }

  }

  def siftDown(position: Int, tree: Array[Int]): Unit = {
    var minIndex = position
    if (position * 2 + 1 <= (tree.length - 1) && tree(minIndex) > tree(position * 2 + 1)) {
      minIndex = position * 2 + 1

    }
    if (position * 2 + 2 <= (tree.length - 1) && tree(minIndex) > tree(position * 2 + 2)) {
      minIndex = position * 2 + 2
    }

    if (minIndex != position) {
      val swap1 = tree(minIndex)
      val swap2 = tree(position)
      tree.update(minIndex, swap2)
      tree.update(position, swap1)
      swapDetail.append(position + " " +  minIndex)
    } else {
      return null
    }

    siftDown(minIndex, tree)


  }


}
