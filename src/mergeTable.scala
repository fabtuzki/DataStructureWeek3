object mergeTable {
  def main(args: Array[String]): Unit = {
    val input = Array(1, 1, 1, 1, 1, 1, 1, 1)
    val disjointedSet = new DisjointedSetImplementation(input)
    println("union 1")
    disjointedSet.union(0, 4)
    disjointedSet.setByInput.foreach(x => println(x))
    println("union 2")

    disjointedSet.union(2, 1)
    disjointedSet.setByInput.foreach(x => println(x))
    println("union 3")

    disjointedSet.union(0, 1)
    disjointedSet.setByInput.foreach(x => println(x))


  }


}
