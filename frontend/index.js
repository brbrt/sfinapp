var express = require('express');
var app = express();

app.use(express.static('build'));

var server = app.listen(4070, function listen() {
  var host = server.address().address;
  var port = server.address().port;

  console.log('sfinapp-frontend is started');
});
