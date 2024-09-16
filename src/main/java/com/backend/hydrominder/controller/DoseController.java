package com.backend.hydrominder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.hydrominder.dto.DoseDTO;
import com.backend.hydrominder.entity.Dose;
import com.backend.hydrominder.service.DoseService;

import io.swagger.v3.oas.annotations.Operation;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/1.0/hydrominder/doses")

public class DoseController {

    @Autowired
    private DoseService doseService;

    @GetMapping
    @Operation(summary = "Get all doses")
    public ResponseEntity<List<DoseDTO>> getAllDoses() {
        List<DoseDTO> doses = doseService.getAllDosesDTO();
        return new ResponseEntity<>(doses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a dose by ID")
    public ResponseEntity<Dose> getDoseById(@PathVariable int id) {
        Optional<Dose> dose = doseService.getDoseById(id);
        return dose.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Create a new dose")
    public ResponseEntity<String> createDose(@RequestBody Dose dose) {
        doseService.saveDose(dose);
        return new ResponseEntity<>("message: 'Dosagem inserida com sucesso!'", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a dose by ID")
    public ResponseEntity<String> updateDose(@PathVariable int id, @RequestBody Dose dose) {
        Dose updatedDose = doseService.updateDose(id, dose);
        if (updatedDose != null) {
            return new ResponseEntity<>("{\"message\": \"Dosagem atualizada com sucesso!\"}", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a dose by ID")
    public ResponseEntity<String> deleteDose(@PathVariable int id) {
        doseService.deleteDose(id);
        return new ResponseEntity<String>("{message: 'Dosagem deletada com sucesso'}", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/goal/{id}/doses")
    @Operation(summary = "Get doses by goal ID")
    public ResponseEntity<List<Map<String, Object>>> getDosesByGoalId(@PathVariable("id") Long goalId) {
        List<Dose> doses = doseService.getDosesByGoalId(goalId);
        List<Map<String, Object>> response = doses.stream()
            .map(dose -> {
                Map<String, Object> doseMap = new HashMap<>();
                doseMap.put("id", dose.getIdDose());
                doseMap.put("dose", dose.getMls());
                doseMap.put("date", new SimpleDateFormat("dd/MM/yyyy").format(dose.getDate()));
                return doseMap;
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }    
}
