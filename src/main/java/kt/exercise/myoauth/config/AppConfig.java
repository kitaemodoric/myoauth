package kt.exercise.myoauth.config;

import kt.exercise.myoauth.common.AppProperties;
import kt.exercise.myoauth.model.MemberRole;
import kt.exercise.myoauth.model.entity.Member;
import kt.exercise.myoauth.services.MemberService;
import kt.exercise.myoauth.services.UserService;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //TODO 이후에 삭제
    //초기값 지정
    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            MemberService memberService;

            @Autowired
            AppProperties appProperties;

            @Override
            public void run(ApplicationArguments args) throws Exception {

                memberService.deleteAll();

                Set<MemberRole> kitaeRole = new HashSet<>();
                kitaeRole.add(MemberRole.USER);

                Member kitae = Member.builder()
                        .email(appProperties.getUserUsername())
                        .password(appProperties.getUserPassword())
                        .roles(kitaeRole)
                        .build();
               memberService.saveMember(kitae);

                Set<MemberRole> adminRole = new HashSet<>();
                adminRole.add(MemberRole.USER);
                adminRole.add(MemberRole.ADMIN);

                Member admin = Member.builder()
                        .email(appProperties.getAdminUsername())
                        .password(appProperties.getAdminPassword())
                        .roles(adminRole)
                        .build();
                memberService.saveMember(admin);

            }
        };
    }
}
