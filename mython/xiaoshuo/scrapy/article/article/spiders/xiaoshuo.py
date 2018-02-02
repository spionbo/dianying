# -*- coding: utf-8 -*-
import scrapy
from article.items import AticleAboutItem

class XiaoshuoSpider(scrapy.Spider):
    name = "xiaoshuo"
    start_urls = [
        #玄幻魔法
        'https://www.88dushu.com/sort1/1/',
    ]

    def parse(self, response):
        for query in response.css("div.booklist li[class!=t]"):
            item = AticleAboutItem()
            item['title'] = query.css("span.sm a b::text").extract()
            item['author'] = query.css("span.zz::text").extract()
            item['count'] = query.css("span.zs::text").extract()[0][:-1]
            item['status'] = 1
            link = response.url[:23]+query.css("span.sm a::attr(href)").extract()[0]

            print item
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