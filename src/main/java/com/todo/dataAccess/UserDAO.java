package com.todo.dataAccess;

import com.todo.dto.UserResponseDTO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO implements IUserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return entityManager.createQuery("from User", UserResponseDTO.class).getResultList();
    }

    @Override
    public UserResponseDTO findById(int id) {
        return entityManager.find(UserResponseDTO.class, id);
    }

    @Override
    public UserResponseDTO update(UserResponseDTO userDTO) {
        return entityManager.merge(userDTO);
    }

    @Override
    public void deleteById(int id) {
        UserResponseDTO userDTO = entityManager.find(UserResponseDTO.class, id);

        entityManager.remove(userDTO);
    }
//    @Override
//    public UserResponseDTO add(UserResponseDTO userDTO){
//      return entityManager.merge(userDTO); // merge ve persistent farkına bak
//
//    }
}
