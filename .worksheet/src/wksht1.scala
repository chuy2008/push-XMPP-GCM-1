import play.api.libs.json._

object wksht1
{;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(91); 
  var message = Map("to" -> Json.toJson("me"));System.out.println("""message  : scala.collection.immutable.Map[String,play.api.libs.json.JsValue] = """ + $show(message ));$skip(40); 
   message += ("1" -> Json.toJson("4"));$skip(84); 
   var list1 = Json.toJson(Map("1" -> Json.toJson("uu"), "2" -> Json.toJson("ii")));System.out.println("""list1  : play.api.libs.json.JsValue = """ + $show(list1 ));$skip(32); 
   message += ("data" -> list1);$skip(11); val res$0 = 
   message;System.out.println("""res0: scala.collection.immutable.Map[String,play.api.libs.json.JsValue] = """ + $show(res$0));$skip(24); val res$1 = 
   Json.toJson(message);System.out.println("""res1: play.api.libs.json.JsValue = """ + $show(res$1));$skip(40); val res$2 = 
   Json.stringify(Json.toJson(message));System.out.println("""res2: String = """ + $show(res$2))}
}
