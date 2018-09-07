package com.home.crm.controller;

import com.home.crm.entity.Baby;
import com.home.crm.entity.Customer;
import com.home.crm.entity.Family;
import com.home.crm.service.BabyService;
import com.home.crm.service.CustomerService;
import com.home.crm.service.FamilyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


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

    private int pageSize = 10;

    private String ALL_DATA = "ALL";
//    @RequestMapping("/")
//    public String index() {
//        return "redirect:/list";
//    }


    @RequestMapping(value = "/list/{pageNo}/{msg}",method = RequestMethod.GET)
//    @RequiresPermissions("customer:view")
    public ModelAndView list(@PathVariable("pageNo")Integer pageNo, @PathVariable("msg") String customerName) {
        ModelAndView mav = new ModelAndView();
        Sort sort = new Sort(Sort.Direction.DESC, "customerId");
//        List<Customer> customers = customerService.findAll(sort);
        if(pageNo==null || pageNo < 1 )
            pageNo = 1;
        int page = pageNo;

        Pageable pageable = PageRequest.of(page-1,pageSize,sort);
//        Page<Customer> customerPage = customerService.findAllByPage(pageable);
        if(customerName==null || customerName.equals(ALL_DATA)) customerName="";
        Page<Customer> customerPage = customerService.findByCustomerNameContains(customerName,pageable);
        pageNo = customerPage.getNumber()+1;
        mav.addObject("customerPage", customerPage);
        mav.addObject("pageIndex",pageNo);
        if(customerName.isEmpty()) customerName = ALL_DATA;
        mav.addObject("customerName",customerName);
        mav.setViewName("customer/list");
        return mav;
    }


    @RequestMapping(value="/add", method= RequestMethod.GET)
//    @RequiresPermissions("customer:add")
    public String toAdd(Customer customer, Baby baby, Family family, Map<String,Object> map) {
        map.put("pageIndex",1);
        map.put("msg",this.ALL_DATA);
        return "/customer/add";
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value="/add",method = RequestMethod.POST)
    @RequiresPermissions("customer:add")
    public String save(@Valid Customer customer, BindingResult result1,
                       @Valid Baby baby, BindingResult result2,
                       @Valid Family family, BindingResult result3,
                       Model Model) throws Exception
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


        //重定向url中带中文需要重新对中文进行utf-8编码
        return "redirect:/customer/list/1/"+URLEncoder.encode(customer.getCustomerName(),"UTF-8");

    }

    @RequestMapping(value = "/toEdit/{id}/{pageNo}")
    @RequiresPermissions("customer:add")
    public ModelAndView edit(@PathVariable("id")Long id,@PathVariable("pageNo")Long pageNo)
    {
        ModelAndView mav = new ModelAndView();
        List<Baby> babyList = babyService.findAllByCustomerId(id);
        List<Family> familyList = familyService.findByCustomerId(id);
        mav.addObject("customer",customerService.findById(id).orElse(new Customer()));
        mav.addObject("baby",babyList.size()>0?babyList.get(0):new Baby());
        mav.addObject("family",familyList.size()>0?familyList.get(0):new Family());
        mav.addObject("pageIndex",pageNo);
        mav.setViewName("customer/add");
        return mav;
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("customer:del")
    public String delete(@PathVariable("id") Long id)
    {
        //id = 3L;
        customerService.delete(id);
        babyService.deleteByCustomerId(id);
        familyService.deleteByCustomerId(id);
        return "redirect:/customer/list/1/"+this.ALL_DATA;
    }

    @RequestMapping("/query")
    @RequiresPermissions("customer:view")
    public ModelAndView query(String customerName) {
        ModelAndView mav = new ModelAndView();
//        List<Customer> customers = customerService.findByCustomerNameContains(customerName);
//        mav.addObject("customers", customers);
        Sort sort = new Sort(Sort.Direction.DESC, "customerId");
        int page = 0;

        if(customerName==null || customerName.equals(ALL_DATA)) customerName="";
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        Page<Customer> customerPage = customerService.findByCustomerNameContains(customerName,pageable);
        mav.addObject("customerPage",customerPage);
        mav.addObject("pageIndex",1);
        if(customerName.isEmpty()) customerName = ALL_DATA;
        mav.addObject("customerName",customerName);
        mav.setViewName("customer/list");
        return mav;
    }

    private void goBug()
    {
        int i = 9/0;
    }
}
