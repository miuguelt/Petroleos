/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class PoettmannCarpenter {
    double Dtuberia, T1, T2, P1, qo, qw, Daceite, RGL, Yg, Yw;
    double Rs, Bo, F, Zsup;
    double Ggas, WOR;
    public PoettmannCarpenter(double T1, double P1, double Daceite, double Ggas, double Zsup, double RGL, double qw, double qo, double Yw, double Yg, double Dtuberia) {
        this.T1 = T1;
        this.P1 = P1;
        this.Daceite = Daceite;
        this.Ggas = Ggas;
        this.Zsup = Zsup;
        this.RGL = RGL;
        this.qw = qw;
        this.qo = qo;
        this.Yw = Yw;
        this.Yg = Yg;
        this.Dtuberia = Dtuberia;
    }
    
     public double getDtuberia() {
        return Dtuberia;
    }

    public void setDtuberia(int Dtuberia) {
        this.Dtuberia = Dtuberia;
    }
    
    public double getT1(){
        return T1;
    }
    
    public double getP1(){
        return P1;
    }
    
    public double getRs(){
        
        Rs =  Ggas*Math.pow(((P1/18.2)+1.4)*Math.pow(10,(0.0125*Daceite-0.00091*T1)),1.2048);
        return Rs;
    }
    
    public double getBo()
    {        
        Bo = 0.972+(0.000147*Math.pow(getF(),1.175));  //Esta ecuacion se debo corregir el valor 199.704297 se puede calcular
        return Bo;
    }
    
    public double getYo()
    { //Cálculo de la gravedad específica del aceite,ϒo          
        return 141.5/(Daceite+131.5);
    }
    
    public double getF(){
        
        F = getRs()*Math.pow((Ggas/getYo()),0.5)+(1.25*T1);
        return F;
    }
    
    public double getWOR(){
        WOR = qw/qo;
        return WOR;
    }
    
    public double getTpc(){
        double Tpc = 238+(210*Ggas);
        return Tpc;
    }
    
    public double getR1(){
        double Rt1 = T1+460;
        return Rt1;
    }
    
    public double getTpr(){
        double Tpr = getR1()/getTpc();
        return Tpr;
    }
    
    public double getpsiap1(){
        double Psia = P1+14.7;
        return Psia;
    }
    
    public double getPpc(){
        double Ppc = 740-(100*Ggas);
        return Ppc;
    }
    
    public double getPpr(){
        double Ppr = getpsiap1()/getPpc();
        
        return Ppr;
    }
   
    public double getZ()
    {
        double H83 = 0.31506;
        double H84 = -1.0467;
        double H85 = -0.5783;
        double H86 = 0.5353;
        double J83 = -0.6123;
        double J84 = -0.6123;
        double J85 = 0.68157;
        double J86 = 0.68446;
        double z = 0;
      
        double contZsup = Zsup;
        double Dr;
        double Zdiferencia;          
        do
        {
            Dr = (0.27*getPpr())/(contZsup*getTpr());
            z =(1+(H83+(H84/getTpr())+(H85/Math.pow(getTpr(),3)))*Dr+(H86+(J83/getTpr()))*Math.pow(Dr,2)+((J83*J84*Math.pow(Dr,5))/getTpr())+((J85*Math.pow(Dr,2))/Math.pow(getTpr(),3))*(1+J86*Math.pow(Dr,2))*(Math.exp(-J86*Math.pow(Dr,2))));
            Zdiferencia = contZsup-z;           
            contZsup = contZsup - 0.0005;
        } while(Zdiferencia>0.0001);
        
        return z;
    }
    
    public double getVt()
    {
        return ((5.61*getBo())+(5.61*getWOR())+((RGL-getRs())*(14.7/getpsiap1())*(getR1()/520)*(getZ())));
    }
    public double getM()
    {
        return (350*getYo())+(0.0764*Yg*RGL)+(350*Yw*getWOR());
    }
    public double getpm()
    {
        return getM()/getVt();
    }
    public double getpm2(double M, double Vt)
    {
        return M/Vt;
    }
    public double getprom(double M, double Vt)//M y Vt son de la P2 segundo modelo
    {
        return (getpm()+getpm2(M, Vt))/2;
    }
    
    public double getFgrafica()//Figura 2 L7
    {
        return 0.008;
    }
     public double getWm()
    {
        return qo*getM();
    }
     
    public double getdpdh() //C132
    {
        return 0.25;//Como leer este dato de la gráfica
    }
    public double getdpdh2() //C145
    {
        return 0.32;//Como leer este dato de la gráfica
    }
    
    public double getdpdhT(double M, double Vt)
    {
        double variable = 7.413E10;                
        double p1 =  getprom(M,Vt);
        double p2 = (getFgrafica()*Math.pow(getWm(),2));
        double p3 = (variable*getprom(M, Vt)*Math.pow((Dtuberia/12),5));
        double resultado = (p1+(p2/p3))/144;        
        return resultado;
    }
    
    public double getDh(double M, double Vt, double P2)
    {
        return (P2-P1)/getdpdhT(M,Vt);        
    }
    
}
