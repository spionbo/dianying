module.exports = function (app) {
    let express = require('express'),
        router = express.Router(),
	    path = require('path');


    /*router.get('/', function(req, res) {
        res.render('index');
    });*/

    app.use('/', router);
	app.get("*", function(request, response) {
		response.redirect("/");
	});
	app.use(function(request,response,next){
	    const req = require('./request');
	    req(request,response)
    });
	app.use(express.static(path.join(__dirname, './src')));
	//app.use('/page2', require('./page2'));
};
