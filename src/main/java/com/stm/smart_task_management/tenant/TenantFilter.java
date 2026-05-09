package com.stm.smart_task_management.tenant;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class TenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String tenant = req.getHeader("X-Tenant-ID");
        log.info("Tenant filter triggered ",tenant );
        if(tenant == null || tenant.isBlank()){
            tenant = "blank";
        }
        TenantContext.setTenant(tenant);
        try{
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}
