class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
}

class Employee extends Person {
  constructor(name, age, role) {
    super(name, age);
    this.role = role;
  }
}

const john = new Employee("John Doe", 30, "Manager");
console.log(john);
