-- 插入分類
-- 層級 1: 頂層分類
INSERT INTO PRODUCT_CATEGORY (CATE_NAME, PARENT_CATE_ID, CATE_DESC) VALUES
(N'服裝', NULL, N'所有穿戴在身上的衣物及配件');               -- 預期 ID = 1
-- 層級 2: 服裝下的主分類
-- PARENT_CATE_ID 全部指向 '服裝'(ID=1)
INSERT INTO PRODUCT_CATEGORY (CATE_NAME, PARENT_CATE_ID, CATE_DESC) VALUES
(N'男裝', 1, N'所有男性專屬的時尚服飾'),                       -- 預期 ID = 2
(N'女裝', 1, N'所有女性專屬的時尚服飾'),                       -- 預期 ID = 3
(N'配件', 1, N'點綴整體造型的時尚單品，如帽子、圍巾等');     -- 預期 ID = 4
-- 層級 3: 各主分類下的子分類
INSERT INTO PRODUCT_CATEGORY (CATE_NAME, PARENT_CATE_ID, CATE_DESC) VALUES
(N'男性上衣', 2, N'包含T恤、Polo衫、襯衫等'),                   -- 預期 ID = 5
(N'男性褲款', 2, N'包含休閒褲、牛仔褲等'),                       -- 預期 ID = 6
(N'女性上衣', 3, N'包含T恤、雪紡衫、襯衫等'),                   -- 預期 ID = 7
(N'女性裙裝', 3, N'包含短裙、長裙、連身裙等'),                   -- 預期 ID = 8
(N'帽子', 4, N'包含棒球帽、漁夫帽等時尚帽款');                  -- 預期 ID = 9


--插入商品
INSERT INTO PRODUCT (PROD_NAME, PROD_DESC, PROD_CATE_ID, PROD_STATUS) VALUES
(N'經典純棉素色T恤', N'採用100%頂級純棉，觸感柔軟舒適，是衣櫃中不可或缺的百搭單品。', 5, 0), -- ID=1
(N'都會風格修身長褲', N'修身剪裁，彈性布料，適合商務休閒等多種場合。', 6, 0), -- ID=2
(N'V領雪紡飄逸上衣', N'輕盈的雪紡材質，V領設計修飾臉型，散發優雅氣質。', 7, 0), -- ID=3
(N'高腰A字丹寧長裙', N'經典不敗的丹寧款式，高腰A字版型，完美拉長身形比例。', 8, 0), -- ID=4
(N'復古水洗棒球帽', N'經過特殊水洗工藝，呈現自然的復古色澤，可調節式帽圍。', 9, 0), -- ID=5
(N'夏季限定亞麻襯衫', N'透氣性極佳的亞麻材質，是夏日防曬與穿搭的好選擇。', 5, 1), -- ID=6
(N'限量聯名款大學T', N'與知名藝術家聯名設計，已全數售罄不再生產。', 7, 2); -- ID=7

-- ═══════════════════════════
-- 步驟 3: 插入商品款式資料 (SKU) - 無需變更
-- ═══════════════════════════
-- 針對 商品ID=1 (經典純棉素色T恤)
INSERT INTO PRODUCT_SKUS (PROD_ID, SKU_CODE, PRICE, STOCK_QUANTITY) VALUES
(1, N'CCT-WHI-M', 499.00, 150),
(1, N'CCT-WHI-L', 499.00, 120),
(1, N'CCT-BLK-M', 499.00, 180),
(1, N'CCT-BLK-L', 499.00, 90),
(1, N'CCT-GRY-M', 520.00, 75);
-- 針對 商品ID=2 (都會風格修身長褲)
INSERT INTO PRODUCT_SKUS (PROD_ID, SKU_CODE, PRICE, STOCK_QUANTITY) VALUES
(2, N'USP-BLK-30', 1280.00, 80),
(2, N'USP-BLK-32', 1280.00, 100),
(2, N'USP-BLK-34', 1280.00, 60),
(2, N'USP-KHA-30', 1300.00, 50),
(2, N'USP-KHA-32', 1300.00, 70);
-- 針對 商品ID=3 (V領雪紡飄逸上衣)
INSERT INTO PRODUCT_SKUS (PROD_ID, SKU_CODE, PRICE, STOCK_QUANTITY) VALUES
(3, N'VNB-APR-S', 980.00, 65),
(3, N'VNB-APR-M', 980.00, 88),
(3, N'VNB-WHT-S', 950.00, 110),
(3, N'VNB-WHT-M', 950.00, 95);
-- 針對 商品ID=4 (高腰A字丹寧長裙)
INSERT INTO PRODUCT_SKUS (PROD_ID, SKU_CODE, PRICE, STOCK_QUANTITY) VALUES
(4, N'HDS-BLU-S', 1550.00, 40),
(4, N'HDS-BLU-M', 1550.00, 55),
(4, N'HDS-BLU-L', 1550.00, 30);
-- 針對 商品ID=5 (復古水洗棒球帽)
INSERT INTO PRODUCT_SKUS (PROD_ID, SKU_CODE, PRICE, STOCK_QUANTITY) VALUES
(5, N'VBC-BLK-F', 790.00, 120),
(5, N'VBC-NAV-F', 790.00, 85);
-- 模擬一個缺貨的 SKU
INSERT INTO PRODUCT_SKUS (PROD_ID, SKU_CODE, PRICE, STOCK_QUANTITY) VALUES
(1, N'CCT-BLK-XXL', 550.00, 0);