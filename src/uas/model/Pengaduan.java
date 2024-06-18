/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.model;

import java.util.Date;

/**
 *
 * @author DANIEL
 */
public class Pengaduan {
    private String id;
    private String namaPelapor;
    private String jabatan;
    private String lokasiKerusakan;
    private byte[] foto;
    private String deskripsi;
    private String status;
    private Date tglLapor;
    private String ketPetugas;
    
    public Pengaduan(){
        
    }
    
    public Pengaduan(String id, String namaPelapor, String jabatan, String lokasiKerusakan, byte[] foto, String deskripsi, Date tglLapor, String status, String ketPetugas) {
        this.id = id;
        this.namaPelapor = namaPelapor;
        this.jabatan = jabatan;
        this.lokasiKerusakan = lokasiKerusakan;
        this.foto = foto;
        this.deskripsi = deskripsi;
        this.tglLapor = tglLapor;
        this.status = status;
        this.ketPetugas = ketPetugas;
    }
    
    public String getID(){
        return id;
    }
    
    public void setID(String id){
        this.id = id;
    }
    
    public String getNamaPelapor(){
        return namaPelapor;
    }
    
    public void setNamaPelapor(String namaPelapor){
        this.namaPelapor = namaPelapor;
    }
    
    public String getJabatan(){
        return jabatan;
    }
    
    public void setJabatan(String jabatan){
        this.jabatan = jabatan;
    }
    
    public String getLokasiKerusakan(){
        return lokasiKerusakan;
    }
    
    public void setLokasiKerusakan(String lokasiKerusakan){
        this.lokasiKerusakan = lokasiKerusakan;
    }
    
    public byte[] getFoto(){
        return foto;
    }
    
    public void setFoto(byte[] foto){
        this.foto = foto;
    }
    
    public String getDeskripsi(){
        return deskripsi;
    }
    
    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public Date getTglLapor(){
        return tglLapor;
    }
    
    public void setTglLapor(Date tglLapor){
        this.tglLapor = tglLapor;
    }
    
    public String getCatatanPetugas(){
        return ketPetugas;
    }
    
    public void setCatatanPetugas(String ketPetugas){
        this.ketPetugas = ketPetugas;
    }
}
