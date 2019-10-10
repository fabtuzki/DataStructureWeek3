/*class priorityQueueTuple2(var tree: Array[ppUnit]) {

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

  def siftUp(position: Int): Unit = {
    var updatedPosition = position
    while (updatedPosition > 0 && greaterPriority(tree(updatedPosition), tree(math.floor((updatedPosition - 1) / 2).toInt))) {
      val swap1 = tree(updatedPosition)
      val swap2 = tree(math.floor((updatedPosition - 1) / 2).toInt)
      tree.update(updatedPosition, swap2)
      tree.update(math.floor((updatedPosition - 1) / 2).toInt, swap1)
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
      val swap1 = tree(minIndex)
      val swap2 = tree(position)
      tree.update(minIndex, swap2)
      tree.update(position, swap1)
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

  /*

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
  */


}*/

//case class ppUnit(time: Long, thread: Int)
