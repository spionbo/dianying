# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class MyarticleItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass

#小说相关信息
class AticleAbout(scrapy.Item):
    #文章名称
    title = scrapy.Field()
    #约束 classify id
    parent_id = scrapy.Field()
    #状态 正在更新，完结
    status = scrapy.Field()
    #简介
    dec = scrapy.Field()
    #排序，展示列表时，小说优先级
    sort = scrapy.Field()
    #作者
    author = scrapy.Field()
    #字数
    count = scrapy.Field()

#小说章节
class AticleChapter(scrapy.Item):
    #章节名称
    title = scrapy.Field()
    #第几章
    num = scrapy.Field()
    #约束 aticleabout
    parent_id = scrapy.Field()

#小说内容
class AticleArticle(scrapy.Item):
    #章节名称
    title = scrapy.Field()
    #内容
    content = scrapy.Field()
    #id
    article_id = scrapy.Field()
    # 约束 xiaoshuo_about id
    parent_id = scrapy.Field()
    #排序
    sort = scrapy.Field()