#!/usr/bin/env python
# -*- encoding: utf-8 -*-
import scrapy
from scrapy_splash import SplashRequest
from faker import Factory
from scrapy.http import Request, FormRequest, HtmlResponse
f = Factory.create()

class Duorong(scrapy.Spider):
    name = "duorong"
    login_url = "https://passport.baidu.com/v2/"
    login = "https://passport.baidu.com/v2/"
    start_urls = [
        "https://passport.baidu.com/v2/"
    ]
    headers = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
        'Accept-Encoding': 'gzip, deflate, sdch, br',
        'Accept-Language': 'zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4',
        'Connection': 'keep-alive',
        'Host': 'vdisk.weibo.com',
        'User-Agent': f.user_agent()
    }
    formdata = {
        'login': 'spion@qq.com', 
        'password':'pengbo1204'
    }

    def start_requests(self):
        return [scrapy.Request(url='http://vdisk.weibo.com/u/2201599764',
               headers=self.headers,
               meta={'cookiejar': 1},
               callback=self.parse)]

         # requests = []
         # request = SplashRequest(self.login, 
         #     headers=self.headers,
         #     meta={'cookiejar': 1},
         #     callback=self.post_login, args={'wait':0.5})
         # requests.append(request)
         # return requests

    def parse(self,response):
        
        filename = response.url.split("/")[-2]
        with open(filename, 'wb') as f:
            f.write(response.body)

    def post_login(self,response):
        print 222222222
        return [scrapy.FormRequest.from_response(response,
             formdata=self.formdata,
             headers=self.headers,
             meta={'cookiejar': response.meta['cookiejar']},
             callback=self.after_login
         )]
    def after_login(self,response):
        print 111111111111111
        requests = []
        for url in self.start_urls:
            url = url.strip()
            request = SplashRequest(url, callback=self.parse, args={'wait':0.5})
            requests.append(request)
        return requests
    def parse_page(self,response):
        filename = response.url.split("/")[-2]
        with open(filename, 'wb') as f:
            f.write(response.body)
        print 333333333333333,response.xpath('//div/@class').extract()
        pass


