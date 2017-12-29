import com.PinYin4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class testPinYin4J {
    @Test
    public void test() {
        String province = "安徽省";
        String city = "亳州市";
        String distrct = "蕉城区";
        //AHBZJC jiaochengqu
        province = province.substring(0, province.length() - 1);
        city = city.substring(0, city.length() - 1);
        distrct = distrct.substring(0, distrct.length() - 1);
        String shortCode = province + city + distrct;
        String[] headByString = PinYin4jUtils.getHeadByString(shortCode);
        shortCode = StringUtils.join(headByString, "");
        distrct=PinYin4jUtils.hanziToPinyin(distrct,"");
        System.out.println(shortCode);
        System.out.println(distrct);



    }


}
