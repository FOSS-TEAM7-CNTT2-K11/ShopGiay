package nhom7.shopgiay.controller.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nhom7.shopgiay.custom.StatusMessage;
import nhom7.shopgiay.entity.Category;
import nhom7.shopgiay.repository.CategoryRepository;

@Controller
@RequestMapping(value = "/admin/categories")
public class CategoryController {

	@Autowired
	public CategoryRepository categoryRepository;

	@GetMapping
	public String getPage(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "admin_categories";
	}

	@PostMapping(value = "/add")
	public String add(@RequestParam("name") String name, RedirectAttributes re) {
		StatusMessage sm = new StatusMessage();
		try {
			System.out.println(name);
			Category cat = new Category();
			cat.setName(name);
			cat.setCreated(new Date(System.currentTimeMillis()));
			categoryRepository.save(cat);
			sm.setError(false);
			sm.setMessage("Thêm danh mục thành công");
			re.addFlashAttribute("statusMessage", sm);
		} catch (Exception e) {
			sm.setError(true);
			sm.setMessage("Thêm danh mục thất bại: " + e.getMessage());
			re.addFlashAttribute("statusMessage", sm);
		}
		return "redirect:/admin/categories";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") long id, RedirectAttributes re) {
		StatusMessage sm = new StatusMessage();
		try {
			categoryRepository.deleteById(id);
			sm.setError(false);
			sm.setMessage("Xóa danh mục thành công");
			re.addFlashAttribute("statusMessage", sm);
		} catch (Exception e) {
			sm.setError(true);
			sm.setMessage("Xóa danh mục thất bại: " + e.getMessage());
			re.addFlashAttribute("statusMessage", sm);
		}
		return "redirect:/admin/categories";
	}
}
