# MyBatisについて

SQLマッパー型と呼ばれるO/Rマッパー  
SQLのコネクション、SQLパラメーターへのマッピング、ResultsetのBeanへのマッピングを自動化し、記載を短縮  
日本語のドキュメントが存在

# URL
https://mybatis.org/mybatis-3/ja/

# 環境構築
mavenの場合は、mavenのdependencyに記載するだけ

# 使い方
以下の実装方法が存在する。
- 従来のXMLベースのMapper
- アノテーションベースのMapper
- SQLプロバイダベースのMapper
- MyBatis Dynamic SQLのMapper
- MyBatis Dynamic SQLのCommon Mapper

参考  
![MyBatisのMapper実装を比較する](https://qiita.com/yoshikawaa/items/80e393e863ab08dfcc01)

# トランザクション
デフォルトはAutocommit falseになっているようなので、デフォルトの設定でOK

# その他便利な設定
コンパイル　オプションに-parametersを追加すると、マッパーのメソッドで引数が2つ以上の場合に  
@Parameオプションを記載しなくてよい
