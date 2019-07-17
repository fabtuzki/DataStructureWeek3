import scala.math

object makeHeap {
  def main(args: Array[String]): Unit = {
val input = Array(1,2,3,4,5)

    buildHeap(5, input)

    input.foreach( x => println(x))
  }

  def buildHeap(size: Int, tree: Array[Int]): Unit = {

    for (i <- math.floor(size / 2).toInt until 1) {
      siftDown(i, tree)

    }

  }

  def siftDown(position: Int, tree: Array[Int]): Unit = {
    var maxIndex = position
    println("max index before siftdown"  + maxIndex)

    if (position * 2 + 1 <= tree.length && tree(maxIndex) < tree(position * 2 + 1)) {
      maxIndex = position * 2 + 1

    }
    if (position * 2 + 2 <= tree.length && tree(maxIndex) < tree(position * 2 + 2)) {
      maxIndex = position * 2 + 2
    }

    if (maxIndex != position) {
      val swap1 = tree(maxIndex)
      val swap2 = tree(position)
      tree.update(maxIndex, swap2)
      tree.update(position, swap1)
    }
println("max index after siftdown"  + maxIndex)
    siftDown(maxIndex, tree)

  }
}
