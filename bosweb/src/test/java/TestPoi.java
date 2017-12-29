import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestPoi {
    @Test
    public void test(){
       String path= "C:\\Users\\dell3020mt-41\\Desktop\\12\\【阶段11】物流BOS系统\\BOS-day05\\BOS-day05\\资料\\区域导入测试数据.xls";
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(new File(path)));
            HSSFSheet sheetAt = hssfWorkbook.getSheetAt(0);
            for(Row row : sheetAt){
                System.out.println();
                for (Cell cell :row){
                    String stringCellValue = cell.getStringCellValue();
                    System.out.print(stringCellValue+"  ");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
