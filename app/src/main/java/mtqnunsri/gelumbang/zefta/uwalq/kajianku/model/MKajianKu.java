package mtqnunsri.gelumbang.zefta.uwalq.kajianku.model;

import io.realm.RealmObject;

/**
 * Created by root on 04/07/17.
 */

public class MKajianKu extends RealmObject {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJdl_kajian() {
        return jdl_kajian;
    }

    public void setJdl_kajian(String jdl_kajian) {
        this.jdl_kajian = jdl_kajian;
    }

    public String getPengisi() {
        return pengisi;
    }

    public void setPengisi(String pengisi) {
        this.pengisi = pengisi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private  int id;
    private String jdl_kajian;
    private String pengisi;
    private String alamat;
    private String tempat;
    private String latitude;
    private String longitude;
    private String tanggal;
    private String waktu;
    private String imageUrl;
}
