package nhom7.shopgiay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nhom7.shopgiay.custom.CustomUserDetails;
import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.repository.AccountRepository;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	public AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account acc = accountRepository.findAccountByUsername(username);
		if (acc == null)
			throw new UsernameNotFoundException("username not found");
		return new CustomUserDetails(acc);
	}

}
