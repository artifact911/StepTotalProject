package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.model.product.insect.Insect;
import by.nurvazarov.generalnasa.service.product.insect.InsectService;
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
@RequestMapping("/references/insects")
public class InsectController {
    private static final String INSECT_TABLE = "references/insect/insectTable :: insect_list";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_INSECT_MODAL = "references/insect/modal/editInsect";
    private static final String ADD_INSECT_MODAL = "references/insect/modal/addInsect";

    private final InsectService insectService;

    @Autowired
    public InsectController(InsectService insectService) {
        this.insectService = insectService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Insect> insects = insectService.getInsects();
        model.addAttribute("insects", insects);
        return "references/insect/insect";
    }

    @GetMapping("/addInsect")
    public String addInsect(Model model) {
        try {
            model.addAttribute("insect", new Insect());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return ADD_INSECT_MODAL;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return ADD_INSECT_MODAL;
        }
    }

    @RequestMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(Long pid) throws IOException {
        Insect insect = insectService.getInsectByPid(pid);
        byte[] imageContent = null;
        imageContent = insect.getImage();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/saveInsect", consumes = {"multipart/form-data"})
    public String saveInsect(Insect insect, Model model,
                             @RequestParam("maintenanceFile") MultipartFile maintenanceFile) {
        try {
            insectService.createNewInsect(insect, maintenanceFile);
            model.addAttribute("insects", insectService.getInsects());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return INSECT_TABLE;
        } catch (Exception e) {
            model.addAttribute("insects", insectService.getInsects());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return INSECT_TABLE;
        }
    }

    @GetMapping("/edit")
    public String editInsect(Long pid, Model model) {
        try {
            Insect insect = insectService.getInsectByPid(pid);
            model.addAttribute("insect", insect);
            model.addAttribute("message", "Успешно редактировано");
            model.addAttribute("alertClass", "alert-success");
            return EDIT_INSECT_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка изменения");
            model.addAttribute("alertClass", "alert-danger");
            return EDIT_INSECT_MODAL;
        }
    }

    @PostMapping("/updateInsect")
    public String updateInsect(Insect insect, Model model) {
        try {
            insectService.updateInsect(insect);
            model.addAttribute("insects", insectService.getInsects());
            model.addAttribute("message", "Успешно изменено");
            model.addAttribute("alertClass", "alert-success");
            return INSECT_TABLE;
        } catch (Exception e) {
            model.addAttribute("insects", insectService.getInsects());
            model.addAttribute("message", "Ошибка изменения");
            model.addAttribute("alertClass", "alert-danger");
            return INSECT_TABLE;
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteInsect(Long pid, Model model) {
        try {
            insectService.deleteInsectById(pid);
            model.addAttribute("insects", insectService.getInsects());
            model.addAttribute("message", "Удалено успешно");
            model.addAttribute("alertClass", "alert-success");
            return INSECT_TABLE;
        } catch (Exception e) {
            model.addAttribute("insects", insectService.getInsects());
            model.addAttribute("message", "Ошибка удаления");
            model.addAttribute("alertClass", "alert-danger");
            return INSECT_TABLE;
        }
    }

    @GetMapping("/filter")
    public String filterInsect(String filterText, Model model) {
        List<Insect> filterInsect;
        try {
            if (!StringUtils.isBlank(filterText)) {
                filterInsect = insectService.getSearchInsect(filterText);
            } else {
                filterInsect = insectService.getInsects();
            }
            model.addAttribute("insects", filterInsect);
            return INSECT_TABLE;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("insects", insectService.getInsects());
            model.addAttribute("message", "Произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return INSECT_TABLE;
        }
    }
}
