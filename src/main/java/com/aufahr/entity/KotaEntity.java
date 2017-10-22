package com.aufahr.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Entity
@Table(name = "kota", schema = "apaptugas1")
public class KotaEntity {
    private long id;
    private String kodeKota;
    private String namaKota;
    private Collection<KecamatanEntity> kecamatansById;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "kode_kota", nullable = false, length = 4)
    public String getKodeKota() {
        return kodeKota;
    }

    public void setKodeKota(String kodeKota) {
        this.kodeKota = kodeKota;
    }

    @Basic
    @Column(name = "nama_kota", nullable = false, length = 255)
    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KotaEntity that = (KotaEntity) o;

        if (id != that.id) return false;
        if (kodeKota != null ? !kodeKota.equals(that.kodeKota) : that.kodeKota != null) return false;
        if (namaKota != null ? !namaKota.equals(that.namaKota) : that.namaKota != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (kodeKota != null ? kodeKota.hashCode() : 0);
        result = 31 * result + (namaKota != null ? namaKota.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "kotaByIdKota")
    public Collection<KecamatanEntity> getKecamatansById() {
        return kecamatansById;
    }

    public void setKecamatansById(Collection<KecamatanEntity> kecamatansById) {
        this.kecamatansById = kecamatansById;
    }
}
