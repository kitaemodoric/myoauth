package kt.exercise.myoauth.services;

import kt.exercise.myoauth.model.entity.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> selectUserInfo() throws Exception;
    Member saveMember(Member member) throws Exception;
    //TODO 추후 삭제
    void deleteAll() throws Exception;


}
