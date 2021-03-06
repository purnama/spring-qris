package id.purnama.qris;

public interface QrisNationalNumberingSystem {

    enum Switching{
        ALTO, ARTAJASA, RINTIS, JALIN
    }

    enum Pjsp{
        SINARMAS(93600153, Switching.ALTO),
        MAYBANK(93600016, Switching.ALTO),
        SHOPEE(93600918, Switching.ALTO),
        BLUEPAY(93600919, Switching.ALTO),
        DOKU(93600899, Switching.ALTO),
        ASTRAPAY(93600822, Switching.ALTO),
        BNI(93600009, Switching.ARTAJASA),
        PERMATA(93600013, Switching.ARTAJASA),
        BPD_JATIM(93600114, Switching.ARTAJASA),
        BPD_BALI(93600129, Switching.ARTAJASA),
        BANK_SYARIAH_MANDIRI(93600451, Switching.ARTAJASA),
        NOBU(93600503, Switching.ARTAJASA),
        TSEL_LINK_AJA(93600911, Switching.ARTAJASA),
        OVO(93600912, Switching.ARTAJASA),
        GOPAY(93600914, Switching.ARTAJASA),
        DANA(93600915, Switching.ARTAJASA),
        PAYTREN(93600917, Switching.ARTAJASA),
        UOB(93600023, Switching.ARTAJASA),
        BPD_NTT(93600130, Switching.ARTAJASA),
        GUDANG_VOUCHER(93600916, Switching.ARTAJASA),
        BANK_MUAMALAT(93600147, Switching.ARTAJASA),
        BANK_BPD_JAMBI(93600115, Switching.ARTAJASA),
        BANK_QNB(93600167, Switching.ARTAJASA),
        BANK_COMMONWEALTH(93600950, Switching.ARTAJASA),
        BANK_MAYORA(93600553, Switching.ARTAJASA),
        DANAMON(93600011, Switching.RINTIS),
        BCA(93600014, Switching.RINTIS),
        CIMB(93600022, Switching.RINTIS),
        OCBC(93600028, Switching.RINTIS),
        BANK_JABAR(93600110, Switching.RINTIS),
        BANK_DKI(93600111, Switching.RINTIS),
        NAGARI(93600118, Switching.RINTIS),
        BANK_MEGA(93600426, Switching.RINTIS),
        OTTOCASH(93600811, Switching.RINTIS),
        BPD_SUMSEL_BABEL(93600120, Switching.RINTIS),
        SULSELBAR(93600126, Switching.RINTIS),
        BTPN(93600213, Switching.RINTIS),
        HANA(93600484, Switching.RINTIS),
        KASPRO(93600812, Switching.RINTIS),
        ISAKU(93600920, Switching.RINTIS),
        BPD_DIY(93600112, Switching.RINTIS),
        BANK_SAHABAT_SAMPOERNA(93600523, Switching.RINTIS),
        BANK_ARTHA_GRAHA_INTERNATIONAL(93600037, Switching.RINTIS),
        BRI(93600002, Switching.JALIN),
        MANDIRI(93600008, Switching.JALIN),
        BIMASAKTI(93600815, Switching.JALIN),
        TELKOM(93600898, Switching.JALIN),
        BRISPAY(93600422, Switching.JALIN),
        FINNET(93600777, Switching.JALIN),
        NETZME(93600814, Switching.JALIN),
        MNC_SPIN(93600816, Switching.JALIN),
        PAYDIA(93600818, Switching.JALIN),
        YUKK(93600817, Switching.JALIN);

        private Integer code;
        private Switching switching;

        Pjsp(Integer code, Switching switching) {
            this.code = code;
            this.switching = switching;
        }

        public Integer getCode() {
            return code;
        }

        public Switching getSwitching() {
            return switching;
        }
        public static QrisNationalNumberingSystem.Pjsp valueOf(Integer code) throws IllegalArgumentException {
            QrisNationalNumberingSystem.Pjsp pjspReturn = null;
            for (QrisNationalNumberingSystem.Pjsp pjsp : values())
                if (pjsp.getCode().equals(code)) {
                    pjspReturn = pjsp;
                    break;
                }
            if (pjspReturn == null) throw new IllegalArgumentException("Invalid MCC " + code);
            return pjspReturn;
        }
    }
}
