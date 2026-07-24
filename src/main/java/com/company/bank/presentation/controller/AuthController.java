package com.company.bank.presentation.controller;

import com.company.bank.infrastructure.security.jwt.JwtService;
import com.company.bank.presentation.request.AuthRequest;
import com.company.bank.presentation.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthController(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Endpoint dummy para generar un token JWT a utilizar en las demás llamadas.
     *
     * @param request credenciales (ej. user/password o admin/admin)
     * @return 200 OK con el token generado
     */
    @PostMapping("/login")
    @Operation(summary = "Login to get JWT token", description = "Generates a JWT token. Use user/password or admin/admin")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        
        // Simulación básica: solo valida si la contraseña enviada coincide con la hardcodeada (sin prefijo {noop})
        if (!"{noop}".concat(request.password()).equals(userDetails.getPassword()) 
            && !request.password().equals(userDetails.getPassword().replace("{noop}",""))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
