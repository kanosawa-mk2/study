// データを取得するためのURL
const apiUrl = 'https://jsonplaceholder.typicode.com/todos/1';

// Fetch APIを使用してデータを取得する
fetch(apiUrl)
  .then(response => {
    // サーバーからのレスポンスをJSON形式に変換
    return response.json();
  })
  .then(data => {
    // JSONデータをコンソールに表示
    console.log(data);
  })
  .catch(error => {
    // エラーが発生した場合の処理
    console.error('Fetchエラー:', error);
  });