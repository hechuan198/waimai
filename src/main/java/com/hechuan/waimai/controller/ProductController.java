package com.hechuan.waimai.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.*;
import com.hechuan.waimai.service.CategoryService;
import com.hechuan.waimai.service.ProductService;
import com.hechuan.waimai.service.impl.ProductServiceImpl;
import com.hechuan.waimai.util.IsBigdecimal;
import com.hechuan.waimai.util.ResultVO;
import com.hechuan.waimai.util.VeDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

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
        for (Product product : productList.getList()) {
            Category categoryRequest = new Category();
            categoryRequest.setId(product.getCategoryId());
            Category category = categoryService.queryCategoryByName(categoryRequest);
            product.setCategory(category.getName());
        }

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
        productRequest.setId("F" + VeDate.getStringId());
        log.info("【添加商品，请求参数】 = {}", JSON.toJSONString(productRequest));

        Category categoryRequest = new Category();
        categoryRequest.setName(productRequest.getCategory());
        categoryRequest.setId(null);
        log.info("【查询分类请求参数】 = {}",JSON.toJSONString(categoryRequest));
        Category category = categoryService.queryCategoryByName(categoryRequest);
        log.info("【查询分类结果】 = {}", JSON.toJSONString(category));
        productRequest.setCategoryId(category.getId());
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
        Category categoryRequest = new Category();
        categoryRequest.setName(productRequest.getCategory());
        categoryRequest.setId(null);
        Category category = categoryService.queryCategoryByName(categoryRequest);
        log.info("【查询分类结果】 = {}", JSON.toJSONString(category));
        productRequest.setCategoryId(category.getId());
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
        Category categoryRequest = new Category();
        categoryRequest.setId(product.getCategoryId());
        Category category = categoryService.queryCategoryByName(categoryRequest);
        product.setCategory(category.getName());
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
        String pathString = null;
        String filename = null;
        if (file != null) {
            filename = "/"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getOriginalFilename();
            pathString = request.getServletContext().getRealPath("/upfiles") + filename;

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
        return ResultVO.success("","upfiles"+filename);
    }

    /**
     * 查询热销商品
     *
     * @param
     * @return
     */
    @PostMapping("queryHotProductList")
    public ResultVO queryHotProductList() {
        log.info("【查询热销商品开始】}");
        List<Product> productList = productService.queryHotProductList();
        log.info("【查询热销商品结果，productList】 = {}",JSON.toJSONString(productList));
        return ResultVO.success(productList);
    }

    @RequestMapping(value = "/getProductExport")
    public ResponseEntity<byte[]> getProductExport() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String date = sdf.format(new Date());
        String fileName = "Product" + date + ".xlsx";
        responseHeaders.set("Content-Disposition", "attachment; filename=" + fileName);
        List<Product> productList = productService.queryHotProductList();
        //转换数据
        List<ProductReportDTO> productReportDTOS = new ArrayList();
        for (Product product : productList) {
            ProductReportDTO productReportDTO = new ProductReportDTO();
            BeanUtils.copyProperties(product,productReportDTO);
            productReportDTOS.add(productReportDTO);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, ProductReportDTO.class).sheet("热销商品").doWrite(productReportDTOS);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(outputStream.toByteArray());
    }
}
