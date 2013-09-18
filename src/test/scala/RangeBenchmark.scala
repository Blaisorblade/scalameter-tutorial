import org.scalameter.{reporting, api, execution}
import org.scalameter.api._

//XXX: this uses def, not lazy vals, because of the code it's copied from. But this seems fishy.
trait MyRegression extends PerformanceTest {
  import Executor.Measurer
  import reporting._
  def warmer = Executor.Warmer.Default()
  def aggregator = Aggregator.complete(Aggregator.average)
  def measurer: Measurer = new Measurer.IgnoringGC with Measurer.PeriodicReinstantiation with Measurer.OutlierElimination with Measurer.RelativeNoise
  def executor: Executor = new execution.SeparateJvmsExecutor(warmer, aggregator, measurer)
  def regressionTester: RegressionReporter.Tester = RegressionReporter.Tester.OverlapIntervals()
  def historian: RegressionReporter.Historian = RegressionReporter.Historian.ExponentialBackoff()
  def regressionReporter =
    new RegressionReporter(regressionTester, historian)
  def reporter: Reporter = Reporter.Composite(
    regressionReporter,
    HtmlReporter(false)
  )
}

object RangeBenchmark
extends MyRegression {
  override def regressionTester = RegressionReporter.Tester.Accepter()
  /*
  //lazy val executor = SeparateJvmsExecutor(
  lazy val executor = LocalExecutor(
    new Executor.Warmer.Default,
    Aggregator.complete(Aggregator.min),
    new Measurer.Default)
    */

  override lazy val reporter =
      Reporter.Composite(
        LoggingReporter()
        , regressionReporter
        , DsvReporter(delimiter=';')
        , HtmlReporter(true)
        /*ChartReporter(ChartFactory.XYLine())*/
      )

  //Ouch!
  //lazy val persistor = Persistor.None
  lazy val persistor = new SerializationPersistor

  // multiple tests can be specified here
  val mult = 1

  val sizes: Gen[Int] = Gen.range("size")(30000 * mult, 150000 * mult, 30000 * mult)
  val ranges: Gen[Range] =
    for {
      size <- sizes
    } yield 0 until size

  performance of "Range" in {
    measure method "map" config (
      exec.benchRuns -> 10
      , reports.regression.significance -> 0.05
    ) in {
      using (ranges) in {
        r => r map (_ + 1)
      }
    }
  }
}
