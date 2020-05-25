package com.newly.vas.business.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class base64Utils {


    public static void imageBase64Test() {
        // String imgUrl = "http://192.168.9.111:8081/kprm-server/static/32e92c7317b54d61be028e00ac87554c.jpg";
        String imgUrl = "http://192.168.9.111/kprm-server/static/bc6243d0d0b447dc9d4e4ac914dae596.jpg";
        // String imgUrl = "http://192.168.9.111:8081/kprm-server/static/32e92c7317b54d61be028e00ac87554c.jpg";
        String base64 = image2Base64(imgUrl);

        byte[] decode = decode(base64);
        System.out.println(base64);
        System.out.println("===================");
        System.out.println(decode.length);
        System.out.println(decodeBuffer(base64));

    }

    public static void main(String[] args) throws Exception {
        imageBase64Test();
    }

    /**
     * 将网络图片编码为base64
     *
     * @param url
     * @return
     * @throws
     */
    public static String encodeImageToBase64(URL url) throws Exception {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        System.out.println("图片的路径为:" + url.toString());
        //打开链接
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //关闭输入流
            inStream.close();
            byte[] data = outStream.toByteArray();
            //对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(data);
            System.out.println("网络文件[{}]编码成base64字符串:[{}]" + base64);
            return base64;//返回Base64编码过的字节数组字符串
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("图片上传失败,请联系客服!");
        }
    }

    public static String image2Base64(String imgUrl) {
        URL url;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            url = new URL(imgUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();

            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[2048];
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inputStream.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                baos.write(buffer, 0, len);
            }
            // 对字节数组Base64编码
            return encode(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return imgUrl;
    }

    public static String encode(byte[] image) {
        BASE64Encoder decoder = new BASE64Encoder();
        return replaceEnter(decoder.encode(image));
    }

    public static String replaceEnter(String str) {
        String reg = "[\n-\r]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    /**
     * 字符串转图片
     *
     * @param base64Str
     * @return
     */
    public static byte[] decode(String base64Str) {
        byte[] b = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            b = decoder.decodeBuffer(replaceEnter(base64Str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static ByteBuffer decodeBuffer(String base64Str) {
        ByteBuffer buffer = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            buffer = decoder.decodeBufferToByteBuffer(base64Str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    //检测是否是正常的url图片链接
    public static boolean testURL(String imgurl) throws IOException {
        URL url = new URL(imgurl);
        HttpURLConnection urlcon2 = (HttpURLConnection) url.openConnection();
        Long TotalSize = Long.parseLong(urlcon2.getHeaderField("Content-Length"));
        if (TotalSize > 0) {
            return true;
        } else {
            return false;
        }
    }
}
