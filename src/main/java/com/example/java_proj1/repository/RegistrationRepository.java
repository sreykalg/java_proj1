package com.example.java_proj1.repository;

import com.example.java_proj1.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByMember_MemberId(Long memberId);
    List<Registration> findByLecture_LectureId(Long lectureId);
}
