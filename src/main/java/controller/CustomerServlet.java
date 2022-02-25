package controller;

import model.Customer;
import model.Product;
import service.CustomerImpl;
import service.CustomerService;
import service.ProductImpl;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerImpl();
    private ProductService productService = new ProductImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showListCustomer(request, response);
                break;
        }
    }

    private void showListCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer/list.jsp");
        List<Customer> customers;
        customers = customerService.findAll();
        List<Product> products = findProduct(customers);
        request.setAttribute("alo", customers);
        request.setAttribute("alo1", products);
        requestDispatcher.forward(request, response);
    }

    List<Product> findProduct(List<Customer> customers) {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < customers.size(); i++) {
            Product product = productService.findById(customers.get(i).getProductId());
            list.add(product);
        }
        return list;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
