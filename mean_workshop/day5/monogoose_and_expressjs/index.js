const express = require("express");
const mongoose = require("mongoose");

const app = express();
const PORT = 5000;
const uri = "mongodb://localhost:27017/";

mongoose.connect(uri, { dbName: "test" }).then(() => {
  console.log("Db connected successfully");

  const userSchema = new mongoose.Schema(
    {
      name: String,
      lang: String,
    },
  );

  const user = mongoose.model("programmers", userSchema);

  app.use(
    express.urlencoded({
      extended: true,
    }),
  );

  app.get("/", (req, res) => {
    res.sendFile(__dirname + "/index.html");
  });

  app.post("/submit", async (req, res) => {
    const { name, lang } = req.body;
    const newUser = new user({ name, lang });
    await newUser.save();
    res.send("Data saved successfully");
  });
})
  .catch((e) => {
    console.log("Error: ", e.message);
  });

app.listen(PORT, () => {
  console.log(`Serving on http://localhost:${PORT}`);
});
