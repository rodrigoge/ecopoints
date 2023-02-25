package br.com.ecopoints.userservice.mappers;

import br.com.ecopoints.userservice.db.User;
import br.com.ecopoints.userservice.dto.UserTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserTO fromUser(User user);
}
