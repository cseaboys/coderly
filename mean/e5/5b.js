function getStockPrice() {
  return Math.floor(Math.random() * 100) + 100;
}

function logStockPrice() {
  const price = getStockPrice();
  console.log(`Stock price: ${price}`);
}

const intervalId = setInterval(logStockPrice, 1000);

setTimeout(() => clearInterval(intervalId), 5000);
