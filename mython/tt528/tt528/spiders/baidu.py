# -*- coding: utf-8 -*-
import sys
reload(sys)
sys.setdefaultencoding("utf-8")
import scrapy
from scrapy_splash import SplashRequest
from scrapy.linkextractors import LinkExtractor  
from scrapy.http import Request, FormRequest, HtmlResponse

class BaiduSpider(scrapy.Spider):
    name = "baidu"
    #allowed_domains = ["baidu.com"]
    start_urls = [
        "http://itougu.jrj.com.cn/",
    ]

    def start_requests(self):
        requests = []
        for url in self.start_urls:
            url = url.strip()
            request = SplashRequest(url, callback=self.parse, args={'wait':3})
            requests.append(request)
        return requests

    def parse(self,response):
        print("111111111111111111")
        filename = response.url.split("/")[-2]
        with open(filename, 'wb') as f:
            f.write(response.body)
