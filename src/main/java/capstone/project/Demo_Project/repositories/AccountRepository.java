package capstone.project.Demo_Project.repositories;

import capstone.project.Demo_Project.domain.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {
    Account findByUserName(String userName);

    Page<Account> findAllByUserNameContains(String userName, Pageable pageable);

    Page<Account> findAllByStatusEquals(Integer status, Pageable pageable);
}
