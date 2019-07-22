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
    * 2 is modify the parent of each node it tranverse through
    * let's try to write this in tail recursive manner */
    var rootID = setByInput(tableNumber).parentNode
    if (rootID != tableNumber) {
      while (rootID != setByInput(rootID).parentNode) {
        rootID = setByInput(rootID).parentNode
      }
    }
    setByInput.update(tableNumber, tableValue(rootID, setByInput(tableNumber).nodeValue))
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


case class tableValue(parentNode: Int, nodeValue: Int)