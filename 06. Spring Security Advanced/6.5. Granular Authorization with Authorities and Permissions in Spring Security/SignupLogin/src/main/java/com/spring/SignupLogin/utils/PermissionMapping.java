package com.spring.SignupLogin.utils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.spring.SignupLogin.enums.Permission;
import com.spring.SignupLogin.enums.Role;

import static com.spring.SignupLogin.enums.Permission.*;
import static com.spring.SignupLogin.enums.Role.*;

public class PermissionMapping {
	private static final Map<Role, Set<Permission>> map = Map.of(
			USER, Set.of(USER_CREATE,USER_VIEW,USER_UPDATE,POST_VIEW),
			CREATOR, Set.of(POST_CREATE,POST_UPDATE),
			ADMIN, Set.of(POST_CREATE,POST_UPDATE,USER_DELETE,POST_DELETE)
	);
	
	public static Set<SimpleGrantedAuthority> getPermissions(Role role){
		return map.get(role).stream().map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toSet());
	}
}
