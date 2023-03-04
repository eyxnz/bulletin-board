package com.board.bulletinboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig { // 생성한 사람, 수정한 사람의 값을 자동으로 처리하기 위해 사용
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("eyxnz"); // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정하자
    }
}
