package com.project.dealer_system.inventory.controller;

import com.project.dealer_system.inventory.dto.VehicleDTO;
import com.project.dealer_system.inventory.entity.Vehicle;
import com.project.dealer_system.inventory.repository.VehicleRepository;
import com.project.dealer_system.inventory.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;

    public InventoryController(VehicleService vehicleService,
                               VehicleRepository vehicleRepository) {
        this.vehicleService = vehicleService;
        this.vehicleRepository = vehicleRepository;
    }

    // POST /inventory
    @PostMapping
    public Vehicle create(@Valid @RequestBody VehicleDTO dto) {
        return vehicleService.createVehicle(dto);
    }

    //  GET /inventory
    @GetMapping
    public Page<Vehicle> getAll(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }

    //  PUT /inventory/{id}/sell
    @PutMapping("/{id}/sell")
    public Vehicle sell(@PathVariable Long id) {
        return vehicleService.sellVehicle(id);
    }

    // PUT /inventory/{id}
    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id,
                          @Valid @RequestBody VehicleDTO dto) {
        return vehicleService.updateVehicle(id, dto);
    }

    // DELETE /inventory/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
