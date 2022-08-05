/** 制定api拿到的資訊的結構 */
package com.example.recyclerview_with_livedata_okhttp;

public class Post {
    private String symbol;
    private String priceChange;

    /** getter and setter 作為private變數或屬性的公用存取介面
     * steps: 選取變數 -> 右鍵 -> Refactor -> Encapsulate fields */
    public String getSymbol() {
        return symbol;
    }
    public String getPriceChange() {
        return priceChange;
    }
}
