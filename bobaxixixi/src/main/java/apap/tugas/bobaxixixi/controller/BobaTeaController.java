package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.Boba_TeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.model.ToppingModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.StoreService;
import apap.tugas.bobaxixixi.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Controller
public class BobaTeaController {

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("storeBobaTeaServiceImpl")
    @Autowired
    private StoreBobaTeaService storeBobaTeaService;

    @GetMapping("/boba")
    public String listBoba(Model model){
        List<Boba_TeaModel> listBoba = bobaTeaService.getBobaTeaList();
        model.addAttribute("listBoba", listBoba);
        return "viewall-boba";
    }
    @GetMapping("/boba/{idBoba}/assign-store")
    public String assignStoreToBobaForm(
            @PathVariable long idBoba,
            long[] storesId,
            Model model
    ){
        Boba_TeaModel boba = bobaTeaService.getBobaTeaById(idBoba);
        List<StoreModel> listStore = storeService.getStoreList();
        model.addAttribute("boba", boba);
        model.addAttribute("listStore", listStore);
        model.addAttribute("storesId", storesId);

        return "form-add-store-to-boba";
    }

    @PostMapping("/boba/{idBoba}/assign-store")
    public String assignStoreToBobaSubmit(
            @ModelAttribute Boba_TeaModel boba,
            @RequestParam(value="storesId", required = false) long[] storesId,
            Model model
    ){
        if (storesId != null){
            for (int i = 0; i < storesId.length; i++){
                StoreBobaTeaModel storeBobaTea = new StoreBobaTeaModel();
                storeBobaTea.setBoba_Tea(boba);
                StoreModel store = storeService.getStoreById(storesId[i]);
                storeBobaTea.setStore(store);
                String code = storeBobaTeaService.productCode(storeBobaTea);
                storeBobaTea.setProduction_code(code);
                storeBobaTeaService.addStoreBobaTea(storeBobaTea);
            }
        }
        model.addAttribute("boba", boba);
        return "add-store-to-boba";
    }

    @GetMapping("/boba/add")
    public String addBobaForm(Model model){
        List<ToppingModel> listTopping = toppingService.getToppingList();
        model.addAttribute("listTopping", listTopping);
        model.addAttribute("boba", new Boba_TeaModel());
        return "form-add-boba";
    }

    @PostMapping("/boba/add")
    public String addBobaSubmit(
            @ModelAttribute Boba_TeaModel boba,
            Model model
    ){
        bobaTeaService.addBobaTea(boba);
        model.addAttribute("boba_name", boba.getName());
        return "add-boba";
    }

    @GetMapping("boba/update/{idBoba}")
    public String updateBobaForm(
            @PathVariable long idBoba,
            Model model
    ){
        List<ToppingModel> listTopping = toppingService.getToppingList();
        Boba_TeaModel bobaTea = bobaTeaService.getBobaTeaById(idBoba);
        model.addAttribute("listTopping", listTopping);
        model.addAttribute("boba", bobaTea);

        return "form-update-boba";
    }

    @PostMapping("boba/update")
    public String updateBobaSubmit(
            @ModelAttribute Boba_TeaModel boba,
            Model model
    ){
        model.addAttribute("boba_name", boba.getName());
        bobaTeaService.updateBobaTea(boba);
        return "update-boba";
    }

    @GetMapping("boba/delete/{idBoba}")
    public String deleteBobaForm(
            @PathVariable Long idBoba,
            Model model
    ){
        Boba_TeaModel boba = bobaTeaService.getBobaTeaById(idBoba);
        List<StoreBobaTeaModel> storeBoba = boba.getStoreBobatea();
        model.addAttribute("boba_name", boba.getName());
        if (storeBoba.size() == 0){
            bobaTeaService.deleteBobaTea(boba);
            return "delete-boba";
        }
        return "error-cannot-delete-boba";
    }

    @GetMapping("/search")
    public String searchBoba(
            @RequestParam(value="bobaName", required = false) String bobaName,
            @RequestParam(value="topping", required = false) String toppingName,
            Model model
    ){
        List<Boba_TeaModel> listBoba = bobaTeaService.getBobaTeaList();
        List<ToppingModel> listTopping = toppingService.getToppingList();
        model.addAttribute("listBoba",listBoba);
        model.addAttribute("listTopping",listTopping);
        ToppingModel topping = toppingService.getToppingByName(toppingName);
        Boba_TeaModel bobaSearch = bobaTeaService.getBobaTeaByNameAndTopping(bobaName, topping);
        if (bobaSearch != null){
            List<StoreBobaTeaModel> storeBoba = bobaSearch.getStoreBobatea();
            model.addAttribute("storeBoba",storeBoba);
        }
        return "search-boba";
    }

}
