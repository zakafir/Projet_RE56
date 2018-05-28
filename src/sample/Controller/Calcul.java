package sample.Controller;

public class Calcul {

    //calcul distance
    private double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt((x2-x1)^2+(y2-y1)^2);
    }

    //calcul Friiz
    private double calculReceivingPower(double transmissionPower, double gainTransmission, double gainReceiving, double lamda, double distance){

        double fspl = 20 * Math.log(lamda / (4 * Math.PI *distance));

        return transmissionPower + gainReceiving + gainTransmission + fspl;
    }

    //wave length calcul
    private double lamda(Long frequency){

        return (3*10^8) / frequency;
    }
}
