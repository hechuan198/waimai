package com.hechuan.waimai.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.*;
import com.hechuan.waimai.service.UserService;
import com.hechuan.waimai.service.impl.ProductServiceImpl;
import com.hechuan.waimai.util.ImageVerificationCode;
import com.hechuan.waimai.util.MD5;
import com.hechuan.waimai.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("user/")
@CrossOrigin
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Autowired
    private UserService userService;

    /**
     * 登录验证
     * @param userDTO
     * @return
     */
    @PostMapping("login")
    public ResultVO login(UserDTO userDTO,String yzm, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

        log.info("【管理员登录开始】，请求参数 = {},验证码 = {}", JSON.toJSONString(userDTO), JSON.toJSONString(yzm));

        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (null == userDTO || StringUtils.isEmpty(userDTO.getUsername()) || StringUtils.isEmpty(userDTO.getPassword())) {
            return ResultVO.error("用户名或密码不能为空");
        }
        /*
        验证用户名密码是否正确
         */
        System.out.println(userDTO);

        String username = userDTO.getUsername();
        Integer role = userDTO.getRole();
        String password = MD5.md5(userDTO.getPassword());

        UserDTO userDTO1 = userService.getUserByUsername(username,role);
        log.info("{}",userDTO1);
        if(null == userDTO1){
            log.info("用户不存在");
            return ResultVO.error("用户不存在");
        }
        System.out.println(userDTO1);
        if (!password.equals(userDTO1.getPassword())){
            log.info("密码不正确");
            return ResultVO.error("密码不正确");
        }
        if (StringUtils.isEmpty(yzm)){
            log.info("验证码不能为空");
            return ResultVO.error("验证码不能为空");
        }
        request.setCharacterEncoding("utf-8");
        String session_vcode = (String) request.getSession().getAttribute("text");
        System.out.println(session_vcode);
        //从session中获取真正的验证码
        if (!session_vcode.equals(yzm)){
            log.info("验证码错误");
            return ResultVO.error("验证码错误");
        }
        log.info("登录成功,username = {}",userDTO1.getUsername());
        return ResultVO.success("登录成功",userDTO1.getUsername());
    }

    /**
     * 注册用户名可用性验证
     * @param username
     * @return
     */
    @PostMapping("registerUsername")
    public ResultVO registerUsername(String username){
        if (null == username){
            return ResultVO.error("用户名不能为空");
        }
        ResultVO resultVO = userService.getConfirmUsername(username);
        return resultVO;
    }

//    /**
//     * 注册
//     * @param userForm
//     * @return
//     */
//    @PostMapping("register")
//    public ResultVO register(@Valid UserForm userForm, BindingResult bindingResult){
//
//        if (null == userForm || StringUtils.isEmpty(userForm.getUsername())){
//            return ResultVO.error("用户名不能为空");
//        }
//
//        System.out.println(userForm);
//
//        ResultVO resultVOUsername = userService.getConfirmUsername(userForm.getUsername());
//        if (resultVOUsername.getCode() == "1"){
//            return resultVOUsername;
//        }
//        if (bindingResult.hasErrors()){
//            String message = bindingResult.getFieldError().getDefaultMessage();
//            return ResultVO.error(message);
//        }
//        /*if (StringUtils.isEmpty(userDTO.getPassword())){
//            return ResultVO.error("密码不能为空");
//        }
//        if (StringUtils.isEmpty(userDTO.getRapassword())){
//            return ResultVO.error("确认密码不能为空");
//        }*/
//        if (!userForm.getPassword().equals(userForm.getRapassword())){
//            return ResultVO.error("两次密码不相同");
//        }
//        /*if (StringUtils.isEmpty(userDTO.getQuestion())){
//            return ResultVO.error("问题不能为空");
//        }
//        if (StringUtils.isEmpty(userDTO.getAnswer())){
//            return ResultVO.error("答案不能为空");
//        }*/
//        UserDTO userDTO = new UserDTO();
//        BeanUtils.copyProperties(userForm,userDTO);
//        ResultVO resultVO = userService.register(userDTO);
//        return resultVO;
//    }

//    /**
//     * 忘记密码，确认用户名
//     * @param username
//     * @return
//     */
//    @PostMapping("username")
//    public ResultVO getUsername(String username){
//        System.out.println(username);
//        if (StringUtils.isEmpty(username)){
//            return ResultVO.error("用户名为空");
//        }
//        ResultVO resultVO = userService.getUsername(username);
//
//
//        return  resultVO;
//    }
//
//    /**
//     * 忘记密码，获取密保问题
//     * @param username
//     * @return
//     */
//    @PostMapping("getQuestion")
//    public ResultVO getQuestion(String username){
//        if (StringUtils.isEmpty(username)){
//            return ResultVO.error("用户为空");
//        }
//        ResultVO resultVO = userService.getQuestion(username);
//        System.out.println(resultVO);
//        return resultVO;
//    }
//
//    /**
//     * 忘记密码，根据用户名、答案查询用户
//     * @param username
//     * @param answer
//     * @return
//     */
//    @PostMapping("question")
//    public ResultVO question(String username,String answer){
//        if (StringUtils.isEmpty(username)){
//            return ResultVO.error("用户名为空");
//        }
//        if (StringUtils.isEmpty(answer)){
//            return ResultVO.error("答案不能为空");
//        }
//        ResultVO resultVO = userService.question(username,answer);
//        return resultVO;
//    }
//
//    /**
//     * 忘记密码，修改密码
//     * @param userDTO
//     * @return
//     */
//    @PostMapping("raPassword")
//    public ResultVO raPassword(UserDTO userDTO){
//        if (null == userDTO){
//            return ResultVO.error("系统错误");
//        }
//        if (StringUtils.isEmpty(userDTO.getUsername())){
//            return ResultVO.error("用户名为空");
//        }
//        if (StringUtils.isEmpty(userDTO.getPassword())){
//            return ResultVO.error("新秘密不能为空");
//        }
//        if (StringUtils.isEmpty(userDTO.getRapassword())){
//            return ResultVO.error("确认密码不能为空");
//        }
//        if (!userDTO.getPassword().equals(userDTO.getRapassword())){
//            return ResultVO.error("两次密码不相同");
//        }
//        ResultVO resultVO = userService.password(userDTO);
//        return resultVO;
//    }

    /**
     * 图片验证码
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("getVerifiCode")
    public void getVerifiCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /*
             1.生成验证码
             2.把验证码上的文本存在session中
             3.把验证码图片发送给客户端
             */
        ImageVerificationCode ivc = new ImageVerificationCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();  //获取验证码
        request.getSession().setAttribute("text", ivc.getText()); //将验证码的文本存在session中
        System.out.println("yanzhengma"+request.getSession().getAttribute("text"));
//        return image;//将验证码图片响应给客户端
        ivc.output(image, response.getOutputStream());
    }

    /**
     * 分页查询用户
     * @return
     */
    @PostMapping("queryUserList")
    public ResultVO queryUserList(UserDTO userDTO){
        log.info("【分页查询用户开始，请求参数】 = {}", JSON.toJSONString(userDTO));
        PageInfo<User> userPageInfo = userService.queryUserList(userDTO);
        log.info("【分页查询用户信息，返回结果】 = {}",JSON.toJSONString(userPageInfo));
        return ResultVO.success(userPageInfo);
    }

    /**
     * 更新用户状态
     * @param userDTO
     * @return
     */
    @PostMapping("updateUserStatus")
    public ResultVO updateUserStatus(UserDTO userDTO){
        log.info("【更新用户状态开始，请求参数】 = {}",JSON.toJSONString(userDTO));
        ResultVO resultVO = userService.updateUserStatus(userDTO);
        log.info("【更新用户状态结束】");
        return resultVO;
    }

    /**
     * 查询每月用户数
     * @return
     */
    @PostMapping("queryUserByMonth")
    public ResultVO queryUserByMonth(){
       log.info("【查询每月用户数开始】");
       List<UserDTO> userDTOList = userService.queryUserByMonth();
       log.info("【查询每月用户数，查询结果】 = {}",JSON.toJSONString(userDTOList));
       return ResultVO.success(userDTOList);
    }


    @RequestMapping(value = "/getUserExport")
    public ResponseEntity<byte[]> getUserExport() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String date = sdf.format(new Date());
        String fileName = "User" + date + ".xlsx";
        responseHeaders.set("Content-Disposition", "attachment; filename=" + fileName);
        List<UserDTO> userDTOList = userService.queryUserByMonth();

        //转换数据
        List<UserReportDTO> userReportDTOS = new ArrayList();
        for (UserDTO userDTO : userDTOList) {
            UserReportDTO userReportDTO = new UserReportDTO();
            BeanUtils.copyProperties(userDTO,userReportDTO);
            userReportDTOS.add(userReportDTO);
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, UserReportDTO.class).sheet("每月用户数").doWrite(userReportDTOS);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(outputStream.toByteArray());
    }
}

