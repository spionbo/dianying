# -*- coding: utf-8 -*-
import sys
reload(sys)
sys.setdefaultencoding("utf-8")
import scrapy
from faker import Factory
import json
from scrapy_splash import SplashRequest
from scrapy.linkextractors import LinkExtractor  
from scrapy.http import Request, FormRequest, HtmlResponse,Headers
f = Factory.create()

class BaiduSpider(scrapy.Spider):
    name = "baidu"
    headers = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
        'Accept-Encoding': 'gzip, deflate, br',
        'Accept-Language': 'zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4',
        'Connection': 'keep-alive',
        'Host': 'passport.baidu.com',
        'User-Agent': f.user_agent()
    }
    allowed_domains = ["baidu.com"]
    start_urls = [
        "https://passport.baidu.com/",
    ]

    def __init__(self, *args, **kwargs):
         super(BaiduSpider, self).__init__(*args, **kwargs)
 
    RENDER_HTML_URL = "http://192.168.99.100:8050/render.html"

    def start_requests(self):
        body = json.dumps({"url": self.start_urls, "wait": 5,'images':0,'allowed_content_types':'text/html; charset=utf-8'})
        return [Request(self.RENDER_HTML_URL, self.parse, method="POST",
                                 body=body, headers=self.headers)]
        pass
        # return [SplashRequest("https://passport.baidu.com/", 
        #     callback=self.parse, 
        #     splash_headers=self.headers,
        #     endpoint='render.html',
        #     splash_url='http://192.168.99.100:8050',
        #     args={'wait':1.5,'timeout':90.0,'images':1,'expand':1})]

    def parse(self,response):
        print("111111111111111111")
        print response.xpath("//div[@id='login']")
        filename = response.url.split("/")[-2]
        with open(filename, 'wb') as f:
            f.write(response.body)