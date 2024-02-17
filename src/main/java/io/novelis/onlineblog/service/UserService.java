package io.novelis.onlineblog.service;

import io.novelis.onlineblog.dao.RepositoriesDao.UserDao;
import io.novelis.onlineblog.service.dto.UserDto;
import io.novelis.onlineblog.service.exception.BusinessException;
import io.novelis.onlineblog.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserDto getById(Long id){
        return userMapper.toDto(userDao.getById(id).orElseThrow(() -> new BusinessException(404, "user.not.found", "User not found")));
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userMapper.toDto(userDao.findAll());
    }

    @Transactional
    public void save(UserDto user) {
        userDao.save(userMapper.toEntity(user));
    }
}