package capstone.project.Demo_Project.repositories;

import capstone.project.Demo_Project.domain.entities.Role;
import capstone.project.Demo_Project.domain.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
