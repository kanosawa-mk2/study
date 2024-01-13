// 親クラスの定義
function Animal(name) {
    this.name = name;
}

// 親クラスのメソッド
Animal.prototype.sayName = function () {
    console.log(`My name is ${this.name}`);
};

// 子クラスの定義
function Dog(name, breed) {
    // 親クラスのコンストラクタを呼び出す
    Animal.call(this, name);
    this.breed = breed;
}

// 親クラスのプロトタイプを継承
Dog.prototype = Object.create(Animal.prototype);

// 子クラスのメソッド
Dog.prototype.bark = function () {
    console.log('Woof! Woof!');
};

// インスタンスの生成
const myDog = new Dog('Buddy', 'Golden Retriever');

// メソッドの呼び出し
myDog.sayName(); // "My name is Buddy"
myDog.bark();    // "Woof! Woof!"
