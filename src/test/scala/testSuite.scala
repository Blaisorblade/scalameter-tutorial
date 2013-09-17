import org.scalameter.api._
/*
class RegressionTest extends PerformanceTest.Regression {
  def persistor = new SerializationPersistor

  val sizes = Gen.range("size")(1000000, 5000000, 2000000)
  val arrays = for (sz <- sizes) yield (0 until sz).toArray

  performance of "Array" in {
    measure method "foreach" in {
      using(arrays) config (
        exec.independentSamples -> 6
      ) in { xs =>
        var sum = 0
        xs.foreach(x => sum += x)
      }
    }
  }
}

class MemoryTest extends PerformanceTest.Regression {
  def persistor = new SerializationPersistor
  override def measurer = new Executor.Measurer.MemoryFootprint

  val sizes = Gen.range("size")(1000000, 5000000, 2000000)

  performance of "MemoryFootprint" in {
    performance of "Array" in {
      using(sizes) config (
        exec.benchRuns -> 10,
        exec.independentSamples -> 2
      ) in { sz =>
        (0 until sz).toArray
      }
    }
  }
}

class TestSuite extends PerformanceTest.Regression {
  //This overrides the persistor from the included classes.
  //The same would happen for reporter.
  def persistor = Persistor.None
  lazy val reporter = LoggingReporter()


  include[MemoryTest]
  include[RegressionTest]
}
 */
