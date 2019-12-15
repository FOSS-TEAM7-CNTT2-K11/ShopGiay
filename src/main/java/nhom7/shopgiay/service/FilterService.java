package nhom7.shopgiay.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.model.FilterModel;

@Service
public class FilterService {

	@PersistenceContext
	EntityManager emf;

	public List<Product> filter(FilterModel model) {
		StringBuilder builderSql = new StringBuilder(
				"select DISTINCT p.* from product p inner join product_has_category pc on pc.product_id=p.id inner join product_detail pd on pd.product_id=p.id where ");
		boolean coFilter = false;
		
		if (model.getCategories().length != 0) {
			coFilter = true;
			builderSql.append(" pc.category_id in(");
			for (long s : model.getCategories()) {
				builderSql.append(s);
				if (model.getCategories()[model.getCategories().length - 1] != s) {
					builderSql.append(", ");
				}
			}
			builderSql.append(") ");
		}
		if (model.getPriceMin() >= 0 && model.getPriceMax() >= model.getPriceMin()) {
			if (coFilter)
				builderSql.append(" and ");
			builderSql.append(" p.price between " + model.getPriceMin()).append(" and ").append(model.getPriceMax());
			coFilter = true;
		}

		if (model.getSizes().length != 0) {
			if (coFilter)
				builderSql.append(" and ");
			builderSql.append(" pd.size_id in (");
			for (long id : model.getSizes()) {
				builderSql.append(id);
				if (model.getSizes()[model.getSizes().length - 1] != id) {
					builderSql.append(", ");
				}
			}
			builderSql.append(" ) ");
			coFilter = true;
		}

		String sql = builderSql.toString();
		if (!coFilter) {
			sql = sql.replace("where", "");
		}
		System.out.println(sql);
		List<Product> list = emf.createNativeQuery(sql, Product.class).getResultList();
		return list;
	}

}
