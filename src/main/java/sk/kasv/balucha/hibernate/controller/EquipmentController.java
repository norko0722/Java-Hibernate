package sk.kasv.balucha.hibernate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.kasv.balucha.hibernate.dto.EquipmentDTO;
import sk.kasv.balucha.hibernate.entities.Equipment;
import sk.kasv.balucha.hibernate.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@Tag(name = "Equipment Management", description = "APIs for managing all equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    @Operation(summary = "Get all available equipment")
    public List<Equipment> getAllEquipment() {
        return equipmentService.findAllEquipment();
    }

    @PostMapping
    @Operation(summary = "Create a new piece of equipment")
    public Equipment createEquipment(@Valid @RequestBody EquipmentDTO equipmentDTO) {
        Equipment newEquipment = new Equipment();
        newEquipment.setName(equipmentDTO.getName());
        newEquipment.setDescription(equipmentDTO.getDescription());
        newEquipment.setIsAvailable(true);
        return equipmentService.saveEquipment(newEquipment);
    }
}