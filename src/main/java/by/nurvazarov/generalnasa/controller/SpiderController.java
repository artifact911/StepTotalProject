package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.model.product.spider.Spider;
import by.nurvazarov.generalnasa.service.product.spider.SpiderService;
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
@RequestMapping("/references/spiders")
public class SpiderController {

    private static final String SPIDER_TABLE = "references/spider/spiderTable";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_SPIDER_MODAL = "references/spider/modal/editSpider";
    private static final String ADD_SPIDER_MODAL = "references/spider/modal/addSpider";

    private final SpiderService spiderService;

    @Autowired
    public SpiderController(SpiderService spiderService) {
        this.spiderService = spiderService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Spider> spiders = spiderService.getSpiders();
        model.addAttribute("spiders", spiders);
        return "references/spider/spider";
    }

    @GetMapping("/addSpider")
    public String addSpider(Model model) {
        try {
            model.addAttribute("spider", new Spider());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return ADD_SPIDER_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return ADD_SPIDER_MODAL;
        }
    }

    @RequestMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(Long pid) throws IOException {
        Spider spider = spiderService.getSpiderByPid(pid);
        byte[] imageContent = null;
        imageContent = spider.getImage();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/saveSpider", consumes = {"multipart/form-data"})
    public String saveSpider(Spider spider, Model model,
                             @RequestParam("maintenanceFile") MultipartFile maintenanceFile) {
        try {
            spiderService.createNewSpider(spider, maintenanceFile);
            model.addAttribute("spiders", spiderService.getSpiders());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return SPIDER_TABLE;
        } catch (Exception e) {
            model.addAttribute("spiders", spiderService.getSpiders());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return SPIDER_TABLE;
        }
    }

    @GetMapping("/edit")
    public String editSpider(Long pid, Model model) {
        try {
            Spider spider = spiderService.getSpiderByPid(pid);
            model.addAttribute("spider", spider);
            model.addAttribute("message", "Успешно изменено");
            model.addAttribute("alertClass", "alert-success");
            return EDIT_SPIDER_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка редактирования");
            model.addAttribute("alertClass", "alert-danger");
            return EDIT_SPIDER_MODAL;
        }
    }

    @PostMapping("/updateSpider")
    public String updateSpider(Spider spider, Model model) {
        try {
            spiderService.updateSpider(spider);
            model.addAttribute("spiders", spiderService.getSpiders());
            model.addAttribute("message", "Успешно изменено");
            model.addAttribute("alertClass", "alert-success");
            return SPIDER_TABLE;
        } catch (Exception e) {
            model.addAttribute("spiders", spiderService.getSpiders());
            model.addAttribute("message", "Ошибка редактирования");
            model.addAttribute("alertClass", "alert-danger");
            return SPIDER_TABLE;
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteSpider(Long pid, Model model) {
        try {
            spiderService.deleteSpiderById(pid);
            model.addAttribute("spiders", spiderService.getSpiders());
            model.addAttribute("message", "Успешно удалено");
            model.addAttribute("alertClass", "alert-success");
            return SPIDER_TABLE;
        } catch (Exception e) {
            model.addAttribute("spiders", spiderService.getSpiders());
            model.addAttribute("message", "Ошибка удаления");
            model.addAttribute("alertClass", "alert-danger");
            return SPIDER_TABLE;
        }
    }

    @GetMapping("/filter")
    public String filterSpider(String filterText, Model model) {
        List<Spider> filterSpider;
        try {
            if (!StringUtils.isBlank(filterText)) {
                filterSpider = spiderService.getSearchSpider(filterText);
            } else {
                filterSpider = spiderService.getSpiders();
            }
            model.addAttribute("spiders", filterSpider);
            return SPIDER_TABLE;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("spiders", spiderService.getSpiders());
            model.addAttribute("message", "Произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return SPIDER_TABLE;
        }
    }

}
