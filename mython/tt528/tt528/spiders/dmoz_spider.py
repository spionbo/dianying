# -*- coding: utf-8 -*-
import scrapy

class DmozSpider(scrapy.Spider):
    name = "dmoz"
    allowed_domains = ["guancha.cn"]
    start_urls = [
        "http://www.guancha.cn/TMT/2017_09_13_426874.shtml",
        "http://www.guancha.cn/politics/2017_09_12_426714.shtml"
    ]

    def parse(self, response):
        self.log('A response from %s just arrived!' % response.url)