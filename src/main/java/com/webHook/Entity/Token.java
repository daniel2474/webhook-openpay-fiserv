package com.webHook.Entity;


/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "configuracion_alpha_openpay")
/*    */ public class Token
/*    */ {
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private long id;
/*    */   @Column(name = "tokenAlpha", nullable = false, length = 100)
/*    */   private String tokenAlpha;
/*    */   @Column(name = "endpointAlpha", nullable = false, length = 100)
/*    */   private String endpointAlpha;
/*    */   @Column(name = "correoAlpha", nullable = false, length = 100)
/*    */   private String correo;
/*    */   @Column(name = "passwordAlpha", nullable = false, length = 100)
/*    */   private String pass;
/*    */   @Column(name = "copiaoculta", nullable = false, length = 100)
/*    */   private String copiaoculta;
/*    */   
/*    */   Token() {}
/*    */   
/*    */   public Token(long id, String tokenAlpha, String endpointAlpha, String correo, String pass, String copiaoculta) {
/* 39 */     this.id = id;
/* 40 */     this.tokenAlpha = tokenAlpha;
/* 41 */     this.endpointAlpha = endpointAlpha;
/* 42 */     this.correo = correo;
/* 43 */     this.pass = pass;
/* 44 */     this.copiaoculta = copiaoculta;
/*    */   }
/*    */   
/*    */   public String getCorreo() {
/* 48 */     return this.correo;
/*    */   }
/*    */   
/*    */   public void setCorreo(String correo) {
/* 52 */     this.correo = correo;
/*    */   }
/*    */   
/*    */   public String getPass() {
/* 56 */     return this.pass;
/*    */   }
/*    */   
/*    */   public void setPass(String pass) {
/* 60 */     this.pass = pass;
/*    */   }
/*    */   
/*    */   public long getId() {
/* 64 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(long id) {
/* 68 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getTokenAlpha() {
/* 72 */     return this.tokenAlpha;
/*    */   }
/*    */   
/*    */   public void setTokenAlpha(String tokenAlpha) {
/* 76 */     this.tokenAlpha = tokenAlpha;
/*    */   }
/*    */   
/*    */   public String getEndpointAlpha() {
/* 80 */     return this.endpointAlpha;
/*    */   }
/*    */   
/*    */   public void setEndpointAlpha(String endpointAlpha) {
/* 84 */     this.endpointAlpha = endpointAlpha;
/*    */   }
/*    */   
/*    */   public String getCopiaoculta() {
/* 88 */     return this.copiaoculta;
/*    */   }
/*    */   
/*    */   public void setCopiaoculta(String copiaoculta) {
/* 92 */     this.copiaoculta = copiaoculta;
/*    */   }
/*    */ }


/* Location:              C:\Users\stark\Desktop\webHook-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\webHook\Entity\Token.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */