package nhom7.shopgiay.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class PreviousPageService {

	public String getPreviousPage(HttpServletRequest req) {
		
		return req.getHeader("Referer");
	}
}
