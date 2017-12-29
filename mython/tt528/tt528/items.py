# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class Tt528Item(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()

    pass

class URL(scrapy.Item):
    lists = scrapy.Field()
    page = scrapy.Field()

class Book(scrapy.Item):
    #本书列表
    #书名
    name = scrapy.Field()
    #分类
    classification = scrapy.Field()
    #字数
    count = scrapy.Field()
    #作者
    author = scrapy.Field()
    #简介
    dec = scrapy.Field()

    pass

class Article(scrapy.Item):
    #标题
    title  = scrapy.Field()
    #