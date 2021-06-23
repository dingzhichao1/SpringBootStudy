//package com.blackstar.springbootstudy.common.util.office.excel;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.poifs.crypt.EncryptionInfo;
//import org.apache.poi.poifs.crypt.EncryptionMode;
//import org.apache.poi.poifs.crypt.Encryptor;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.xssf.usermodel.*;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.Collection;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
///**
// *
//* Description: excel导出工具类
//* @author weiyang.li
//* @date 2019年8月8日
//* @version 1.0
// */
//@Component
//public class ExportExcelUtil<T>{
//
//	// 2007 版本以上 最大支持1048576行
//	public  final static String  EXCEl_FILE_2007 = "2007";
//	// 2003 版本 最大支持65536 行
//	public  final static String  EXCEL_FILE_2003 = "2003";
//
//	/**
//	 * <p>
//	 * 导出无头部标题行Excel <br>
//	 * 时间格式默认：yyyy-MM-dd hh:mm:ss <br>
//	 * </p>
//	 *
//	 * @param title 表格标题
//	 * @param dataset 数据集合
//	 * @param out 输出流
//	 * @param version 2003 或者 2007，不传时默认生成2003版本
//	 */
//	public void exportExcel(String title, Collection<T> dataset, OutputStream out, String version) {
//		if(StringUtils.isEmpty(version) || EXCEL_FILE_2003.equals(version.trim())){
//			exportExcel2003(title,dataset, out, "yyyy-MM-dd HH:mm:ss");
//		}else{
//			exportExcel2007(title, dataset, out, "yyyy-MM-dd HH:mm:ss");
//		}
//	}
//
//	/**
//	 * <p>
//	 * 导出带有头部标题行的Excel <br>
//	 * 时间格式默认：yyyy-MM-dd hh:mm:ss <br>
//	 * </p>
//	 *
//	 * @param title 表格标题
//	 * @param headers 头部标题集合
//	 * @param dataset 数据集合
//	 * @param out 输出流
//	 * @param version 2003 或者 2007，不传时默认生成2003版本
//	 */
//	public void exportExcel(String title,String[] headers, Collection<T> dataset, OutputStream out,String version) {
//		if(StringUtils.isEmpty(version) || EXCEL_FILE_2003.equals(version.trim())){
//			exportExcel2003(title,dataset, out, "yyyy-MM-dd HH:mm:ss");
//		}else{
//			exportExcel2007(title,dataset, out, "yyyy-MM-dd HH:mm:ss");
//		}
//	}
//
//	/**
//	 * <p>
//	 * 通用Excel导出方法,利用反射机制遍历对象的所有字段，将数据写入Excel文件中 <br>
//	 * 此版本生成2007以上版本的文件 (文件后缀：xlsx)
//	 * </p>
//	 *
//	 * @param title
//	 *            表格标题名
//	 * @param dataset
//	 *            需要显示的数据集合,集合中一定要放置符合JavaBean风格的类的对象。此方法支持的
//	 *            JavaBean属性的数据类型有基本数据类型及String,Date
//	 * @param out
//	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
//	 * @param pattern
//	 *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
//	 */
//	public void exportExcel2007(String title, Collection<T> dataset, OutputStream out, String pattern) {
//		exportExcel2007(title, dataset, out, pattern, null);
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public void exportExcel2007(String title, Collection<T> dataset, OutputStream out, String pattern, String password) {
//		// 声明一个工作薄
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		// 生成一个表格
//		XSSFSheet sheet = workbook.createSheet(title);
//		if(CollectionUtils.isEmpty(dataset)) {
//			try {
//				workbook.write(out);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally {
//				try {
//					workbook.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			return;
//		}
//
//		// 设置表格默认列宽度为15个字节
//		sheet.setDefaultColumnWidth(20);
//		// 生成一个样式
//		XSSFCellStyle style = workbook.createCellStyle();
//		// 设置这些样式
//		style.setFillForegroundColor(new XSSFColor(java.awt.Color.gray));
//		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
//		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//		// 生成一个字体
//		XSSFFont font = workbook.createFont();
//		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
//		font.setFontName("宋体");
//		font.setColor(new XSSFColor(java.awt.Color.BLACK));
//		font.setFontHeightInPoints((short) 11);
//		// 把字体应用到当前的样式
//		style.setFont(font);
//		// 生成并设置另一个样式
//		XSSFCellStyle style2 = workbook.createCellStyle();
//		style2.setFillForegroundColor(new XSSFColor(java.awt.Color.WHITE));
//		style2.setWrapText(true);
//		style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//		style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//		style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//		style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
//		style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
//		style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//		style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
//		// 生成另一个字体
//		XSSFFont font2 = workbook.createFont();
//		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
//		// 把字体应用到当前的样式
//		style2.setFont(font2);
//
//		// 产生表格标题行
//		XSSFRow row = sheet.createRow(0);
//		XSSFCell cellHeader;
//		T headerT = ((List<T>) dataset).get(0);
//		Field[] declaredFields = headerT.getClass().getDeclaredFields();
//		// 实际表格标题行下标
//		int realHeaderIndex = 0;
//		for (int i = 0; i < declaredFields.length; i++) {
//			if(declaredFields[i].isAnnotationPresent(ExcelFieldControl.class)) {
//				ExcelFieldControl annotation = declaredFields[i].getAnnotation(ExcelFieldControl.class);
//				if(!annotation.ignore()) {
//					cellHeader = row.createCell(realHeaderIndex++);
//					cellHeader.setCellStyle(style);
//					cellHeader.setCellValue(new XSSFRichTextString(annotation.header()));
//				}
//			}
//		}
//
//		// 遍历集合数据，产生数据行
//		Iterator<T> it = dataset.iterator();
//		int index = 0;
//		T t;
//		Field[] fields;
//		Field field;
//		XSSFRichTextString richString;
//		Pattern p = Pattern.compile("^//d+(//.//d+)?$");
//		Matcher matcher;
//		String fieldName;
//		String getMethodName;
//		XSSFCell cell;
//		Class tCls;
//		Method getMethod;
//		Object value;
//		String textValue;
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//		while (it.hasNext()) {
//			index++;
//			row = sheet.createRow(index);
//			t = (T) it.next();
//			// 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
//			fields = t.getClass().getDeclaredFields();
//			// 实际表格数据行下标
//			int realFieldIndex = 0;
//			for (int i = 0; i < fields.length; i++) {
//				if(!fields[i].isAnnotationPresent(ExcelFieldControl.class)) {
//					continue;
//				}
//				ExcelFieldControl annotation = fields[i].getAnnotation(ExcelFieldControl.class);
//				if(annotation.ignore()) {
//					continue;
//				}
//
//
//				cell = row.createCell(realFieldIndex++);
//				cell.setCellStyle(style2);
//				field = fields[i];
//				fieldName = field.getName();
//				getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
//						+ fieldName.substring(1);
//				try {
//					tCls = t.getClass();
//					getMethod = tCls.getMethod(getMethodName, new Class[] {});
//					value = getMethod.invoke(t, new Object[] {});
//					// 判断值的类型后进行强制类型转换
//					textValue = null;
//					if (value instanceof Integer) {
//						cell.setCellValue((Integer) value);
//					} else if (value instanceof Float) {
//						textValue = String.valueOf((Float) value);
//						cell.setCellValue(textValue);
//					} else if (value instanceof Double) {
//						textValue = String.valueOf((Double) value);
//						cell.setCellValue(textValue);
//					} else if (value instanceof Long) {
//						cell.setCellValue((Long) value);
//					}
//					if (value instanceof Boolean) {
//						textValue = "是";
//						if (!(Boolean) value) {
//							textValue = "否";
//						}
//					} else if (value instanceof Date) {
//						textValue = sdf.format((Date) value);
//					} else {
//						// 其它数据类型都当作字符串简单处理
//						if (value != null) {
//							textValue = value.toString();
//						}
//					}
//					if (textValue != null) {
//						matcher = p.matcher(textValue);
//						if (matcher.matches()) {
//							// 是数字当作double处理
//							cell.setCellValue(Double.parseDouble(textValue));
//						} else {
//							richString = new XSSFRichTextString(textValue);
//							cell.setCellValue(richString);
//						}
//					}
//				} catch (SecurityException e) {
//					e.printStackTrace();
//				} catch (NoSuchMethodException e) {
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					e.printStackTrace();
//				} finally {
//
//				}
//			}
//		}
//
//		try {
//
//			if (!StringUtils.isEmpty(password)) {
//				encrypt(workbook, out, password);
//			}
//
//			workbook.write(out);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				workbook.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private void encrypt(XSSFWorkbook workbook, OutputStream out, String password) {
//		ByteArrayOutputStream bout = null;
//		ByteArrayInputStream workbookinput = null;
//		OPCPackage opc = null;
//		try {
//			bout = new ByteArrayOutputStream();
//			workbook.write(bout);
//			bout.flush();
//			workbookinput = new ByteArrayInputStream(bout.toByteArray());
//			POIFSFileSystem fs = new POIFSFileSystem();
//			EncryptionInfo encryptionInfo = new EncryptionInfo(fs, EncryptionMode.agile);
//			Encryptor enc = encryptionInfo.getEncryptor();
//			enc.confirmPassword(password);
//			OutputStream os = enc.getDataStream(fs);
//			opc = OPCPackage.open(workbookinput);
//			opc.save(os);
//			fs.writeFilesystem(out);
//		}catch (Exception e) {
//
//		}finally {
//			try {
//				if (bout != null) {
//					bout.close();
//				}
//				if (workbookinput != null) {
//					workbookinput.close();
//				}
//				if (opc != null) {
//					opc.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//	/**
//	 * <p>
//	 * 通用Excel导出方法,利用反射机制遍历对象的所有字段，将数据写入Excel文件中 <br>
//	 * 此方法生成2003版本的excel,文件名后缀：xls <br>
//	 * </p>
//	 *
//	 * @param title
//	 *            表格标题名
//	 * @param dataset
//	 *            需要显示的数据集合,集合中一定要放置符合JavaBean风格的类的对象。此方法支持的
//	 *            JavaBean属性的数据类型有基本数据类型及String,Date
//	 * @param out
//	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
//	 * @param pattern
//	 *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public void exportExcel2003(String title, Collection<T> dataset, OutputStream out, String pattern) {
//		// 声明一个工作薄
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		// 生成一个表格
//		HSSFSheet sheet = workbook.createSheet(title);
//		if(CollectionUtils.isEmpty(dataset)) {
//			try {
//				workbook.write(out);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally {
//				try {
//					workbook.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			return;
//		}
//		// 设置表格默认列宽度为15个字节
//		sheet.setDefaultColumnWidth(20);
//		// 生成一个样式
//		HSSFCellStyle style = workbook.createCellStyle();
//		// 设置这些样式
//		style.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		// 生成一个字体
//		HSSFFont font = workbook.createFont();
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		font.setFontName("宋体");
//		font.setColor(HSSFColor.WHITE.index);
//		font.setFontHeightInPoints((short) 11);
//		// 把字体应用到当前的样式
//		style.setFont(font);
//		// 生成并设置另一个样式
//		HSSFCellStyle style2 = workbook.createCellStyle();
//		style2.setFillForegroundColor(HSSFColor.WHITE.index);
//		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		// 生成另一个字体
//		HSSFFont font2 = workbook.createFont();
//		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//		// 把字体应用到当前的样式
//		style2.setFont(font2);
//
//		// 产生表格标题行
//		HSSFRow row = sheet.createRow(0);
//		HSSFCell cellHeader;
//		T headerT = ((List<T>) dataset).get(0);
//		Field[] declaredFields = headerT.getClass().getDeclaredFields();
//		// 实际表格标题行下标
//		int realHeaderIndex = 0;
//		for (int i = 0; i < declaredFields.length; i++) {
//			if(declaredFields[i].isAnnotationPresent(ExcelFieldControl.class)) {
//				ExcelFieldControl annotation = declaredFields[i].getAnnotation(ExcelFieldControl.class);
//				if(!annotation.ignore()) {
//					cellHeader = row.createCell(realHeaderIndex++);
//					cellHeader.setCellStyle(style);
//					cellHeader.setCellValue(new XSSFRichTextString(annotation.header()));
//				}
//			}
//		}
//
//		// 遍历集合数据，产生数据行
//		Iterator<T> it = dataset.iterator();
//		int index = 0;
//		T t;
//		Field[] fields;
//		Field field;
//		HSSFRichTextString richString;
//		Pattern p = Pattern.compile("^//d+(//.//d+)?$");
//		Matcher matcher;
//		String fieldName;
//		String getMethodName;
//		HSSFCell cell;
//		Class tCls;
//		Method getMethod;
//		Object value;
//		String textValue;
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//		while (it.hasNext()) {
//			index++;
//			row = sheet.createRow(index);
//			t = (T) it.next();
//			// 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
//			fields = t.getClass().getDeclaredFields();
//			// 实际表格数据行下标
//			int realFieldIndex = 0;
//			for (int i = 0; i < fields.length; i++) {
//				if(!fields[i].isAnnotationPresent(ExcelFieldControl.class)) {
//					continue;
//				}
//				ExcelFieldControl annotation = fields[i].getAnnotation(ExcelFieldControl.class);
//				if(annotation.ignore()) {
//					continue;
//				}
//				cell = row.createCell(realFieldIndex++);
//				cell.setCellStyle(style2);
//				field = fields[i];
//				fieldName = field.getName();
//				getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
//						+ fieldName.substring(1);
//				try {
//					tCls = t.getClass();
//					getMethod = tCls.getMethod(getMethodName, new Class[] {});
//					value = getMethod.invoke(t, new Object[] {});
//					// 判断值的类型后进行强制类型转换
//					textValue = null;
//					if (value instanceof Integer) {
//						cell.setCellValue((Integer) value);
//					} else if (value instanceof Float) {
//						textValue = String.valueOf((Float) value);
//						cell.setCellValue(textValue);
//					} else if (value instanceof Double) {
//						textValue = String.valueOf((Double) value);
//						cell.setCellValue(textValue);
//					} else if (value instanceof Long) {
//						cell.setCellValue((Long) value);
//					}
//					if (value instanceof Boolean) {
//						textValue = "是";
//						if (!(Boolean) value) {
//							textValue = "否";
//						}
//					} else if (value instanceof Date) {
//						textValue = sdf.format((Date) value);
//					} else {
//						// 其它数据类型都当作字符串简单处理
//						if (value != null) {
//							textValue = value.toString();
//						}
//					}
//					if (textValue != null) {
//						matcher = p.matcher(textValue);
//						if (matcher.matches()) {
//							// 是数字当作double处理
//							cell.setCellValue(Double.parseDouble(textValue));
//						} else {
//							richString = new HSSFRichTextString(textValue);
//							cell.setCellValue(richString);
//						}
//					}
//				} catch (SecurityException e) {
//					e.printStackTrace();
//				} catch (NoSuchMethodException e) {
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					e.printStackTrace();
//				} finally {
//					// 清理资源
//				}
//			}
//		}
//		try {
//			workbook.write(out);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				workbook.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
