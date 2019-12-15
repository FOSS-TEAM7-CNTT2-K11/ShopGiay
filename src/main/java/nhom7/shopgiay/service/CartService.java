package nhom7.shopgiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import nhom7.shopgiay.custom.CustomUserDetails;
import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.entity.CartItem;
import nhom7.shopgiay.entity.Color;
import nhom7.shopgiay.entity.ProductDetail;
import nhom7.shopgiay.entity.Size;
import nhom7.shopgiay.repository.CartItemRepository;
import nhom7.shopgiay.repository.ColorRepository;
import nhom7.shopgiay.repository.SizeRepository;
import sun.jvm.hotspot.oops.CheckedExceptionElement;

@Service
public class CartService {

	@Autowired
	public CartItemRepository cartItemRepository;
	@Autowired
	ColorRepository colorRep;
	@Autowired
	SizeRepository sizeRep;
	@Autowired
	ProductService productService;


	
	public List<CartItem> getWaitingCartsByUserId(long accId) {
		return cartItemRepository.getWaitingItemsByAccountId(accId);
	}

	public boolean checkInput(String sizeInput, String colorInput, int amout) {

		if (sizeInput.equalsIgnoreCase("") || colorInput.equalsIgnoreCase("") || amout < 1)
			return false;

		return true;
	}

	public CartItem checkCartItemExists(CartItem cart) {
		List<CartItem> carts = cartItemRepository.getWaitingItemsByAccountId(cart.getAccount().getId());
		for (CartItem cartItem : carts) {
			if (cartItem.getProductDetail().getId() == cart.getProductDetail().getId())
				return cartItem;
		}
		return null;
	}

	public boolean addToCart(long productId, String sizeInput, String colorInput, int amout, Account acc) {

		Size size = sizeRep.findBySize(sizeInput);
		Color color = colorRep.findByColor(colorInput);
		ProductDetail pd = productService.getProductDetailByColorAndSize(size, color, productId);
		if (pd == null)
			return false;
		CartItem cart = new CartItem();
		cart.setAccount(acc);
		cart.setAmount(amout);
		// cart.setCheckout(null);
		cart.setProductDetail(pd);
		cart.setShow(true);
		CartItem cartExists = checkCartItemExists(cart);
		if (cartExists != null) {
			cartExists.setAmount(cartExists.getAmount() + cart.getAmount());
			cartItemRepository.save(cartExists);
		} else
			cartItemRepository.save(cart);
		return true;
	}

	
}
