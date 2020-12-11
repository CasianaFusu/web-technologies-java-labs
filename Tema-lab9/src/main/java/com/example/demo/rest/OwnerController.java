package com.example.demo.rest;
import com.example.demo.domain.Owner;
import com.example.demo.domain.Shop;
import com.example.demo.dto.OwnerDto;
import com.example.demo.mapper.OwnerMapper;
import com.example.demo.mapper.OwnerMapperImpl;
import com.example.demo.service.OwnerService;
import com.example.demo.service.ShopService;
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
    private final OwnerMapper mapper = new OwnerMapperImpl();
    private final ShopService shopService;

    @Autowired
    public OwnerController(OwnerService ownerService, ShopService shopService) {
        this.ownerService = ownerService;
        this.shopService = shopService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<OwnerDto>> getAll() {
        List<Owner> ownerList = ownerService.getAll();
        return new ResponseEntity<>(mapper.toDto(ownerList), HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerDto> create(@Valid @RequestBody OwnerDto request) {
        ownerService.create(mapper.toEntity(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

   @PutMapping(path = "/update/{cnp}")
   public void update(@PathVariable String cnp, @RequestBody String nume)
   {
       ownerService.update(cnp,nume);
   }

    @PostMapping(path = "/delete/{cnp}")
    public String delete(@PathVariable String cnp) {
        return ownerService.delete(cnp);
    }

   @GetMapping(path = "/{cnp}")
    public ResponseEntity<OwnerDto> get(@PathVariable String cnp) {
        Owner owner = ownerService.get(cnp);
        return new ResponseEntity<>(mapper.toDto(owner),HttpStatus.OK);
    }
}
