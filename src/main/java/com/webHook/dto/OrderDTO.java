package com.webHook.dto;



/*     */ import java.util.Date;
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
/*     */ public class OrderDTO
/*     */ {
/*     */   private int noPedido;
/*     */   private double monto;
/*     */   private String notarjeta;
/*     */   private String folioInterbancario;
/*     */   private String noAutorizacion;
/*     */   private int fechaPago;
/*     */   private int horaPago;
/*     */   private String titularCuenta;
/*     */   private String json;
/*     */   private Date fechaCreacion;
/*     */   private boolean sincronizado;
/*     */   
/*     */   public OrderDTO() {}
/*     */   
/*     */   public OrderDTO(int noPedido, double monto, String notarjeta, String folioInterbancario, String noAutorizacion, int fechaPago, int horaPago, String titularCuenta, String json, Date fechaCreacion, boolean sincronizado) {
/*  39 */     this.noPedido = noPedido;
/*  40 */     this.monto = monto;
/*  41 */     this.notarjeta = notarjeta;
/*  42 */     this.folioInterbancario = folioInterbancario;
/*  43 */     this.noAutorizacion = noAutorizacion;
/*  44 */     this.fechaPago = fechaPago;
/*  45 */     this.horaPago = horaPago;
/*  46 */     this.titularCuenta = titularCuenta;
/*  47 */     this.json = json;
/*  48 */     this.fechaCreacion = fechaCreacion;
/*  49 */     this.sincronizado = sincronizado;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getFechaCreacion() {
/*  54 */     return this.fechaCreacion;
/*     */   }
/*     */   
/*     */   public void setFechaCreacion(Date fechaCreacion) {
/*  58 */     this.fechaCreacion = fechaCreacion;
/*     */   }
/*     */   
/*     */   public boolean isSincronizado() {
/*  62 */     return this.sincronizado;
/*     */   }
/*     */   
/*     */   public void setSincronizado(boolean sincronizado) {
/*  66 */     this.sincronizado = sincronizado;
/*     */   }
/*     */   
/*     */   public String getJson() {
/*  70 */     return this.json;
/*     */   }
/*     */   
/*     */   public void setJson(String json) {
/*  74 */     this.json = json;
/*     */   }
/*     */   
/*     */   public int getNoPedido() {
/*  78 */     return this.noPedido;
/*     */   }
/*     */   
/*     */   public void setNoPedido(int noPedido) {
/*  82 */     this.noPedido = noPedido;
/*     */   }
/*     */   
/*     */   public double getMonto() {
/*  86 */     return this.monto;
/*     */   }
/*     */   
/*     */   public void setMonto(double monto) {
/*  90 */     this.monto = monto;
/*     */   }
/*     */   
/*     */   public String getNotarjeta() {
/*  94 */     return this.notarjeta;
/*     */   }
/*     */   
/*     */   public void setNotarjeta(String notarjeta) {
/*  98 */     this.notarjeta = notarjeta;
/*     */   }
/*     */   
/*     */   public String getFolioInterbancario() {
/* 102 */     return this.folioInterbancario;
/*     */   }
/*     */   
/*     */   public void setFolioInterbancario(String folioInterbancario) {
/* 106 */     this.folioInterbancario = folioInterbancario;
/*     */   }
/*     */   
/*     */   public String getNoAutorizacion() {
/* 110 */     return this.noAutorizacion;
/*     */   }
/*     */   
/*     */   public void setNoAutorizacion(String noAutorizacion) {
/* 114 */     this.noAutorizacion = noAutorizacion;
/*     */   }
/*     */   
/*     */   public int getFechaPago() {
/* 118 */     return this.fechaPago;
/*     */   }
/*     */   
/*     */   public void setFechaPago(int fechaPago) {
/* 122 */     this.fechaPago = fechaPago;
/*     */   }
/*     */   
/*     */   public int getHoraPago() {
/* 126 */     return this.horaPago;
/*     */   }
/*     */   
/*     */   public void setHoraPago(int horaPago) {
/* 130 */     this.horaPago = horaPago;
/*     */   }
/*     */   
/*     */   public String getTitularCuenta() {
/* 134 */     return this.titularCuenta;
/*     */   }
/*     */   
/*     */   public void setTitularCuenta(String titularCuenta) {
/* 138 */     this.titularCuenta = titularCuenta;
/*     */   }
/*     */ }


/* Location:              C:\Users\stark\Desktop\webHook-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\webHook\DTO\OrderDTO.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */