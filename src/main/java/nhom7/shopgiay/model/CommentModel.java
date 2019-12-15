package nhom7.shopgiay.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentModel {

	private long id;
	private long parentId;
	private List<CommentModel> commentChildrens;
	private String content;
	private Date created;
	private long accountId;
	private String accountName;
	private long productId;

	public CommentModel() {
		commentChildrens = new ArrayList<CommentModel>();
	}

	public CommentModel(long id, long parentId, List<CommentModel> commentChildrens, String content, Date created,
			long accountId, String accountName, long productId) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.commentChildrens = commentChildrens;
		this.content = content;
		this.created = created;
		this.accountId = accountId;
		this.accountName = accountName;
		this.productId = productId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public List<CommentModel> getCommentChildrens() {
		return commentChildrens;
	}

	public void setCommentChildrens(List<CommentModel> commentChildrens) {
		this.commentChildrens = commentChildrens;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

}
