const { User, validate } = require("./5d1");
const user = new User("John", "password1");

let passwd = "some_passwd";
console.log(validate(user.name, passwd));
passwd = "password1";
console.log(validate(user.name, passwd));
