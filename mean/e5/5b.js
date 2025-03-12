function getStockPrice() {
  return Math.floor(Math.random() * 100) + 100;
}

function logStockPrice() {
  const price = getStockPrice();
  console.log(`Stock price: ${price}`);
}

// Log stock price every 3 seconds
const intervalId = setInterval(logStockPrice, 3000);

// Stop logging after 10 seconds
setTimeout(() => clearInterval(intervalId), 10000);
