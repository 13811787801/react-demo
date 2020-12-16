package com.yxxs.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxxs.common.handler.CommonErrorHandler;

@Controller
public class ExcelController extends BaseWwwController {
	
	@RequestMapping("admin/excel/exportExcel") 
	public void exportExcel(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
			) throws IOException{ 
		
        response.setHeader("content-disposition", "attachment;filename="  
                + URLEncoder.encode("excel.xls", "UTF-8"));  
        
		exportExcel(null,null,response.getOutputStream());
	}

	/**
	 * 
	 * @param columns [["columnAlias","hashKeyName"],["columnAlias","hashKeyName"]]
	 * @param list
	 * @param out
	 */
    public static void exportExcel(List<String[]> columns,  
    		List<Hashtable<String, Object>> list, OutputStream out)  
    {  

        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet();  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.VIOLET.index);  
        //font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.WHITE.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        for (int i = 0; i < columns.size(); i++)  
        {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(columns.get(i)[0]);  
            cell.setCellValue(text);  
        }  

        // 遍历集合数据，产生数据行 
        for(int i=0;i<list.size();i++){
            row = sheet.createRow(i+1);  
            
            for (int j = 0; j < columns.size(); j++)  
            {  
                HSSFCell cell = row.createCell(j);  
                cell.setCellStyle(style2);  
                
                Object v = list.get(i).get(columns.get(j)[1]);
                
                HSSFRichTextString text = new HSSFRichTextString((null == v?"":String.valueOf(v)));
                cell.setCellValue(text);  
            }  

        }
        
        try  
        {  
            workbook.write(out);  
        }  
        catch (IOException e)  
        {  
            //e.printStackTrace(); 
			CommonErrorHandler.printException(e); 
        }  
    }
}