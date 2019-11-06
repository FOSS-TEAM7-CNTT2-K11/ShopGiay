package nhom7.shopgiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import nhom7.shopgiay.custom.CustomUserDetails;
import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.entity.CartItem;
import nhom7.shopgiay.repository.CartItemRepository;

@Service
public class CartService {

	@Autowired
	public CartItemRepository cartItemRepository;

	public List<CartItem> getListCartByUserId(long id) {
		return cartItemRepository.getListCart(id);
	}

	public void addCart(CartItem cartItem) {
		Authentication auth;
		auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails ud = (CustomUserDetails) auth;
		Account acc = ud.getAccount();

		cartItem.setAccount(acc);
		cartItemRepository.save(cartItem);
	}
}
