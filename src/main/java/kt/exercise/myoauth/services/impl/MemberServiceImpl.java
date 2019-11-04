package kt.exercise.myoauth.services.impl;

import kt.exercise.myoauth.model.entity.Member;
import kt.exercise.myoauth.repository.MemberRepository;
import kt.exercise.myoauth.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //유저 정보 불러오기
    public Optional<Member> selectUserInfo() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getPrincipal().getClass().getName();
        Optional<Member> Member = memberRepository.findByEmail(userEmail);

        return Member;
    }

    @Override
    public Member saveMember(Member member) throws Exception {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }
}
