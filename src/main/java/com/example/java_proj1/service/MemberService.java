package com.example.java_proj1.service;

import com.example.java_proj1.domain.Member;
import com.example.java_proj1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // register new member
    @Transactional
    public Long register(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    // remove redundancy of memebers' name
    private void validateDuplicateMember(Member member) {
        List<Member> byName = memberRepository.findByName(member.getName());
        if (!byName.isEmpty()) {
            throw new IllegalStateException("This member already exists.");
        }
    }

    // List all members
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow();
    }

    @Transactional
    public void update(Long memberId, String name, int age, String address){
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeName(name);
        member.changeAge(age);
        member.changeAddress(address);
    }

}

