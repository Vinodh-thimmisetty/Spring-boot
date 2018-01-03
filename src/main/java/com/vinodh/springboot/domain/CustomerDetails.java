package com.vinodh.springboot.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6789202248199262680L;

	private List<String> customerRoles;
	private CustomerDTO customerDTO;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = StringUtils.collectionToCommaDelimitedString(customerRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	@Value("abcd1234")
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return customerDTO.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
