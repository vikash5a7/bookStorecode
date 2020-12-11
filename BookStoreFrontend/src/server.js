const express = require('express');
const path = require('path');
const app = express();
app.use(express.static(__dirname + '/dist/book-store'));
app.get('/*', function(req, res) {
    res.sendFile(path.join(__dirname +
        '/dist/book-store/index.html'));
});
app.listen(process.env.PORT || 8080);