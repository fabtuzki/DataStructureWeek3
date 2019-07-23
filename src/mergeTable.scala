import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object mergeTable {
  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("D:\\Other shit\\week2_priority_queues_and_disjoint_sets\\3_merging_tables\\tests\\116")
      .getLines().toArray
    val input = source(1).split(" ").map(_.toInt)
    val disjointedSet = new DisjointedSetImplementation(input)
    val correctOutput = Source.fromFile("D:\\Other shit\\week2_priority_queues_and_disjoint_sets\\3_merging_tables\\tests\\116.a")
      .getLines().toArray.map(x => x.toLong)
    var maxValue = 0

    val unionList = source.drop(2)
      .map(_.split(" "))
      .map(x => (x(0).toInt, x(1).toInt))


    /*
        unionList.take(10).foreach( x => println(x.mkString(",")))
    */
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

  }


}
