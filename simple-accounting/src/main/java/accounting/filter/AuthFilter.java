package accounting.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends OncePerRequestFilter{

	
	private static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
			"/api/accounts/create",
			"/api/accounts/login"
			);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
