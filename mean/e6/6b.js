const http = require("node:http");

const HOST = "localhost";
const PORT = 3000;

const server = http.createServer((req, res) => {
  if (req.method === "GET") {
    const url = new URL(req.url, `http://${HOST}:${PORT}`);  
    console.log(url);
    
    res.writeHead(200, {
      "content-type": "text/plain",
    });
    res.end("Hi, from the server");
  }
});

server.listen(PORT, HOST, () => {
  console.log(`Running on http://${HOST}:${PORT}`);
});
