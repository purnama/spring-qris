package id.purnama.qris;

import id.purnama.qris.object.*;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * <b>4.8</b> Data objects—Additional Data Field Template (ID "62")
 * <b>4.9</b> Data objects—Merchant Information—Language Template (ID "64")
 */
@Component
public class QrisParser {

    public QrisPayload parse(String qris) {
        QrisPayload qrisPayload = new QrisPayload();
        qrisPayload.setPayload(qris);
        parse(qrisPayload);
        return qrisPayload;
    }

    public void parse(QrisPayload payload) {
        Map<Integer, QrisDataObject> qrisMap = new LinkedHashMap<>();
        parseRoot(payload.getPayload(), qrisMap);
        parseMerchantAccountInformationTemplate(qrisMap);
        parseMerchantDomesticRepository(qrisMap);
        parseAdditionalDataFieldTemplate(qrisMap);
        parseMerchantInformationLanguageTemplate(qrisMap);
        if(qrisMap.containsKey(62)){
            parseProprietaryDataTemplate(qrisMap.get(62).getTemplateMap());
        }
        payload.setQrisRoot(qrisMap);
    }

    public Qris map(Map<Integer, QrisDataObject> payload) {
        Qris object = new Qris();
        object.setPayloadFormatIndicator(payload.get(0).getValue());
        object.setPointOfInitiationMethod(Integer.valueOf(payload.get(1).getValue()));
        mapMerchantAccountInformation(payload, object);
        mapDomesticCentralRepository(payload, object);
        object.setMerchantCategoryCode(Integer.valueOf(payload.get(52).getValue()));
        for(Currency currency : Currency.getAvailableCurrencies()) {
            if(currency.getNumericCodeAsString().equals(payload.get(53).getValue())) {
                object.setTransactionCurrency(currency);
                break;
            }
        }
        if(payload.containsKey(54)) {
            object.setTransactionAmount(Double.valueOf(payload.get(54).getValue()));
        }
        mapTip(payload, object);
        mapCountryCode(payload, object);
        object.setMerchantName(payload.get(59).getValue());
        object.setMerchantCity(payload.get(60).getValue());
        if(payload.containsKey(61)) {
            object.setPostalCode(Integer.valueOf(payload.get(61).getValue()));
        }
        mapAdditionalData(payload);
        mapMerchantInformationLanguage(payload, object);
        object.setCrc(payload.get(63).getValue());
        return object;
    }

    private void mapMerchantInformationLanguage(Map<Integer, QrisDataObject> payload, Qris object) {
        if(payload.containsKey(64)){
            MerchantInformationLanguage merchantInformationLanguage = new MerchantInformationLanguage();
            String languagePreference = payload.get(64).getTemplateMap().get(0).getValue();
            for(Locale locale : Locale.getAvailableLocales()){
                if(languagePreference.equals(locale.getISO3Language())){
                    merchantInformationLanguage.setLanguagePreference(locale);
                }
            }
            merchantInformationLanguage.setMerchantNameAlternateLanguage(payload.get(64).getTemplateMap().get(1).getValue());
            merchantInformationLanguage.setMerchantCityAlternateLanguage(payload.get(64).getTemplateMap().get(2).getValue());
            object.setMerchantInformationLanguage(merchantInformationLanguage);
        }
    }

    private void mapAdditionalData(Map<Integer, QrisDataObject> payload) {
        if(payload.containsKey(62)){
            AdditionalData additionalData = new AdditionalData();
            Map<Integer, String> dataObjects = new LinkedHashMap<>();
            for(int i = 1; i<=8; i++){
                if(payload.get(62).getTemplateMap().containsKey(i)){
                    dataObjects.put(i, payload.get(62).getTemplateMap().get(i).getValue());
                }
            }
            additionalData.setDataObjects(dataObjects);
            if(payload.get(62).getTemplateMap().containsKey(9)){
                additionalData.setConsumerDataRequest(payload.get(62).getTemplateMap().get(9).getValue());
            }
            if(payload.get(62).getTemplateMap().containsKey(99)){
                ProprietaryDomestic proprietaryDomestic = new ProprietaryDomestic();
                proprietaryDomestic.setProprietary(payload.get(62).getTemplateMap().get(99).getTemplateMap().get(0).getValue());
                proprietaryDomestic.setProprietary(payload.get(62).getTemplateMap().get(99).getTemplateMap().get(1).getValue());
                additionalData.setProprietaryDomestic(proprietaryDomestic);
            }
        }
    }

    private void mapCountryCode(Map<Integer, QrisDataObject> payload, Qris object) {
        for(Locale locale : Locale.getAvailableLocales()) {
            if(locale.getCountry().equals(payload.get(58).getValue())) {
                object.setCountryCode(locale);
            }
        }
    }

    private void mapTip(Map<Integer, QrisDataObject> payload, Qris object) {
        if(payload.containsKey(55)) {
            Tip tip = new Tip();
            tip.setIndicator(payload.get(55).getValue());
            if(payload.containsKey(56)){
                tip.setFixed(Double.valueOf(payload.get(56).getValue()));
            }
            if(payload.containsKey(57)){
                tip.setPercentage(Double.valueOf(payload.get(57).getValue()));
            }
            object.setTip(tip);
        }
    }

    private void mapDomesticCentralRepository(Map<Integer, QrisDataObject> payload, Qris object) {
        if(payload.containsKey(51)){
            Map<Integer, QrisDataObject> merchantAccountInformationMap = payload.get(51).getTemplateMap();
            MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation();
            merchantAccountInformation.setGloballyUniqueIdentifier(merchantAccountInformationMap.get(0).getValue());
            merchantAccountInformation.setMerchantId(merchantAccountInformationMap.get(2).getValue());
            object.setDomesticCentralRepository(merchantAccountInformation);
        }
    }

    private void mapMerchantAccountInformation(Map<Integer, QrisDataObject> payload, Qris object) {
        for(int i=26; i<=45; i++){
            if(payload.containsKey(i)){
                Map<Integer, QrisDataObject> merchantAccountInformationMap = payload.get(i).getTemplateMap();
                MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation();
                merchantAccountInformation.setGloballyUniqueIdentifier(merchantAccountInformationMap.get(0).getValue());
                merchantAccountInformation.setPersonalAccountNumber(merchantAccountInformationMap.get(1).getValue());
                merchantAccountInformation.setMerchantId(merchantAccountInformationMap.get(2).getValue());
                merchantAccountInformation.setCriteria(MerchantCriteria.valueOf(merchantAccountInformationMap.get(3).getValue()));
                if(object.getMerchantAccountInformationDomestics() == null){
                    object.setMerchantAccountInformationDomestics(new LinkedHashMap<>());
                }
                object.getMerchantAccountInformationDomestics().put(i, merchantAccountInformation);
            }
        }
    }

    private void parseMerchantAccountInformationTemplate(Map<Integer, QrisDataObject> qrisMap) {
        for (int i = 26; i <= 45; i++) {
            parseTemplate(qrisMap, i);
        }
    }

    private void parseMerchantDomesticRepository(Map<Integer, QrisDataObject> qrisMap) {
        parseTemplate(qrisMap, 51);
    }

    private void parseAdditionalDataFieldTemplate(Map<Integer, QrisDataObject> qrisMap) {
        parseTemplate(qrisMap, 62);
    }

    private void parseMerchantInformationLanguageTemplate(Map<Integer, QrisDataObject> qrisMap) {
        parseTemplate(qrisMap, 64);
    }

    private void parseProprietaryDataTemplate(Map<Integer, QrisDataObject> qrisMap) {
        parseTemplate(qrisMap, 99);
    }

    private void parseTemplate(Map<Integer, QrisDataObject> qrisMap, int i) {
        if (qrisMap.containsKey(i)) {
            Map<Integer, QrisDataObject> map = new LinkedHashMap<>();
            QrisDataObject object = qrisMap.get(i);
            parser(object.getValue(), map);
            object.setTemplateMap(map);
        }
    }


    private void parseRoot(String qris, Map<Integer, QrisDataObject> qrisMap) {
        parser(qris, qrisMap);
    }

    /**
     * <b>4.3.1.2</b> Hanya boleh terdapat satu data object dengan ID spesifik di bawah root QR Code dan hanya boleh terdapat satu ID spesifik dalam template-nya.
     */
    private void parser(String qris, Map<Integer, QrisDataObject> qrisMap) {
        String length;
        for (int i = 0 ; i < qris.length(); i = i + 4 + Integer.parseInt(length)) {
            String id = qris.substring(i, i + 2);
            length = qris.substring(i + 2, i + 4);
            String value = qris.substring(i + 4, i + 4 + Integer.parseInt(length));
            QrisDataObject qrisDataObject = new QrisDataObject(id, length, value);
            qrisMap.merge(qrisDataObject.getIntId(), qrisDataObject, (v1, v2) -> {
                throw new IllegalArgumentException("Duplicate key '" + qrisDataObject.getIntId() + "'.");
            });
        }
    }
}
