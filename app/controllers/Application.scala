package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
// import play.api.libs.functional.syntax._
// import play.api.libs.json.Writes._
import play.api.libs.ws.WS


import models._

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.DefaultPacketExtension;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.StringUtils;

// import org.json.simple.JSONValue;
// import org.json.simple.parser.ParseException;
// import org.xmlpull.v1.XmlPullParser;

/*
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocketFactory;
*/

object Application extends Controller 
{
  
  def index = Action 
  {
    val userName = "Your GCM Sender Id" + "@gcm.googleapis.com"
    val password = "API Key"

    var ccsClient = new SmackCcsClient

//    try {
      ccsClient.connect(userName, password)
//    } catch (XMPPException e) {
//      e.printStackTrace();
//    }

    // Send a sample hello downstream message to a device.
    var toRegId = "RegistrationIdOfTheTargetDevice"
    var messageId = ccsClient.getRandomMessageId
    var payload = new scala.collection.mutable.HashMap[String, String]
    payload += ("Hello" -> "World")
    payload += ("CCS" -> "Dummy Message")
    payload += ("EmbeddedMessageId" -> messageId)
    var collapseKey = "sample"
    val timeToLive: Long = 10000L
    val delayWhileIdle = true
    ccsClient.send(JsonStuff.createJsonMessage(toRegId, messageId, payload, collapseKey, timeToLive, delayWhileIdle))

    
    
    Ok(views.html.index("Your new application is ready."))
  }
  
  def sendCode = 
  {         
      WS.url("https://android.googleapis.com/gcm/send").post(Json.toJson(Map("Code" -> "", "DeviceId" -> "")))
  }
  
  def receiveCode = Action(parse.json)
  {
      request => 
         println("Application scala routine, line 43, request content type = " + request.contentType)
         println("Application scala routine, line 44, request body = " + request.body)
         println("Application scala routine, line 45, request body to String = " + request.toString)
         var codd = ""
         var deviceId = ""
         (request.body \ "Code").asOpt[String].map { code => codd = code}.getOrElse 
         {
             BadRequest("Missing parameter [name]")
          }        
         (request.body \ "deviceID").asOpt[String].map { dId => deviceId = dId}.getOrElse 
         {
             BadRequest("Missing parameter [name]")
          }     
         
         WS.url("https://android.googleapis.com/gcm/send").post(Json.toJson(Map("Code" -> codd, "DeviceId" -> deviceId)))
//         Ok(Json.toJson(Map("Code" -> codd, "DeviceId" -> deviceId)))
         Ok("hullo")
  }
  
}