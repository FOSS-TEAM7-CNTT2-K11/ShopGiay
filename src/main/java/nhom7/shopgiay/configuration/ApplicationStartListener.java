package nhom7.shopgiay.configuration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.repository.AccountRepository;

@Component
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	public AccountRepository accountRepository;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Account admin = new Account();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setAdmin(true);
		admin.setCreated(new Date(System.currentTimeMillis()));
		admin.setAddress("Ha Noi");
		admin.setPhone("0349109373");
		try {
			accountRepository.save(admin);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

}
