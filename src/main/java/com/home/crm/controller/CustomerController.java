package com.home.crm.controller;

import com.home.crm.entity.Baby;
import com.home.crm.entity.Customer;
import com.home.crm.entity.Family;
import com.home.crm.service.BabyService;
import com.home.crm.service.CustomerService;
import com.home.crm.service.FamilyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: xu.dm
 * @Date: 2018/6/10 17:28
 * @Description:
 */

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;
    @Resource
    private BabyService babyService;
    @Resource
    private FamilyService familyService;

//    @RequestMapping("/")
//    public String index() {
//        return "redirect:/list";
//    }

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        Sort sort = new Sort(Sort.Direction.ASC, "customerId");
//        List<Customer> customers = customerService.findAll(sort);

        int page = 0;
        int size = 10;

        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Customer> customerPage = customerService.findAllByPage(pageable);
        mav.addObject("customerPage", customerPage);
        mav.setViewName("customer/list");
        return mav;
    }


    @RequestMapping(value="/add", method= RequestMethod.GET)
    public String toAdd(Customer customer, Baby baby, Family family) {
        return "customer/add";
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public String save(@Valid Customer customer, BindingResult result1,
                       @Valid Baby baby, BindingResult result2,
                       @Valid Family family, BindingResult result3)
    {
        if(result1.hasErrors() || result2.hasErrors() || result3.hasErrors())
        {
            return "customer/add";
        }


        customer = customerService.saveAndFlush(customer);
//        goBug();
        baby.setCustomerId(customer.getCustomerId());
        family.setCustomerId(customer.getCustomerId());
        babyService.save(baby);
        familyService.save(family);

        return "redirect:/customer/list";
    }

    @RequestMapping(value = "/toEdit/{id}")
    public ModelAndView edit(@PathVariable("id")Long id)
    {
        ModelAndView mav = new ModelAndView();
        List<Baby> babyList = babyService.findAllByCustomerId(id);
        List<Family> familyList = familyService.findByCustomerId(id);
        mav.addObject("customer",customerService.findById(id).orElse(new Customer()));
        mav.addObject("baby",babyList.size()>0?babyList.get(0):new Baby());
        mav.addObject("family",familyList.size()>0?familyList.get(0):new Family());
        mav.setViewName("/customer/add");
        return mav;
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/delete")
    public String delete(Long id)
    {
        //id = 3L;
        customerService.delete(id);
        babyService.deleteByCustomerId(id);
        familyService.deleteByCustomerId(id);
        return "redirect:/customer/list";
    }

    @RequestMapping("/query")
    public ModelAndView query(String customerName) {
        ModelAndView mav = new ModelAndView();
//        List<Customer> customers = customerService.findByCustomerNameContains(customerName);
//        mav.addObject("customers", customers);
        Sort sort = new Sort(Sort.Direction.ASC, "customerId");
        int page = 0;
        int size = 10;

        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Customer> customerPage = customerService.findByCustomerNameContains(customerName,pageable);
        mav.addObject("customerPage",customerPage);
        mav.setViewName("customer/list");
        return mav;
    }

    private void goBug()
    {
        int i = 9/0;
    }
}
