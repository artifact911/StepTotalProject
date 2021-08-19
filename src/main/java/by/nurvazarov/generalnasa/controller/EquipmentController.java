package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.model.product.equipment.Equipment;
import by.nurvazarov.generalnasa.service.product.equipment.EquipmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/references/equipments")
public class EquipmentController {

    private static final String EQUIPMENT_TABLE = "references/equipment/equipmentTable :: equipment_list";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_EQUIPMENT_MODAL = "references/equipment/modal/editEquipment";
    private static final String ADD_EQUIPMENT_MODAL = "references/equipment/modal/addEquipment";

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Equipment> equipments = equipmentService.getEquipments();
        model.addAttribute("equipments", equipments);
        return "references/insect/insect";
    }

    @GetMapping("/addInsect")
    public String addEquipment(Model model) {
        try {
            model.addAttribute("equipment", new Equipment());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return ADD_EQUIPMENT_MODAL;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return ADD_EQUIPMENT_MODAL;
        }
    }

    @RequestMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(Long pid) throws IOException {
        Equipment equipment = equipmentService.getEquipmentByPid(pid);
        byte[] imageContent = null;
        imageContent = equipment.getImage();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/saveEquipment", consumes = {"multipart/form-data"})
    public String saveEquipment(Equipment equipment, Model model,
                                @RequestParam("maintenanceFile") MultipartFile maintenanceFile) {
        try {
            equipmentService.createNewEquipment(equipment, maintenanceFile);
            model.addAttribute("equipments", equipmentService.getEquipments());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return EQUIPMENT_TABLE;
        } catch (Exception e) {
            model.addAttribute("equipments", equipmentService.getEquipments());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return EQUIPMENT_TABLE;
        }
    }

    @GetMapping("/edit")
    public String editEquipment(Long pid, Model model) {
        try {
            Equipment equipment = equipmentService.getEquipmentByPid(pid);
            model.addAttribute("equipment", equipment);
            model.addAttribute("message", "Успешно редактировано");
            model.addAttribute("alertClass", "alert-success");
            return EDIT_EQUIPMENT_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка изменения");
            model.addAttribute("alertClass", "alert-danger");
            return EDIT_EQUIPMENT_MODAL;
        }
    }

    @PostMapping("/updateEquipment")
    public String updateEquipment(Equipment equipment, Model model) {
        try {
            equipmentService.updateEquipment(equipment);
            model.addAttribute("equipments", equipmentService.getEquipments());
            model.addAttribute("message", "Успешно изменено");
            model.addAttribute("alertClass", "alert-success");
            return EQUIPMENT_TABLE;
        } catch (Exception e) {
            model.addAttribute("equipments", equipmentService.getEquipments());
            model.addAttribute("message", "Ошибка изменения");
            model.addAttribute("alertClass", "alert-danger");
            return EQUIPMENT_TABLE;
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteEquipment(Long pid, Model model) {
        try {
            equipmentService.deleteEquipmentById(pid);
            model.addAttribute("equipments", equipmentService.getEquipments());
            model.addAttribute("message", "Удалено успешно");
            model.addAttribute("alertClass", "alert-success");
            return EQUIPMENT_TABLE;
        } catch (Exception e) {
            model.addAttribute("equipments", equipmentService.getEquipments());
            model.addAttribute("message", "Ошибка удаления");
            model.addAttribute("alertClass", "alert-danger");
            return EQUIPMENT_TABLE;
        }
    }

    @GetMapping("/filter")
    public String filterEquipment(String filterText, Model model) {
        List<Equipment> filterEquipment;
        try {
            if (!StringUtils.isBlank(filterText)) {
                filterEquipment = equipmentService.getSearchEquipment(filterText);
            } else {
                filterEquipment = equipmentService.getEquipments();
            }
            model.addAttribute("equipment", filterEquipment);
            return EQUIPMENT_TABLE;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("equipments", equipmentService.getEquipments());
            model.addAttribute("message", "Произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return EQUIPMENT_TABLE;
        }
    }
}
