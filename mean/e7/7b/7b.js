const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");

const app = express();

app.use(express.static("public"));
app.use(bodyParser.urlencoded());

app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "7b.html"));
});

app.post("/login", (req, res) => {
  console.log("Received POST request.");
  const fname = req.body.fname;
  const lname = req.body.lname;
  console.log("First Name:", fname);
  console.log("Last Name:", lname);

  res.redirect(`/user/${fname + "-" + lname}`);
});

app.get("/user/:name", (req, res) => {
  const name = req.params.name;
  res.send(`<h1>Logged in as User: ${name}</h1>`);
});

app.listen(3000, () => console.log("Server running at http://localhost:3000"));
