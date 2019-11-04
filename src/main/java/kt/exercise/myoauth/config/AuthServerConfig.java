package kt.exercise.myoauth.config;


import kt.exercise.myoauth.common.AppProperties;
import kt.exercise.myoauth.services.MemberService;
import kt.exercise.myoauth.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
@Slf4j
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {



    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    AppProperties appProperties;

    DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
//        clients.inMemory()
//                .withClient(appProperties.getClientId())
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("read", "write")
//                .secret(this.passwordEncoder.encode(appProperties.getClientSecret()))
//                .accessTokenValiditySeconds(10 * 60)
//                .refreshTokenValiditySeconds(6 * 10 * 60);

        log.info(this.passwordEncoder.encode(appProperties.getClientSecret()));

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userService)
                .approvalStore(approvalStore())
                .tokenStore(tokenStore());
    }
}
