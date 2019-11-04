package kt.exercise.myoauth.controller;

import kt.exercise.myoauth.model.entity.Member;
import kt.exercise.myoauth.services.MemberService;
import kt.exercise.myoauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping
    public ResponseEntity showMyInfo() throws Exception{
        Optional<Member> member = memberService.selectUserInfo();
        return ResponseEntity.ok(member);
    }
}
