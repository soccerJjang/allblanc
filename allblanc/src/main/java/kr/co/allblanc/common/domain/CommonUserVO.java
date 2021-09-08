package kr.co.allblanc.common.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommonUserVO implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	
	/** 사용자id */
	private String memberId;
	
	/** 사용자이름 */
	private String memberNm;
	
	/** 사용자권한 */
	private String memberRole;
	
	/** 사용자비밀번호 */
	private String memberPwd;
	
	/** 관리자id */
	private String adminId;
	
	/** 관리자이름 */
	private String adminNm;
	
	/** 관리자권한 */
	private String adminRole;
	
	/** 관리자비밀번호 */
	private String adminPwd;
	
	/** 로그인 상태 */
	private String status;
	
	private String ID;
	private String PASSWORD;
	private String NAME;
	private String AUTHORITY;
	private boolean ENABLED;
	
	public CommonUserVO() {
	}
	
	public CommonUserVO(String ID, String PASSWORD, String NAME, String AUTHORITY, boolean ENABLED) {
		this.ID = ID;
		this.PASSWORD = PASSWORD;
		this.NAME = NAME;
		this.AUTHORITY = AUTHORITY;
		this.ENABLED = ENABLED;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(AUTHORITY));
        return auth;
	}

	@Override
	public String getPassword() {
		return PASSWORD;
	}

	@Override
	public String getUsername() {
		return ID;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return ENABLED;
	}
}