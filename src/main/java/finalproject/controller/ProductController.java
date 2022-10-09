package finalproject.controller;

import com.iviettech.finalproject.repository.CategoryRepository;
import com.iviettech.finalproject.repository.ManufactorRepository;
import com.iviettech.finalproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ManufactorRepository manufactorRepository;

    @RequestMapping(method = GET)
    public String test() {

        return "home";
    }
}
