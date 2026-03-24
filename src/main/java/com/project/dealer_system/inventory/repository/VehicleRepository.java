package com.project.dealer_system.inventory.repository;

import com.project.dealer_system.inventory.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByDealerIdAndVin(Long dealerId, String vin);
}
