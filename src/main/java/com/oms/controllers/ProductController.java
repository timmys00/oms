package com.oms.controllers;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oms.domain.Product;
import com.oms.exceptions.NoProductsFoundUnderCategoryException;
import com.oms.exceptions.ProductNotFoundException;
import com.oms.services.ProductService;

@Controller
@RequestMapping("market")
public class ProductController {

	@Autowired
	private ProductService productService;

	/*
	 * TODO: - dateformat fields
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId", "name", "description", "manufacturer", "unitPrice", "category",
				"unitsInStock", "condition", "productImage", "language");

		// DateFormat dateFormat = new SimpleDateFormat("MMM d, YYYY");
		// CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat,
		// true);
		// binder.registerCustomEditor(Date.class, orderDateEditor);

	}

	@RequestMapping("/products/invalidPromoCode")
	public String invalidPromoCode() {
		return "invalidPromoCode";
	}

	/*
	 * TODO: Change Product with ProductViewModel
	 */
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}

	/*
	 * TODO: Change Product with ProductViewModel
	 */
	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct,
			BindingResult result, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return "addProduct";
		}

		if (result.getSuppressedFields().length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
		}

		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(
						new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		productService.addProduct(newProduct);
		return "redirect:/market/products";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();
		return "redirect:/market/products";
	}

	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getById(productId));
		return "product";
	}

	// example:
	// market/products/filter/params;brands=Google,Dell;categories=Tablet,Laptop/specification;dimention=10,20,15;color=red,green,blue
	@RequestMapping("/products/filter/{params}/{specification}")
	public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams,
			@MatrixVariable(pathVar = "specification") Map<String, List<String>> specFilter, Model model) {
		model.addAttribute("products", productService.getByFilter(filterParams));
		return "products";
	}

	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		List<Product> products = productService.getByCategory(productCategory);
		if (products == null || products.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products", products);
		return "products";
	}

	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAll());
		return "products";

		// Product iphone = new Product("P1234","iPhone 6s", new
		// BigDecimal(500));
		// iphone.setDescription("Apple iPhone 6s smartphone with 4.00-inch
		// 640x1136 display and 8-megapixel rear camera");
		// iphone.setCategory("Smartphone");
		// iphone.setManufacturer("Apple");
		// iphone.setUnitsInStock(1000);
		//
		// model.addAttribute("product", iphone);
		//
		// return "products";
	}

	/*
	 * TODO: Display dynamic message in view TODO: Apply error handlers for all
	 * methods in controller TODO: Internationalization error messages TODO:
	 * Display the view for all types of exceptions TODO: Set internationalized
	 * messages accross application in several languages TODO: Export exception
	 * handling to a @ControllerAdvice class
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}

}
