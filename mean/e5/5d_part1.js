class User {
  constructor(username, password) {
    this.username = username;
    this.password = password;
  }
}

function validate(username, password) {
  if (username === password) {
    return "Login Successful";
  } else {
    return "Invalid Credentials";
  }
}

module.exports = { User, validate };
