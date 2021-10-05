package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.model.product.scorpion.Scorpion;
import by.nurvazarov.generalnasa.service.product.scorpion.ScorpionService;
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
@RequestMapping("/references/scorpions")
public class ScorpionController {

    private static final String SCORPION_TABLE = "references/scorpion/scorpionTable :: scorpion_list";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_SCORPION_MODAL = "references/scorpion/modal/editScorpion";
    private static final String ADD_SCORPION_MODAL = "references/scorpion/modal/addScorpion";

    private final ScorpionService scorpionService;

    @Autowired
    public ScorpionController(ScorpionService scorpionService) {
        this.scorpionService = scorpionService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Scorpion> scorpions = scorpionService.getScorpions();
        model.addAttribute("scorpions", scorpions);
        return "references/scorpion/scorpion";
    }

    @GetMapping("/addScorpion")
    public String addScorpion(Model model) {
        try {
            model.addAttribute("scorpion", new Scorpion());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return ADD_SCORPION_MODAL;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return ADD_SCORPION_MODAL;
        }
    }

    @RequestMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(Long pid) throws IOException {
        byte[] imageContent = scorpionService.getImg(pid);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/saveScorpion", consumes = {"multipart/form-data"})
    public String saveScorpion(Scorpion scorpion, Model model,
                               @RequestParam("maintenanceFile") MultipartFile maintenanceFile) {
        try {
            scorpionService.createNewScorpion(scorpion, maintenanceFile);
            model.addAttribute("scorpions", scorpionService.getScorpions());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return SCORPION_TABLE;
        } catch (Exception e) {
            model.addAttribute("scorpions", scorpionService.getScorpions());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return SCORPION_TABLE;
        }
    }

    @GetMapping("/edit")
    public String editScorpion(Long pid, Model model) {
        try {
            Scorpion scorpion = scorpionService.getScorpionByPid(pid);
            model.addAttribute("scorpion", scorpion);
            model.addAttribute("message", "Успешно изменен");
            model.addAttribute("alertClass", "alert-success");
            return EDIT_SCORPION_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка редактирования");
            model.addAttribute("alertClass", "alert-danger");
            return EDIT_SCORPION_MODAL;
        }
    }

    @PostMapping("/updateScorpion")
    public String updateScorpion(Scorpion scorpion, Model model) {
        try {
            scorpionService.updateScorpion(scorpion);
            model.addAttribute("scorpions", scorpionService.getScorpions());
            model.addAttribute("message", "Успешно изменен");
            model.addAttribute("alertClass", "alert-success");
            return SCORPION_TABLE;
        } catch (Exception e) {
            model.addAttribute("scorpions", scorpionService.getScorpions());
            model.addAttribute("message", "Ошибка редактирования");
            model.addAttribute("alertClass", "alert-danger");
            return SCORPION_TABLE;
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteScorpion(Long pid, Model model) {
        try {
            scorpionService.deleteScorpionById(pid);
            model.addAttribute("scorpions", scorpionService.getScorpions());
            model.addAttribute("message", "Успешно удалено");
            model.addAttribute("alertClass", "alert-success");
            return SCORPION_TABLE;
        } catch (Exception e) {
            model.addAttribute("scorpions", scorpionService.getScorpions());
            model.addAttribute("message", "Ошибка удаления");
            model.addAttribute("alertClass", "alert-danger");
            return SCORPION_TABLE;
        }
    }

    @GetMapping("/filter")
    public String filterScorpion(String filterText, Model model) {
        List<Scorpion> filterScorpion;
        try {
            if (!StringUtils.isBlank(filterText)) {
                filterScorpion = scorpionService.getSearchScorpion(filterText);
            } else {
                filterScorpion = scorpionService.getScorpions();
            }
            model.addAttribute("scorpions", filterScorpion);
            return SCORPION_TABLE;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("scorpions", scorpionService.getScorpions());
            model.addAttribute("message", "Произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return SCORPION_TABLE;
        }
    }
}
