/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Miguel
 */
public class BeggsBrill {

    double d = 4.0; //diametro
    double L; //Longitud
    double P1 = 500; //presion corriente arriba
    double Dp = 100; //caida de presión supuesta
    double Tprom = 120; //Temperatura promedio
    double R = 1000; //Relacion gas-aceite producido
    double Yg = 0.65; //Gravedad especifica del gas
    double YAPI = 42; //Gravedad del aceite
    double qL = 2000; //Caudal de liquido
    double qw = 0; //Caudal de agua
    double Bw = 1; //Factor volumetrico de formacion del agua
    double WOR = 0; //Relacion agua-aceite
    double Yw = 1.07; //Gravedad especifica del agua
    char patron = 'i'; //Patron de flujo
    double ug = 0.02; //Viscosidad del gas
    double uo = 1; //viscosidad del aceite
    double uw = 1; //Viscosidad del agua
    //COLGAMIENTO HORIZONTAL
    double sa = 0.98;
    double ia = 0.845;
    double da = 1.065;
    double sb = 0.4846;
    double ib = 0.5351;
    double db = 0.5824;
    double sc = 0.0458;
    double ic = 0.0173;
    double dc = 0.0609;

    public BeggsBrill(double L) {
        this.L = L;
    }

    public double getLentrada()
    {
        return L;
    }
    
    public double getP1() {  //Presion corriente arriba  Presiónd entrada
        return P1;
    }
    
    public double getPprom() //Presion promedio 
    {
        return P1 - (Dp / 2);
    }

    public double getPpc() {
        return 756.8 - (131 * Yg) - (3.6 * Math.pow(Yg, 2));
    }

    public double getPpr() {
        return P1 / getPpc();
    }

    public double getTpc() {
        return 169.2 - (349.5 * Yg) - (74 * Math.pow(Yg, 2)) + 460;
    }

    public double getTpr() {
        return (Tprom + 460) / getTpc();
    }

    public double getqo() {
        return qL - qw;
    }

    public double getZprom() {
        double A1 = 0.31506;
        double A2 = -1.0467;
        double A3 = -0.5783;
        double A4 = 0.5353;
        double A5 = -0.6123;
        double A6 = -0.6123;
        double A7 = 0.68157;
        double A8 = 0.68446;
        double Zsup = 0.8;
        double pr = (0.27 * getPpr()) / (Zsup * getTpr());
        double Z = 1 + ((A1 + (A2 / getTpr()) + (A3 / (Math.pow(getTpr(), 3)))) * pr) + ((A4 + (A5 / getTpr())) * Math.pow(pr, 2)) + ((A5 * A6 * (Math.pow(pr, 5))) / getTpr()) + (((A7 * Math.pow(pr, 2)) / Math.pow(getTpr(), 3)) * (1 + (A8 * Math.pow(pr, 2))) * Math.exp((-1) * A8 * Math.pow(pr, 2)));

        for (int i = 0; i < 8; i++) {
            Zsup = Z;
            pr = (0.27 * getPpr()) / (Zsup * getTpr());
            Z = 1 + ((A1 + (A2 / getTpr()) + (A3 / (Math.pow(getTpr(), 3)))) * pr) + ((A4 + (A5 / getTpr())) * Math.pow(pr, 2)) + ((A5 * A6 * (Math.pow(pr, 5))) / getTpr()) + (((A7 * Math.pow(pr, 2)) / Math.pow(getTpr(), 3)) * (1 + (A8 * Math.pow(pr, 2))) * Math.exp((-1) * A8 * Math.pow(pr, 2)));
        }
        return Z;
    }

    public double getRsprom() {
        return Yg * (Math.pow((((getPprom() / 18.2) + 1.4) * (Math.pow(10, ((0.0125 * YAPI) - (0.00091 * Tprom))))), 1.2048));
    }

    public double getqg() //Caudal de gas a condiciones de escurrimiento
    {
        return (0.000000327 * getZprom() * getqo() * (R - getRsprom()) * (Tprom + 460)) / (getPprom() + 14.7);
    }

    public double getAp() //Area transversal de la tuberia
    {
        return (Math.PI * Math.pow(d, 2)) / 4.0;
    }

    public double getYo() {
        return 141.5 / (YAPI + 131.5);
    }

    public double getF() {
        return (getRsprom() * Math.pow((Yg / getYo()), 0.5)) + (1.25 * Tprom);
    }

    public double getBoprom() //Factor volumetrico de formacion promedio del aceite 
    {
        return 0.9759 + 0.00012 * Math.pow(getF(), 1.2);
    }

    public double getqw() //Caudal de agua
    {
        return (WOR * qL) / (1 + WOR);
    }

    public double getqL() //Caudal de liquido a condiciones de escurrimiento
    {
        return 0.0000649 * ((getqo() * getBoprom()) + (getqw() * Bw));
    }

    public double getVsL() //Velocidad superficial del liquido
    {
        return (144 * getqL()) / getAp();
    }

    public double getVm() //Velocidad de la mezcla
    {
        return getVsg() + getVsL();
    }

    public double getVsg() //Velocidad superficial del gas
    {
        return (144.0 * getqg()) / getAp();
    }

    public double getpgprom() //Densidad promedio del gas
    {
        return (0.0764 * Yg * (getPprom() + 14.7) * 520) / (14.7 * (Tprom + 460) * getZprom());
    }

    public double getGg() //Flujo masico de gas por unidad de area
    {
        return getpgprom() * getVsg();
    }

    public double getpoprom() //Densidad promedio del aceite
    {
        return ((350 * getYo()) + (0.0764 * getRsprom() * Yg)) / (5.6146 * getBoprom());
    }

    public double getpwprom() //Densidad promedio del agua
    {
        return (350 * Yw) / (5.615 * Bw);
    }

    public double getpLprom() //Densidad promedio del liquido
    {
        return (getpoprom() / (1 + WOR)) + ((getpwprom() * WOR) / (1 + WOR));
    }

    public double getGL() //Flujo masico de liquido por unidad de area
    {
        return getpLprom() * getVsL();
    }

    public double getGT() //Flujo masico de la mezcla por unidad de area
    {
        return getGg() + getGL();
    }

    public double getL() //Colgamiento de liquido sin resbalamiento    equival a Landa
    {
        return getqL() / (getqL() + getqg());
    }

    public double getNFR() //Numero de Froude
    {
        return (Math.pow(getVm(), 2)) / (32.2 * (d / 12));
    }

    public double getHLO() //Colgamiento horizontal de liquido
    {
        if (patron == 's') //Segregado
        {
            return (sa * Math.pow(getL(), sb)) / Math.pow(getNFR(), sc);
        }
        if (patron == 'i') //Intermitente
        {
            return (ia * Math.pow(getL(), ib)) / Math.pow(getNFR(), ic);
        }
        if (patron == 'd') //Distribuido
        {
            return (da * Math.pow(getL(), db)) / Math.pow(getNFR(), dc);
        }
        if (patron == 't') //transicion
        {
            return (((sa * Math.pow(getL(), sb)) / Math.pow(getNFR(), sc)) + ((ia * Math.pow(getL(), ib)) / Math.pow(getNFR(), ic)) / 2);
        }
        return 0;
    }

    public double getpm() //Densidad de la mezcla
    {
        return (getpLprom() * getHLO()) + (getpgprom() * (1 - getHLO()));
    }

    public double getuL() //Viscosidad del liquido
    {
        return (uo / (1 + WOR)) + ((uw * WOR) / (1 + WOR));
    }

    public double getum() //Viscosidad de la mezcla
    {           
        return ((getuL() * getL()) + (ug * (1 - getL())));
    }

    public double getNRe() //Numero de Reynolds sin resbalamiento
    {          //((getGT() * (d / 12.0)) / (getum() * 0.000672))
        double n=getGT() * (d / 12.0);
        double d= getum() * 0.000672; 
        return  n/d;
    }

    public double getfns() //Factor de friccion sin resbalamiento
    {
        return 1.0 / (Math.pow((2 * Math.log10(getNRe() / (4.5223 * Math.log10(getNRe()) - 3.8215))), 2));
    }

    public double gety() //Parametro y
    {
        return getL() / (Math.pow(getHLO(), 2));
    }

    public double gets() //Parametro s
    {
        if ((1 < gety()) && (gety() < 1.2)) {
            return Math.log((2.2 * gety()) - 1.2);
        } else {
            return Math.log(gety()) / (-0.0523 + (3.182 * Math.log(gety())) - (0.8725 * Math.pow(Math.log(gety()), 2)) + (0.01853 * Math.pow(Math.log(gety()), 4)));
        }
    }

    public double getftfns() //Relacion del factor de friccion de la mezcla respecto al factor de friccion sin resbalamiento
    {
        return Math.exp(gets());
    }

    public double getfT() //Factor de friccion de la mezcla
    {
        return getfns() * getftfns();
    }

    public double getDp() //Caida de presion total
    {
        return (L * ((getfT() * getGT() * getVm()) / (2 * 32.2 * d * 12))) / (1 - ((getpm() * getVm() * getVsg()) / (32.2 * (getPprom() + 14.7) * 144)));
    }
}