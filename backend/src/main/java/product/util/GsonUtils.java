package product.util;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 一個通用的、線程安全的 Gson 工具類
 */
public final class GsonUtils { // 使用 final class，表示這是一個不應被繼承的工具類

    // 1. 建立一個靜態、最終的 GSON 實例，全局共享
    // 使用 GsonBuilder 可以進行更多自訂配置
    private static final Gson GSON = new GsonBuilder()
            // .setDateFormat("yyyy-MM-dd HH:mm:ss") // 如果需要，可以統一設定日期格式
            // .excludeFieldsWithoutExposeAnnotation() // 如果使用 @Expose，打開此註解
    		.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
    		.serializeNulls()
    		.create();

    // 如果需要一個用於調試、帶有縮進的 "漂亮" JSON 格式，可以再建立一個
    private static final Gson PRETTY_GSON = new GsonBuilder()
            .setPrettyPrinting() // 設定為美化格式 (帶縮進和換行)
            .create();

    // 2. 私有化構造函數，防止外部 new 這個工具類
    private GsonUtils() {
    }

    /**
     * 將任何 Java 物件序列化為 JSON 字符串
     * @param object 任何 Java 物件
     * @param <T> 泛型標識
     * @return JSON 字符串
     */
    public static <T> String toJson(T object) {
        return GSON.toJson(object);
    }

    /**
     * 將任何 Java 物件序列化為 "美化格式" 的 JSON 字符串 (方便閱讀)
     * @param object 任何 Java 物件
     * @param <T> 泛型標識
     * @return 美化後的 JSON 字符串
     */
    public static <T> String toPrettyJson(T object) {
        return PRETTY_GSON.toJson(object);
    }

    /**
     * 將 JSON 字符串反序列化為指定的 Java 物件
     * @param json JSON 字符串
     * @param classOfT 要轉換成的目標類 Class
     * @param <T> 泛型標識
     * @return 指定類型的物件實例
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }
}