package com.pragma.orbita.driver.users.infrastructure.persistence.DAO;


import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioRolEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUsuarioRolDao extends CrudRepository<UsuarioRolEntity, Integer> {

    Optional<UsuarioRolEntity> findByIdUsuario(int id);
}
