package com.hechuan.waimai.util;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016101800716910";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDdR6u+gUjq8H4lApbGAvfU5KcPOJCQCosbHIibCnzqp5JdiuOPaC4CnO83LAz1TmareMC3X0b6qhfuYzSOHGTta3Jfa1uMPNglufy60iceNycDiYXP/FpUIVBeO0lZtXRnWJpqVhDqsLyxAA//6Moa2lDhpcsOwXwQozJNndP93HXFxuv1HrFi6Y19mVOO2WYJFCAXYqdQiA4okDXTZkGFWvKGAvp1zBKd6uSXMTJRLG5TehVAvT0/H4kOrVcha4ZciYFG6HsiqUE8HSLGz1vIul7MQ+4dGLZnUKfXbJCFyHqvmJvsdcl7WDAGTeX06htcBLptmIYvKC65HrhgnxdAgMBAAECggEAKe2mLrsR/pGe2ZEmSG3AA6gdP/XvWKAsk2S3dqE3ND+8IsQtGj4vz+ONjLR7At1EdUB3AofwioHq2DVPZBGasDknR/fs5BN63Njk/EbtLvMhsiiVH59GeYe0KPDP+rj1A9mF9RuNSm/hpBq91avMt2yi82WvO+1LdJlVXCwiG//hOquaTLuuo6qoEZGYhCmtd9Sei1AmOgI5vO8UIsmLOw8wZmRaQuW+mQaQdlEGOXUG327aHxbUCq2FmEMkhEsg4/nMiQq90rzD3FuBOI2WzMO/DdZkpZP0H23aGM1NSgVQATS44PuYqkOYv5ChHA6lBFXwFcGSMDgeHKQ2BFtOYQKBgQDnjFGLrcicQBIe9mMSisi//g7q+/XJlqekxh8cerxaSL8WXrIngb0IQhJQ0GDVJPYw51hHm/jwFy7GTl4yXEvtkkX+9NmzEqknMVg9TH/uSa67jMvgdSQhJ823bTSm7Z41r7/Rs2XGL0vNkPYJTZO29eoJ9NWJmToUd/uYLEPpyQKBgQCRVvKFlOaiNnDfdgerUARd46NlfljE7kAzOXri/Q57r43eT1SweeKDEWIdhiOcIrSQ6DpdG4rxKr6wCWNOL6/vDaL3mI0xWMDFtxdbiCcLK737en74kWfS14UJ2++tkAjcq5v85I9CA7hUbaRH6imXaiNa+pCtXdbNs0W8D8VH9QKBgCul1dFgxwo9Qa04gOCD7OfUOnetW13O2ndDKaYytMLIJ81NuIOd/0ZJPi6huT80onwBgDCvhDOuCxEywKr5nw5/nlY7TbZl9CEBOkMD4rHGjfuoxN3g9xiQ6kSDf4NgE4zHPn+HM84yKDXt95M1Xn0EykNqNb6kdcBaBcFfxsWRAoGALWVQ/vRRTV1fyAxfvNyTYJgXkz3xkCEyoE1V2sxb17ovv75bn3uSionFcZ9SRBR6/b/rauWNcmJ9iRKFmAhBFZPW0Ssu/rjQDXFwtEBEobKZ9pB+VDlmim6SuU+e/0E742EeqdDiBiaPTp+obmXvc8HNJT+aLOxgiYNzRphOcHkCgYEAkTE9tcdxCTRzjW+NOURaTVlJcG57pkC0xENn0jx+Hd3HArMgPhSUMCm/CPDh2VFl82sy4KelX7GI2fE/9y+0+IMfuITAbKgQzDm/KfA/t/zFLAd/ancEpzJDKIgkaFjwZAcMeBbTXbASWww1P0a8oSJwKT12NdpqKpA/yaF6y0k=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4qq5YmL3RTHTSV2RWcLeZD/LMBEjg2+Ec0RoG852OiRWBN/iZnGcSGkACCzYiTRVHHtml2GLPjBriImlAfozX0dY/qXeIMSCSMdCNwjXI1F20ff1HGMqsxqSB853fZgL/fDXlb86Fj5gIc0iU+C7ePTxtawXkuWgWm4BOcCEnmGQcEIKfbNNkbCG+8mmvnBerHE1pk8TvMInhT8/7DHEf365zTMH+cndnbcqcWtM83mQwgUwUsk9U2p133/xwfqyV6GUKjq9fBMJinvRVzIt40Rxgwel4Dbth3c/15IToLdDpHiaUc0qHCyGO7UGKreOMg6I4StOGOOax0F0NZs98wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/index/payed.action";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}