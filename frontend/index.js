var express = require('express');
var app = express();
var apiProxy = require('http-proxy').createProxyServer();

var apiUrl = 'http://localhost:4060';


app.use(express.static('build'));

app.all("/api/*", function(req, res) {
    apiProxy.web(req, res, {target: apiUrl});
});

app.listen(4070, () => console.log('sfinapp-frontend is started'));
