package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.model.product.lizard.Lizard;
import by.nurvazarov.generalnasa.service.product.lizard.LizardService;
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
@RequestMapping("/references/lizards")
public class LizardController {

    private static final String LIZARD_TABLE = "references/lizard/lizardTable :: lizard_list";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_LIZARD_MODAL = "references/lizard/modal/editLizard";
    private static final String ADD_LIZARD_MODAL = "references/lizard/modal/addLizard";

    private final LizardService lizardService;

    @Autowired
    public LizardController(LizardService lizardService) {
        this.lizardService = lizardService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Lizard> lizards = lizardService.getLizards();
        model.addAttribute("lizards", lizards);
        return "references/lizard/lizard";
    }

    @GetMapping("/addLizard")
    public String addLizard(Model model) {
        try {
            model.addAttribute("lizard", new Lizard());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return ADD_LIZARD_MODAL;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return ADD_LIZARD_MODAL;
        }
    }

    @RequestMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(Long pid) throws IOException {
        byte[] imageContent = lizardService.getImg(pid);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/saveLizard", consumes = {"multipart/form-data"})
    public String saveLizard(Lizard lizard, Model model,
                             @RequestParam("maintenanceFile") MultipartFile maintenanceFile) {
        try {
            lizardService.createNewLizard(lizard, maintenanceFile);
            model.addAttribute("lizards", lizardService.getLizards());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return LIZARD_TABLE;
        } catch (Exception e) {
            model.addAttribute("lizards", lizardService.getLizards());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return LIZARD_TABLE;
        }
    }

    @GetMapping("/edit")
    public String editLizard(Long pid, Model model) {
        try {
            Lizard lizard = lizardService.getLizardByPid(pid);
            model.addAttribute("lizard", lizard);
            model.addAttribute("message", "Успешно редактировано");
            model.addAttribute("alertClass", "alert-success");
            return EDIT_LIZARD_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка изменения");
            model.addAttribute("alertClass", "alert-danger");
            return EDIT_LIZARD_MODAL;
        }
    }

    @PostMapping("/updateLizard")
    public String updateLizard(Lizard lizard, Model model) {
        try {
            lizardService.updateLizard(lizard);
            model.addAttribute("lizards", lizardService.getLizards());
            model.addAttribute("message", "Успешно изменено");
            model.addAttribute("alertClass", "alert-success");
            return LIZARD_TABLE;
        } catch (Exception e) {
            model.addAttribute("lizards", lizardService.getLizards());
            model.addAttribute("message", "Ошибка изменения");
            model.addAttribute("alertClass", "alert-danger");
            return LIZARD_TABLE;
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteLizard(Long pid, Model model) {
        try {
            lizardService.deleteLizardById(pid);
            model.addAttribute("lizards", lizardService.getLizards());
            model.addAttribute("message", "Удалено успешно");
            model.addAttribute("alertClass", "alert-success");
            return LIZARD_TABLE;
        } catch (Exception e) {
            model.addAttribute("lizards", lizardService.getLizards());
            model.addAttribute("message", "Ошибка удаления");
            model.addAttribute("alertClass", "alert-danger");
            return LIZARD_TABLE;
        }
    }

    @GetMapping("/filter")
    public String filterLizard(String filterText, Model model) {
        List<Lizard> filterLizard;
        try {
            if (!StringUtils.isBlank(filterText)) {
                filterLizard = lizardService.getSearchLizard(filterText);
            } else {
                filterLizard = lizardService.getLizards();
            }
            model.addAttribute("lizards", filterLizard);
            return LIZARD_TABLE;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("lizards", lizardService.getLizards());
            model.addAttribute("message", "Произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return LIZARD_TABLE;
        }
    }
}
