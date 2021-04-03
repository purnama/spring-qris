package id.purnama.qris;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <b>4.8</b> Data objects—Additional Data Field Template (ID "62")
 * <b>4.9</b> Data objects—Merchant Information—Language Template (ID "64")
 */
@Component
public class QrisParser {

    public id.purnama.qris.QrisPayload parse(String qris) {
        Map<Integer, id.purnama.qris.QrisDataObject> qrisMap = new LinkedHashMap<>();
        parseRoot(qris, qrisMap);
        parseMerchantAccountInformationTemplate(qrisMap);
        parseMerchantDomesticRepository(qrisMap);
        parseAdditionalDataFieldTemplate(qrisMap);
        parseMerchantInformationLanguageTemplate(qrisMap);
        if(qrisMap.containsKey(62)){
            parseProprietaryDataTemplate(qrisMap.get(62).getTemplateMap());
        }
        return new id.purnama.qris.QrisPayload(qris, qrisMap);
    }

    private void parseMerchantAccountInformationTemplate(Map<Integer, id.purnama.qris.QrisDataObject> qrisMap) {
        for (int i = 26; i <= 45; i++) {
            parseTemplate(qrisMap, i);
        }
    }

    private void parseMerchantDomesticRepository(Map<Integer, id.purnama.qris.QrisDataObject> qrisMap) {
        parseTemplate(qrisMap, 51);
    }

    private void parseAdditionalDataFieldTemplate(Map<Integer, id.purnama.qris.QrisDataObject> qrisMap) {
        parseTemplate(qrisMap, 62);
    }

    private void parseMerchantInformationLanguageTemplate(Map<Integer, id.purnama.qris.QrisDataObject> qrisMap) {
        parseTemplate(qrisMap, 64);
    }

    private void parseProprietaryDataTemplate(Map<Integer, id.purnama.qris.QrisDataObject> qrisMap) {
        parseTemplate(qrisMap, 99);
    }

    private void parseTemplate(Map<Integer, id.purnama.qris.QrisDataObject> qrisMap, int i) {
        if (qrisMap.containsKey(i)) {
            Map<Integer, id.purnama.qris.QrisDataObject> map = new LinkedHashMap<>();
            id.purnama.qris.QrisDataObject object = qrisMap.get(i);
            parser(object.getValue(), map);
            object.setTemplateMap(map);
        }
    }


    private void parseRoot(String qris, Map<Integer, id.purnama.qris.QrisDataObject> qrisMap) {
        parser(qris, qrisMap);
    }

    /**
     * <b>4.3.1.2</b> Hanya boleh terdapat satu data object dengan ID spesifik di bawah root QR Code dan hanya boleh terdapat satu ID spesifik dalam template-nya.
     *
     * @param qris
     * @param qrisMap
     */
    private void parser(String qris, Map<Integer, id.purnama.qris.QrisDataObject> qrisMap) {
        for (int i = 0; i < qris.length(); ) {
            String id = qris.substring(i + 0, i + 2);
            String length = qris.substring(i + 2, i + 4);
            String value = qris.substring(i + 4, i + 4 + Integer.valueOf(length));
            id.purnama.qris.QrisDataObject qrisDataObject = new id.purnama.qris.QrisDataObject(id, length, value);
            qrisMap.merge(qrisDataObject.getIntId(), qrisDataObject, (v1, v2) -> {
                throw new IllegalArgumentException("Duplicate key '" + qrisDataObject.getIntId() + "'.");
            });
            i = i + 4 + Integer.valueOf(length);
        }
    }
}
