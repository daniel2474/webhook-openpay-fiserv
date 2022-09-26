package com.webHook.Controller;

import com.webHook.Correo.Correo;
import com.webHook.Entity.Notificacion;
import com.webHook.Entity.Order;
 import com.webHook.Entity.Token;
 import com.webHook.Service.ConfiguracionService;
 import com.webHook.Service.OrderService;

import java.io.BufferedReader;
import java.io.IOException;
 import java.net.URI;
 import java.net.http.HttpClient;
 import java.net.http.HttpRequest;
 import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




 @RestController
 @RequestMapping({"/pagos"})
 public class whController
 {
   
  @Autowired
   OrderService orderService;
   @Autowired
  ConfiguracionService confiService;
   
   @Value("${my.property.data}")
	String dbURL;

	@Value("${my.property.userData}")
	String userData;

	@Value("${my.property.passData}")
   String passData;
   
 
   @PostMapping({"/openpay"})   
   @ResponseBody
  protected void doPost(@RequestBody Notificacion notificacion)  {
	   System.out.println(notificacion);
       Order orderDTO = new Order();
       Long thirtyDaysFromNowUnixTimestamp= 8L * 60 * 60*1000;
	Date fecha = new Date();
	if (notificacion.getType().equals("charge.succeeded")) {
		float amount = 0;
		long Unixfecha;
		String noPedido = "", TitularCuenta = "", NoAutorizacion = "", FolioInterbancario = "", Reference = "";
		amount = notificacion.getTransaction().getAmount();
		Unixfecha = (notificacion.getTransaction().getCreation_date().getTime()-thirtyDaysFromNowUnixTimestamp)/1000;
		noPedido = notificacion.getTransaction().getDescription();
		TitularCuenta = notificacion.getTransaction().getCustomer().getName();
		NoAutorizacion = notificacion.getTransaction().getAuthorization();
		FolioInterbancario = notificacion.getTransaction().getId();
		Reference = notificacion.getTransaction().getPayment_method().getReference();;
       
		String[] fe = TimeStampDate(Unixfecha);
		Date FeCo = TimeStampDateC(Unixfecha);
		orderDTO.setNoPedido(Long.parseLong(noPedido));
		orderDTO.setMonto(amount);
		orderDTO.setNotarjeta(Reference);
		orderDTO.setFolioInterbancario(FolioInterbancario);
		orderDTO.setNoAutorizacion(NoAutorizacion);
		orderDTO.setFechaPago(FeCo);
		orderDTO.setTitularCuenta(TitularCuenta);
		orderDTO.setJson(notificacion.toString());
		orderDTO.setFechaCreacion(new Date(fecha.getTime()-thirtyDaysFromNowUnixTimestamp));
       
       
	this.sp(orderDTO.getNoPedido(), orderDTO.getMonto(), orderDTO.getNotarjeta(), orderDTO.getFolioInterbancario(), 
			orderDTO.getNoAutorizacion(), fe[0], fe[1], orderDTO.getTitularCuenta());
       
	 orderDTO.setSincronizado(true);
	 //Correo co = new Correo(t.getCorreo(), t.getPass(), correo, noPedido, t.getCopiaoculta());
	 
	 try {
		   //co.enviar_correo(orderDTO.getTitularCuenta(), Amount(orderDTO.getMonto()), orderDTO.getFolioInterbancario(), idUsuario, Membresia);
		   //logJava.info("Correo Eviado Correctamente");
		 }
		 catch (Exception e) {
		} 
       	this.orderService.save(orderDTO);
	}
}
   
   private String[] TimeStampDate(long timestampString) {
     Long timestamp = Long.valueOf(timestampString * 1000L);
     String date = (new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss")).format(new Date(timestamp.longValue()));
     String[] fechaHora = date.split("-");
     return fechaHora;
   }
   
   private Date TimeStampDateC(long timestampString) {
     Date time = new Date(timestampString * 1000L);
     return time;
   }
   
private void sp(long noPedido, double monto, String noTarjeta, String folioInterbancario, String noAutorizacion, String fechaPago, String horaPago, String titularCuenta) {
	   Connection conn = null;
       try {
           // Carga el driver de oracle
       		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
           conn = DriverManager.getConnection(dbURL, userData, passData);
           Long thirtyDaysFromNowUnixTimestamp= 8L * 60 * 60*1000;
           PreparedStatement ps=conn.prepareStatement("EXEC DataFlowAlpha.dbo.CreaReciboPagoPOL_Open_Pay ?,?,?,?,?,?,?,?,?,?,?,?,? ");
           ps.setInt(1, 1);
           ps.setDate(2, new java.sql.Date((System.currentTimeMillis()-thirtyDaysFromNowUnixTimestamp)/1000));
           ps.setDate(3, new java.sql.Date((System.currentTimeMillis()-thirtyDaysFromNowUnixTimestamp)/1000));
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
           ps.executeQuery();
           
         
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
 }