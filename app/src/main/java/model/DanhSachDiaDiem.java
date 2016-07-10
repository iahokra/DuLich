package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinh on 27/06/2016.
 */
public class DanhSachDiaDiem implements Serializable {
    public ArrayList<DiaDiem> getDsdiadiem() {
        return dsdiadiem;
    }

    public void setDsdiadiem(ArrayList<DiaDiem> dsdiadiem) {
        this.dsdiadiem = dsdiadiem;
    }

    private ArrayList<DiaDiem> dsdiadiem;
}
