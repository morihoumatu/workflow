# Spring Boot + Activiti (BPMN) スターター

Java 21 • Gradle 8 • VS Code

## 概要

**Spring Boot 3.2** 上で **Activiti 8** の BPMN ワークフローを動かすための極小ボイラープレートです。  
Java 21／Gradle Kotlin DSL を採用し、VS Code でコード・プロセス定義・デバッグを一元的に行えます。

---

## 1. 前提ソフトウェア

| ツール         | 推奨バージョン | 補足                                 |
| -------------- | -------------- | ------------------------------------ |
| JDK            | **21 (LTS)**   | `JAVA_HOME` を設定                   |
| Gradle         | **8.5** 以上   | ラッパー同梱のためローカル導入は任意 |
| VS Code        | 最新版         | 拡張機能は後述                       |
| Docker Desktop | 任意           | 本番用 DB をコンテナ実行する場合     |

### 推奨 VS Code 拡張機能

- **Extension Pack for Java**（Red Hat）— Java 言語サポート & デバッガ
- **Spring Boot Extension Pack**（VMware）— Spring Dashboard
- **Gradle for Java** — タスク実行／依存関係ツリー
- **BPMN Editor** — `*.bpmn20.xml` を直接編集
- **REST Client** — API テスト

---

## 2. クローンと起動

```bash
git clone https://github.com/your-org/activiti-spring-boot-starter.git
cd activiti-spring-boot-starter
./gradlew bootRun          # 初回起動（約30秒）
```

別端末でプロセスを開始:

```bash
curl -X POST http://localhost:8080/start
# → Started: 3f2c0f9b-…
```

### ホットリロード

Spring DevTools が有効です。Java ファイルや BPMN を保存すると自動で再起動します。

---

## 3. プロジェクト構成

```
├─ src
│  ├─ main
│  │  ├─ java/com/example/demo
│  │  │  └─ ProcessController.java   ← プロセス起動用 REST
│  │  ├─ resources
│  │  │  ├─ application.yml          ← H2 + Activiti 設定
│  │  │  └─ processes/
│  │  │      └─ hello.bpmn20.xml     ← サンプル BPMN
│  └─ test/…                         ← JUnit テスト
├─ build.gradle.kts                  ← Gradle Kotlin DSL
└─ README.md
```

---

## 4. カスタマイズ例

| やりたいこと          | 方法                                                                                 |
| --------------------- | ------------------------------------------------------------------------------------ |
| **PostgreSQL を使用** | `runtimeOnly("org.postgresql:postgresql")` を追加し、`application.yml` の URL を変更 |
| **監査 UI を有効化**  | `implementation("org.activiti:activiti-spring-boot-starter-audit:8.2.0")` を追加     |
| **BPMN を追加**       | `src/main/resources/processes/` に `*.bpmn20.xml` を置く                             |
| **仮想スレッド**      | デバッグ設定に `--spring.threads.virtual.enabled=true` を付与                        |

---

## 5. 主な Gradle タスク

```bash
./gradlew test             # JUnit 5
./gradlew bootRun          # 開発用起動
./gradlew clean build      # 本番ビルド（fat JAR）
./gradlew dependencyUpdates  # 依存更新チェック
```

---

## 6. VS Code でのデバッグ

1. **Run & Debug → Debug Spring Boot** を選択
2. Java デリゲートや REST コントローラにブレークポイントを設定
3. [http://localhost:8080/h2-console](http://localhost:8080/h2-console) でプロセステーブルを確認

   - JDBC URL: `jdbc:h2:mem:activiti`

---

## 7. ライセンス

MIT License — 自由に利用・改変・配布してください。

---

## 8. 参考リンク

- Activiti: [https://github.com/Activiti/Activiti](https://github.com/Activiti/Activiti)
- Spring Boot ドキュメント: [https://docs.spring.io/spring-boot/docs/current/reference/html/](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- Java 21 リリースノート: [https://docs.oracle.com/en/java/javase/21/](https://docs.oracle.com/en/java/javase/21/)
