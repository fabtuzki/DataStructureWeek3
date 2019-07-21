class DisjointedSetImplementation(var tableList: Array[Int]) {
  var setByInput = makeSet(tableList)

  /*difference here is node containing value too, not just table indexing */
  def makeSet(input: Array[Int]): Array[tableValue] = {
    //parent of each ID is ??
    val output = input.zipWithIndex.map(x => tableValue(x._2, x._1))
    output
  }

  def find(tableNumber: Int): tableValue = {
    /*Since we do path compression here we have to implement 2 job:
    * 1 is return the tableValue
    * 2 is modify the parent of each node it tranverse through */

    null

  }

  def union(tableNumber1: Int, tableNumber2: Int): Unit = {
    /*Here we do rank heuristic union, which mean we don't have to return anything, just do
    * Check the rank and union the two table by rank  */
    null
  }


}


case class tableValue(nodeNumber: Int, nodeValue: Int)