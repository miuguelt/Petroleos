/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Central;

/**
 *
 * @author Miguel
 */
public class LockhartMartinelli {
    double d, Yw, qL, L, P1, Yg, Tprom, RGL, uL, ug;

    public LockhartMartinelli(double d, double Yw, double qL, double L, double P1, double Yg, double Tprom, double RGL, double uL, double ug) {
        this.d = d;
        this.Yw = Yw;
        this.qL = qL;
        this.L = L;
        this.P1 = P1;
        this.Yg = Yg;
        this.Tprom = Tprom;
        this.RGL = RGL;
        this.uL = uL;
        this.ug = ug;
    }
    
    public double getf()
    {       
        return (0.032/Math.pow(d,(1.0/3.0)));
    }    
    public double getDPL()
    {
        return (0.000011476*getf()*Yw*(Math.pow(qL,2))*L)/(Math.pow(d,5));
    }
    public double getPpc()
    {
        return 756.8-(131*Yg)-(3.6*Math.pow(Yg,2));
    }
    public double getPpr()
    {
        return P1/getPpc();
    }
    public double getTpc()
    {
        return 169.2-(349.5*Yg)-(74*Math.pow(Yg,2))+460;
    }
    public double getTpr()
    {
        return (Tprom+460)/getTpc();
    }
    
    public double getZprom()
    {
        double A1 = 0.31506;
        double A2 = -1.0467;
        double A3 = -0.5783;
        double A4 = 0.5353;
        double A5 = -0.6123;
        double A6 = -0.6123;
        double A7 = 0.68157;
        double A8 = 0.68446;
        double Zsup = 0.8;
        double pr = (0.27*getPpr())/(Zsup*getTpr());
        double Z = 1+((A1+(A2/getTpr())+(A3/(Math.pow(getTpr(),3))))*pr)+((A4+(A5/getTpr()))*Math.pow(pr,2))+((A5*A6*(Math.pow(pr,5)))/getTpr())+(((A7*Math.pow(pr,2))/Math.pow(getTpr(),3))*(1+(A8*Math.pow(pr,2)))*Math.exp((-1)*A8*Math.pow(pr,2)));
        
        for (int i = 0; i < 8; i++) 
        {
            Zsup = Z;
            pr = (0.27*getPpr())/(Zsup*getTpr());
            Z = 1+((A1+(A2/getTpr())+(A3/(Math.pow(getTpr(),3))))*pr)+((A4+(A5/getTpr()))*Math.pow(pr,2))+((A5*A6*(Math.pow(pr,5)))/getTpr())+(((A7*Math.pow(pr,2))/Math.pow(getTpr(),3))*(1+(A8*Math.pow(pr,2)))*Math.exp((-1)*A8*Math.pow(pr,2)));        
        }        
        return Z;
    }
    public double getqg()
    {
        return RGL*qL;
    }
    public double getP2()
    {
        return Math.pow(((Math.pow(P1,2))-((0.000000000025343*Math.pow(getqg(),2)*Yg*(Tprom+460)*L*getf()*getZprom())/(Math.pow(d,5)))),0.5);
    }
    public double getDPG()
    {
        return P1-getP2();
    }
    
    public double getNReL()
    {
        return (92.2*qL*Yw)/(d*uL);
    }
    public double getNReg()
    {
        return (0.0201056*getqg()*Yg)/(d*ug);
    }
    public String getRegimefl()
    {
        String regimen;
        if (getNReL()>2000)
        {
            regimen = "Turbulento";
        }
        else if (getNReL()<1000)
        {
            regimen = "Laminar";
        } else regimen = "Zona de Transición";
        
        return regimen;
    }
    public String getRegimefg()
    {
        String regimen;
        if (getNReg()>2000)
        {
            regimen = "Turbulento";
        }
        else if (getNReg()<1000)
        {
            regimen = "Laminar";
        } else regimen = "Zona de Transición";
        
        return regimen;
    }
    public double getOL()//Tomado de la grafica 1 D34
    {
        return 3.5;
    }
    public double getOg()//Tomado de la grafica 1 D35
    {
        return 5.4;
    }
    public double getDPTL()//Datos figura 1
    {
        return Math.pow(getOL(), 2)*getDPL();
    }
    public double getP2L()//Datos figura 1
    {
        return P1-getDPTL();
    }
    public double getDPTg()//Datos figura 1
    {
        return Math.pow(getOg(), 2)*getDPG();
    }
    public double getP2g()//Datos figura 1
    {
        return P1-getDPTg();
    }
    
    
    
    
}
