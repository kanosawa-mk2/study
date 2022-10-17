/**
 * 
 */

const url = 'http://localhost:8080/ajax-form-request-sample/HelloWorld';

const action = () => {
	document.getElementById("ajaxTest").disabled = true;

	const aaa = document.getElementById("aaa").value;
	const bbb = document.getElementById("bbb").value;
	const ccc = document.getElementById("ccc").value;

	const data = new URLSearchParams();
	data.append('aaa',aaa);
	data.append('bbb',bbb);
	data.append('ccc',ccc);

	fetch(url, {
		method: 'POST',
		headers:{
			'Context-Type' : 'application/x-www-form-urlencoded',
		},
		body: data,
		mode:"cors", // CORSなリクエスト 
		// modeについてはここを参照 https://qiita.com/ryokkkke/items/79f1d338e141d4b7201b
		// CORSについてはここを参照 https://yamory.io/blog/about-cors/
	}).then(res => res.text())
	.then(data => {
		console.log('Success:',data);
		document.getElementById("response").value = data;
	}).catch((error) => {
		console.log('Error:',error);
		alert("エラーが発生しました。" + error)
	}).finally(() => {
		document.getElementById("ajaxTest").disabled = false;
	})
}

document.addEventListener('DOMContentLoaded', function (event) {
	console.log('DOMContentLoaded:');
	const button = document.getElementById("ajaxTest");
	button.addEventListener('click',action);
	
});