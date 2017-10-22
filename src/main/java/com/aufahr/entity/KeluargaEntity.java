package com.aufahr.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Entity
@Table(name = "keluarga", schema = "apaptugas1")
public class KeluargaEntity {
    private long id;
    private String nomorKk;
    private String alamat;
    private String rt;
    private String rw;
    private byte isTidakBerlaku;
    private KelurahanEntity kelurahanByIdKelurahan;
    private Collection<PendudukEntity> penduduksById;
    private Date created;
    private Date updated;

    public void updateFromObject(KeluargaEntity other) {
        this.nomorKk = other.nomorKk;
        this.alamat = other.alamat;
        this.rt = other.rt;
        this.rw = other.rw;
        this.isTidakBerlaku = other.isTidakBerlaku;
        this.kelurahanByIdKelurahan = other.kelurahanByIdKelurahan;
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
    @Column(name = "nomor_kk", nullable = false, length = 16)
    public String getNomorKk() {
        return nomorKk;
    }

    public void setNomorKk(String nomorKk) {
        this.nomorKk = nomorKk;
    }

    @Basic
    @Column(name = "alamat", nullable = false, length = 256)
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Basic
    @Column(name = "RT", nullable = false, length = 3)
    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    @Basic
    @Column(name = "RW", nullable = false, length = 3)
    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    @Basic
    @Column(name = "is_tidak_berlaku", nullable = false)
    public byte getIsTidakBerlaku() {
        return isTidakBerlaku;
    }

    public void setIsTidakBerlaku(byte isTidakBerlaku) {
        this.isTidakBerlaku = isTidakBerlaku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeluargaEntity that = (KeluargaEntity) o;

        if (id != that.id) return false;
        if (isTidakBerlaku != that.isTidakBerlaku) return false;
        if (nomorKk != null ? !nomorKk.equals(that.nomorKk) : that.nomorKk != null) return false;
        if (alamat != null ? !alamat.equals(that.alamat) : that.alamat != null) return false;
        if (rt != null ? !rt.equals(that.rt) : that.rt != null) return false;
        if (rw != null ? !rw.equals(that.rw) : that.rw != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nomorKk != null ? nomorKk.hashCode() : 0);
        result = 31 * result + (alamat != null ? alamat.hashCode() : 0);
        result = 31 * result + (rt != null ? rt.hashCode() : 0);
        result = 31 * result + (rw != null ? rw.hashCode() : 0);
        result = 31 * result + (int) isTidakBerlaku;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_kelurahan", referencedColumnName = "id", nullable = false)
    public KelurahanEntity getKelurahanByIdKelurahan() {
        return kelurahanByIdKelurahan;
    }

    public void setKelurahanByIdKelurahan(KelurahanEntity kelurahanByIdKelurahan) {
        this.kelurahanByIdKelurahan = kelurahanByIdKelurahan;
    }

    @OneToMany(mappedBy = "keluargaByIdKeluarga")
    public Collection<PendudukEntity> getPenduduksById() {
        return penduduksById;
    }

    public void setPenduduksById(Collection<PendudukEntity> penduduksById) {
        this.penduduksById = penduduksById;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
