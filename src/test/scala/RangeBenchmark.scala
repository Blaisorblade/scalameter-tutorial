import org.scalameter.api._

object RangeBenchmark
extends PerformanceTest {
  lazy val executor = SeparateJvmsExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  // multiple tests can be specified here
  val mult = 1

  val sizes: Gen[Int] = Gen.range("size")(30000 * mult, 150000 * mult, 30000 * mult)
  val ranges: Gen[Range] =
    for {
      size <- sizes
    } yield 0 until size

  performance of "Range" in {
    measure method "map" in {
      using (ranges) in {
        r => r map (_ + 1)
      }
    }
  }
}
