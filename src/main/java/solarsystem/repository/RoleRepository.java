package solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solarsystem.domain.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

    Role findByAuthority(String authority);
}
