package com.stm.smart_task_management.rate_limit;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {
    private final StringRedisTemplate redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        String key = "rate limit:" + ip;
        String current = redisTemplate.opsForValue().get(key);
        int count = current == null ? 0 : Integer.parseInt(current);
        if(count > 20){
            response.setStatus(429);
            response.getWriter().write("Too many requests");
            return;
        }
        redisTemplate.opsForValue().increment(key);
        redisTemplate.expire(key, Duration.ofMinutes(1));
        filterChain.doFilter(request, response);
    }
}
