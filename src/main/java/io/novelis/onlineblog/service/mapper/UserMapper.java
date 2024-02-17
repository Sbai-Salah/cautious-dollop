package io.novelis.onlineblog.service.mapper;

import io.novelis.onlineblog.domain.User;
import io.novelis.onlineblog.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);
    List<UserDto> toDto(List<User> users);

    User toEntity(UserDto userDto);

}
