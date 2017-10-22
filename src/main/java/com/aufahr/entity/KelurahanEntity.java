package com.aufahr.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Entity
@Table(name = "kelurahan", schema = "apaptugas1")
public class KelurahanEntity {
    private long id;
    private String kodeKelurahan;
    private String namaKelurahan;
    private String kodePos;
    private Collection<KeluargaEntity> keluargasById;
    private KecamatanEntity kecamatanByIdKecamatan;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "kode_kelurahan", nullable = false, length = 10)
    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    @Basic
    @Column(name = "nama_kelurahan", nullable = false, length = 255)
    public String getNamaKelurahan() {
        return namaKelurahan;
    }

    public void setNamaKelurahan(String namaKelurahan) {
        this.namaKelurahan = namaKelurahan;
    }

    @Basic
    @Column(name = "kode_pos", nullable = false, length = 5)
    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KelurahanEntity that = (KelurahanEntity) o;

        if (id != that.id) return false;
        if (kodeKelurahan != null ? !kodeKelurahan.equals(that.kodeKelurahan) : that.kodeKelurahan != null)
            return false;
        if (namaKelurahan != null ? !namaKelurahan.equals(that.namaKelurahan) : that.namaKelurahan != null)
            return false;
        if (kodePos != null ? !kodePos.equals(that.kodePos) : that.kodePos != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (kodeKelurahan != null ? kodeKelurahan.hashCode() : 0);
        result = 31 * result + (namaKelurahan != null ? namaKelurahan.hashCode() : 0);
        result = 31 * result + (kodePos != null ? kodePos.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "kelurahanByIdKelurahan")
    public Collection<KeluargaEntity> getKeluargasById() {
        return keluargasById;
    }

    public void setKeluargasById(Collection<KeluargaEntity> keluargasById) {
        this.keluargasById = keluargasById;
    }

    @ManyToOne
    @JoinColumn(name = "id_kecamatan", referencedColumnName = "id", nullable = false)
    public KecamatanEntity getKecamatanByIdKecamatan() {
        return kecamatanByIdKecamatan;
    }

    public void setKecamatanByIdKecamatan(KecamatanEntity kecamatanByIdKecamatan) {
        this.kecamatanByIdKecamatan = kecamatanByIdKecamatan;
    }
}
