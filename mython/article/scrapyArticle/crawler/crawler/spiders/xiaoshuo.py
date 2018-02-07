# -*- coding: utf-8 -*-
import scrapy , re
from crawler.items import AticleAboutItem
from crawler.info import Info
from scrapy.http import Request

class XiaoshuoSpider(scrapy.Spider):
    name = "xiaoshuo"
    start_urls = [
        #玄幻魔法
        'https://www.88dushu.com/sort1/1/',
    ]

    #处理请求
    def request(self,url):
        return Request(url,
                    meta={'cookiejar': 1},
                    headers=Info.headers,
                    callback=self.parse)
    #请求前处理
    def start_requests(self):
        try :
            #设置 host
            Info.headers['Host'] = re.compile(r"www.[\w\d]+.\w+").findall(self.start_urls[0])[0]
        except:
            print("域名解析错误")

        for url in self.start_urls:
            yield self.request(url)

    def parse(self, response):
        for query in response.css("div.booklist li[class!=t]"):
            item = AticleAboutItem()
            item['title'] = query.css("span.sm a b::text").extract_first()
            item['author'] = query.css("span.zz::text").extract_first()
            item['count'] = query.css("span.zs::text").extract_first()[:-1]
            item['status'] = 1
            item['parent_id'] = 1
            link = response.url[:23]+query.css("span.sm a::attr(href)").extract_first()

            print(item)

            #设置下一页
            next_pages = response.css("div#pagelink a[class=next]::attr(href)").extract_first()

            yield item


            # if next_pages:
            #     yield scrapy.Request(next_pages[0], callback=self.parse)
            pass

            # item['title'] =
            # item['parent_id']
            # item['status']
            # item['dec']
            # item['sort']
            # item['author']


            #进入章节
        #     if(link):
        #         yield scrapy.Request(link,callback=self.getList)
        #         pass
        #
        # #设置下一页
        # next_pages = response.css("div#pagelink a[class=next]::attr(href)").extract()
        # if next_pages:
        #     yield scrapy.Request(next_pages, callback=self.parse)
        # pass

    #进入章节
    def getList(self,response):
        print 1
        pass