const Users = [];
class User {
  constructor(name, passwd) {
    this.name = name;
    this.passwd = passwd;
    Users.push(this);
  }
}

const validate = (name, passwd) => {
  const user = Users.find((user) =>
    user.name === name && user.passwd === passwd
  );
  if (user) {
    return "Login successful";
  } else {
    return "Login failed";
  }
};

module.exports = { User, validate };
