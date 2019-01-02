
package ars.utility.library.controllers;

import ars.utility.library.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class HomeController {

    private  static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Reader") String name, Model model) throws IOException {
        model.addAttribute("name",name);
        model.addAttribute("books", bookService.findAll());
        return "home";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam(name="file") String file) throws IOException {
        File f = new File(file);
        if (f.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+f.getName());
            logger.info(String.format("Transfer file %s",f.getName()));
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(f.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } else {
            logger.info(String.format("Requested file %s not found",f.getName()));
            return ResponseEntity.notFound().build();
        }


    }
}
