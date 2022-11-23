package com.project.fashionbusinesswebsite.controller;

import com.project.fashionbusinesswebsite.domain.CustomerEntity;
import com.project.fashionbusinesswebsite.domain.ProductCategoryEntity;
import com.project.fashionbusinesswebsite.model.ChargeRequest;
import com.project.fashionbusinesswebsite.model.cart.CartDTO;
import com.project.fashionbusinesswebsite.model.cart.CartRequest;
import com.project.fashionbusinesswebsite.model.cart.CartResponse;
import com.project.fashionbusinesswebsite.model.cart.ListCartsRequest;
import com.project.fashionbusinesswebsite.model.payment.PaymentRequest;
import com.project.fashionbusinesswebsite.model.payment.PaymentResponse;
import com.project.fashionbusinesswebsite.model.product.*;
import com.project.fashionbusinesswebsite.model.user.RegisterRequest;
import com.project.fashionbusinesswebsite.service.*;
import com.project.fashionbusinesswebsite.utils.FinderUtil;
import com.project.fashionbusinesswebsite.utils.PaymentConstantUtil;
import com.project.fashionbusinesswebsite.utils.ProductConstantUtil;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.eclipse.jetty.client.api.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stripe.model.Coupon;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FinderUtil finderUtil;
    @Autowired
    private CartService cartService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StripeService stripeService;
    @Autowired
    private PaymentService paymentService;

    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    @GetMapping("/")
    public String homePage(ProductRequest request, Model model) {
        // category with shirt
        int categoryIdShirt = ProductConstantUtil.SHIRT;
        ProductCategoryRequest productCategoryRequest = new ProductCategoryRequest();
        productCategoryRequest.setCategoryId(categoryIdShirt);
        model.addAttribute("listShirts", productService.findAllProductsByProductCategory(productCategoryRequest));
        // category with hat
        int categoryIdHat = ProductConstantUtil.HAT;
        productCategoryRequest.setCategoryId(categoryIdHat);
        model.addAttribute("listHats", productService.findAllProductsByProductCategory(productCategoryRequest));
        // category with hat
        int categoryIdJacket = ProductConstantUtil.JACKET;
        productCategoryRequest.setCategoryId(categoryIdJacket);
        model.addAttribute("listJackets", productService.findAllProductsByProductCategory(productCategoryRequest));

        model.addAttribute("listProducts", productService.getAllProduct(request));
        CartRequest cartRequest = new CartRequest();
        model.addAttribute("cartForm", cartRequest);
        return "index";
    }

    @GetMapping("/search")
    public String searchPage(@RequestParam(name = "keySearch") String keySearch,
                             @RequestParam(name = "page") int currentPage, Model model) {
        SearchProductRequest request = new SearchProductRequest();
        request.setKeySearch(keySearch);
        request.setPage(currentPage);
        request.setSize(24);
        int totalPag = productService.getSizePageByProductTitle(keySearch);
        int numberPage = Integer.valueOf(totalPag / 24) + 1;
        List<ProductResponse> result = productService.findProductsByProductName(request);
        model.addAttribute("sizeResult", result.size());
        model.addAttribute("numberPage", numberPage);
        model.addAttribute("totalResult", totalPag);
        model.addAttribute("keySearch", keySearch);
        model.addAttribute("currentPage", request.getPage());
        model.addAttribute("listProducts", productService.findProductsByProductName(request));
        return "category";
    }

    @GetMapping("/detail")
    public String detailPage(@RequestParam(name = "productId") int productId, Model model) {
        ProductViewResponse product = productService.getProductById(productId);
        ProductCategoryRequest productCategoryRequest = new ProductCategoryRequest();
        productCategoryRequest.setCategoryId(product.getProductCategoryId());
        model.addAttribute("listProducts", productService.findAllProductsByProductCategory(productCategoryRequest));
        ProductCategoryEntity productCategoryEntity = finderUtil.findProductCategoryById(product.getProductCategoryId());
        if (ObjectUtils.isEmpty(productCategoryEntity)) {
            throw new ServiceException("Dang mục sản phẩm không tồn tại");
        }
        product.setProductCategoryName(productCategoryEntity.getPCategoryTitle());
        product.setCurrentProductQuantity(1);
        model.addAttribute("product", product);
        return "productDetail";
    }

    @PostMapping("/create-cart")
    public Object createCart(@ModelAttribute("product") ProductViewResponse productRequest, Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            return "login";
        }
        CartRequest request = new CartRequest();
        request.setProductsId(productRequest.getProductsId());
        request.setQuantity(productRequest.getCurrentProductQuantity());
        request.setMoney(productRequest.getProductPriceAfterDiscount() * productRequest.getCurrentProductQuantity());
        cartService.createCart(request, principal);
        return new ModelAndView("redirect:/cart");
    }

    @PostMapping("/create-payment")
    public Object createPayment(@RequestParam(name = "costForm") double cost, Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            return "login";
        }
        PaymentResponse response = paymentService.findPayment(principal);
        if (!(ObjectUtils.isNotEmpty(response) && PaymentConstantUtil.PAYMENT_ACTIVE == response.getActive())) {
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setPrice(cost);
            paymentService.createPayment(paymentRequest, principal);
        }
        return new ModelAndView("redirect:/checkout");
    }

    @GetMapping("/create-cart")
    public Object createCartPage(@RequestParam(name = "productId") int productId, Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            return "login";
        }
        ProductViewResponse product = productService.getProductById(productId);
        CartRequest request = new CartRequest().builder().productsId(productId).money(product.getProductPrice()).quantity(1).build();
        cartService.createCart(request, principal);
        return new ModelAndView("redirect:/cart");
    }

    @GetMapping("/cart")
    public String cartPage(Principal principal, Model model) {
        if (ObjectUtils.isEmpty(principal)) {
            return "login";
        }
        ListCartsRequest requests = new ListCartsRequest();
        List<CartResponse> listCartResponses = cartService.getAllCartByCustomerName(principal);
        if (CollectionUtils.isNotEmpty(listCartResponses)) {
            for (CartResponse cart : listCartResponses) {
                requests.addCart(cart);
            }
            model.addAttribute("cartForm", requests);
            return "cart";
        } else {
            return "cartEmpty";

        }
    }

    @GetMapping("/delete-cart")
    public Object removeCartByCartId(@RequestParam(name = "cartId") int cartId, Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            return "login";
        }
        boolean isRemoved = cartService.removeCart(cartId, principal);
        if (Boolean.TRUE.equals(isRemoved)) {
            return new ModelAndView("redirect:/cart");
        }
        throw new ServiceException("Có lỗi hệ thống");

    }

    @PostMapping("/update-cart")
    public Object updateCart(@ModelAttribute ListCartsRequest request) {
        List<CartDTO> listCartDTO = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(request) && CollectionUtils.isNotEmpty(request.getListCarts())) {
            for (CartResponse cart : request.getListCarts()) {
                listCartDTO.add(mapper.map(cart, CartDTO.class));
            }
        }
        boolean isSuccess = cartService.saveAllCarts(listCartDTO);
        if (Boolean.TRUE.equals(isSuccess)) {
            return new ModelAndView("redirect:/cart");
        }
        throw new ServiceException("Có lỗi khi lưu");

    }

    @PostMapping("/create-checkout-session")
    public String createPayment(Principal principal) {
        if (ObjectUtils.isEmpty(principal)) {
            return "login";
        }

        return "paymentSuccess";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        RegisterRequest registerRequest = new RegisterRequest();
        model.addAttribute("registerForm", registerRequest);
        return "register";
    }

    @PostMapping("/user/register")
    public Object register(@ModelAttribute("registerForm") RegisterRequest request) {
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("name", request.getUserName());
            params.put("email", request.getCustomerEmail());
            Customer customer = Customer.create(params);
            request.setCustomerId(customer.getId());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        accountService.register(request);
        return "/login";
    }

    @GetMapping("/userAccountInfo")
    @ResponseBody
    public String successPage() {
        return "<h2>Success </h2>";
    }

    @GetMapping("/403")
    @ResponseBody
    public String errorPage() {
        return "<h1>Bug</h1>";
    }
    @Value("${stripe.keys.public}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model, Principal principal) {
//        ChargeRequest chargeRequest = new ChargeRequest();
//        model.addAttribute("chargeForm", chargeRequest);
//        PaymentResponse response = paymentService.findPayment(principal);
//        model.addAttribute("money", response.getMoney());
        PaymentResponse response = paymentService.findPayment(principal);
        double cost = response.getMoney();
        double dollar = 24848;
        double money = cost/ dollar;

        model.addAttribute("amount",  money * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "charge";
    }

    @PostMapping("/charge-test")
    public Object charge(ChargeRequest chargeRequest, Model model, Principal principal)
            throws StripeException {
        PaymentResponse response = paymentService.findPayment(principal);
        double cost = response.getMoney();
        double dollar = 24848;
        double money = cost/ dollar;

        chargeRequest.setAmount(money * 100);
        chargeRequest.setDescription("Thanh toán hóa đơn");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = stripeService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "paymentSuccess";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        throw new ServiceException(ex.getMessage());
    }

}
