package com.stm.smart_task_management.tenant;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TenantFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String tenant = req.getHeader("X-Tenant-ID");
        if(tenant == null || tenant.isBlank()){
            tenant = "blank";
        }
        TenantContext.setTenant(tenant);
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            TenantContext.clear();
        }

    }
}
