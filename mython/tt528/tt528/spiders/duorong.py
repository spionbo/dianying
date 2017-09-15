#!/usr/bin/env python
# -*- encoding: utf-8 -*-
import scrapy
from scrapy.http import Request, FormRequest, HtmlResponse

class Duorong(scrapy.Spider):
    name = "duorong"
    login_url = "http://www.duorongcf.com/dl"
    login = "http://www.duorongcf.com/doLogin"
    start_urls = [
        "http://www.duorongcf.com/main/myAccount/myInvest"
    ]
    # post_headers = {
    #     "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    #     "Accept-Encoding": "gzip, deflate",
    #     "Accept-Language": "zh-CN,zh;q=0.8,en;q=0.6",
    #     "Cache-Control": "no-cache",
    #     "Connection": "keep-alive",
    #     "Content-Type": "application/x-www-form-urlencoded",
    #     "User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36",
    #     "Referer": "http://www.iteye.com/",
    # }
    post_headers = {
        "Accept": "application/json, text/plain, */*",
        "Accept-Encoding": "gzip, deflate",
        "Accept-Language": "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4",
        "Cache-Control": "no-cache",
        "Connection": "keep-alive",
        "Content-Type": "application/json;charset=UTF-8",
        "User-Agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36",
        "Referer": login_url,
    }
    request_headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko",
        "Referer": login_url,
    }

    def start_requests(self):
        return [Request(url=self.login_url,
            callback=self.post_login,
            meta={'cookiejar': 1}
        )]

    def post_login(self,response):
        return[FormRequest(
            url = self.login,
            headers=self.post_headers,
            method='POST',
            formdata={
                 "mobilePhone":"13717772672",
                 "passWord":"pengbo1204"
            },
            meta={'cookiejar': 1},
            dont_filter=True,
            callback=self.after_login
        )]
    def after_login(self,response):
        for url in self.start_urls:
            #yield Request(url, meta={'cookiejar': response.meta['cookiejar']})
            yield Request(url, dont_filter=True,
                callback=self.parse_page
            )
    def parse_page(self,response):
        filename = response.url.split("/")[-2]
        with open(filename, 'wb') as f:
            f.write(response.body)
        print 333333333333333,response.xpath('//div/@class').extract()
        pass


