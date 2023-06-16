package accounting.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends OncePerRequestFilter {

	private static final List<String> PUBLIC_ENDPOINTS = Arrays.asList("/api/accounts/create", "/api/accounts/login");

	private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		if (isPublicEndpoint(requestUri)) {
			filterChain.doFilter(request, response);
			return;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		filterChain.doFilter(request, response);
	}

	private boolean isPublicEndpoint(String requestURI) {
		for (String publicEndpoint : PUBLIC_ENDPOINTS) {
			if (PATH_MATCHER.match(publicEndpoint, requestURI))
				return true;
		}
		return false;
	}

}
