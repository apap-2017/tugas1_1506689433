package com.aufahr.controller;

import com.aufahr.common.Util.GeneralUtil;
import com.aufahr.entity.*;
import com.aufahr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Controller
@RequestMapping("/penduduk")
public class PendudukController {
    @Autowired
    private PendudukService pendudukService;
    @Autowired
    private KeluargaService keluargaService;
    @Autowired
    private KotaService kotaService;
    @Autowired
    private KecamatanService kecamatanService;
    @Autowired
    private KelurahanService kelurahanService;


    @GetMapping("")
    public String pendudukNikDetail(Model model, @RequestParam(value = "nik", required = true) String nik) {
        PendudukEntity pendudukEntity = pendudukService.findByNik(nik);
        model.addAttribute("penduduk", pendudukEntity);
        return "content/penduduk/pendudukDetail";
    }

    @GetMapping("/cari")
    public String searchWithSteps(Model model, @RequestParam(value = "kt", required = false) String kt,
                                  @RequestParam(value = "kc", required = false) String kc,
                                  @RequestParam(value = "kl", required = false) String kl) {

        try {
            if (kt == null) {
                List<KotaEntity> kotas = kotaService.getAll();
                kotas.sort(new Comparator<KotaEntity>() {
                    @Override
                    public int compare(KotaEntity o1, KotaEntity o2) {
                        return o1.getNamaKota().compareTo(o2.getNamaKota());
                    }
                });
                model.addAttribute("kotas", kotas);
            } else if (kc == null) {
                KotaEntity selectedKota = kotaService.get(Long.parseLong(kt));
                List<KecamatanEntity> kecamatans = new ArrayList<>(selectedKota.getKecamatansById());
                kecamatans.sort(new Comparator<KecamatanEntity>() {
                    @Override
                    public int compare(KecamatanEntity o1, KecamatanEntity o2) {
                        return o1.getNamaKecamatan().compareTo(o2.getNamaKecamatan());
                    }
                });
                model.addAttribute("selectedKota", selectedKota);
                model.addAttribute("kecamatans", kecamatans);
            } else if (kl == null) {
                KotaEntity selectedKota = kotaService.get(Long.parseLong(kt));
                KecamatanEntity kecamatanEntity = kecamatanService.get(Long.parseLong(kc));
                List<KelurahanEntity> kelurahans = new ArrayList<>(kecamatanEntity.getKelurahansById());
                kelurahans.sort(new Comparator<KelurahanEntity>() {
                    @Override
                    public int compare(KelurahanEntity o1, KelurahanEntity o2) {
                        return o1.getNamaKelurahan().compareTo(o2.getNamaKelurahan());
                    }
                });
                model.addAttribute("selectedKota", selectedKota);
                model.addAttribute("selectedKecamatan", kecamatanEntity);
                model.addAttribute("kelurahans", kelurahans);
            } else {
                KelurahanEntity kelurahanEntity = kelurahanService.get(Long.parseLong(kl));
                List<KeluargaEntity> keluargaEntities = new ArrayList<>(kelurahanEntity.getKeluargasById());
                List<PendudukEntity> pendudukEntities = new ArrayList<>();
                for (KeluargaEntity ke : keluargaEntities) {
                    pendudukEntities.addAll(ke.getPenduduksById());
                }
                pendudukEntities.sort(new Comparator<PendudukEntity>() {
                    @Override
                    public int compare(PendudukEntity o1, PendudukEntity o2) {
                        return o1.getNama().compareTo(o2.getNama());
                    }
                });
                model.addAttribute("penduduks", pendudukEntities);
            }
        } catch (Exception e) {

        }
        return "content/penduduk/pendudukSearch";


    }

    @GetMapping("/tambah")
    public String pendudukCreatePage(Model model) {
        PendudukEntity pendudukEntity = new PendudukEntity();
        model.addAttribute("penduduk", pendudukEntity);
        return "content/penduduk/pendudukCreate";
    }

    @GetMapping("/ubah/{nik}")
    public String pendudukEditPage(Model model, @PathVariable(value = "nik", required = true) String nik) {
        PendudukEntity pendudukEntity = pendudukService.findByNik(nik);
        if (pendudukEntity == null) {
            model.addAttribute("message", "Penduduk tidak ditemukan");
            model.addAttribute("detail", "Penduduk dengan NIK " + nik + " tidak ditemukan");
            return "content/common/defaultMessage";
        }
        model.addAttribute("penduduk", pendudukEntity);
        return "content/penduduk/pendudukEdit";
    }


    @PostMapping("/tambah")
    public String pendudukCreateSubmit(@RequestParam(value = "nomorKK", required = true) String nomorKK, Model model, PendudukEntity pendudukEntity,
                                       BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "content/penduduk/pendudukCreate";
        }
        try {
            PendudukEntity newPenduduk = new PendudukEntity();
            newPenduduk.updateFromObject(pendudukEntity);
            String nik = createOrSavePenduduk(nomorKK, newPenduduk, false);
            model.addAttribute("message", "Penambahan penduduk berhasil");
            model.addAttribute("detail", "Penduduk dengan NIK " + nik + " berhasil ditambahkan");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Terjadi Kesalahan dalam menambah Penduduk");
            model.addAttribute("detail", e.toString());
        }
        return "content/common/defaultMessage";
    }


    @PostMapping("/ubah")
    public String pendudukEditSubmit(@RequestParam(value = "nomorKK", required = true) String nomorKK, Model model, PendudukEntity pendudukEntity,
                                     BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "content/penduduk/pendudukUbah";
        }
        try {
            PendudukEntity persistObject = pendudukService.get(pendudukEntity.getId());
            persistObject.updateFromObject(pendudukEntity);
            String oldNik = persistObject.getNik();
            String nik = createOrSavePenduduk(nomorKK, persistObject, true);
            model.addAttribute("message", "Pengubahan penduduk berhasil");
            model.addAttribute("detail", "Penduduk dengan NIK " + oldNik + " dan id berhasil diubah");
        } catch (Exception e) {
            model.addAttribute("message", "Terjadi Kesalahan dalam menambah Penduduk");
            model.addAttribute("detail", e.toString());
        }
        return "content/common/defaultMessage";
    }

    @PostMapping("/mati")
    public String pendudukSetAsDead(Model model, @RequestParam(value = "nik", required = true) String nik) {
        try {
            PendudukEntity pendudukEntity = pendudukService.findByNik(nik);
            if (pendudukEntity == null) {
                model.addAttribute("message", "Penduduk tidak ditemukan");
                model.addAttribute("detail", "Penduduk dengan nik " + nik + "tidak ditemukan");
            } else {
                byte dead = 1;
                pendudukEntity.setIsWafat(dead);
                pendudukService.update(pendudukEntity);
                model.addAttribute("message", "Penduduk berhasil di set sebabgai wafat");
                model.addAttribute("detail", "Penduduk dengan nik " + nik + " berhasil di set sebabgai wafat");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Terjadi Kesalahan dalam menambah Penduduk");
            model.addAttribute("detail", e.toString());
        }
        return "content/common/defaultMessage";
    }


    private String createOrSavePenduduk(String nomorKK, PendudukEntity pendudukEntity, boolean persistNum) throws Exception {
        KeluargaEntity keluargaEntity = keluargaService.findByNomorKk(nomorKK);
        if (keluargaEntity == null) {
            throw new Exception("Keluarga Entity with " + nomorKK + " is not found or null");
        }
        pendudukEntity.setKeluargaByIdKeluarga(keluargaEntity);
        String nik = generateNIK(pendudukEntity, persistNum);
        pendudukEntity.setNik(nik);
        this.pendudukService.add(pendudukEntity);
        return nik;
    }

    private String generateNIK(PendudukEntity pendudukEntity, boolean persistNum) {
        String result = "";
        KelurahanEntity kelurahanEntity = pendudukEntity.getKeluargaByIdKeluarga().getKelurahanByIdKelurahan();
        KecamatanEntity kecamatanEntity = kelurahanEntity.getKecamatanByIdKecamatan();
        String kodeKota = kecamatanEntity.getKotaByIdKota().getKodeKota();
        result += kodeKota;
        DateFormat df = new SimpleDateFormat("ddmmyy");
        Date date = (Date) pendudukEntity.getTanggalLahir().clone();
        if (pendudukEntity.getJenisKelamin() == 1) {
            date = GeneralUtil.addDays(date, 40);
        }
        String dateString = df.format(date);
        result += dateString;

        Long num = 0L;
        if (pendudukService.getMaxId() != null) {
            num = pendudukService.getMaxId();
        }
        num = num++;
        if (persistNum) {
            num = pendudukEntity.getId();
        }
        String numString = String.format("%04d", num);
        result += numString;
        return result;
    }


}
