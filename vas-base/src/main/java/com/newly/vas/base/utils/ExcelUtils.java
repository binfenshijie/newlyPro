package com.newly.vas.base.utils;

import com.newly.common.constant.ErrorCode;
import com.newly.common.exception.BusinessException;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public class ExcelUtils {

    public static void downloadExcel(String fileName, HttpServletResponse response, Workbook workbook){
     /*   try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
            response.setHeader("content-Type","application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("content-Type", "application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.ERROR, e.getMessage(), e);
        }
    }

}
