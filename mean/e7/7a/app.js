const express = require("express");
const app = express();
const things = require("./route");

app.use("/things", things);

app.listen(3000, () => {
  console.log("http://localhost:3000");
});
