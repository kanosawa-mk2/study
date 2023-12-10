# Super CSV Annotationについて

2023/12/9

CSV、XML、Excelなどで記述したテストデータをデータベースに登録、削除してくれるツール。  
他にもデータをエクスポートする機能や、テストケース実施後のデータが予想した値になっていることを検証する機能も提供されています。

# URL

* [Welcome to Super CSV Annotation](https://mygreen.github.io/super-csv-annotation/sphinx/index.html)

# 環境構築
Java ver.1.8以上  

mavenの場合は、mavenのdependencyに記載するだけ

		<dependency>
			<groupId>com.github.mygreen</groupId>
			<artifactId>super-csv-annotation</artifactId>
			<version>2.3</version>
		</dependency>


# 基本的な使用方法

1. Beanの定義を行う
    1. 引数なしのpublic なコンストラクタが必要
    2. CSV用のクラスであることを示すために、svBeanをクラスに付与する。
    3. カラムをフィールドにマッピングするために、アノテーション@CsvColumnをフィールドに付与
        - 属性 number で、マッピングするカラムの番号を指定します。カラムの番号は1から始まります。
        - label で、ヘッダー行のラベル名を指定することができます。省略した場合はフィールド名が適用されます。
    4. フィールドに対応するgetter/setterのアクセッサメソッドを作成します。(lombok使用可能)
2. CSVの読み込み・書き込みを行う。
3. Beanに値の加工方法を設定する。  
   書式の指定 、トリムなどの値の変換 、 値の検証

# Beanの定義

* [Beanの定義](https://mygreen.github.io/super-csv-annotation/sphinx/howtouse.html#csvbean)

# 基本機能 CSVの読み込み

* [読み込み方法](https://mygreen.github.io/super-csv-annotation/sphinx/howtouse.html#id4)

# 基本機能 書き込み方法

* [書き込み方法](https://mygreen.github.io/super-csv-annotation/sphinx/howtouse.html#id6)


# 値の加工方法
* [書式の指定方法](https://mygreen.github.io/super-csv-annotation/sphinx/format.html)
* [値の変換方法](https://mygreen.github.io/super-csv-annotation/sphinx/conversion.html)
* [値の検証方法](https://mygreen.github.io/super-csv-annotation/sphinx/conversion.html)


# その他機能

* [部分的なカラムの読み書き](https://mygreen.github.io/super-csv-annotation/sphinx/partialization.html)
* [固定長のカラムの読み書き](https://mygreen.github.io/super-csv-annotation/sphinx/fixedsizecolumn.html)
* [ラベルによるカラムのマッピング](https://mygreen.github.io/super-csv-annotation/sphinx/labelledcolumn.html)
* [ライフサイクルイベントの管理](https://mygreen.github.io/super-csv-annotation/sphinx/lifecycle.html)


