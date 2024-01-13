// 親クラスの定義
class Animal {
  constructor(name) {
    this.name = name;
  }

  sayName() {
    console.log(`My name is ${this.name}`);
  }
}

// 子クラスの定義
class Dog extends Animal {
  constructor(name, breed) {
    super(name); // 親クラスのコンストラクタを呼び出す
    this.breed = breed;
  }

  bark() {
    console.log('Woof! Woof!');
  }
}

// インスタンスの生成
const myDog = new Dog('Buddy', 'Golden Retriever');

// メソッドの呼び出し
myDog.sayName(); // "My name is Buddy"
myDog.bark();    // "Woof! Woof!"