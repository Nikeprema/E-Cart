package com.example.E_Cart.Config;

import com.example.E_Cart.user.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
@Configuration
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private  final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String auth=request.getHeader("Authorization");
        final String jwt;
        final String email;
        final String userId;
        if(auth==null|| !auth.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=auth.substring(7);
        email = jwtUtils.extractEmail(jwt);
        userId = String.valueOf(jwtUtils.extractUserId(jwt));


        if (email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userDetailsService.loadUserByUsername(email);
            try{
                if(jwtUtils.isTokenValid(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails ,jwtUtils.extractRole(jwt));
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    UserContext.setUserId(Long.valueOf(userId));
                }

            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

   filterChain.doFilter(request,response);

    }
}
