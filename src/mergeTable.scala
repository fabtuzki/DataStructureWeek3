object mergeTable {
  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.stdin.getLines().toArray
    //    val source = Array("5 5", "1 1 1 1 1", "3 5", "2 4", "1 4", "5 4", "5 3")

    val input = source(1).split(" ").map(_.toInt)

    val disjointedSet = new DisjointedSetImplementation(input)
    //    val correctOutput = Source.fromFile("D:\\Other shit\\week2_priority_queues_and_disjoint_sets\\3_merging_tables\\tests\\116.a")
    //      .getLines().toArray.map(x => x.toLong)
    //    var maxValue = 0

    val unionList = source.drop(2)
      .map(_.split(" "))
      .map(x => (x(0).toInt, x(1).toInt))
    for (i <- 0 until unionList.length) {
      disjointedSet.union(unionList(i)._1, unionList(i)._2)
      val out = disjointedSet.setByInput.map(x => x.nodeValue).max
      println(out)
    }


    /*
            unionList.take(10).foreach( x => println(x.mkString(",")))
        for (i <- 0 until unionList.length) {
          disjointedSet.union(unionList(i)._1, unionList(i)._2)
          if (correctOutput(i) != disjointedSet.setByInput.map(x => x.nodeValue).max) {
            maxValue += 1
          }

        }

        if (maxValue == 0) {
          println("true")
        } else {
          println("false")
        }
    */

  }


}

class DisjointedSetImplementation(var tableList: Array[Int]) {

  var setByInput = makeSet(tableList)
  var rankOfTree = Array.fill(setByInput.length)(0)

  /*difference here is node containing value too, not just table indexing */
  private def makeSet(input: Array[Int]): Array[tableValue] = {
    //parent of each ID is ??
    val output = input.zipWithIndex.map(x => tableValue(x._2, x._1))
    output
  }

  def find(tableNumber: Int): tableValue = {
    /*Since we do path compression here we have to implement 2 job:
    * 1 is return the tableValue
    * 2 is modify the parent of each node it traverse through
    * let's try to write this in tail recursive manner */
    var rootID = setByInput(tableNumber - 1).parentNode
    if (rootID != tableNumber - 1) {
      while (rootID != setByInput(rootID).parentNode) {
        rootID = setByInput(rootID).parentNode
      }
    }
    setByInput.update(tableNumber - 1, tableValue(rootID, setByInput(tableNumber - 1).nodeValue))
    setByInput(rootID)
  }

  def union(tableNumber1: Int, tableNumber2: Int): Unit = {
    /*Here we do rank heuristic union, which mean we don't have to return anything, just do
    * Check the rank and union the two table by rank  */
    val rootTableNumber1 = find(tableNumber1)
    val rootTableNumber2 = find(tableNumber2)
    if (rootTableNumber1.parentNode == rootTableNumber2.parentNode) {
      return null
    } else if (rankOfTree(rootTableNumber1.parentNode) > rankOfTree(rootTableNumber2.parentNode)) {
      setByInput.update(rootTableNumber2.parentNode, tableValue(rootTableNumber1.parentNode, 0))
      setByInput.update(rootTableNumber1.parentNode, tableValue(rootTableNumber1.parentNode, rootTableNumber2.nodeValue + rootTableNumber1.nodeValue))
    } else {
      setByInput.update(rootTableNumber1.parentNode, tableValue(rootTableNumber2.parentNode, 0))
      setByInput.update(rootTableNumber2.parentNode, tableValue(rootTableNumber2.parentNode, rootTableNumber1.nodeValue + rootTableNumber2.nodeValue))
      if (rankOfTree(rootTableNumber1.parentNode) == rankOfTree(rootTableNumber2.parentNode)) {
        rankOfTree.update(rootTableNumber1.parentNode, rankOfTree(rootTableNumber1.parentNode) + 1)
      }
    }

  }


}


case class tableValue(parentNode: Int, nodeValue: Long)