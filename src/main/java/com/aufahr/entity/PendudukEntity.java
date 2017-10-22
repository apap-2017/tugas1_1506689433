package com.aufahr.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Entity
@Table(name = "penduduk", schema = "apaptugas1")
public class PendudukEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String nik;
    private String nama;
    private String tempatLahir;
    private Date tanggalLahir;
    private int jenisKelamin;
    private byte isWni;
    private String agama;
    private String pekerjaan;
    private String statusPerkawinan;
    private String statusDalamKeluarga;
    private String golonganDarah;
    private byte isWafat;
    private KeluargaEntity keluargaByIdKeluarga;

    public void updateFromObject(PendudukEntity other) {
        this.nama = other.nama;
        this.tempatLahir = other.tempatLahir;
        this.tanggalLahir = other.tanggalLahir;
        this.jenisKelamin = other.jenisKelamin;
        this.isWni = other.isWni;
        this.agama = other.agama;
        this.pekerjaan = other.pekerjaan;
        this.statusPerkawinan = other.statusPerkawinan;
        this.statusDalamKeluarga = other.statusDalamKeluarga;
        this.golonganDarah = other.golonganDarah;
        this.isWafat = other.isWafat;
    }

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nik", nullable = false, length = 16)
    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    @Basic
    @Column(name = "nama", nullable = false, length = 128)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Basic
    @Column(name = "tempat_lahir", nullable = false, length = 128)
    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    @Basic
    @Column(name = "tanggal_lahir", nullable = false)
    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    @Basic
    @Column(name = "jenis_kelamin", nullable = false)
    public int getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(int jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    @Basic
    @Column(name = "is_wni", nullable = false)
    public byte getIsWni() {
        return isWni;
    }

    public void setIsWni(byte isWni) {
        this.isWni = isWni;
    }

    @Basic
    @Column(name = "agama", nullable = false, length = 64)
    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    @Basic
    @Column(name = "pekerjaan", nullable = false, length = 64)
    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    @Basic
    @Column(name = "status_perkawinan", nullable = false, length = 64)
    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    @Basic
    @Column(name = "status_dalam_keluarga", nullable = false, length = 64)
    public String getStatusDalamKeluarga() {
        return statusDalamKeluarga;
    }

    public void setStatusDalamKeluarga(String statusDalamKeluarga) {
        this.statusDalamKeluarga = statusDalamKeluarga;
    }

    @Basic
    @Column(name = "golongan_darah", nullable = false, length = 32)
    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    @Basic
    @Column(name = "is_wafat", nullable = false)
    public byte getIsWafat() {
        return isWafat;
    }

    public void setIsWafat(byte isWafat) {
        this.isWafat = isWafat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PendudukEntity that = (PendudukEntity) o;

        if (id != that.id) return false;
        if (jenisKelamin != that.jenisKelamin) return false;
        if (isWni != that.isWni) return false;
        if (isWafat != that.isWafat) return false;
        if (nik != null ? !nik.equals(that.nik) : that.nik != null) return false;
        if (nama != null ? !nama.equals(that.nama) : that.nama != null) return false;
        if (tempatLahir != null ? !tempatLahir.equals(that.tempatLahir) : that.tempatLahir != null) return false;
        if (tanggalLahir != null ? !tanggalLahir.equals(that.tanggalLahir) : that.tanggalLahir != null) return false;
        if (agama != null ? !agama.equals(that.agama) : that.agama != null) return false;
        if (pekerjaan != null ? !pekerjaan.equals(that.pekerjaan) : that.pekerjaan != null) return false;
        if (statusPerkawinan != null ? !statusPerkawinan.equals(that.statusPerkawinan) : that.statusPerkawinan != null)
            return false;
        if (statusDalamKeluarga != null ? !statusDalamKeluarga.equals(that.statusDalamKeluarga) : that.statusDalamKeluarga != null)
            return false;
        if (golonganDarah != null ? !golonganDarah.equals(that.golonganDarah) : that.golonganDarah != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nik != null ? nik.hashCode() : 0);
        result = 31 * result + (nama != null ? nama.hashCode() : 0);
        result = 31 * result + (tempatLahir != null ? tempatLahir.hashCode() : 0);
        result = 31 * result + (tanggalLahir != null ? tanggalLahir.hashCode() : 0);
        result = 31 * result + jenisKelamin;
        result = 31 * result + (int) isWni;
        result = 31 * result + (agama != null ? agama.hashCode() : 0);
        result = 31 * result + (pekerjaan != null ? pekerjaan.hashCode() : 0);
        result = 31 * result + (statusPerkawinan != null ? statusPerkawinan.hashCode() : 0);
        result = 31 * result + (statusDalamKeluarga != null ? statusDalamKeluarga.hashCode() : 0);
        result = 31 * result + (golonganDarah != null ? golonganDarah.hashCode() : 0);
        result = 31 * result + (int) isWafat;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_keluarga", referencedColumnName = "id", nullable = false)
    public KeluargaEntity getKeluargaByIdKeluarga() {
        return keluargaByIdKeluarga;
    }

    public void setKeluargaByIdKeluarga(KeluargaEntity keluargaByIdKeluarga) {
        this.keluargaByIdKeluarga = keluargaByIdKeluarga;
    }

}
