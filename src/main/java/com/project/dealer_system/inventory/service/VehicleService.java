package com.project.dealer_system.inventory.service;

import com.project.dealer_system.audit.dto.AuditLogRequest;
import com.project.dealer_system.audit.service.AuditLogService;
import com.project.dealer_system.inventory.dto.VehicleDTO;
import com.project.dealer_system.inventory.entity.Vehicle;
import com.project.dealer_system.inventory.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final AuditLogService auditLogService;

    public VehicleService(VehicleRepository vehicleRepository,
                          AuditLogService auditLogService) {
        this.vehicleRepository = vehicleRepository;
        this.auditLogService = auditLogService;
    }

    // CREATE VEHICLE
    public Vehicle createVehicle(VehicleDTO dto) {

        Optional<Vehicle> existing =
                vehicleRepository.findByDealerIdAndVin(dto.getDealerId(), dto.getVin());

        if (existing.isPresent()) {
            Vehicle v = existing.get();

            if (v.getPrice() == null || v.getMileage() == null) {
                v.setPrice(dto.getPrice());
                v.setMileage(dto.getMileage());
                Vehicle updated = vehicleRepository.save(v);

                auditLogService.log(new AuditLogRequest(
                        dto.getDealerId(), 1L,
                        "INVENTORY", "UPDATE",
                        "Incomplete vehicle updated VIN: " + dto.getVin()
                ));

                return updated;
            }

            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate VIN for this dealer");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVin(dto.getVin());
        vehicle.setDealerId(dto.getDealerId());
        vehicle.setPrice(dto.getPrice());
        vehicle.setMileage(dto.getMileage());

        Vehicle saved = vehicleRepository.save(vehicle);

        auditLogService.log(new AuditLogRequest(
                dto.getDealerId(), 1L,
                "INVENTORY", "CREATE",
                "Vehicle created VIN: " + dto.getVin()
        ));

        return saved;
    }

    //  SELL VEHICLE
    public Vehicle sellVehicle(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));

        if (vehicle.isSold()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle is already sold");
        }

        vehicle.setSold(true);
        Vehicle saved = vehicleRepository.save(vehicle);

        auditLogService.log(new AuditLogRequest(
                vehicle.getDealerId(), 1L,
                "SALES", "SELL",
                "Vehicle sold ID: " + id + " VIN: " + vehicle.getVin()
        ));

        return saved;
    }

    //  UPDATE VEHICLE
    public Vehicle updateVehicle(Long id, VehicleDTO dto) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));

        vehicle.setPrice(dto.getPrice());
        vehicle.setMileage(dto.getMileage());

        Vehicle saved = vehicleRepository.save(vehicle);

        auditLogService.log(new AuditLogRequest(
                vehicle.getDealerId(), 1L,
                "INVENTORY", "UPDATE",
                "Vehicle updated ID: " + id + " VIN: " + vehicle.getVin()
        ));

        return saved;
    }

    //  DELETE VEHICLE
    public void deleteVehicle(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));

        vehicleRepository.deleteById(id);

        auditLogService.log(new AuditLogRequest(
                vehicle.getDealerId(), 1L,
                "INVENTORY", "DELETE",
                "Vehicle deleted ID: " + id + " VIN: " + vehicle.getVin()
        ));
    }
}