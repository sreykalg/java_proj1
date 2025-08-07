package com.example.java_proj1.mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(source = "team.teamId", target = "teamId")
    MemberDTO toDto(Member entity);

    @Mapping(source = "teamId", target = "team.teamId")
    Member toEntity(MemberDTO dto);

}
