package com.spring.SignupLogin.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.envers.Audited;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.SignupLogin.enums.Role;
import com.spring.SignupLogin.utils.PermissionMapping;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
@Audited
public class UserEntity implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false,unique = true, length = 50)
	private String email;
	
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		roles.forEach(role ->{
			Set<SimpleGrantedAuthority> permissions = PermissionMapping.getPermission(role);
			authorities.addAll(permissions);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
		});
		
		return authorities;
	}
	
	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public @Nullable String getPassword() {
		return this.password;
	}
}
