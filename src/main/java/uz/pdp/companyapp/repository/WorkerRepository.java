package uz.pdp.companyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyapp.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
}
