//package com.github.snippet.javase6;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.UUID;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.math.NumberUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFPatriarch;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFShape;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.CellRangeAddress;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.ShapeTypes;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.sgcc.deptree.bizc.IDepTreeBizc;
//import com.sgcc.electricDeviceManage.bizc.ISpotInspectionBizc;
//import com.sgcc.uap.persistence.IHibernateDao;
//
//public class ElectricDeviceBizc implements IElectricDeviceBizc {
//
//	@Autowired
//	private IHibernateDao hibernateDao;
//
//	@Autowired
//	private ISpotInspectionBizc spotInspectionBizc;
//
//	@Autowired
//	private IDepTreeBizc depTreeBizc;
//
//	private final Log log = LogFactory.getLog(ElectricDeviceBizc.class);
//
//	@Override
//	public Object deleteElectricDevice(Map params) {
//		StringBuffer sql = new StringBuffer();
//		List<Object> paramsList = new ArrayList<Object>();
//		sql.append("DELETE FROM ELECTRIC_DEVICE_MANAGE_INFO WHERE ID = ? ");
//		String id = params.get("id").toString();
//		paramsList.add(id);
//		int result = hibernateDao.updateWithSql(sql.toString(), paramsList.toArray());
//		if (result > 0) {
//			return "删除设备成功";
//		}
//		return "删除设备失败";
//	}
//
//	/**
//	 * return ?,?,?
//	 *
//	 * @param depIds
//	 * @return
//	 */
//	private String getInCondition(List<? extends Object> depIds) {
//		return depIds.stream().map(new Function<Object, String>() {
//			@Override
//			public String apply(Object t) {
//				return "?";
//			}
//		}).collect(Collectors.joining(","));
//	}
//
//	/**
//	 * 获取电站档案列表
//	 */
//
//	/**
//	 *
//	 * @param map
//	 * @param keys
//	 * @return
//	 */
//	private List<Object> getParameters(final Map<String, Object> map, String... keys) {
//		return Arrays.asList(keys).stream().map(new Function<String, Object>() {
//			@Override
//			public Object apply(String s) {
//				return map.get(s);
//			}
//		}).collect(Collectors.toList());
//	}
//
//	public static String getPageQuery(String sql) {
//		return "                                                                                        "
//				+ "SELECT * FROM (                                                                          "
//				+ "  SELECT A.*, ROWNUM RN FROM ( " + sql + ") A                                            "
//				+ "  WHERE ROWNUM <= ?                                                                      "
//				+ ") WHERE RN > ?                                                                           ";
//	}
//
//	public static String getCountQuery(String sql) {
//		return "SELECT count(1) FROM (" + sql + ")";
//	}
//
//	@Override
//	public HSSFWorkbook export(Map map) throws ParseException {
//		SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
//		Date currentYear = yyyy.parse(yyyy.format(new Date()));
//		Calendar instance = Calendar.getInstance();
//		instance.setTime(currentYear);
//		instance.add(Calendar.YEAR, 1);
//		Date nextYear = instance.getTime();
//		String depId = Objects.toString(map.get("CHECK_DEP_ID"), StringUtils.EMPTY);
//		String condition = "";
//		List<Object> paramList = new ArrayList<Object>();
//		String depPath4LoginUser = depTreeBizc.getDepPath4LoginUser("0");
//		paramList.add("/" + depPath4LoginUser + "%");
//
//		if (!StringUtils.isEmpty(depId)) {
//			condition = "where i.CHECK_DEP_ID = ?                                                               ";
//			paramList.add(depId);
//
//		}
//
//		List<Map<String, Object>> info = hibernateDao.queryForListWithSql(
//				"select                                                                               "
//						+ "i.ELECTRIC_DEVICE_ID, i.ELECTRIC_DEVICE_NAME,                                            "
//						+ "to_char(i.INSTALL_TIME, 'yyyy-MM-dd') INSTALL_TIME,                                        "
//						+ "i.ELECTRIC_DEVICE_PARENT_ID,                                                              "
//						+ "p1.PERSON_NAME ELECTRIC_DEVICE_MANAGER,                                                   "
//						+ "p2.PERSON_NAME ELECTRIC_DEVICE_REGULATOR                                                  "
//						+ "from ELECTRIC_DEVICE_MANAGE_INFO i                                                        "
//						+ "join TB_CONTACT_DEP dep                                                                   "
//						+ "  on dep.DEP_ID = i.CHECK_DEP_ID and dep.DEP_FULLPATH like ?                              "
//						+ "left join TB_CONTACT_PERSON p1                                                              "
//						+ "  on i.ELECTRIC_DEVICE_MANAGER = p1.PERSON_ID                                               "
//						+ "left join TB_CONTACT_PERSON p2                                                              "
//						+ "  on i.ELECTRIC_DEVICE_REGULATOR = p2.PERSON_ID                                           "
//						+ condition,
//				paramList.toArray());
//		List<Map<String, Object>> spotInspectionType = spotInspectionBizc.getSpotInspectionType();
//		Map<String, String> typeMaps = new LinkedHashMap<String, String>();
//		for (Map<String, Object> m : spotInspectionType) {
//			typeMaps.put(Objects.toString(m.get("DICT_VALUE"), StringUtils.EMPTY),
//					Objects.toString(m.get("DICT_NAME"), StringUtils.EMPTY));
//		}
//
//		paramList = new ArrayList<Object>();
//		paramList.add("/" + depPath4LoginUser + "%");
//
//		List<String> conditions = new ArrayList<String>();
//
//		if (!StringUtils.isEmpty(depId)) {
//			conditions.add("i.CHECK_DEP_ID = ?                                                               ");
//			paramList.add(depId);
//		}
//
//		conditions.add("c.SPOT_CHECK_TIME >= ?                                                               ");
//		paramList.add(currentYear);
//
//		conditions.add("c.SPOT_CHECK_TIME < ?                                                               ");
//		paramList.add(nextYear);
//
//		List<Map<String, Object>> check = hibernateDao.queryForListWithSql(
//				"SELECT                                                                                 "
//						+ "c.DEVICE_PID,                                                                     "
//						+ "to_char(c.SPOT_CHECK_TIME, 'MM') SPOT_CHECK_TIME,                                 "
//						+ "d.SPOT_CHECK_TYPE,                                                                "
//						+ "d.PASSED                                                                          "
//						+ "FROM                                                                              "
//						+ "  ELECTRIC_DEVICE_SPOT_CHECK c                                                    "
//						+ "join TB_CONTACT_DEP dep                                                                   "
//						+ "  on dep.DEP_ID = c.DEVICE_PID  and dep.DEP_FULLPATH like ?                             "
//						+ "LEFT JOIN ELECTRIC_DEVICE_MANAGE_INFO i                                           "
//						+ "  ON i.ELECTRIC_DEVICE_ID = c.DEVICE_PID                                          "
//						+ "LEFT JOIN ELECTRIC_DEVICE_SPOT_DETAIL d                                           "
//						+ "  ON d.ELECTRIC_DEVICE_SPOT_CHECK_ID = c.ID                                       "
//						+ "where " + String.join(" and ", conditions),
//				paramList.toArray());
//		// DEVICE_PID > SPOT_CHECK_TYPE > SPOT_CHECK_TIME
//		Map<String, Map<String, Map<String, Object>>> checkMaps = new HashMap<String, Map<String, Map<String, Object>>>();
//
//		for (Map<String, Object> m : check) {
//			// DEVICE_PID
//			String devicePid = Objects.toString(m.get("DEVICE_PID"), StringUtils.EMPTY);
//			if (!checkMaps.containsKey(devicePid)) {
//				checkMaps.put(devicePid, new HashMap<String, Map<String, Object>>());
//			}
//			Map<String, Map<String, Object>> dMap = checkMaps.get(devicePid);
//			// SPOT_CHECK_TYPE
//			String spotCheckType = typeMaps.get(Objects.toString(m.get("SPOT_CHECK_TYPE"), StringUtils.EMPTY));
//			if (!dMap.containsKey(spotCheckType)) {
//				dMap.put(spotCheckType, new HashMap<String, Object>());
//			}
//			Map<String, Object> cMap = dMap.get(spotCheckType);
//			// SPOT_CHECK_TIME
//			cMap.put(Objects.toString(m.get("SPOT_CHECK_TIME"), StringUtils.EMPTY),
//					Objects.equals(1, m.get("PASSED")) ? "√" : "×");
//		}
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// log.info("info:" + info);
//		for (Map<String, Object> header : info) {
//			Object electricDeviceId = header.get("ELECTRIC_DEVICE_ID");
//			Map<String, Map<String, Object>> data = checkMaps.get(electricDeviceId);
//			writeSheet(wb, header, typeMaps.values(), data);
//		}
//		return wb;
//	}
//
//	/**
//	 * @param wb
//	 * @param header
//	 * @param types
//	 * @param data
//	 */
//	private void writeSheet(HSSFWorkbook wb, Map<String, Object> header, Collection<String> types,
//			Map<String, Map<String, Object>> data) {
//		 log.info("header: " + header);
//		HSSFSheet sheet = wb.createSheet(Objects.toString(header.get("ELECTRIC_DEVICE_ID"), StringUtils.EMPTY));
//
//		HSSFCellStyle style = getCellStyle(wb);
//
//		HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
//		setHeaderCell(sheet, style, row, "设备名称", 3);
//		setHeaderCell(sheet, style, row, Objects.toString(header.get("ELECTRIC_DEVICE_NAME"), StringUtils.EMPTY), 4);
//		setHeaderCell(sheet, style, row, "设备编号", 3);
//		setHeaderCell(sheet, style, row, Objects.toString(header.get("ELECTRIC_DEVICE_ID"), StringUtils.EMPTY), 5);
//		setHeaderCell(sheet, style, row, "安装日期", 3);
//		setHeaderCell(sheet, style, row, Objects.toString(header.get("INSTALL_TIME"), StringUtils.EMPTY), 4);
//
//		row = sheet.createRow(sheet.getLastRowNum() + 1);
//		setHeaderCell(sheet, style, row, "上一级设备编号", 3);
//		setHeaderCell(sheet, style, row, Objects.toString(header.get("ELECTRIC_DEVICE_PARENT_ID"), StringUtils.EMPTY),
//				4);
//		setHeaderCell(sheet, style, row, "管理责任人", 3);
//		setHeaderCell(sheet, style, row, Objects.toString(header.get("ELECTRIC_DEVICE_MANAGER"), StringUtils.EMPTY), 5);
//		setHeaderCell(sheet, style, row, "监管责任人", 3);
//		setHeaderCell(sheet, style, row, Objects.toString(header.get("ELECTRIC_DEVICE_REGULATOR"), StringUtils.EMPTY),
//				4);
//		// row 3
//		row = sheet.createRow(sheet.getLastRowNum() + 1);
//		setHeaderCell(sheet, style, row, "检查内容             检查月份", null);
//		// 斜线
////		HSSFPatriarch drawingPatriarch = sheet.createDrawingPatriarch();
////		HSSFClientAnchor clientAnchor = new HSSFClientAnchor();
////		clientAnchor.setCol1(0);
////		clientAnchor.setCol2(9);
////		clientAnchor.setRow1(2);
////		clientAnchor.setRow2(2);
////		HSSFSimpleShape simpleShape = drawingPatriarch
////				.createSimpleShape(new HSSFClientAnchor(0, 0, 1023, 255, (short) 0, 2, (short) 9, 2));
////		simpleShape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
////		simpleShape.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);
//		// simpleShape.setLineWidth(HSSFShape.LINEWIDTH_DEFAULT);
//		// simpleShape.setLineStyleColor(0, 0, 0);
//		// 月份
//		for (int i = 0; i < 12; i++) {
//			setHeaderCell(sheet, style, row, i + 1 + "月", null);
//		}
//		// 检查类型
//		for (String type : types) {
//			row = sheet.createRow(sheet.getLastRowNum() + 1);
//			setHeaderCell(sheet, style, row, type, 10);
//			if (data == null) {
//				data = new HashMap<String, Map<String, Object>>();
//			}
//			Map<String, Object> map = data.get(type);
//			if (map == null) {
//				map = new HashMap<String, Object>();
//			}
//			for (int i = 0; i < 12; i++) {
//				setHeaderCell(sheet, style, row,
//						Objects.toString(map.get(StringUtils.leftPad(String.valueOf(i), 2, "0")), StringUtils.EMPTY),
//						null);
//			}
//		}
//		row = sheet.createRow(sheet.getLastRowNum() + 1);
//		setHeaderCell(sheet, style, row, "检查人", 3);
//		setHeaderCell(sheet, style, row, "", 7);
//		for (int i = 0; i < 12; i++) {
//			setHeaderCell(sheet, style, row, "", null);
//		}
//		row = sheet.createRow(sheet.getLastRowNum() + 1);
//		setHeaderCell(sheet, style, row, "备注", 3);
//		setHeaderCell(sheet, style, row, "1.每月至少检查电气(柜)盒1次，并于每月10日前记录在上面表格中。\n" + "2.检查合格打“√”,不合格打“x”，不合格的箭及时整改。\n"
//				+ "3.管理责任人:负责日常检查、维护和保养:出现故障、损坏等异常状况，及时报告。\n" + "4.监管责任人:定期检查管理责任人的工作和设备工作状态:及时处理管理责任人上报的故障、维修信息。", 19);
//	}
//
//	private HSSFCellStyle getCellStyle(HSSFWorkbook wb) {
//		HSSFFont headfont = wb.createFont();
//		headfont.setBoldweight(Font.BOLDWEIGHT_BOLD);
//
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setFont(headfont);
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
//		style.setWrapText(true);// 换行
//		style.setTopBorderColor(HSSFColor.BLACK.index);
//		style.setBorderTop(CellStyle.BORDER_DASHED);
//		style.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
//		style.setBorderRight(CellStyle.BORDER_DASHED);// 边框的大小
//		style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
//		style.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
//		style.setBorderLeft(CellStyle.BORDER_DASHED);// 边框的大小
//		return style;
//	}
//
//	private void setHeaderCell(HSSFSheet sheet, HSSFCellStyle style, HSSFRow row, String name, Integer width) {
//		HSSFCell cell = row.createCell(row.getLastCellNum() + 1);
//		cell.setCellValue(name);
//		cell.setCellStyle(style);
//		if (width != null && width > 1) {
//			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), cell.getColumnIndex(),
//					cell.getColumnIndex() + width));
//		}
//	}
//
//}
