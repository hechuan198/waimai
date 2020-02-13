package com.hechuan.waimai.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.Product;
import com.hechuan.waimai.dto.ProductListRequest;
import com.hechuan.waimai.dto.ProductRequest;
import com.hechuan.waimai.service.ProductService;
import com.hechuan.waimai.service.impl.ProductServiceImpl;
import com.hechuan.waimai.util.IsBigdecimal;
import com.hechuan.waimai.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * 分页查询商品
     *
     * @param productListRequest
     * @return
     */
    @PostMapping("queryProductList")
    public ResultVO queryProductList(ProductListRequest productListRequest) {
        log.info("【请求参数】 = {}", productListRequest);

        PageInfo<Product> productList = productService.queryProductList(productListRequest);
        log.info("【分页查询商品结果，productList】 = {}",JSON.toJSONString(productList));
        return ResultVO.success(productList);
    }

    /**
     * 添加商品
     * @param productRequest
     * @return
     */
    @PostMapping("addProduct")
    public ResultVO addProduct(@Valid ProductRequest productRequest, BindingResult bindingResult){
        log.info("【添加商品，请求参数】 = {}", JSON.toJSONString(productRequest));
        if (bindingResult.hasErrors()){
            String message = bindingResult.getFieldError().getDefaultMessage();
            return ResultVO.error(message);
        }
        if (IsBigdecimal.isBigdecimal(productRequest.getPrice()) == false) {
            return ResultVO.error("商品价格格式不正确");
        }
        productService.addProduct(productRequest);
        log.info("【添加商品成功】");
        return ResultVO.success("添加商品成功");
    }

    /**
     * 修改商品
     * @param productRequest
     * @return
     */
    @PostMapping("updataProduct")
    public ResultVO updataProduct(ProductRequest productRequest){

        log.info("【修改商品，请求参数】 = {}",JSON.toJSONString(productRequest));

        productService.updataProduct(productRequest);
        log.info("【修改商品，完成】");
        return ResultVO.success("商品改变状态成功");
    }

    /**
     * 查询单个商品
     * @param productRequest
     * @return
     */
    @PostMapping("queryProduct")
    public ResultVO queryProduct(ProductRequest productRequest){

        log.info("【查询单个商品，请求参数】 = {}", JSON.toJSONString(productRequest));
        Product product = productService.queryProduct(productRequest);
        log.info("【查询单个商品，返回结果】 = {}", JSON.toJSONString(product));
        return ResultVO.success(product);
    }

    /**
     * 上传商品图片
     * @param file
     * @param request
     * @return
     */
    @PostMapping("upload")
    public ResultVO upload(@RequestParam("file") MultipartFile file , HttpServletRequest request) {
        System.out.println(file);
//        log.info("【图片信息】 = {}", JSON.toJSONString(file));
        String pathString = null;
        String filename = null;
        if (file != null) {
            filename =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getOriginalFilename();
            pathString = "G:\\workspace_webStom\\waimai\\img/" + filename;

        }

        try {
            File files = new File(pathString);
            //打印查看上传路径
            log.info("【图片路径】 = {}",pathString);
            if (!files.getParentFile().exists()) {
                files.getParentFile().mkdirs();
            }
            file.transferTo(files);

        } catch (Exception e) {
            log.error("【上传图片出错】 = {}",e.getMessage());
            e.printStackTrace();
        }

        log.info("【上传成功】");
        return ResultVO.success("",filename);


    }
}
