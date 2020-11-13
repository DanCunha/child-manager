package br.com.child.manager.controller;

import br.com.child.manager.domain.Child;
import br.com.child.manager.dto.ChildDTO;
import br.com.child.manager.exception.ChildException;
import br.com.child.manager.form.ChildForm;
import br.com.child.manager.repository.ChildRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/child")
@Api(value = "API Rest Child")
@CrossOrigin(origins = "*")
public class ChildController {

    @Autowired
    private ChildRepository childRepository;

    @PostMapping
    @ApiOperation(value = "Create a new child", response = Child.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a new child"),
            @ApiResponse(code = 400, message = "Validation error")
    })
    public ResponseEntity<Object> add(@Valid @RequestBody ChildForm form, UriComponentsBuilder uriBuilder) {
        try {
            childValidation(form);
            Child entity = childRepository.save(form.converter());
            URI uri = uriBuilder.path("/child/{id}").buildAndExpand(entity.getId()).toUri();
            return ResponseEntity.created(uri).body(new ChildDTO(entity));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @ApiOperation(value = "List child", response = Child.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a new child"),
            @ApiResponse(code = 400, message = "Validation error")
    })
    public ResponseEntity<List<Child>> list() {
        List<Child> list = childRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "List child", response = Child.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a new child"),
            @ApiResponse(code = 400, message = "Validation error")
    })
    public ResponseEntity<Child> findById(@PathVariable(value = "id") long id) {
        Optional<Child> child = childRepository.findById(id);
        if(child.isPresent()){
            return ResponseEntity.ok(child.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    private void childValidation(ChildForm form) throws ChildException {
        Optional<Child> findByName = childRepository.findByName(form.getName());
        if(!findByName.isEmpty())
            throw new ChildException("Nome duplicado", null);
    }
}
