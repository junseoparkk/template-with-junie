package com.noaats.template.user.infrastructure.persistence;

import com.noaats.template.user.domain.User;
import com.noaats.template.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserRepository 인터페이스의 JPA 구현체
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;
    
    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }
    
    @Override
    public User save(User user) {
        UserJpaEntity savedEntity = userJpaRepository.save(userMapper.toEntity(user));
        return userMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<User> findById(String id) {
        return userJpaRepository.findById(id)
                .map(userMapper::toDomain);
    }
    
    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<User> findByName(String name) {
        return userJpaRepository.findByName(name).stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void delete(User user) {
        userJpaRepository.deleteById(user.getId());
    }
}