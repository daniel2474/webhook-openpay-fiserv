package com.webHook.Controller;

import com.webHook.Correo.Correo;
import com.webHook.Correo.Html;
import com.webHook.Entity.FiservRespuesta;
import com.webHook.Service.ConfiguracionService;
import com.webHook.Service.DatosUsuarioService;
import com.webHook.Service.FiservRespuestaService;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




 @RestController
 @RequestMapping({"/fiserv"})
 @CrossOrigin(origins = "*")
 public class FiservController
 {
	    // creating a logger
	    Logger logger= LoggerFactory.getLogger(FiservController.class);
   
	 @Autowired
	 DatosUsuarioService datosUsuarioService;
	 

	 @Autowired
	 FiservRespuestaService fiservRespuestaService;

	  @Autowired
	  ConfiguracionService confiService;
	  

	   @Value("${my.property.data}")
		String dbURL;

		@Value("${my.property.userData}")
		String userData;

		@Value("${my.property.passData}")
	   String passData;		

		@Value("${my.property.usuarioCorreo}")
		String usuarioCorreo;
		
		@Value("${my.property.contrasenaCorreo}")
		String contrasenaCorreo;
		
		@Value("${my.property.copiaOculta2}")
		String copiaOculta;
		
		@Value("${my.property.url}")
		String url;		

		@Value("${my.property.urlSports}")
		String urlSports;

	 @PostMapping(value = "/registrarDatos", produces = MediaType.TEXT_HTML_VALUE)
	 @ResponseBody
	public String registrarDatos(@RequestBody String body){
		Correo correo = null;
		JSONObject respuesta2 = null;
		JSONObject bodyJson = null;
		try {
			logger.info(body);
			 Map<String, String> query_pairs = new LinkedHashMap<String, String>();
			    String[] pairs = body.split("&");
			    for (String pair : pairs) {
			        int idx = pair.indexOf("=");
			        try {
						query_pairs.put("\""+URLDecoder.decode(pair.substring(0, idx)+"\"", "UTF-8"),"\""+ URLDecoder.decode(pair.substring(idx+1 )+"\"", "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			    String nuevostring=query_pairs.toString().replace("=", ":");
			   	bodyJson=new JSONObject(nuevostring);
			    String fechaPago=bodyJson.getString("txndate_processed");
				 String[] fechaSplit= fechaPago.split(" ");
				 double monto=bodyJson.getDouble("chargetotal");
				 String formato=NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(monto);
				 String noAutorizacion=bodyJson.getString("approval_code");
				 String[] autorizacionSplit=noAutorizacion.split(":");
				 String oid=bodyJson.getString("oid");
				logger.info("Conversion a json exitosa : "+nuevostring);

			    
			    HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create(url+"fiserv/guardar")).header("Content-Type", "application/json").POST(BodyPublishers.ofString(nuevostring)).build();
			    CompletableFuture<String> client = HttpClient.newHttpClient().sendAsync(request1, BodyHandlers.ofString()).thenApply(HttpResponse::body);
		    	String json = "";
			   	try {
					json = String.valueOf(client.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			   	try {
				   	JSONObject respuesta=new JSONObject(json);
				   	HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("http://192.168.20.47/ServiciosClubAlpha/api/Miembro/"+respuesta.getInt("id_cliente"))).header("Content-Type", "application/json").GET().build();
				    CompletableFuture<String> client2 = HttpClient.newHttpClient().sendAsync(request2, BodyHandlers.ofString()).thenApply(HttpResponse::body);
			    	String json2 = "";
						json2 = String.valueOf(client2.get());
				   	respuesta2=new JSONObject(json2);
				   	correo=new Correo(usuarioCorreo,contrasenaCorreo,respuesta2.getString("EMail"),copiaOculta);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					logger.error(e.getMessage());
				    return "<html>\n" + "<header><title>Welcome</title></header>\n" +
			          "<body>\n" + "<img src=\"https://img.freepik.com/vector-premium/mensaje-alerta-notificacion-movil-alertas-error-peligro-problema-virus-telefono-inteligente-o-notificaciones-problemas-mensajes-no-deseados-no-seguros-ilustracion_100456-1401.jpg\"> No se ha completado el pago\n"  + "</body>\n" + "</html>";
				    
				}
			 String status=query_pairs.get("\"status\"");

			 logger.info("El pago fue : "+status.equals("\"APROBADO\""));
			 if(status.equals("\"APROBADO\"")) {
				 try {
					 
					 correo.enviar_correo_aprobado(respuesta2.getString("Nombre")+" "+respuesta2.getString("ApellidoPaterno")+" "
							 +respuesta2.getString("ApellidoMaterno"), respuesta2.getInt("IdCliente"),
							 respuesta2.getString("NoMembresia"), fechaSplit[0], fechaSplit[1]+" "+fechaSplit[2], formato,
							 autorizacionSplit[1], oid);
						logger.info("El correo se envio exitosamente: "+respuesta2.getString("EMail"));
				 }catch(Exception e) {
					 logger.error("No se envio el correo por el siguiente error: "+e.getMessage());
				 }
				 Html html=new Html();
				 html.setHtmlAprobado(respuesta2.getString("Nombre")+" "+respuesta2.getString("ApellidoPaterno")+" "
							 +respuesta2.getString("ApellidoMaterno"), respuesta2.getInt("IdCliente"),
							 respuesta2.getString("NoMembresia"),fechaSplit[0], fechaSplit[1], formato, autorizacionSplit[1], oid);
				 return html.getHtmlAprobado();
			 }else {
				 correo.enviar_correo_no_aprobado(respuesta2.getString("Nombre")+" "+respuesta2.getString("ApellidoPaterno")+" "
							 +respuesta2.getString("ApellidoMaterno"), autorizacionSplit[1], oid);
				 Html html=new Html();
				 html.setHtmlRechazado(respuesta2.getString("Nombre")+" "+respuesta2.getString("ApellidoPaterno")+" "
							 +respuesta2.getString("ApellidoMaterno"), autorizacionSplit[1], oid);

				 return html.getHtmlRechazado();
			 }
		}catch(Exception e) {
		    logger.error(e.getMessage());
		    return "<html>\n" + "<header><title>Welcome</title></header>\n" +
	          "<body>\n" + "<img src=\"https://img.freepik.com/vector-premium/mensaje-alerta-notificacion-movil-alertas-error-peligro-problema-virus-telefono-inteligente-o-notificaciones-problemas-mensajes-no-deseados-no-seguros-ilustracion_100456-1401.jpg\"> No se ha completado el pago\n"  + "</body>\n" + "</html>";
		}
	 } 
	 @PostMapping(value = "/domiciliarCliente", produces = MediaType.TEXT_HTML_VALUE)
	 @ResponseBody
	public String domiciliarCliente(@RequestBody String body){
		Correo correo = null;
		JSONObject respuesta2 = null;
		JSONObject bodyJson = null;
		try {
			logger.info(body);
			 Map<String, String> query_pairs = new LinkedHashMap<String, String>();
			    String[] pairs = body.split("&");
			    for (String pair : pairs) {
			        int idx = pair.indexOf("=");
			        try {
						query_pairs.put("\""+URLDecoder.decode(pair.substring(0, idx)+"\"", "UTF-8"),"\""+ URLDecoder.decode(pair.substring(idx+1 )+"\"", "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			    String nuevostring=query_pairs.toString().replace("=", ":");
			   	bodyJson=new JSONObject(nuevostring);
			    String fechaPago=bodyJson.getString("txndate_processed");
				 String[] fechaSplit= fechaPago.split(" ");
				 double monto=bodyJson.getDouble("chargetotal");
				 String formato=NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(monto);
				 String noAutorizacion=bodyJson.getString("approval_code");
				 String[] autorizacionSplit=noAutorizacion.split(":");
				 String oid=bodyJson.getString("oid");
				logger.info("Conversion a json exitosa : "+nuevostring);

			    
			    HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create(url+"fiserv/guardar")).header("Content-Type", "application/json").POST(BodyPublishers.ofString(nuevostring)).build();
			    CompletableFuture<String> client = HttpClient.newHttpClient().sendAsync(request1, BodyHandlers.ofString()).thenApply(HttpResponse::body);
		    	String json = "";
			   	try {
					json = String.valueOf(client.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			   	
			   	try {
				   	JSONObject respuesta=new JSONObject(json);
				   	HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("http://192.168.20.47/ServiciosClubAlpha/api/Miembro/"+respuesta.getInt("id_cliente"))).header("Content-Type", "application/json").GET().build();
				    CompletableFuture<String> client2 = HttpClient.newHttpClient().sendAsync(request2, BodyHandlers.ofString()).thenApply(HttpResponse::body);
			    	String json2 = "";
						json2 = String.valueOf(client2.get());
				   	respuesta2=new JSONObject(json2);
				   	correo=new Correo(usuarioCorreo,contrasenaCorreo,respuesta2.getString("EMail"),copiaOculta);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					logger.error(e.getMessage());
				    return "<html>\n" + "<header><title>Welcome</title></header>\n" +
			          "<body>\n" + "<img src=\"https://img.freepik.com/vector-premium/mensaje-alerta-notificacion-movil-alertas-error-peligro-problema-virus-telefono-inteligente-o-notificaciones-problemas-mensajes-no-deseados-no-seguros-ilustracion_100456-1401.jpg\"> No se ha completado el pago\n"  + "</body>\n" + "</html>";
				    
				}
			 String status=query_pairs.get("\"status\"");

			 logger.info("El pago fue : "+status.equals("\"APROBADO\""));
			 if(status.equals("\"APROBADO\"")) {
				 try {
					 String domiciliacion="{\r\n"
					   			+ "    \"usuario\":"+bodyJson.getInt("id_cliente")+",\r\n"
					   			+ "    \"token\":\""+bodyJson.getString("hosteddataid")+"\"\r\n"
					   			+ "}";
					    HttpRequest request3 = HttpRequest.newBuilder().uri(URI.create(urlSports+"alpha/domiciliarCliente")).header("Content-Type", "application/json").POST(BodyPublishers.ofString(domiciliacion)).build();
					    CompletableFuture<String> client3 = HttpClient.newHttpClient().sendAsync(request3, BodyHandlers.ofString()).thenApply(HttpResponse::body);
				    	String json3 = "";
					   	try {
							json3 = String.valueOf(client3.get());
							System.out.println(json3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
					 correo.enviar_correo_domiciliado(respuesta2.getString("Nombre")+" "+respuesta2.getString("ApellidoPaterno")+" "
							 +respuesta2.getString("ApellidoMaterno"), respuesta2.getInt("IdCliente"),
							 respuesta2.getString("NoMembresia"), fechaSplit[0], fechaSplit[1]+" "+fechaSplit[2], formato,
							 autorizacionSplit[1], oid);
						logger.info("El correo se envio exitosamente: "+respuesta2.getString("EMail"));
				 }catch(Exception e) {
					 logger.error("No se envio el correo por el siguiente error: "+e.getMessage());
				 }
				 Html html=new Html();
				 html.setHtmlDomiciliado(respuesta2.getString("Nombre")+" "+respuesta2.getString("ApellidoPaterno")+" "
							 +respuesta2.getString("ApellidoMaterno"), respuesta2.getInt("IdCliente"),
							 respuesta2.getString("NoMembresia"),fechaSplit[0], fechaSplit[1], formato, autorizacionSplit[1], oid);
				 return html.getHtmlDomiciliado();
			 }else {
				 correo.enviar_correo_no_aprobado(respuesta2.getString("Nombre")+" "+respuesta2.getString("ApellidoPaterno")+" "
							 +respuesta2.getString("ApellidoMaterno"), autorizacionSplit[1], oid);
				 Html html=new Html();
				 html.setHtmlRechazado(respuesta2.getString("Nombre")+" "+respuesta2.getString("ApellidoPaterno")+" "
							 +respuesta2.getString("ApellidoMaterno"), autorizacionSplit[1], oid);

				 return html.getHtmlRechazado();
			 }
		}catch(Exception e) {
		    logger.error(e.getMessage());
		    return "<html>\n" + "<header><title>Welcome</title></header>\n" +
	          "<body>\n" + "<img src=\"https://img.freepik.com/vector-premium/mensaje-alerta-notificacion-movil-alertas-error-peligro-problema-virus-telefono-inteligente-o-notificaciones-problemas-mensajes-no-deseados-no-seguros-ilustracion_100456-1401.jpg\"> No se ha completado el pago\n"  + "</body>\n" + "</html>";
		}
	 } 
	 
	 
	 @PostMapping("/guardar")
	 @ResponseBody
	public ResponseEntity<?> convertidor(@RequestBody FiservRespuesta body){
		 try {
			 logger.info("Se aplicara el pago : "+body);
		     String[] fe = TimeStampDate(Long.parseLong(body.getTdate()));
			 body=fiservRespuestaService.save(body);
			logger.info("Registro de pago guardado : "+body);
			 //Token t = this.confiService.getById(1).get();
			 if(body.getStatus().equals("APROBADO")) {
				 this.sp(Long.parseLong(body.getOv()),Double.parseDouble(body.getChargetotal()) , body.getRefnumber(), body.getIpgTransactionId(), 
						 body.getApproval_code(), fe[0], fe[1], "");
					logger.info("Pago reflejado en dataflow : "+body);
			 }
	        return new ResponseEntity<>(body, HttpStatus.OK);
		 }catch(Exception e ) {
			 logger.error("El pago no se reflejo en dataflow : "+body);
		        return new ResponseEntity<>("", HttpStatus.OK);
		 }
		
	 } 
	        
	 private String[] TimeStampDate(long timestampString) {
        Long timestamp = Long.valueOf(timestampString * 1000L);
        String date = (new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss")).format(new Date(timestamp.longValue()));
        String[] fechaHora = date.split("-");
        return fechaHora;
      }
	 private void sp(long noPedido, double monto, String noTarjeta, String folioInterbancario, String noAutorizacion, String fechaPago, String horaPago, String titularCuenta) {
		   Connection conn = null;
	       try {
	           // Carga el driver de oracle

	    	    String datePattern = "yyyy-MM-dd HH:mm:ss.SSS";
	    	    System.out.println(new Date());
	    	    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	    	    System.out.println(dateFormatter.format(new Date()));
	       		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
	           conn = DriverManager.getConnection(dbURL, userData, passData);
	           PreparedStatement ps=conn.prepareStatement("EXEC DataFlowAlpha.dbo.CreaReciboPagoPOL_Fiserv ?,?,?,?,?,?,?,?,?,?,?,?,? ");
	           ps.setInt(1, 1);
	           ps.setString(2,dateFormatter.format(new Date()));
	           ps.setString(3, dateFormatter.format(new Date()));
	           ps.setLong(4, noPedido);
	           ps.setString(5, "");
	           ps.setDouble(6, monto);
	           ps.setInt(7, 20);
	           ps.setString(8, noTarjeta);
	           ps.setString(9, folioInterbancario);
	           ps.setString(10, fechaPago+" "+horaPago);
	           ps.setString(11, noAutorizacion);
	           ps.setInt(12, 6);
	           ps.setString(13, titularCuenta);
	           ps.execute();
	           
	         
	       	conn.close();
	       } catch (SQLException ex) {
	           System.out.println("Error: " + ex.getMessage());
	           ex.printStackTrace();
	       } finally {
	           try {
	           	conn.close();
	           } catch (SQLException ex) {
	               System.out.println("Error: " + ex.getMessage());
	           }
	       }
	   }
	 @GetMapping(value = "/pagar/{idCliente}", produces = MediaType.TEXT_HTML_VALUE)
	 @ResponseBody
	public String pagar(@PathVariable("idCliente") int idCliente){
		
		 String body2 = "{\n"
	    			+ "\"IdCliente\":"+idCliente+",\n"
	    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
	    			+ "}";

		    
		    HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("http://192.168.20.44/ServiciosClubAlpha/api/Pagos/GetPedidoByCliente")).header("Content-Type", "application/json").POST(BodyPublishers.ofString(body2)).build();
		    CompletableFuture<String> client = HttpClient.newHttpClient().sendAsync(request1, BodyHandlers.ofString()).thenApply(HttpResponse::body);
	    	String json = "";
		   	try {
				json = String.valueOf(client.get());
				JSONObject resp=new JSONObject(json);
				JSONArray detalle=resp.getJSONArray("Detalle");
				double importe=0;
				for(int i=0;i<detalle.length();i++) {
					JSONObject aux=detalle.getJSONObject(i);
					importe=importe+aux.getDouble("Importe");
				}
				if(importe>0) {
					return "<html>\r\n"
							+ "    <head>\r\n"
							+ "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n"
							+ "        <title>Pago en linea</title>\r\n"
							+ "        <style type=\"text/css\">\r\n"
							+ "            /* Reset -------------------------------------------------------------------- */\r\n"
							+ "\r\n"
							+ "            /* OPPS --------------------------------------------------------------------- */\r\n"
							+ "\r\n"
							+ "            h3 {\r\n"
							+ "                margin-bottom: 10px;\r\n"
							+ "                font-size: 15px;\r\n"
							+ "                font-weight: 600;\r\n"
							+ "                text-transform: uppercase;\r\n"
							+ "            }\r\n"
							+ "\r\n"
							+ "            .opps {\r\n"
							+ "                width: 100%; \r\n"
							+ "                border-radius: 4px;\r\n"
							+ "                box-sizing: border-box;\r\n"
							+ "                padding: 0 45px;\r\n"
							+ "                margin: 40px auto;\r\n"
							+ "                overflow: hidden;\r\n"
							+ "                border: 1px solid #b0afb5;\r\n"
							+ "                font-family: 'Open Sans', sans-serif;\r\n"
							+ "                color: #4f5365;\r\n"
							+ "            }\r\n"
							+ "\r\n"
							+ "            .opps-reminder {\r\n"
							+ "                position: relative;\r\n"
							+ "                top: -1px;\r\n"
							+ "                padding: 9px 0 10px;\r\n"
							+ "                font-size: 11px;\r\n"
							+ "                text-transform: uppercase;\r\n"
							+ "                text-align: center;\r\n"
							+ "                color: #ffffff;\r\n"
							+ "                background: #000000;\r\n"
							+ "            }\r\n"
							+ "\r\n"
							+ "            .opps-info {\r\n"
							+ "                margin-top: 26px;\r\n"
							+ "                position: relative;\r\n"
							+ "            }\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "            .opps-reference {\r\n"
							+ "                margin-top: 14px;\r\n"
							+ "            }\r\n"
							+ "\r\n"
							+ "            h1 {\r\n"
							+ "                font-size: 27px;\r\n"
							+ "                color: #000000;\r\n"
							+ "                text-align: center;\r\n"
							+ "                margin-top: -1px;\r\n"
							+ "                padding: 6px 0 7px;\r\n"
							+ "                border: 1px solid #b0afb5;\r\n"
							+ "                border-radius: 4px;\r\n"
							+ "                background: #f8f9fa;\r\n"
							+ "            }\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "        </style>\r\n"
							+ "        <script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script>\r\n"
							+ "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js\"></script>\r\n"
							+ "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js\"></script>\r\n"
							+ "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.33/moment-timezone-with-data-10-year-range.min.js\"></script>\r\n"
							+ "        <script>\r\n"
							+ "            $(function() {\r\n"
							+ "                /* Actualizar fecha de transacción Hora */\r\n"
							+ "                function updateDatetime() {\r\n"
							+ "              var timezone = $(\"input[name='timezone']\").val();\r\n"
							+ "              $(\"input[name='txndatetime']\").val(moment().tz(timezone).format('YYYY:MM:DD-HH:mm:ss'));\r\n"
							+ "                  }\r\n"
							+ "    \r\n"
							+ "                $(\"input[name='timezone']\").change(function() {\r\n"
							+ "                    updateDatetime();\r\n"
							+ "                });\r\n"
							+ "    \r\n"
							+ "                /* Interceptar formulario de pago Enviar para calcular hash de solicitud */\r\n"
							+ "                $(\"#paymentForm\").submit(function(event) {\r\n"
							+ "                    /* URL del entorno */\r\n"
							+ "                    var environmentUrl = $(\"input[name='environment']\").val();\r\n"
							+ "                    /* Formulario de pago */\r\n"
							+ "                    var paymentForm = $(\"#paymentForm\");\r\n"
							+ "                    paymentForm.attr('action', environmentUrl);\r\n"
							+ "                    /* Extraer parámetros de formulario de pago */\r\n"
							+ "                    var paymentParameters = paymentForm.serializeArray().filter(function(item) {\r\n"
							+ "                        return item.value !== \"\";\r\n"
							+ "                    }).reduce(function(obj, item) {\r\n"
							+ "                        obj[item.name] = item.value;\r\n"
							+ "                        return obj;\r\n"
							+ "                    }, {});\r\n"
							+ "                    /* Preparar el contenido de la firma del mensaje */\r\n"
							+ "                    const sharedSecret = $(\"input[name='sharedsecret']\").val();\r\n"
							+ "                    var messageSignatureContent = [ ];\r\n"
							+ "                    const ignoreSignatureParameteres = [ \"hashExtended\" ,\"id_cliente\",\"ov\"];\r\n"
							+ "                    Object.keys(paymentParameters).filter(key => !ignoreSignatureParameteres.includes(key)).sort().forEach(function(key, index) {\r\n"
							+ "                        messageSignatureContent.push(paymentParameters[key]);\r\n"
							+ "                    });\r\n"
							+ "                    /* Calcular firma de mensaje */\r\n"
							+ "                    console.log(messageSignatureContent.join(\"|\"));\r\n"
							+ "                    const messageSignature = CryptoJS.HmacSHA256(messageSignatureContent.join(\"|\"), sharedSecret);\r\n"
							+ "                    const messageSignatureBase64 = CryptoJS.enc.Base64.stringify(messageSignature);\r\n"
							+ "            console.log('Hash: ',messageSignatureBase64);\r\n"
							+ "                    /* Actualizar parámetros de formulario */\r\n"
							+ "                    $(\"input[name='hashExtended']\").val(messageSignatureBase64);\r\n"
							+ "                	   const button = document.querySelector('#submit');\r\n"
							+ "               	   button.disabled=true;"
							+ "                });\r\n"
							+ "    \r\n"
							+ "                updateDatetime();\r\n"
							+ "            });\r\n"
							+ "        </script>\r\n"
							+ "        <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,600,700\" rel=\"stylesheet\">\r\n"
							+ "    </head>\r\n"
							+ "    <body>\r\n"
							+ "        <div class=\"opps\">\r\n"
							+ "            <div class=\"opps-header\">\r\n"
							+ "                <div class=\"opps-info\">\r\n"
							+ "                    <div class=\"opps-brand\"><img src=\"https://www.clubalpha.com.mx/images/logo_positivo2.png\" alt=\"pagoOnline\" width=\"200px\" height=\"100px\"></div>\r\n"
							+ "                   \r\n"
							+ "                </div>\r\n"
							+ "                <div class=\"opps-reference\">\r\n"
							+ "                    \r\n"
							+ "                    <h1> ID DE CLIENTE: "+idCliente+" <div style=\"padding-left: 1%;\"> MONTO: $"+this.fijarNumero(importe, 2)+"</div>  </h1>  \r\n"
							+ "                    <div>\r\n"
							+ "                        <legend hidden>Fiserv Connect</legend>\r\n"
							+ "                        <p>\r\n"
							+ "                          <label hidden for=\"environment\">Test Server Fiserv</label>\r\n"
							+ "                          <input hidden type=\"text\" name=\"environment\" value=\"https://www2.ipg-online.com/connect/gateway/processing\">\r\n"
							+ "                        </p>\r\n"
							+ "                        <p>\r\n"
							+ "                          <label hidden for=\"sharedsecret\">Shared Secreta:</label>\r\n"
							+ "                          <input hidden type=\"text\" name=\"sharedsecret\" value=\"Er9L3+@eym\">\r\n"
							+ "                        </p>\r\n"
							+ "                        \r\n"
							+ "                        <form id=\"paymentForm\" method=\"post\" action=\"#\" target=\"myFrame\">\r\n"
							+ "                            <!-- <input  type=\"text\" name=\"assignToken\" value=\"false\" hidden></input>      -->\r\n"
							+ "                          <input type=\"text\" name=\"chargetotal\" value=\""+this.fijarNumero(importe, 2)+"\" hidden>\r\n"
							+ "                          <input name=\"checkoutoption\" value=\"combinedpage\" hidden>\r\n"
							+ "                          <input type=\"text\" name=\"currency\" value=\"484\" hidden>\r\n"
							+ "                          <input name=\"hash_algorithm\" value=\"HMACSHA256\" hidden>\r\n"
							+ "                          <input name=\"hashExtended\" value=\"\" hidden>\r\n"
							+ "                          <input type=\"text\" name=\"responseFailURL\" size=\"60\" value=\"https://pagos.clubalpha.com.mx:8443/fiserv/registrarDatos\" hidden>\r\n"
							+ "                          <input type=\"text\" name=\"responseSuccessURL\" size=\"60\" value=\"https://pagos.clubalpha.com.mx:8443/fiserv/registrarDatos\" hidden>\r\n"
							+ "                          <input type=\"text\" name=\"storename\" value=\"6202681\" hidden>\r\n"
							+ "                          <input type=\"text\" name=\"timezone\" value=\"America/Mexico_City\" hidden>\r\n"
							+ "                          <input type=\"text\" name=\"txndatetime\" value=\"\" readonly=\"readonly\" hidden>\r\n"
							+ "                          <input type=\"text\" name=\"txntype\" value=\"sale\" hidden>\r\n"
							+ "                          <input type=\"hidden\" name=\"id_cliente\" value=\""+idCliente+"\" >\r\n"
							+ "                          <input type=\"hidden\" name=\"ov\" value=\""+resp.getString("NoPedido")+"\" >\r\n"
							+ "                          <p>\r\n"
							+ "                            <input type=\"submit\" id=\"submit\" value=\"PAGAR\">\r\n"
							+ "                          </p>\r\n"
							+ "                        </form>\r\n"
							+ "                      </div>\r\n"
							+ "                    \r\n"
							+ "                      <iframe name=\"myFrame\" style=\"height:800px;width:100%;border:none;overflow:hidden;\"></iframe>\r\n"
							+ "                </div>\r\n"
							+ "            </div>\r\n"
							+ "            \r\n"
							+ "        </div>\r\n"
							+ "    </body>\r\n"
							+ "</html>";
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (JSONException e) {
			}catch(Exception e) {
				e.printStackTrace();
			}
		   	return "<html>\r\n"
		   			+ "    <head>\r\n"
		   			+ "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n"
		   			+ "        <title>Pago en linea</title>\r\n"
		   			+ "        <style type=\"text/css\">\r\n"
		   			+ "            /* Reset -------------------------------------------------------------------- */\r\n"
		   			+ "\r\n"
		   			+ "            /* OPPS --------------------------------------------------------------------- */\r\n"
		   			+ "\r\n"
		   			+ "            h3 {\r\n"
		   			+ "                margin-bottom: 10px;\r\n"
		   			+ "                font-size: 15px;\r\n"
		   			+ "                font-weight: 600;\r\n"
		   			+ "                text-transform: uppercase;\r\n"
		   			+ "            }\r\n"
		   			+ "\r\n"
		   			+ "            .opps {\r\n"
		   			+ "                width: 100%; \r\n"
		   			+ "                border-radius: 4px;\r\n"
		   			+ "                box-sizing: border-box;\r\n"
		   			+ "                padding: 0 45px;\r\n"
		   			+ "                margin: 40px auto;\r\n"
		   			+ "                overflow: hidden;\r\n"
		   			+ "                border: 1px solid #b0afb5;\r\n"
		   			+ "                font-family: 'Open Sans', sans-serif;\r\n"
		   			+ "                color: #4f5365;\r\n"
		   			+ "            }\r\n"
		   			+ "\r\n"
		   			+ "            .opps-reminder {\r\n"
		   			+ "                position: relative;\r\n"
		   			+ "                top: -1px;\r\n"
		   			+ "                padding: 9px 0 10px;\r\n"
		   			+ "                font-size: 11px;\r\n"
		   			+ "                text-transform: uppercase;\r\n"
		   			+ "                text-align: center;\r\n"
		   			+ "                color: #ffffff;\r\n"
		   			+ "                background: #000000;\r\n"
		   			+ "            }\r\n"
		   			+ "\r\n"
		   			+ "            .opps-info {\r\n"
		   			+ "                margin-top: 26px;\r\n"
		   			+ "                position: relative;\r\n"
		   			+ "            }\r\n"
		   			+ "\r\n"
		   			+ "\r\n"
		   			+ "\r\n"
		   			+ "\r\n"
		   			+ "            .opps-reference {\r\n"
		   			+ "                margin-top: 14px;\r\n"
		   			+ "            }\r\n"
		   			+ "\r\n"
		   			+ "            h1 {\r\n"
		   			+ "                font-size: 27px;\r\n"
		   			+ "                color: #000000;\r\n"
		   			+ "                text-align: center;\r\n"
		   			+ "                margin-top: -1px;\r\n"
		   			+ "                padding: 6px 0 7px;\r\n"
		   			+ "                border: 1px solid #b0afb5;\r\n"
		   			+ "                border-radius: 4px;\r\n"
		   			+ "                background: #f8f9fa;\r\n"
		   			+ "            }\r\n"
		   			+ "\r\n"
		   			+ "\r\n"
		   			+ "\r\n"
		   			+ "        </style>\r\n"
		   			+ "        <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,600,700\" rel=\"stylesheet\">\r\n"
		   			+ "    </head>\r\n"
		   			+ "    <body>\r\n"
		   			+ "        <div class=\"opps\">\r\n"
		   			+ "            <div class=\"opps-header\">\r\n"
		   			+ "                <div class=\"opps-info\">\r\n"
		   			+ "                    <div class=\"opps-brand\"><img src=\"https://www.clubalpha.com.mx/images/logo_positivo2.png\" alt=\"pagoOnline\" width=\"10%\" height=\"10%\"></div>\r\n"
		   			+ "                   \r\n"
		   			+ "                </div>\r\n"
		   			+ "                <div class=\"opps-reference\">\r\n"
		   			+ "                    <h1>Usuario sin adeudo</h1> \r\n"
		   			+ "                    \r\n"
		   			+ "                </div>\r\n"
		   			+ "            \r\n"
		   			+ "            </div>\r\n"
		   			+ "        </div>\r\n"
		   			+ "    </body>\r\n"
		   			+ "</html>";
			 

	 }
	 @PostMapping("/registrarDatos/error")
	 @ResponseBody
	public ResponseEntity<?> registrarDatosError(@RequestBody String body){
		//JSONObject respuesta2=null;
		
		/*respuesta2=new JSONObject();
		try {
			datosUsuarioService.save(body);		
			respuesta2.put("respuesta","Informacion guardada correctamente");
			return new ResponseEntity<>(respuesta2.toString(), HttpStatus.OK);	    
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			respuesta2.put("respuesta","ocurrio un error durante el registro");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		    String[] pairs = body.split("&");
		    for (String pair : pairs) {
			    System.out.println(pair);
		        int idx = pair.indexOf("=");
		        try {
					query_pairs.put("\""+URLDecoder.decode(pair.substring(0, idx)+"\"", "UTF-8"),"\""+ URLDecoder.decode(pair.substring(idx+1 )+"\"", "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    String nuevostring=query_pairs.toString().replace("=", ":");
		    System.out.println( nuevostring);
		    System.out.println( );

		    
		    HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create(url+"fiserv/guardar")).header("Content-Type", "application/json").POST(BodyPublishers.ofString(nuevostring)).build();
		    CompletableFuture<String> client = HttpClient.newHttpClient().sendAsync(request1, BodyHandlers.ofString()).thenApply(HttpResponse::body);
	    	String json = "";
		   	try {
				json = String.valueOf(client.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		 System.out.println(json);
		return new ResponseEntity<>(json, HttpStatus.OK);
	} 
	 public double fijarNumero(double numero, int digitos) {
	        double resultado;
	        resultado = numero * Math.pow(10, digitos);
	        resultado = Math.round(resultado);
	        resultado = resultado/Math.pow(10, digitos);
	        return resultado;
	    }
 }