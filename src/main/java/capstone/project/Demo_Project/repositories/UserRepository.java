package capstone.project.Demo_Project.repositories;

import capstone.project.Demo_Project.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User findByUserName(String userName);

    Page<User> findAllByUserNameContains(String userName, Pageable pageable);

    Page<User> findAllByStatusEquals(Integer status, Pageable pageable);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
