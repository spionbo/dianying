#!/usr/bin/env python
# -*- encoding: utf-8 -*-
import scrapy
from scrapy_splash import SplashRequest
from faker import Factory
from scrapy.http import Request, FormRequest, HtmlResponse
f = Factory.create()
from tt528.items import List,People

class Duorong(scrapy.Spider):
    name = "sina"
    headers = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
        'Accept-Encoding': 'gzip, deflate, sdch, br',
        'Accept-Language': 'zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4',
        'Connection': 'keep-alive',
        'Host': 'vdisk.weibo.com',
        'User-Agent': f.user_agent()
    }
    start_urls = [
        "http://vdisk.weibo.com/u/2630646082"
    ]
    def start_requests(self):
        return [scrapy.Request(url='http://vdisk.weibo.com/u/2630646082',
               headers=self.headers,
               meta={'cookiejar': 1},
               callback=self.parse)]

    def parse(self,response):
        people = People()
        list = List()
        for item in response.xpath("//div[@class='sort_name_detail']"):
            list['href'] = item.xpath('a//@href').extract()[0]
    