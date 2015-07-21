package com.fcmm.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.fcmm.webstore.domain.Product;
import com.fcmm.webstore.exception.NoProductsFoundUnderCategoryException;
import com.fcmm.webstore.exception.ProductNotFoundException;
import com.fcmm.webstore.service.ProductService;
import com.fcmm.webstore.validator.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductValidator productValidator;

	@InitBinder
	public void initializeBinder(WebDataBinder binder) {

		binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category",
				"unitsInStock", "productImage", "language");
		
		binder.setValidator(productValidator);
	}

	@RequestMapping
	public String list(Model pModel) {
		pModel.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/all")
	public String allProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String pProductCategory) {
		List<Product> products = productService.getProductsByCategory(pProductCategory);

		if (products == null || products.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}

		model.addAttribute("products", products);
		return "products";
	}

	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> pFilterParams,
			Model pModel) {
		Set<Product> products = productService.getProductsByFilter(pFilterParams);

		if (products == null || products.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}

		pModel.addAttribute("products", products);
		return "products";
	}

	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String pProductId, Model pModel) {
		pModel.addAttribute("product", productService.getProductById(pProductId));
		return "product";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model pModel) {
		Product newProduct = new Product();
		pModel.addAttribute("newProduct", newProduct);
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product pNewProduct, BindingResult pResult,
			HttpServletRequest pRequest) {

		MultipartFile productImage = pNewProduct.getProductImage();
		String rootDirectory = pRequest.getSession().getServletContext().getRealPath("/");

		String[] suppressedFields = pResult.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(
						new File(rootDirectory + "resources\\images\\" + pNewProduct.getProductId() + ".jpg"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		
		if (pResult.hasErrors()) {
			return "addProduct";
		}
		
		productService.addProduct(pNewProduct);
		return "redirect:/products";
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest pRequest, ProductNotFoundException pException) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", pException.getProductId());
		mav.addObject("exception", pException);
		mav.addObject("url", pRequest.getRequestURL() + "?" + pRequest.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}
	
	@RequestMapping("/invalidPromoCode")
	public String invalidPromoCode() {
	    return "invalidPromoCode";
	}
}
