const express = require("express");
const things = express.Router();

things.get("/", (req, res) => {
  res.send("Got get req");
});
things.post("/", (req, res) => {
  res.send("Got post req");
});

module.exports = things;
