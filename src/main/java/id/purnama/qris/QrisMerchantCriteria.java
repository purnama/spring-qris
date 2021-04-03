package id.purnama.qris;

/**
 * Tabel 4.6: Data object turunan Merchant Criteria (IDs "03")
 * <table>
 *   <thead>
 *     <tr><th>ID</th><th>Definisi</th><th>Format</th><th>Panjang Karakter</th></tr>
 *   </thead>
 *   <tbody>
 *      <tr><td>UMI</td><td>Usaha Mikro</td><td>ans</td><td>3</td></tr>
 *      <tr><td>UKE</td><td>Usaha Kecil</td><td>ans</td><td>3</td></tr>
 *      <tr><td>UME</td><td>Usaha Menengah</td><td>ans</td><td>3</td></tr>
 *      <tr><td>UBE</td><td>Usaha Besar</td><td>ans</td><td>3</td></tr>
 *      <tr><td>URE</td><td>Usaha Reguler</td><td>ans</td><td>3</td></tr>
 *   </tbody>
 *</table>
 * Jika ID”03” tidak tersedia maka Penerbit wajib mengisi nilai default “URE” dalam message transaksi.
 */
public enum QrisMerchantCriteria {
    UMI, UKE, UME, UBE, URE
}