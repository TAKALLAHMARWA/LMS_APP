package com.example.courseservice.mapper;
import com.example.courseservice.dao.Entity.CourseEntity;
import com.example.courseservice.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "publishedAt", expression = "java(java.time.LocalDateTime.now())")
    CourseDTO toDTO(CourseEntity course);

    CourseEntity toEntity(CourseDTO courseDTO);
}
