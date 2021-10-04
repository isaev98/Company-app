package uz.pdp.companyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyapp.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Integer> {
}
