const { User, validate } = require("./5d_part1");

const user1 = new User("john", "doe");
console.log(validate(user1.username, user1.password));

const user2 = new User("jane", "jane");
console.log(validate(user2.username, user2.password));
