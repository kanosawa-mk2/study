# JasperReports 帳票サンプル

JavaBean の明細データを JRXML に渡し、JasperReports で売上明細 PDF を作る最小の Maven プロジェクトです。Excel は帳票レイアウトを検討・共有するための設計図です。JasperReports が Excel をテンプレートとして読み込むことはありません。

## 使用技術

- Java 11 以上（この環境では Java 21 で確認）
- Maven 3.8 以上
- JasperReports **6.21.3**（6.x に固定）

## 構成

```text
pom.xml
report-design/sample-report.xlsx                 Excelのレイアウト設計図
src/main/java/com/example/jasper/sample/
  SampleReportApplication.java                   サンプルデータ作成とPDF出力
  model/ReportData.java                           発行日・顧客・明細・合計
  model/ReportItem.java                           商品ごとのJavaBean
src/main/java/com/example/jasper/sample2/
  Sample2ReportApplication.java                  2つ目の帳票の出力
  model/Sample2ReportData.java                    2つ目の帳票のヘッダーと明細
  model/Sample2ReportItem.java                    2つ目の帳票の売上明細Bean
  model/PurchaseHistoryItem.java                  購入履歴Bean
src/main/resources/reports/sample/
  sample-report.jrxml                             サンプル帳票テンプレート
src/main/resources/reports/sample2/
  sample2-report.jrxml                            2つ目の帳票テンプレート
target/sample-report.pdf                          実行時に生成
target/sample2-report.pdf                         2つ目の帳票の実行時出力
```

## Excel → JRXML → PDF

1. `report-design/sample-report.xlsx` でタイトル、発行日、顧客名、明細表、合計欄の配置を確認します。
2. Excel の列幅・見出し・配色を参考に `src/main/resources/reports/sample/sample-report.jrxml` を作成します。Excel 自体は読み込みません。
3. `ReportData` と `ReportItem` の JavaBean に値を設定します。
4. JRXML をコンパイルし、明細リストを `JRBeanCollectionDataSource` として渡して帳票を埋め、PDF に出力します。

JRXML は classpath リソースとして読み込むため、実行時の作業ディレクトリにテンプレートの相対パスを要求しません。

## 実行

JDK 11 以上と Maven 3.8 以上を用意し、プロジェクトルートで実行します。

```bash
mvn clean package
mvn exec:java
```

生成先は `target/sample-report.pdf` です。サンプルには日本語を含みます。日本語フォントがない環境では PDF 上の日本語が欠ける場合があります。その場合は JRXML のフォント設定を環境の日本語フォントまたは JasperReports のフォント拡張に合わせてください。

## 帳票の追加

たとえば `invoice` を追加する場合は次のようにします。

1. `src/main/resources/reports/invoice/invoice-report.jrxml` を追加します。
2. `model` に請求書用 JavaBean と明細 JavaBean を追加します。
3. 請求書の帳票固有データ作成・出力クラスを追加し、classpath resource から JRXML を読み、適切な `JRBeanCollectionDataSource` を渡します。
4. 再利用できる帳票生成処理が必要になった段階で、テンプレート名・パラメーター・データソース・出力先を受け取る共通クラスへ切り出します。

既存サンプルは `reports/sample/` とサンプル出力クラスに閉じているため、新しい帳票は `reports/invoice/` 以下に独立して追加できます。

## 追加サンプル: 売上明細書と購入履歴

`report-design/sample2-report.xlsx` の「売上明細書」シートを参考に、売上明細・合計金額と購入履歴の2つの表をPDFに出力します。JRXMLのテーブルコンポーネントで購入履歴を別のBeanデータソースから表示します。

- Excel設計図: `report-design/sample2-report.xlsx`
- JRXML: `src/main/resources/reports/sample2/sample2-report.jrxml`
- 売上データ: `com.example.jasper.sample2.model.Sample2ReportData` と `Sample2ReportItem`
- 購入履歴データ: `com.example.jasper.sample2.model.PurchaseHistoryItem`
- 実行クラス: `com.example.jasper.sample2.Sample2ReportApplication`
- PDF出力先: `target/sample2-report.pdf`

```bash
mvn clean package
mvn exec:java "-Dexec.mainClass=com.example.jasper.sample2.Sample2ReportApplication"
```

sample2のJavaクラスは `com.example.jasper.sample2` とその `model` サブパッケージに配置し、既存の `sample` パッケージのJavaクラスには依存しません。売上明細は `Sample2ReportItem`、購入履歴は `PurchaseHistoryItem` で表し、次の帳票を追加するときは、この実行クラスのclasspath JRXML読み込みとBeanデータソース設定、および `reports/sample2/sample2-report.jrxml` の二つのデータセット構成が参考になります。

この帳票のPDFは日本語CIDフォントを指定していますが、フォント自体は埋め込んでいません。この環境ではPopplerの日本語フォント代替で表示を確認しました。閲覧環境によっては日本語フォントの代替表示になるため、必要に応じてJasperReportsのフォント拡張を追加してください。
