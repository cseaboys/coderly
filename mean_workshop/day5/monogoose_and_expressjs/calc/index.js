const express = require("express");
const app = express();
const PORT = 5000;

app.use(
  express.json(),
  express.urlencoded({
    extended: true,
  }),
);

const router = express.Router();

router.post("/calc/add", (req, res) => {
  const { a, b } = req.body;
  const result = a + b;
  res.send(JSON.stringify({
    status: 200,
    result: result.toString(),
  }));
});

router.post("/calc/add", (req, res) => {
  const { a, b } = req.body;
  const result = BigInt(a) + BigInt(b);
  res.send(JSON.stringify({
    status: 200,
    result: result.toString(),
  }));
});
router.post("/calc/sub", (req, res) => {
  const { a, b } = req.body;
  const result = BigInt(a) - BigInt(b);
  res.send(JSON.stringify({
    status: 200,
    result: result.toString(),
  }));
});
router.post("/calc/mul", (req, res) => {
  const { a, b } = req.body;
  const result = BigInt(a) * BigInt(b);
  res.send(JSON.stringify({
    status: 200,
    result: result.toString(),
  }));
});
router.post("/calc/div", (req, res) => {
  const { a, b } = req.body;
  if (b == 0) {
    res.send(JSON.stringify(
      {
        status: 400,
        result: "null",
      },
    ));
  }
  const result = a / b
  res.send(JSON.stringify({
    status: 200,
    result: result.toString(),
  }));
});
router.post("/calc/pow", (req, res) => {
  const { a, b } = req.body;
  const result = BigInt(a) ** BigInt(b);
  res.send(JSON.stringify({
    status: 200,
    result: result.toString(),
  }));
});

app.use("/", router);
app.listen(PORT, () => {
  console.log(`Serving on http://localhost:${PORT}`);
});
