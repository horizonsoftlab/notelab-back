package sn.hsl.notelabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.hsl.notelabback.entities.AccountEntity;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndFirstAttempt(String username, boolean firstAttempt);

    default boolean isAccountFirstAttempt(String username) {
        return existsByUsernameAndFirstAttempt(username, Boolean.TRUE);
    }
}
