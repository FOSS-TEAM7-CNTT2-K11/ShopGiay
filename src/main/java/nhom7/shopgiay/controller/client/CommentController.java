package nhom7.shopgiay.controller.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.entity.Comment;
import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.model.CommentModel;
import nhom7.shopgiay.repository.AccountRepository;
import nhom7.shopgiay.repository.CommentRepository;
import nhom7.shopgiay.repository.ProductRepository;
import nhom7.shopgiay.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	@Autowired
	AccountRepository accRep;
	@Autowired
	ProductRepository productRep;
	@Autowired
	CommentRepository commentRep;
	
	
	@GetMapping("/get-comment")
	public ResponseEntity<Object> getCommentByProductId(@RequestParam long productId) {
		System.out.println("Controller get comment");
		List<CommentModel> cms = commentService.getCommentModelsByProductId(productId);
		return new ResponseEntity<Object>(cms, HttpStatus.OK);
	}
	
	@GetMapping("/save")
	public ResponseEntity<Object> getSaveComment(HttpServletRequest req) {
		System.out.println("controller save comment");
		try {
			Comment cm = new Comment();
			long parent = Long.parseLong(req.getParameter("parentId"));
			String content = req.getParameter("content");
			Account acc = accRep.findById(Long.parseLong(req.getParameter("accountId"))).get();
			Product product = productRep.findById(Long.parseLong(req.getParameter("productId"))).get();
			cm.setAccount(acc);
			cm.setProduct(product);
			cm.setContent(content);
			cm.setCreated(new Date());
			cm.setCommentParent(parent);
			cm = commentRep.save(cm);
			if(cm != null) {
				List<String> a = new ArrayList<String>();
				a.add("ok");
				return new ResponseEntity<Object>(a, HttpStatus.OK) ;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
}
