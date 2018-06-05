package sample.Controller;

import sample.Model.BTS;

public final class Calcul {

    public Calcul() {

    }

    //calcul distance
    public static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(
                Math.pow(Math.abs(x2-x1),2)+
                        Math.pow(Math.abs(y2-y1),2));
    }

    //calcul Friiz
    public static double calculReceivingPower(BTS bts, double gainReceiving, double lamda, double distance){

        double fspl = 20 * Math.log(lamda / (4 * Math.PI *distance));

        return bts.getPowerTransmitting() + gainReceiving + bts.getGainTransmitting() + fspl;
    }

    //wave length calcul
    public static double lamda(Long frequency){

        return (Math.pow(10,8) * 3) / frequency;
    }
}
