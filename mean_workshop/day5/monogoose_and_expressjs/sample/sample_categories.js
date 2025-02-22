const express = require('express')

const router = express.Router()

router.get('/', (req, res) => {
  res.send("Mobiles, TV, Laptops")
})



router.get("/mobiles", (req, res) => {
  res.send("Samsung, Rog, Legion")
})

module.exports = router

