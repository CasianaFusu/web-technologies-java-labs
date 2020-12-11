package com.example.demo.rest;
import com.example.demo.domain.Owner;
import com.example.demo.dto.OwnerDto;
import com.example.demo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OwnerDto> getAll() {
        return ownerService.getAll();
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerDto> create(@Valid @RequestBody OwnerDto request) {
        return new ResponseEntity<>(
                ownerService.create(request),
                HttpStatus.CREATED);
    }

   /* @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerDto> update(@RequestBody OwnerDto request) {
        return new ResponseEntity<>(ownerService.update(request), ownerService.update(request) != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }*/

    @PostMapping(path = "/delete/{cnp}")
    public String delete(@PathVariable String cnp) {
        return ownerService.delete(cnp);
    }

    @GetMapping(path = "/{cnp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerDto> get(@PathVariable String cnp) {
        return new ResponseEntity<>(ownerService.get(cnp), HttpStatus.OK);
    }
}
