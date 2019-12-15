package nhom7.shopgiay.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nhom7.shopgiay.entity.Comment;
import nhom7.shopgiay.model.CommentModel;
import nhom7.shopgiay.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRep;
	
	private CommentModel convertCommentToCommentModel(Comment c) {
		CommentModel cm = new CommentModel();
		cm.setId(c.getId());
		cm.setAccountId(c.getAccount().getId());
		cm.setAccountName(c.getAccount().getUsername());
		cm.setContent(c.getContent());
		cm.setCreated(c.getCreated());
		cm.setProductId(c.getProduct().getId());
		cm.setParentId(0);
		
		return cm;
	}
	
	public List<CommentModel> getCommentModelsByProductId(long productId){
		List<Comment> cmts = commentRep.getsByProductId(productId);
		List<CommentModel> cmtModels = new ArrayList<CommentModel>();
		for (Comment c : cmts) {
			if(c.getCommentParent() == 0)
			{
				cmtModels.add(convertCommentToCommentModel(c));
			}
		}
		
		List<CommentModel> cmChildren; 
		for (CommentModel cm : cmtModels) {
			cmChildren = new ArrayList<CommentModel>();
			for (Comment c : cmts) {
				if(c.getCommentParent() == cm.getId()) {
					cmChildren.add(convertCommentToCommentModel(c));
				}
			}
			cm.setCommentChildrens(cmChildren);
		}
		
		for (CommentModel commentModel : cmtModels) {
			if(commentModel.getId() == 613 || commentModel.getId() == 615 )
				System.out.println(commentModel.getCommentChildrens().size());
		}
		
		return cmtModels;
	}
}
