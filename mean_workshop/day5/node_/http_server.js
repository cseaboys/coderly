const http = require("http");
const host = "localhost";
const port = 8000;

const server = http.createServer((req, res) => {
  if (req.url === "/") {
    res.writeHead(200);
    res.end(`On path: ${req.url}`);
  } else if (req.url === "/about") {
    res.writeHead(200);
    res.end(`On path: ${req.url}`);
  } else {
    res.writeHead(404);
    res.end(`Not found ${req.url}`);
  }
});

server.listen(port, host, () => {
  console.log(`running on http://${host}:${port} `);
});


module.exports = { server }
