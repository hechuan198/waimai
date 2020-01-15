package com.hechuan.waimai.util;

import lombok.Data;
import lombok.ToString;

/**
 * 响应结果包装类
 */
@Data
@ToString
public class ResultVO {

    private String code;
    private String message;
    private Object data;


    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("0");
        resultVO.setMessage("成功");
        return resultVO;
    }
    public static ResultVO success(String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("0");
        resultVO.setMessage(message);
        return resultVO;
    }

    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("0");
        resultVO.setMessage("成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO success(String message, Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("0");
        resultVO.setMessage(message);
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO error(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("1");
        resultVO.setMessage("失败");
        return resultVO;
    }

    public static ResultVO error(String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("1");
        resultVO.setMessage(message);
        return resultVO;
    }


}
