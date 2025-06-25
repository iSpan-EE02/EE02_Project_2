# iSpan Project 2

這是一個電商專案，
使用Eclipse建立的Dynamic Web Project，
採用MVC架構：
Model：（JavaBean Class）
View：（html和JSP）
Controller：（Servlet）
資料庫：MS SQL Server

## 專案結構

```
Project2/
├── sql/           # 建立資料庫和插入資料的.sql檔案.
└── backend/           # 後端模組
    └── src/
        ├── main/     # 主源代碼
        └── test/     # 測試代碼
```

## 開發環境要求

- Java 17 或更高版本
- apache-tomcat-10.1.41
- MS SQLEXPRESS

## 構建和運行
1. 下載tomcat
2. 下載MS SQLEXPRESS 
3. 在sql server中建立資料庫project2
4. 在eclipse中新建server
5. 在server中配置JNDI資料（context.xml,server.xml）

