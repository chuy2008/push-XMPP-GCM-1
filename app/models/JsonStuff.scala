package models

import play.api.libs.json._


object JsonStuff
{
   def createJsonMessage(to: String, messageId: String, payload: Map[String, String],
      collapseKey: String, timeToLive: Long, delayWhileIdle: Boolean): String =  
  {
//    var message = new scala.collection.mutable.HashMap[String, Object]
    var message = Map("to" -> Json.toJson(to))
    if (collapseKey != null) 
    {
      message += ("collapse_key" -> Json.toJson(collapseKey))
    }
    if (timeToLive != null) 
    {
      message += ("time_to_live" -> Json.toJson(timeToLive))
    }
    if (delayWhileIdle != null && delayWhileIdle) 
    {
      message += ("delay_while_idle" -> Json.toJson(true))
    }
    message += ("message_id" -> Json.toJson(messageId))
    val payloadd = for (pp <- payload) yield (pp._1, Json.toJson(pp._2))
    val payloaddd = Json.toJson(payloadd)
    message += ("data" -> payloaddd)
    Json.stringify(Json.toJson(message))
  } 
}