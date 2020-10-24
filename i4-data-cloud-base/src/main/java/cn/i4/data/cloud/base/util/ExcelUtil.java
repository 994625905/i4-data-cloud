package cn.i4.data.cloud.base.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.i4.data.cloud.base.exception.CommonException;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 封装easy poi对于excel的操作
 * @author wangjc
 * @title: ExcelUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:55
 */
public class ExcelUtil {

    /**
     * 导出成excel,对带注解的实体,是否创造头信息
     * @param list：实体数据
     * @param title：excel的标题栏
     * @param sheetName：excel左下角的表名
     * @param pojoClass：实体泛型
     * @param fileName：文件名称（带后缀）
     * @param isCreateHeader：是否创造头信息
     * @param response：请求响应
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    /**
     * 导出成excel,对带注解的实体
     * @param list：实体数据
     * @param title：excel的标题栏
     * @param sheetName：excel左下角的表名
     * @param pojoClass：实体泛型
     * @param fileName：文件名称（带后缀）
     * @param response：请求响应
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    /**
     * 导出成excel,不对实体
     * @param list：构造的数据，map类型，
     * @param fileName：文件名称（带后缀）
     * @param response：请求响应
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
        if (workbook != null);
        try {
            downLoadExcel(fileName, response, workbook);
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws CommonException{
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new CommonException(e.getMessage());
        }
    }
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null);
        try {
            downLoadExcel(fileName, response, workbook);
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入excel，根据路径来导入，
     * @param filePath：文件路径
     * @param titleRows：标题占行数
     * @param headerRows：表头占行数
     * @param pojoClass：对应实体的泛型
     * @param <T> 实体泛型
     * @return （只是提供返回list的方式存储获取到excel的值）
     */
    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass) throws CommonException{
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new CommonException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        return list;
    }

    /**
     * 导入excel，线上导入
     * @param file：一般为HTTP请求的流加载
     * @param titleRows：标题占行数
     * @param headerRows：表头占行数
     * @param pojoClass：对应实体的泛型
     * @param <T> 实体泛型
     * @return （只是提供返回list的方式存储获取到excel的值）
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws CommonException{
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new CommonException("excel文件不能为空");
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
        return list;
    }
}
