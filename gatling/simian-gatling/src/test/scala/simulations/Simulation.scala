package simulations

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.core.session.Expression
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory
import org.slf4j.LoggerFactory
import ch.qos.logback.classic.{Level, LoggerContext}

class SimianSimulation extends Simulation {

  def getEnv(name:String, deft:String):String =
    sys.env.getOrElse(name,deft)

  def getInt(name:String, deft:Int):Int =
    getEnv(name,""+deft).toInt

  val logLevel = "WARN"
  val context = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
  context.getLogger("io.gatling.http").setLevel(Level.valueOf(logLevel))

  var baseUrl = getEnv("GATLING_BASE_URL", "http://localhost:8080")

  var acceptHdr = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
  var doNotTrackHdr = "1"
  var userAgentHdr = "Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0"

  val httpProtocol = http
    .baseUrl(baseUrl)
    .acceptHeader(acceptHdr)
    .doNotTrackHeader(doNotTrackHdr)
    .userAgentHeader(userAgentHdr)

  val post_dna_body =
    """["CTAGAA", "CAGAGC", "TGGGGT", "AGAGGG", "CCACGG", "TCACTG"]"""

  val post_dna = http("post_dna").post("/simian/dna")
    .header("content-type","application/json")
    .body(StringBody(post_dna_body))

  val get_stats = http("get_stats").get("/simian/stats")

  val scn = scenario("Telemetry Scenario for Simian API Requests").exec(get_stats).exec(post_dna)

  val incUsersPerSec = getInt("GATLING_USERS_SEC", 5)
  val incTimes = getInt("GATLING_TIMES", 3)
  val lvlDuration = getInt("GATLING_LEVEL_MINUTES",1).minutes
  val rampDuration = getInt("GATLING_RAMP_MINUTES",30).seconds

  setUp(
    scn.inject(
      incrementUsersPerSec(incUsersPerSec)
        .times(incTimes)
        .eachLevelLasting(lvlDuration)
        .separatedByRampsLasting(rampDuration)
        .startingFrom(1)
    )
  ).protocols(httpProtocol)

}