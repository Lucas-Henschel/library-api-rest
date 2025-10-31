package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.dto.user.CreateUserRequestDTO;
import com.library.dto.user.UpdateUserRequestDTO;
import com.library.entities.UserEntity;
import com.library.helpers.UpdateValue;
import com.library.repositories.UserRepository;
import com.library.services.exceptions.DatabaseException;
import com.library.services.exceptions.ResourceNotFoundException;
import com.library.services.exceptions.UnprocessableEntityException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public void delete(Long id) {
        try {
            findById(id);
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserEntity update(Long id, UpdateUserRequestDTO updateUser) {
        UserEntity entity = findById(id);
        updateData(entity, updateUser);

        return userRepository.save(entity);
    }

    private void updateData(UserEntity entity, UpdateUserRequestDTO updateUser) {
        UpdateValue.updateIfNotNull(entity::setName, updateUser.getName());
        UpdateValue.updateIfNotNull(entity::setLogin, updateUser.getLogin());
        UpdateValue.updateIfNotNull(entity::setPassword, updateUser.getPassword());
    }

    public UserEntity create(CreateUserRequestDTO createUser) {
        Optional<UserEntity> findUserByLogin = userRepository.findByLogin(createUser.getLogin());

        if (findUserByLogin.isPresent()) {
            throw new UnprocessableEntityException("Já existe um usuário com esse login");
        }
        
        String passwordEncryption = passwordEncoder.encode(createUser.getPassword());

        UserEntity user = new UserEntity();
        user.setLogin(createUser.getLogin());
        user.setName(createUser.getName());
        user.setPassword(passwordEncryption);

        return userRepository.save(user);
    }
}
