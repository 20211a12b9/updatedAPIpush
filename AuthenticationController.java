package com.vms.medxbid.controllers;

import com.vms.medxbid.models.User;

import com.vms.medxbid.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vms.medxbid.models.AuthenticationRequest;
import com.vms.medxbid.models.AuthenticationResponse;
import com.vms.medxbid.models.MyUserDetails;
import com.vms.medxbid.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:8081")  // Applied at the class level
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createCustomerAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		String userType;
		if (userDetailsService.isCustUser(authenticationRequest.getUsername())) {
			userType = "custuser";
		} else if (userDetailsService.isDistUser(authenticationRequest.getUsername())) {
			userType = "distuser";
		} else {
			throw new Exception("Unknown user type");
		}

		return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getUserId(), userType));
	}
}