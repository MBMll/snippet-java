package com.github.snippet.javase6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/8/31 23:52
 */

public class POIDemo {

  private HSSFCellStyle getCellStyle(HSSFWorkbook wb) {
    HSSFFont headfont = wb.createFont();
    headfont.setBoldweight(Font.BOLDWEIGHT_BOLD);

    HSSFCellStyle style = wb.createCellStyle();
    style.setFont(headfont);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
    style.setWrapText(true);// 换行
    style.setTopBorderColor(HSSFColor.BLACK.index);
    style.setBorderTop(CellStyle.BORDER_THIN);
    style.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
    style.setBorderRight(CellStyle.BORDER_THIN);// 边框的大小
    style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
    style.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
    style.setBorderLeft(CellStyle.BORDER_THIN);// 边框的大小
    return style;
  }

  private void setHeaderCell(HSSFSheet sheet, HSSFCellStyle style, HSSFRow row, String name, Integer width) {
    HSSFCell cell = row.createCell(row.getLastCellNum() + 1);
    cell.setCellValue(name);
    cell.setCellStyle(style);
    if (width != null && width > 1) {
      sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), cell.getColumnIndex(),
        cell.getColumnIndex() + width-1));
      for (int i = 1; i < width; i++) {
        row.createCell(row.getLastCellNum()).setCellStyle(style);
      }
    }

//    HSSFCell cell1 = sheet.getRow(row.getRowNum()).getCell(cell.getColumnIndex());
//    cell1.setCellStyle(style);
  }

  @Test
  public void test() throws IOException {
    HSSFWorkbook workbook = new HSSFWorkbook();

    HSSFSheet sheet = workbook.createSheet("a");
    HSSFRow row = sheet.createRow(sheet.getLastRowNum());
    HSSFCellStyle style = getCellStyle(workbook);
    setHeaderCell(sheet, style, row, "设备名称", 3);
    row = sheet.createRow(sheet.getLastRowNum() + 1);
    setHeaderCell(sheet, style, row, "aaaaaaa", 10);
    row = sheet.createRow(sheet.getLastRowNum() + 1);
    setHeaderCell(sheet, style, row, "bbbbbbbb", 10);

    // 斜线
    HSSFPatriarch drawingPatriarch = sheet.createDrawingPatriarch();
    HSSFSimpleShape simpleShape = drawingPatriarch
      .createSimpleShape(new HSSFClientAnchor(0, 0, 1023, 255, (short) 0, 2, (short) 9, 2));
    simpleShape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
    simpleShape.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);
    simpleShape.setLineWidth(HSSFShape.LINEWIDTH_DEFAULT);
    simpleShape.setLineStyleColor(0, 0, 0);


    FileOutputStream outputStream = new FileOutputStream(
      new File("D:\\repository\\snippet-java\\java-se\\jdk6\\target\\test.xls"));
    workbook.write(outputStream);
    outputStream.flush();
    outputStream.close();
  }
}
