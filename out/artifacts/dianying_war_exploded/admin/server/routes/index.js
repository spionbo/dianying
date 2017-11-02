module.exports = function (app) {
    let express = require('express'),
        router = express.Router(),
        request = require('request'),
        proxy = require('http-proxy-middleware'),
	    path = require('path');


    /*router.get('/', function(req, res) {
        res.render('index');
    });*/

   // app.use('/', router);

	/*router.get("*", function(request, response) {
		response.redirect("/");
	});*/

    app.get(/^\/web\/(\w+)\/(\w+)/, function(req, res){
        var from = req.params[0];
        var to = req.params[1] || 'HEAD';
        require('./request')(req,res);
        /*res.json('commit range ' + from + '..' + to);
        res.send('commit range ' + from + '..' + to);*/
    });
	app.use(function(request,response,next){
        
	    const req = require('./request');
	    req(request,response)
    });
	app.use(express.static(path.join(__dirname, './src')));
    /*app.use('/web',proxy({
        target: 'http://192.168.0.108:8080', 
        changeOrigin: true,
        router: {
            // when request.headers.host == 'dev.localhost:3000',
            // override target 'http://www.example.org' to 'http://localhost:8000'
            'dev.localhost:3000' : 'http://localhost:8080'
        }
    }));*/
    app.use('/', router);
};
