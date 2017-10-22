package com.aufahr.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Entity
@Table(name = "kecamatan", schema = "apaptugas1")
public class KecamatanEntity {
    private long id;
    private String kodeKecamatan;
    private String namaKecamatan;
    private KotaEntity kotaByIdKota;
    private Collection<KelurahanEntity> kelurahansById;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "kode_kecamatan", nullable = false, length = 7)
    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    @Basic
    @Column(name = "nama_kecamatan", nullable = false, length = 255)
    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KecamatanEntity that = (KecamatanEntity) o;

        if (id != that.id) return false;
        if (kodeKecamatan != null ? !kodeKecamatan.equals(that.kodeKecamatan) : that.kodeKecamatan != null)
            return false;
        if (namaKecamatan != null ? !namaKecamatan.equals(that.namaKecamatan) : that.namaKecamatan != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (kodeKecamatan != null ? kodeKecamatan.hashCode() : 0);
        result = 31 * result + (namaKecamatan != null ? namaKecamatan.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_kota", referencedColumnName = "id", nullable = false)
    public KotaEntity getKotaByIdKota() {
        return kotaByIdKota;
    }

    public void setKotaByIdKota(KotaEntity kotaByIdKota) {
        this.kotaByIdKota = kotaByIdKota;
    }

    @OneToMany(mappedBy = "kecamatanByIdKecamatan")
    public Collection<KelurahanEntity> getKelurahansById() {
        return kelurahansById;
    }

    public void setKelurahansById(Collection<KelurahanEntity> kelurahansById) {
        this.kelurahansById = kelurahansById;
    }
}
