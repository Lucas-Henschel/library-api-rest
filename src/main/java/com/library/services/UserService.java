package com.library.services;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.dto.auth.CurrentUserDTO;
import com.library.dto.user.CreateUserRequestDTO;
import com.library.dto.user.UpdateUserRequestDTO;
import com.library.entities.UserEntity;
import com.library.helpers.UpdateValueHelper;
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

    public Optional<UserEntity> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void delete(CurrentUserDTO currentUser, Long id) {
        try {
            if (currentUser.getId().equals(id)) {
                throw new DatabaseException("Usuário não pode deletar a si mesmo");
            }

            findById(id);
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserEntity update(Long id, UpdateUserRequestDTO updateUser) {
        UserEntity entity = findById(id);

        String passwordEncryption = passwordEncoder.encode(updateUser.getPassword());
        updateUser.setPassword(passwordEncryption);
        
        updateData(entity, updateUser);

        return userRepository.save(entity);
    }

    private void updateData(UserEntity entity, UpdateUserRequestDTO updateUser) {
        UpdateValueHelper.updateIfNotNull(entity::setName, updateUser.getName());
        UpdateValueHelper.updateIfNotNull(entity::setLogin, updateUser.getLogin());
        UpdateValueHelper.updateIfNotNull(entity::setPassword, updateUser.getPassword());
    }

    public UserEntity create(@Valid CreateUserRequestDTO createUser) {
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
