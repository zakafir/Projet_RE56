package sample.Model;

import java.util.ArrayList;

public class BSC {

    private ArrayList<BTS> bts;

    public BSC() {
        bts = new ArrayList<BTS>();
    }

    public ArrayList<BTS> getBts() {
        return bts;
    }

    public void setBts(ArrayList<BTS> bts) {
        this.bts = bts;
    }
}
