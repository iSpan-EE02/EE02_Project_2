-- 產品相關資料表

--  產品表
CREATE TABLE PRODUCT (
    PROD_ID INT IDENTITY(1,1) NOT NULL PRIMARY KEY, -- 商品編號
    PROD_NAME NVARCHAR(50) NOT NULL, -- 商品名稱
    PROD_DESC NVARCHAR(MAX) NOT NULL, -- 商品描述
    PROD_CATE_ID INT NOT NULL, -- 商品分類編號
    CREATED_AT DATETIME2(0) NOT NULL CONSTRAINT DF_ProductListTime DEFAULT GETUTCDATE(),
    -- ═══════ 約束 ═══════
    CONSTRAINT FK_Product_Category FOREIGN KEY (PROD_CATE_ID) 
        REFERENCES PRODUCT_CATEGORY(CATE_ID) 
);

--  商品分類表
CREATE TABLE PRODUCT_CATEGORY (
    CATE_ID INT IDENTITY(1,1) NOT NULL PRIMARY KEY, -- 商品分類編號
    CATE_NAME NVARCHAR(50) NOT NULL, -- 商品分類名稱
    PARENT_CATE_ID INT NULL, -- 父分類編號，允許為 NULL 表示頂層分類
    CATE_DESC NVARCHAR(500) NULL, -- 商品分類描述
    CREATED_AT DATETIME2(0) NOT NULL DEFAULT GETUTCDATE(), -- 創建時間
    -- ═══════ 約束 ═══════
    
    -- 1. 確保分類名不重複
    CONSTRAINT UQ_ProductCategory_Name UNIQUE (CATE_NAME),
    
    -- 2. 自我參考FK，允許階層結構
    --    允許 PARENT_CATE_ID 為 NULL，表示頂層分類
    CONSTRAINT FK_ProductCategory_Parent FOREIGN KEY (PARENT_CATE_ID)
        REFERENCES PRODUCT_CATEGORY(CATE_ID)
);

--  產品款式表
CREATE TABLE PRODUCT_SKUS (
    SKU_ID INT IDENTITY(1,1) NOT NULL PRIMARY KEY, --款式編號
    PROD_ID INT NOT NULL, -- 關聯的商品編號
    SKU_CODE NVARCHAR(50) NOT NULL, -- SKU 貨號，通常是商品的唯一識別碼，供內部人員辨識
    PRICE DECIMAL(10, 2) NOT NULL, -- 商品價格
    STOCK_QUANTITY INT NOT NULL CONSTRAINT DF_ProductSkus_Stock DEFAULT 0,  -- 庫存數量，預設為 0
    CREATED_AT DATETIME2(0) NOT NULL DEFAULT GETUTCDATE(), -- 創建時間，預設為當前時間
    -- ═══════ 約束 (Constraints) ═══════
    
    -- 1. 確保每個SKU都關聯到一個有效的商品
    CONSTRAINT FK_ProductSkus_Product FOREIGN KEY (PROD_ID)
        REFERENCES PRODUCT(PROD_ID),
        
    -- 2. 確保 SKU 貨號在系統中是唯一的，防止重複
    CONSTRAINT UQ_ProductSkus_SkuCode UNIQUE (SKU_CODE),
    -- 3. 確保價格和庫存不能是負數，維持資料完整性
    CONSTRAINT CK_ProductSkus_Price CHECK (PRICE >= 0),
    CONSTRAINT CK_ProductSkus_Stock CHECK (STOCK_QUANTITY >= 0)
);

--  商品圖片表
CREATE TABLE PRODUCT_IMAGES (
    IMAGE_ID INT IDENTITY(1,1) NOT NULL PRIMARY KEY, -- 圖片編號
    PROD_ID INT NOT NULL, -- 關聯的商品編號
    IMAGE_URL VARCHAR(2083) NOT NULL, -- 圖片的 URL，最大長度為 2083 字元
    -- 'IS_PRIMARY' 旗標：1 代表主要圖片, 0 代表其他圖片
    IS_PRIMARY BIT NOT NULL CONSTRAINT DF_ProductImages_IsPrimary DEFAULT 0,
    -- 'SORT_ORDER' 用於決定圖片在商品詳情頁的顯示順序
    SORT_ORDER INT NOT NULL CONSTRAINT DF_ProductImages_SortOrder DEFAULT 0,
    UPLOADED_AT DATETIME2(0) NOT NULL DEFAULT GETUTCDATE(), -- 上傳時間，預設為當前時間
    -- ═══════ 約束 (Constraints) ═══════
    
    -- 1. 確保每張圖片都關聯到一個有效的商品
    CONSTRAINT FK_ProductImages_Product FOREIGN KEY (PROD_ID)
        REFERENCES PRODUCT(PROD_ID)
);