# -*- coding: utf-8 -*-
import scrapy , re
from myArticle.info import Info
from scrapy.http import Request
from myArticle.mysql import Mysql
from myArticle.items import AticleAbout

class XiaoshuoSpider(scrapy.Spider):
    name = "xiaoshuo"
    start_urls = [
        #玄幻魔法
        'https://www.88dushu.com/sort9/1/',
    ]

    #处理请求
    def request(self,url,callback,item=False,obj=False):
        request = Request(url,
                    meta={'cookiejar': 1},
                    headers=Info.headers,
                    callback=callback)
        if(item):
            request.meta["item"] = item
        if (obj):
            request.meta["obj"] = obj
        return request
    #请求前处理
    def start_requests(self):
        try :
            #设置 host
            Info.headers['Host'] = re.compile(r"www.[\w\d]+.\w+").findall(self.start_urls[0])[0]
        except:
            print("域名解析错误")

        for url in self.start_urls:
            yield self.request(url,self.getAboutItem)

    def parse(self, response):
        data = []
        self.getAboutItem(response)

        # for i, query in enumerate(response.css("div.booklist li[class!=t]")):
        #     item = AticleAbout()
        #     item['parent_id'] = item['status'] = 1
        #     item['title'] = query.css("span.sm a b::text").extract_first()
        #     item['author'] = query.css("span.zz::text").extract_first()
        #     item['count'] = query.css("span.zs::text").extract_first()[:-1]
        #     status = query.css("span.zt::text").extract_first()
        #     item['status'] = 1 if (status == "连载中") else 0
        #     link = response.url[:23] + query.css("span.sm a::attr(href)").extract_first()
        #
        #
        #
        #     # 进文章列表页及文章相关说明
        #     if(link):
        #         yield self.request(link,self.getList,item)
                    # val = (item['parent_id'], item['title'], item['dec'],
                    #        item['count'], item['author'], item['status'], i
                    #        )
                    # data.append(val)
            #设置下一页
            #next_pages = response.css("div#pagelink a[class=next]::attr(href)").extract_first()

        #添加文章名称及相关信息
        #Mysql.xiaoshuoAbout(data)

    def getAboutItem(self,response):
        booklist = list(enumerate(response.css("div.booklist li[class!=t]")))
        length = len(booklist)
        for i, query in booklist:
            item = AticleAbout()
            item['parent_id'] = item['status'] = 1
            item['title'] = query.css("span.sm a b::text").extract_first()
            item['author'] = query.css("span.zz::text").extract_first()
            item['count'] = query.css("span.zs::text").extract_first()[:-1]
            status = query.css("span.zt::text").extract_first()
            item['status'] = 1 if (status == "连载中") else 0
            link = response.url[:23] + query.css("span.sm a::attr(href)").extract_first()

            # 进文章列表页及文章相关说明
            if (link):
                yield self.request(link, self.getList, item , length )

    itemlist = []
    #进入章节
    def getList(self,response):
        #获取总共有多少条数据
        count = response.meta['obj']
        item = response.meta['item']
        dec = response.css("div.intro::text").extract_first()
        item["dec"] = dec.strip()
        length = len(self.itemlist)
        val = (item['parent_id'], item['title'], item['dec'],
               item['count'], item['author'], item['status'], length
               )
        self.itemlist.append(val)
        #总共有30条数据，
        if(count == (length+1)):
            Mysql.xiaoshuoAbout(self.itemlist)
            self.itemlist = []
        return item

    #进入说情页
    def getArticle(self,response):
        pass