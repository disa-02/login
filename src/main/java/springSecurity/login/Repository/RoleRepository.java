package springSecurity.login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springSecurity.login.Entity.Role;
import springSecurity.login.Utils.EnumRole;
import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findByName(String name);
    
}
