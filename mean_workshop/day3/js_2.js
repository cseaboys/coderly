let n = "6.88"

console.log(parseInt(n));
console.log(parseFloat(n));

console.log(Math.floor(n))
console.log(Math.ceil(n))

console.log(Math.round(n))


//otp

function getOtp(len) {
  return parseInt(parseInt(Math.random() * (10 ** len)))
}
console.log(getOtp(4))

/*
c = 10

var c = 2;


console.log(c)
//2
*/
/*
var c = 1

console.log(c)

let c = 2
// Uncaught SyntaxError: Identifier 'c' has already been declared
*/


var a = 10
console.log("Outside:", a)
{
  var a = 20 // var goes beyond the scope, its globally changed
  console.log("inside:", a)
}

console.log("downmost", a)

const movieDetails = {
  name: "Abc",
  starring: "Xyz",
  rating: "10",
  "h/f": "h",
  1.1: 1.1,
  1: 1,
  date: {
    month: 1,
    year: 2000
  }
}
//document.write can print movieDetails directly, so we use JSON.stringify() to stringify the object, or else we get object object
console.log(movieDetails.name)
//console.log(movieDetails[name]) //undefined because name is treated as a new variable here, not key
console.log(movieDetails['name'])
console.log(movieDetails["h/f"])
console.log((movieDetails))

console.log(movieDetails.date)

const { date: { month } } = movieDetails
console.log(month)
let student = {
  name: "Abc",
  age: 100,
  greet: () => {
    console.log("Hi", student.name)
  },
}

student.greet()

// TODO
/* 
 * main 
 * bags, mobiles, laptops,
 * buy 3 types, calculate total
 *
 */

function sum(...args) {
  let _sum = 0
  args.forEach(el => _sum += el)
  return _sum
}

console.log(sum(1, 2, 3, 4, 5))

let x = { a: 1 },
  y = { b: 2 };
let z = { ...x, ...y }
console.log(z)

let o1 = { a: 1 }

let o2 = o1

o2.a = 2 // o1 object's value is changed
console.log(o1)

let o3 = { ...o1 }

o3.a = 3
console.log(o3, o1) // gives  {a:3} {a:2}

// ... operator can be used for shallow cloning of objects and for rest (variable number of parameters) parameters
// when cloning, if two objects have same property then the last one overrides the main value


const obj1 = { name: "a", age: "1" }

let { name, age: Age } = obj1;

console.log(name, Age)

// console.log(age) // age is not defined because we desturctured age as Age

// we can add new fields too

let { age, branch = "CSE" } = obj1;
/*
 *The output will be { name: 'a', age: '1' } because the destructuring operations do not modify the original object.
 The branch variable exists, but it's a separate variable, not a property of obj1.
*/
console.log(obj1)
console.log(branch)

class Vehicle {
  constructor(type) {
    this.type = type
  }
  go() {
    console.log("Vehicle goes")
  }
}

class Car extends Vehicle {
  constructor(name, color, type) {
    super(type)
    this.name = name
    this.color = color
  }

  sayDetails() {
    console.log(this.name, this.color)
  }
  go() {
    console.log("Car goes")
  }
}

let obj = new Car("A", "white", "Car")
obj.sayDetails()
obj.a = "a"
console.log(obj)
obj.go()
new Vehicle().go()


//slice is not mutating 
//splice is mutating, but also returns the removed elements

let arr = [1, 2, 3, 4, 5]

console.log(arr.push(6), arr.pop(), arr.shift(),
  arr.unshift(0), arr.slice(-1 * arr.length, -1))

let b = [1, 2, 3, 4, 5]
console.log(b.splice(1, 2, "dvdfv", "sdvds", 'dsvcd'))

console.log(b)

let arr1 = [1, 2, 3, 4]
arr1.forEach(el => console.log(el))
console.log(arr1.map(el => el * 2))
console.log(arr1.filter(e => e % 2 != 0))
