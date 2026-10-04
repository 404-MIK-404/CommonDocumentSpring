package com.mik.common.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    public static final String DEFAULT_USER = "system";

    public static final String USERNAME_HEADER = "X-User-Name";

    @Override
    public Optional<String> getCurrentAuditor() {
        HttpServletRequest request = HttpRequestUtils.getCurrentHttpRequest();
        String username = null;


        //SecurityContext securityContext = SecurityContextHolder.getContext();
        //User user = (User) securityContext.getAuthentication().getPrincipal();
        return Optional.of(DEFAULT_USER);
    }



}
