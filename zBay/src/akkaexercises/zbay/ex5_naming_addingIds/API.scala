package akkaexercises.zbay.ex5_naming_addingIds

import akka.actor.Actor
import Auction.Protocol.Bid
import scala.concurrent.duration._
import akka.util.Timeout

class API extends Actor {
  import API.Protocol._

  def receive = {
    case BidRequest(auctionId, userId, value) =>
      val userRef = context.actorFor(s"../user$userId")
      context.actorSelection(s"../auction$auctionId") ! Bid(value, userRef)
  }
}
object API {
  object Protocol {
    case class BidRequest(auctionId: Long,
                          userId: Long,
                          value: BigDecimal)
  }
}
