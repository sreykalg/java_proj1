package com.example.java_proj1.mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

TeamDTO toDto(Team entity);

Team toEntity(TeamDTO dto);
}
