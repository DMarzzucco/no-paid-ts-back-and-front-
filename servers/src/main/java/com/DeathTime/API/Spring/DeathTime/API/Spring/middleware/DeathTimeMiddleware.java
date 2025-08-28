package com.DeathTime.API.Spring.DeathTime.API.Spring.middleware;

import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

@Component
public class DeathTimeMiddleware extends OncePerRequestFilter {

    private final Logger logger = Logger.getLogger(DeathTimeMiddleware.class.getName());
    private UserRepository repository;

    @Autowired
    public DeathTimeMiddleware(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws SecurityException, IOException {
        try {
            ZonedDateTime currentTime = ZonedDateTime.now();
            ZonedDateTime deathTime = LocalDateTime.parse("YYYY-MM-DDTHH:MM:SS").atZone(ZoneId.systemDefault());

            if (currentTime.isAfter(deathTime)) {
                logger.info("The time has passed, the date expired");
                this.repository.deleteAll();
                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                res.getWriter().write("{\"message\": \"the time has expired\"}");
                res.setContentType("application/json");
                return;
            }
            filterChain.doFilter(req, res);

        } catch (Exception ex) {

            logger.severe("An error occurred" + ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.getWriter().write("{\"message\": \"Server " + ex.getMessage() + "\"}");
            res.setContentType("application/json");
        }
    }
}
