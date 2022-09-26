package com.webHook.Entity;


/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "order_alpha_openpay")
/*     */ public class Order
/*     */ {
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   private int id;
/*     */   @Column(name = "noPedido", nullable = false, length = 20)
/*     */   private long noPedido;
/*     */   @Column(name = "monto", nullable = false, length = 10)
/*     */   private double monto;
/*     */   @Column(name = "notarjeta", nullable = false, length = 50)
/*     */   private String notarjeta;
/*     */   @Column(name = "folioInterbancario", nullable = false, length = 50)
/*     */   private String folioInterbancario;
/*     */   @Column(name = "noAutorizacion", nullable = false, length = 50)
/*     */   private String noAutorizacion;
/*     */   @Column(name = "fechaPago", nullable = false)
/*     */   private Date fechaPago;
/*     */   @Column(name = "titularCuenta", nullable = false, length = 50)
/*     */   private String titularCuenta;
/*     */   @Column(name = "json", nullable = false, length = 50000)
/*     */   private String json;
/*     */   @Column(name = "fechaCreacion", nullable = false)
/*     */   private Date fechaCreacion;
/*     */   @Column(name = "sincronizado", nullable = false)
/*     */   private boolean sincronizado;
/*     */   
/*     */   public Order() {}
/*     */   
/*     */   public Order(int noPedido, double monto, String notarjeta, String folioInterbancario, String noAutorizacion, Date fechaPago, String titularCuenta, String json, Date fechaCreacion, boolean sincronizado) {
/*  61 */     this.noPedido = noPedido;
/*  62 */     this.monto = monto;
/*  63 */     this.notarjeta = notarjeta;
/*  64 */     this.folioInterbancario = folioInterbancario;
/*  65 */     this.noAutorizacion = noAutorizacion;
/*  66 */     this.fechaPago = fechaPago;
/*     */     
/*  68 */     this.titularCuenta = titularCuenta;
/*  69 */     this.json = json;
/*  70 */     this.fechaCreacion = fechaCreacion;
/*  71 */     this.sincronizado = sincronizado;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  75 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  79 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Date getFechaCreacion() {
/*  83 */     return this.fechaCreacion;
/*     */   }
/*     */   
/*     */   public void setFechaCreacion(Date fechaCreacion) {
/*  87 */     this.fechaCreacion = fechaCreacion;
/*     */   }
/*     */   
/*     */   public boolean isSincronizado() {
/*  91 */     return this.sincronizado;
/*     */   }
/*     */   
/*     */   public void setSincronizado(boolean sincronizado) {
/*  95 */     this.sincronizado = sincronizado;
/*     */   }
/*     */   
/*     */   public String getJson() {
/*  99 */     return this.json;
/*     */   }
/*     */   
/*     */   public void setJson(String json) {
/* 103 */     this.json = json;
/*     */   }
/*     */   
/*     */   public long getNoPedido() {
/* 107 */     return this.noPedido;
/*     */   }
/*     */   
/*     */   public void setNoPedido(long noPedido) {
/* 111 */     this.noPedido = noPedido;
/*     */   }
/*     */   
/*     */   public double getMonto() {
/* 115 */     return this.monto;
/*     */   }
/*     */   
/*     */   public void setMonto(double monto) {
/* 119 */     this.monto = monto;
/*     */   }
/*     */   
/*     */   public String getNotarjeta() {
/* 123 */     return this.notarjeta;
/*     */   }
/*     */   
/*     */   public void setNotarjeta(String notarjeta) {
/* 127 */     this.notarjeta = notarjeta;
/*     */   }
/*     */   
/*     */   public String getFolioInterbancario() {
/* 131 */     return this.folioInterbancario;
/*     */   }
/*     */   
/*     */   public void setFolioInterbancario(String folioInterbancario) {
/* 135 */     this.folioInterbancario = folioInterbancario;
/*     */   }
/*     */   
/*     */   public String getNoAutorizacion() {
/* 139 */     return this.noAutorizacion;
/*     */   }
/*     */   
/*     */   public void setNoAutorizacion(String noAutorizacion) {
/* 143 */     this.noAutorizacion = noAutorizacion;
/*     */   }
/*     */   
/*     */   public Date getFechaPago() {
/* 147 */     return this.fechaPago;
/*     */   }
/*     */   
/*     */   public void setFechaPago(Date fechaPago) {
/* 151 */     this.fechaPago = fechaPago;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitularCuenta() {
/* 163 */     return this.titularCuenta;
/*     */   }
/*     */   
/*     */   public void setTitularCuenta(String titularCuenta) {
/* 167 */     this.titularCuenta = titularCuenta;
/*     */   }
/*     */ }


/* Location:              C:\Users\stark\Desktop\webHook-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\webHook\Entity\Order.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */