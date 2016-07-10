package model;

import java.io.Serializable;

/**
 * Created by thinh on 27/06/2016.
 */
public class DiaDiem implements Serializable {
    private int ID;
    private String Loai;
    private double KinhDo;
    private double ViDo;
    private String Ten;
    private  String VatDung;
    private  String LuuY;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public double getKinhDo() {
        return KinhDo;
    }

    public void setKinhDo(double kinhDo) {
        KinhDo = kinhDo;
    }

    public double getViDo() {
        return ViDo;
    }

    public void setViDo(double viDo) {
        ViDo = viDo;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getVatDung() {
        return VatDung;
    }

    public void setVatDung(String vatDung) {
        VatDung = vatDung;
    }

    public String getLuuY() {
        return LuuY;
    }

    public void setLuuY(String luuY) {
        LuuY = luuY;
    }
}
