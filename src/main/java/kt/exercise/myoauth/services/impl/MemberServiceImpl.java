package kt.exercise.myoauth.services.impl;

import kt.exercise.myoauth.model.entity.Member;
import kt.exercise.myoauth.repository.MemberRepository;
import kt.exercise.myoauth.services.MemberService;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String userEmail = ((UserDetails) principal).getUsername();
        Optional<Member> Member = memberRepository.findByEmail(userEmail);

        return Member;
    }

    @Override
    public Member saveMember(Member member) throws Exception {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    @Override
    public void deleteAll() throws Exception {
        memberRepository.deleteAll();
    }


}
