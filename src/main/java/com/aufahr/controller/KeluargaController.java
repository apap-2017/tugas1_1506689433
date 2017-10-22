package com.aufahr.controller;

import com.aufahr.entity.*;
import com.aufahr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by AufaHR on 10/21/2017.
 */

@Controller
@RequestMapping("/keluarga")
public class KeluargaController {

    @Autowired
    private KeluargaService keluargaService;
    @Autowired
    private KelurahanService kelurahanService;


    @GetMapping("")
    public String keluargaDetail(Model model, @RequestParam(value = "nkk", required = true) String nkk) {
        KeluargaEntity keluargaEntity = keluargaService.findByNomorKk(nkk);
        model.addAttribute("keluarga", keluargaEntity);
        return "content/keluarga/keluargaDetail";
    }

    @GetMapping("/tambah")
    public String keluargaCreatePage(Model model) {
        KeluargaEntity keluargaEntity = new KeluargaEntity();
        model.addAttribute("keluarga", keluargaEntity);
        List<KelurahanEntity> kelurahans = kelurahanService.getAll();
        Collections.sort(kelurahans, new Comparator<KelurahanEntity>() {
            @Override
            public int compare(KelurahanEntity o1, KelurahanEntity o2) {
                return o1.getNamaKelurahan().compareTo(o2.getNamaKelurahan());
            }
        });
        model.addAttribute("kelurahans", kelurahans);
        return "content/keluarga/keluargaCreate";
    }

    @GetMapping("/ubah/{nkk}")
    public String keluargaEditPage(Model model, @PathVariable(value = "nkk", required = true) String nkk) {
        KeluargaEntity keluargaEntity = keluargaService.findByNomorKk(nkk);
        model.addAttribute("keluarga", keluargaEntity);
        List<KelurahanEntity> kelurahans = kelurahanService.getAll();
        Collections.sort(kelurahans, new Comparator<KelurahanEntity>() {
            @Override
            public int compare(KelurahanEntity o1, KelurahanEntity o2) {
                return o1.getNamaKelurahan().compareTo(o2.getNamaKelurahan());
            }
        });
        model.addAttribute("kelurahans", kelurahans);
        return "content/keluarga/keluargaEdit";
    }


    @PostMapping("/tambah")
    public String KeluargaCreateSubmit(Model model, KeluargaEntity keluargaEntity,
                                       BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "content/keluarga/keluargaCreate";
        }
        try {
            KeluargaEntity newKeluarga = new KeluargaEntity();
            newKeluarga.updateFromObject(keluargaEntity);
            String nkk = createOrSaveKeluarga(newKeluarga, false);
            model.addAttribute("message", "Penambahan keluarga berhasil");
            model.addAttribute("detail", "Keluarga dengan NKK " + nkk + " berhasil ditambahkan");
        } catch (Exception e) {
            model.addAttribute("message", "Terjadi Kesalahan dalam menambah keluarga");
            model.addAttribute("detail", e.toString());
        } finally {
            return "content/common/defaultMessage";
        }

    }

    @PostMapping("/ubah")
    public String keluargaEditSubmit(Model model, KeluargaEntity keluargaEntity,
                                     BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "content/keluarga/keluargaEdit";
        }
        try {
            KeluargaEntity persistObject = keluargaService.get(keluargaEntity.getId());
            persistObject.updateFromObject(keluargaEntity);
            String oldNkk = persistObject.getNomorKk();
            createOrSaveKeluarga(persistObject, true);
            model.addAttribute("message", "Pengubahan data keluarga berhasil");
            model.addAttribute("detail", "Penduduk dengan NIK " + oldNkk + " dan id berhasil diubah");
        } catch (Exception e) {
            model.addAttribute("message", "Terjadi Kesalahan dalam menambah keluarga");
            model.addAttribute("detail", e.toString());
        } finally {
            return "content/common/defaultMessage";
        }
    }

    private String createOrSaveKeluarga(KeluargaEntity keluargaEntity, boolean persistNum) throws Exception {
        String nkk = generateNKK(keluargaEntity, persistNum);
        keluargaEntity.setNomorKk(nkk);
        keluargaService.add(keluargaEntity);
        return nkk;
    }

    private String generateNKK(KeluargaEntity keluargaEntity, boolean persistNum) {
        String result = "";
        KelurahanEntity kelurahanEntity = keluargaEntity.getKelurahanByIdKelurahan();
        KecamatanEntity kecamatanEntity = kelurahanEntity.getKecamatanByIdKecamatan();
        KotaEntity kotaEntity = kecamatanEntity.getKotaByIdKota();
        Date currentDate = new Date();
        //TODO ask, in seed db kecamatan code also contains kota code
//        result += kotaEntity.getKodeKota();
        result += kecamatanEntity.getKodeKecamatan();
        DateFormat df = new SimpleDateFormat("ddmmyy");
        String dateString = df.format(currentDate);
        result += dateString;
//        TODO this should be persisted?, what if kelurahan of keluarga changes(?)
        int c = 0;
        try {
            c = keluargaService.getNumberOfSameDatedAndSameLocationKeluarga(kelurahanEntity, currentDate);
        } catch (Exception e) {
            //old datas might have no creation date -> fix seed datas
        }
        int num = c+1;
        String numString = String.format("%04d", num);
        if (persistNum && keluargaEntity.getNomorKk() != null) {
            numString = keluargaEntity.getNomorKk().substring(keluargaEntity.getNomorKk().length() - 4);
        }
        result += num;
        return result;
    }
}
