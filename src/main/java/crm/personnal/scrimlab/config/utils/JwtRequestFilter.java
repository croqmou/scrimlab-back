package crm.personnal.scrimlab.config.utils;

import crm.personnal.scrimlab.config.domain.TokenBlacklistService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    public JwtRequestFilter(JwtUtil jwtUtil, TokenBlacklistService tokenBlacklistService) {
        this.jwtUtil = jwtUtil;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            // ✅ 1. Vérifie si le token est blacklisté
            if (tokenBlacklistService.isTokenBlacklisted(token)) {
                logger.warn("Tentative d'utilisation d'un token blacklisté");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return; // Stoppe immédiatement le filtre
            }

            try {
                username = jwtUtil.extractEmail(token);
            } catch (Exception e) {
                logger.warn("Token invalide ou expiré : " + e.getMessage());
            }
        }

        // ✅ 2. Authentifie si le token est valide
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(token, username)) {
                UserDetails userDetails = User.withUsername(username)
                        .password("")
                        .authorities("USER")
                        .build();

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.info("✅ Authentifié en tant que : " + username);
            } else {
                logger.warn("Token JWT invalide pour l'utilisateur : " + username);
            }
        }

        // Continue la chaîne de filtres
        filterChain.doFilter(request, response);
    }
}
