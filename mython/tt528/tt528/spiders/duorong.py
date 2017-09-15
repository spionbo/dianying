#!/usr/bin/env python
# -*- encoding: utf-8 -*-
import scrapy
from scrapy_splash import SplashRequest
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
        "Cookie":"__jsluid=a27331bb8bbd60117587e1faed5f51fe; Hm_lvt_3d3e8edb7bcc47a5be2981b6a877ca99=1504510032; JSESSIONID=C0841CFAF81B9628B1115A4C1BD7A711; Hm_lvt_f7c570071c5e4a0cf001fac7a2152cae=1503913009,1504235231,1505291942; Hm_lpvt_f7c570071c5e4a0cf001fac7a2152cae=1505465240"
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

    def parse(self,response):
        print("111111111111111111")
        filename = response.url.split("/")[-2]
        with open(filename, 'wb') as f:
            f.write(response.body)

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
        requests = []
        for url in self.start_urls:
            url = url.strip()
            request = SplashRequest(url, callback=self.parse, args={'wait':3})
            requests.append(request)
        return requests
    def parse_page(self,response):
        filename = response.url.split("/")[-2]
        with open(filename, 'wb') as f:
            f.write(response.body)
        print 333333333333333,response.xpath('//div/@class').extract()
        pass


