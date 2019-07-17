object makeHeap {
  def main(args: Array[String]): Unit = {


  }


  def siftDown(position: Int, tree: Array[Int]): Unit = {
    var maxIndex = position
    if (position * 2 + 1 <= tree.length && tree(maxIndex) < tree(position * 2 + 1)) {
      maxIndex = position * 2 + 1

    }
    if (position * 2 + 2 <= tree.length && tree(maxIndex) < tree(position * 2 + 2)) {
      maxIndex = position * 2 + 2
    }

    if (maxIndex != position ){
      val swap1 = tree(maxIndex)
      val swap2 = tree(position)
      tree.update(maxIndex, swap2)
      tree.update(position, swap1)
    }

    siftDown(maxIndex, tree)

  }
}
