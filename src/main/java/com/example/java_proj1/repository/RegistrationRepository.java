package com.example.java_proj1.repository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByMember_MemberId(Long memberId);

    List<Registration> findByLecture_LectureId(Long lectureId);
}
