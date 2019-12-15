package nhom7.shopgiay.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.entity.CartItem;
import nhom7.shopgiay.repository.AccountRepository;
import nhom7.shopgiay.repository.CartItemRepository;

@Component
public class HeaderInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	AccountRepository accountRep;
	@Autowired
	CartItemRepository cartItemRep;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			String username;
			if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
				username = SecurityContextHolder.getContext().getAuthentication().getName();
				Account account = accountRep.findAccountByUsername(username);
				List<CartItem> cartItems = cartItemRep.getWaitingItemsByAccountId(account.getId());
				request.setAttribute("account", account);
				request.setAttribute("cartItems", cartItems);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
