package com.example.adorsys.configuration;

import com.example.adorsys.domain.User;
import com.example.adorsys.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        mapper.typeMap(UserDto.class, User.class)
                .addMappings(m -> m.skip(User::setId))
                .addMappings(m -> m.skip(User::setRoles))
                .addMappings(m -> m.skip(User::setActive));
        return mapper;
    }
}