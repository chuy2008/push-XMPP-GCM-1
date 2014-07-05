import play.api.libs.json._

object wksht1
{
  var message = Map("to" -> Json.toJson("me"))    //> message  : scala.collection.immutable.Map[String,play.api.libs.json.JsValue] 
                                                  //| = Map(to -> "me")
   message += ("1" -> Json.toJson("4"))
   var list1 = Json.toJson(Map("1" -> Json.toJson("uu"), "2" -> Json.toJson("ii")))
                                                  //> list1  : play.api.libs.json.JsValue = {"1":"uu","2":"ii"}
   message += ("data" -> list1)
   message                                        //> res0: scala.collection.immutable.Map[String,play.api.libs.json.JsValue] = Ma
                                                  //| p(to -> "me", 1 -> "4", data -> {"1":"uu","2":"ii"})
   Json.toJson(message)                           //> res1: play.api.libs.json.JsValue = {"to":"me","1":"4","data":{"1":"uu","2":"
                                                  //| ii"}}
   Json.stringify(Json.toJson(message))           //> res2: String = {"to":"me","1":"4","data":{"1":"uu","2":"ii"}}
}