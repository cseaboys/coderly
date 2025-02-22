// Syntax
// app.method(path,callback)
// GET - Handle get req
// POST - Handle post req
// PUT - Handle put req, to create or update a resource
// put has no additional effects even if called multiple times


const express = require('express')
const PORT = 3000
const app = express()

app.use(express.json())
const categories = require("./categories")


app.get("/", (req, res) => {
  res.send(
    `Welcome to /
Available paths:
/categories
/categories/mobiles
/about
`)
})

app.post('/', function(req, res) {
  res.send("Used post feature")
})

app.get("/about", (req, res) => {
  res.send("Welcome to /about")
})

app.use('/categories', categories)

app.listen(PORT, () => {
  console.log(`http://localhost:${PORT}`)
})

