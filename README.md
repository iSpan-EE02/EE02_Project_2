# Monorepo Project

這是一個使用 Maven 多模組結構的專案，包含前端和後端兩個模組。

## 專案結構

```
monorepo/
├── frontend/           # 前端模組
│   ├── public/        # 靜態文件
│   ├── src/          # 源代碼
│   └── tests/        # 測試代碼
│
└── backend/           # 後端模組
    └── src/
        ├── main/     # 主源代碼
        └── test/     # 測試代碼
```

## 開發環境要求

- Java 17 或更高版本
- Maven 3.6 或更高版本
- Node.js 16.14.0 或更高版本
- npm 8.3.1 或更高版本

## 構建和運行

### 構建整個專案

```bash
mvn clean install
```

### 運行前端開發服務器

```bash
cd frontend
npm start
```

### 運行後端服務器

```bash
mvn tomcat7:run
```

## 測試

### 前端測試

```bash
cd frontend
npm test
```

### 後端測試

```bash
mvn test
``` 