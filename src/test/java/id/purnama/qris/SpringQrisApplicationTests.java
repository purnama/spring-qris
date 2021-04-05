package id.purnama.qris;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert.*;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
class SpringQrisApplicationTests {

    @Test
    void contextLoads() {
        String qris =
                "00" + "02" + "01" +
                        "01" + "02" + "11" +
                        "26" + "53" +   "00" + "12" + "COM.DOKU.WWW" +
                        "01" + "18" + "936008990000002475" +
                        "02" + "04" + "2475" +
                        "03" + "03" + "URE" +
                        "51" + "44" +   "00" + "14" + "ID.CO.QRIS.WWW" +
                        "02" + "15" + "ID1020039293482" +
                        "03" + "03" + "URE" +
                        "52" + "04" + "5399" +
                        "53" + "03" + "360" +
                        "58" + "02" + "ID" +
                        "59" + "11" + "GUREUM SHOP" +
                        "60" + "13" + "JAKARTA PUSAT" +
                        "61" + "05" + "10640" +
                        "62" + "07" + "0703A01" +
                        "63" + "04" + "455C";
        String qrisCrc =
                "00" + "02" + "01" +
                        "01" + "02" + "11" +
                        "26" + "53" +   "00" + "12" + "COM.DOKU.WWW" +
                        "01" + "18" + "936008990000002475" +
                        "02" + "04" + "2475" +
                        "03" + "03" + "URE" +
                        "51" + "44" +   "00" + "14" + "ID.CO.QRIS.WWW" +
                        "02" + "15" + "ID1020039293482" +
                        "03" + "03" + "URE" +
                        "52" + "04" + "5399" +
                        "53" + "03" + "360" +
                        "58" + "02" + "ID" +
                        "59" + "11" + "GUREUM SHOP" +
                        "60" + "13" + "JAKARTA PUSAT" +
                        "61" + "05" + "10640" +
                        "62" + "07" + "0703A01" +
                        "63" + "04" ;
        QrisParser qrisParser = new QrisParser();
        qrisParser.parse(qris);
        CRC32 crc32 = new CRC32();
        crc32.update(qrisCrc.getBytes(StandardCharsets.UTF_8));
        assertEquals("455C", Long.toHexString(crc32.getValue()));

    }

    @Test
    void qrisTest(){
        String qris = "00020101021126630014ID.SPINPAY.WWW011893600816343100062402121314310006240303UMI51440014ID.CO.QRIS.WWW0215ID10200384314890303UMI5204839853033605802ID5904SPIN6013Jakarta Pusat61051034062140103***0703A0163045CDD";
        QrisParser qrisParser = new QrisParser();
        qrisParser.parse(qris);
        assertTrue(true);
    }

    @Test
    void qrisTest2(){
        String qris = "00020101021226530012COM.DOKU.WWW0118936008990000002475020424750303URE51440014ID.CO.QRIS.WWW0215ID10200392934820303URE5204539953033605404100055020256035005802ID5911GUREUM SHOP6013JAKARTA PUSAT61051064062070703A016304FD26";
        QrisParser qrisParser = new QrisParser();
        qrisParser.parse(qris);
        assertTrue(true);
    }
}
