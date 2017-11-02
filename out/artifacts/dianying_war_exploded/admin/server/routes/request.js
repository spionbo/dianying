module.exports = function(request,response){

    const http = require('http'),
        URL=require('url'),
        /*host = 'localhost' ,
        port = '3000';*/
        host = '192.168.0.108' ,
        port = '8080';
        /*host = '106.15.181.92' ,
        port = '80';*/

    let pathname = URL.parse(request.url).pathname;

        let headers = request.headers;
        const superagent = require('superagent');
        const url = "http://"+host+":"+port+pathname,
            res = response,
            ServerCookie = request.headers.cookie;  // 获取到cookie的字段


        function requireHTTP(_data){
            let data = _data.toString() ,
                query;
            if(request.method==='POST') {
                query = superagent.post(url)
                query.send(data)
                    .set(headers)
                    .redirects(0)
                    .end(function(err, response){
                        if (err || !response.ok) {
                            res.send('error');
                        } else {
                            res.set(response.headers);
                            res.send(response.body);
                        }
                    });
            }else{
                query = superagent.get(url)
                query.send(_data)
                    .end(function(err, response){
                        if (err || !response.ok) {
                            res.send('error');
                        } else {
                            res.send(response.body);
                        }
                    });
            }
                
        }
        request.on("data",function(data) {
            requireHTTP(data)
        });
        if(request.method!=='POST') {
            requireHTTP({});
        }
};
