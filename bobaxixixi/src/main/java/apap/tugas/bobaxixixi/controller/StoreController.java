package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.Boba_TeaModel;
import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.ManagerService;
import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
public class StoreController {

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("storeBobaTeaServiceImpl")
    @Autowired
    private StoreBobaTeaService storeBobaTeaService;


    @GetMapping("/store")
    public String listStore(Model model) {
        List<StoreModel> listStore = storeService.getStoreList();
        model.addAttribute("listStore", listStore);
        return "viewall-store";
    }

    @GetMapping("/store/add")
    public String addStoreForm(Model model) {
        List<ManagerModel> listManager = managerService.getManagerList();
        model.addAttribute("listManager", listManager);
        model.addAttribute("store", new StoreModel());
        return "form-add-store";
    }
    @PostMapping("/store/add")
    public String addStoreSubmit(
            @ModelAttribute StoreModel store,
            Model model
    ){
        String code = storeService.generateCode(store);
        store.setStore_code(code);
        storeService.addStore(store);
        model.addAttribute("store_code", store.getStore_code());
        model.addAttribute("store_name", store.getName());
        return "add-store";
    }

    @GetMapping("/store/{idStore}")
    public String viewDetailStore(
            @PathVariable long idStore,
            Model model
    ){
        StoreModel store = storeService.getStoreById(idStore);
        ManagerModel manager = store.getManager();
        //Boba nanti
        List<StoreBobaTeaModel> storeBoba = store.getStoreBobatea();
        model.addAttribute("storeBoba", storeBoba);
        model.addAttribute("store", store);
        model.addAttribute("manager", manager);
        return "view-store";
    }

    @GetMapping("/store/{idStore}/assign-boba")
    public String assignBobaToStoreForm(
            @PathVariable long idStore,
            long[] bobas,
            Model model
    ){
        StoreModel store = storeService.getStoreById(idStore);
        List<Boba_TeaModel> listBoba = bobaTeaService.getBobaTeaList();
        model.addAttribute("store", store);
        model.addAttribute("listBoba", listBoba);
        model.addAttribute("bobas", bobas);
        return "form-add-boba-to-store";
    }

    @PostMapping("/store/{idStore}/assign-boba")
    public String assignBobaToStoreSubmit(
            @ModelAttribute StoreModel store,
            @RequestParam(value = "bobas" , required = false) long[] bobas,
            Model model
    ){
        model.addAttribute("store", store);
        if (bobas != null){
            for (int i = 0; i < bobas.length; i++){
                StoreBobaTeaModel storeBobaTea = new StoreBobaTeaModel();
                Boba_TeaModel boba = bobaTeaService.getBobaTeaById(bobas[i]);
                storeBobaTea.setStore(store);
                storeBobaTea.setBoba_Tea(boba);
                String code = storeBobaTeaService.productCode(storeBobaTea);
                storeBobaTea.setProduction_code(code);
                storeBobaTeaService.addStoreBobaTea(storeBobaTea);
            }
        }
        return "add-boba-to-store";
    }

    @GetMapping("store/update/{idStore}")
    public String updateStoreForm(
            @PathVariable long idStore,
            Model model
    ){
        StoreModel store = storeService.getStoreById(idStore);
        List<ManagerModel> listManager = managerService.getManagerList();
        model.addAttribute("listManager", listManager);
        model.addAttribute("store", store);
        return "form-update-store";
    }

    @PostMapping("store/update")
    public String updateStoreSubmit(
            @ModelAttribute StoreModel store,
            Model model
    ){
        String code = storeService.generateCode(store);
        store.setStore_code(code);
        LocalTime now = LocalTime.now();
        if (now.isBefore(store.getOpen_hour()) || now.isAfter(store.getClose_hour())){
            storeService.updateStore(store);
            model.addAttribute("store_code", store.getStore_code());
            model.addAttribute("store_name", store.getName());
            return "update-store";
        }
        model.addAttribute("store_name", store.getName());
        return "error-cannot-update";
    }

    @GetMapping("store/delete/{idStore}")
    public String deleteStoreForm(
            @PathVariable long idStore,
            Model model
    ){
        StoreModel store = storeService.getStoreById(idStore);
        List<StoreBobaTeaModel> storeBoba = store.getStoreBobatea();
        model.addAttribute("store", store);
        if (storeBoba.size() == 0){
            storeService.deleteStore(store);
            return "delete-store";
        }
        return "error-cannot-delete-store";
    }

    @GetMapping("filter/manager")
    public String filterForm(
            @RequestParam(value = "nameBoba" , required = false) String nameBoba,
            Model model){
        List<Boba_TeaModel> listBoba = bobaTeaService.getBobaTeaList();
        model.addAttribute("listBoba", listBoba);
        Boba_TeaModel boba = bobaTeaService.getBobaTeaByName(nameBoba);
        if (nameBoba != null){
            List<StoreBobaTeaModel> storeBoba = boba.getStoreBobatea();
            model.addAttribute("storeBoba", storeBoba);
        }
        return "filter-manager";
    }


}
