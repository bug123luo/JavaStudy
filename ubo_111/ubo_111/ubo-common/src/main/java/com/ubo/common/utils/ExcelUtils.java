package com.ubo.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelUtils {
	
	
	public static final int EXCEL_HEAD_TITLE_LENGTH = 8;
	public static final String[] EXCEL_HEAD_TITLE = {
		"IMEI号:",
		"学号",
		"姓名",
		"性别",
		"出生日期",
		"监护人（父）手机号",
		"监护人（母）手机号",
		"其他监护人"	
	};

	public static void main(String[] args) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\友娃项目\\学生信息表.xls");

			POIFSFileSystem ts = new POIFSFileSystem(fis);
			HSSFWorkbook wb = new HSSFWorkbook(ts);
			HSSFSheet sh = wb.getSheetAt(0);
			HSSFRow ro = null;
			for (int i = 0; sh.getRow(i) != null; i++) {
				ro = sh.getRow(i);
				for (int j = 0; ro.getCell(j) != null && !"".equals(ro.getCell(j).toString()); j++) {
					if (i > 0 && j == 4) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
						Date date = sdf.parse(ro.getCell(j).getStringCellValue());
						System.out.print(sdf2.format(date) + "\t");
					} else {
						System.out.print(ro.getCell(j) + "\t");
					}
				}
				if (ro.getCell(0) == null || "".equals(ro.getCell(0).toString())) {
					break;
				}
				System.out.println();
			}

			// System.err.println(getSdudentInfoFromExcel(fis).size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
	}

	public static List<StudentInfo> getStudentInfoFromExcel(String filePath) throws Exception {
		List<StudentInfo> list = new ArrayList<>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			POIFSFileSystem ts = new POIFSFileSystem(fis);
			HSSFWorkbook workbook = new HSSFWorkbook(ts);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = null;
			StudentInfo studentInfo = null;
			Info info = null;
			for (int i = 0; sheet.getRow(i) != null; i++) { // 从第一行开始读取
				row = sheet.getRow(i);

				// 判断头部是否符合格式化
				/*************************************************************************
				 * IMEI号       学号       姓名        性别        出生日期           监护人（父）手机号         监护人（母）手机号           其他监护人 *
				 *************************************************************************/
				if (i == 0) {
					for (int j=0; j< EXCEL_HEAD_TITLE_LENGTH; j++) {
						HSSFCell headTitle = row.getCell(j);
						if (headTitle == null || "".equals(headTitle.toString().trim())
								|| !EXCEL_HEAD_TITLE[j].equals(headTitle.toString().trim())) {
							throw new Exception("header cheak error");
						}
					}
					continue;
				}

				studentInfo = new StudentInfo();
				info = new Info();
				
				for (int j = 0; row.getCell(j) != null && !"".equals(row.getCell(j).toString()); j++) {
					String rowVal = row.getCell(j).toString();
					switch (j) {
					case 0:
						studentInfo.imei = rowVal;
						break;
					case 1:
						info.studentNumber = rowVal;
						break;
					case 2:
						info.name = rowVal;
						break;
					case 3:
						info.sex = rowVal;
						break;
					case 4:
						info.birthday = rowVal;
						break;
					case 5:
						studentInfo.guardianFatherPhone = rowVal;
						break;
					case 6:
						studentInfo.guardianMotherPhone = rowVal;
						break;
					case 7:
						studentInfo.guardianOtherPhone = rowVal;
						break;
					default:
						break;
					}
				}

				// 排除空行
				if (studentInfo.imei == null) {
					continue;
				}
				
				studentInfo.info = info;
				list.add(studentInfo);
			}
		} finally {
			fis.close();
		}
		return list;

	}

	public static List<StudentInfo> getSdudentInfoFromExcel(FileInputStream fis) throws Exception {
		List<StudentInfo> list = new ArrayList<>();
		try {
			POIFSFileSystem ts = new POIFSFileSystem(fis);
			HSSFWorkbook workbook = new HSSFWorkbook(ts);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = null;
			StudentInfo studentInfo = null;
			Info info = null;
			for (int i = 1; sheet.getRow(i) != null; i++) { // 从第一行开始读取
				row = sheet.getRow(i);
				
				// 判断头部是否符合格式化
				/*************************************************************************
				 * IMEI号       学号       姓名        性别        出生日期           监护人（父）手机号         监护人（母）手机号           其他监护人 *
				 *************************************************************************/
				if (i == 0) {
					for (int j=0; j< EXCEL_HEAD_TITLE_LENGTH; j++) {
						HSSFCell headTitle = row.getCell(j);
						if (headTitle == null || "".equals(headTitle.toString().trim())
								|| !EXCEL_HEAD_TITLE[j].equals(headTitle.toString().trim())) {
							throw new Exception("header cheak error");
						}
					}
					continue;
				}
				
				studentInfo = new StudentInfo();
				info = new Info();
				for (int j = 0; row.getCell(j) != null && !"".equals(row.getCell(j).toString()); j++) {
					String rowVal = row.getCell(j).toString();
					switch (j) {
					case 0:
						studentInfo.imei = rowVal;
						break;
					case 1:
						info.studentNumber = rowVal;
						break;
					case 2:
						info.name = rowVal;
						break;
					case 3:
						info.sex = rowVal;
						break;
					case 4:
						info.birthday = rowVal;
						break;
					case 5:
						studentInfo.guardianFatherPhone = rowVal;
						break;
					case 6:
						studentInfo.guardianMotherPhone = rowVal;
						break;
					case 7:
						studentInfo.guardianOtherPhone = rowVal;
						break;
					default:
						break;
					}
				}

				// 排除空行
				if (studentInfo.imei == null) {
					continue;
				}
				
				studentInfo.info = info;
				list.add(studentInfo);
			}
		} finally {
			fis.close();
		}
		return list;
	}

	static final class StudentInfo {
		String imei;
		Info info;
		String guardianFatherPhone;
		String guardianMotherPhone;
		String guardianOtherPhone;
	}
	
	static final class Info {
		String studentNumber;
		String name;
		String sex;
		String birthday;
	}
}
