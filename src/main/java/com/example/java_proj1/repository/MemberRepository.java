package com.example.java_proj1.repository;

import com.example.java_proj1.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Will it be received as `List<Member>` because of the duplicate name? How about specifying `name` as a unique index in the Member class?
    List<Member> findByName(String name);
}