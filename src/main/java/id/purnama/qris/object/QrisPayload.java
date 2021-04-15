package id.purnama.qris.object;

import id.purnama.qris.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author Arthur Purnama
 */
@CheckSum
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QrisPayload {

    /**
     * <b>4.1 Payload</b>
     * <p>Panjang karakter dari konten QR Code sebaiknya tidak melebihi 512 karakter. Harap diperhatikan apabila menggunakan karakter Unicode karena akan berdampak pada sisa kapasitas payload.</p>
     */
    @Size(max = 512)
    private String payload;

    /**
     * * <b>3.2 Organisasi Data</b>
     * <p>Payload Format Indicator (ID "00") adalah data object pertama di bawah root. CRC (ID "63") adalah data object terakhir di bawah root.</p>
     * <b>4.3.1.2</b> Hanya boleh terdapat satu data object dengan ID spesifik di bawah root QR Code dan hanya boleh terdapat satu ID spesifik dalam template-nya.
     * <p>
     * <b>4.7.2.1</b> Jika tersedia, maka Point of Initiation Method harus berisi Value "11" atau "12".<br/>
     * Data Object ini mengidentifikasikan teknologi yang digunakan dalam QR Code, apakah datanya statis atau dinamis. Value lainnya diperuntukkan dalam penggunaan yang belum didefinisikan. <br/>
     * Point of Initiation Method memiliki Value "11" untuk QR Code statis dan Value "12" untuk QR Code dinamis:
     *     <ul>
     *         <li>Value "11" digunakan saat QR Code yang sama ditampilkan pada setiap transaksi.</li>
     *         <li>Value "12" digunakan saat QR Code baru dibuat dan ditampilkan untuk tiap-tiap transaksi.</li>
     *     </ul>
     * </p>
     * <b>4.6.1.1</b> Payload Format Indicator (ID "00") harus menjadi urutan pertama data object dalam QR Code.<br/>
     * <b>4.6.1.2</b> CRC (ID "63") harus menjadi urutan terakhir data object dalam QR Code. Seluruh turunan data object root dapat ditempatkan di urutan lain. Data object dengan template seperti Additional Data Field Template (ID "62") atau Merchant Information—Language Template (ID "64"), dapat ditempatkan di urutan manapun di bawah template-nya.<br/>
     * <b>4.7.1.1</b> Payload Format Indicator wajib memiliki Value “01”. Value lainnya diperuntukkan dalam penggunaan yang belum didefinisikan.<br />
     * <b>4.7.3.1</b> Setidaknya satu data object Merchant Account Information dari "02" - "51" harus ditampilkan.<br />
     * <b>4.7.6</b> Merchant Category Code (ID "52")<br  />
     * <b>4.7.6.1</b> Merchant Category Code (MCC) harus memuat informasi MCC yang didefinisikan oleh [ISO 18245]. <br />
     * <b>4.7.7</b> Merchant Account Information Template (ID “51”) ID “51” wajib ditampilkan jika Value dari Point of Initiation Method “11”.<br />
     * <b>4.7.7</b> Transaction Currency (ID "53")<br />
     * <b>4.7.7.1</b> Transaction Currency harus mengacu pada [ISO 4217] dan merupakan 3 digit angka yang merepresentasikan mata uang . Indonesia Rupiah direpresentasikan oleh Value "360". Value tersebut wajib digunakan jika Value ID “58” adalah “ID” (Indonesia) <br />
     * <b>4.7.13</b> Merchant Name (ID "59")<br />
     * <b>4.7.13.1</b> Merchant Name wajib ditampilkan untuk mengidentifikasi nama merchant yang dapat dikenali oleh konsumen.<br />
     * <b>4.7.14</b> Merchant City (ID "60")<br />
     * <b>4.7.14.1</b> Merchant City wajib ditampilkan untuk mengindikasikan kota lokasi toko atau merchant beroperasi.<br />
     */
    @PayloadFormatIndicatorFirstPosition
    @CRCLastPosition
    @PointOfInitiationMethodValue
    @PayloadFormatIndicatorValue
    @MerchantAccountInformationExist
    @MerchantAccountInformation51Exist
    @MandatoryField(id = 58)
    @MandatoryField(id = 59)
    @MandatoryField(id = 60)
    @MerchantCategoryCode
    @TransactionCurrency
    @TransactionAmount(id = 54)
    @TipValueIndicator
    @TransactionAmount(id = 56)
    @TransactionAmount(id = 57)
    @TipValuePercentage
    @CountryCode
    @IdNotNull(id = 59)
    @IdNotNull(id = 60)
    @PostalCode
    @Valid
    private Map<Integer, QrisDataObject> qrisRoot;
}
