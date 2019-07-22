object mergeTable {
def main(args: Array[String]): Unit ={
  val input = Array(1,1,1,1,1,1,1,1)
  val disjointedSet= new DisjointedSetImplementation(input)

  println(disjointedSet.find(3))
  disjointedSet.setByInput.foreach(x => println(x))

}


}
