# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy

class People(scrapy.Item):
    uId = scrapy.Field()         #帐号的ID
    name = scrapy.Field()        #用户名
    shareTotal = scrapy.Field()  #分享的资源数量
    headPic = scrapy.Field()     #头像

class List(scrapy.Item):
    title = scrapy.Field()    #文件名称
    doc = scrapy.Field()      #格示 .doc .mp3 .mp4 .pdf 等
    type = scrapy.Field()     #类型(视频，音乐等)
    href = scrapy.Field()      #地址
    picUrl = scrapy.Field()   #图片地址
    size = scrapy.Field()     #大小
    columnId = scrapy.Field() #栏目分类[1,2]则为 视频和音乐
    addTime = scrapy.Field()  #在新浪创建的时间
    endTime = scrapy.Field()  #在新浪失效时间
    getTime = scrapy.Field()  #爬取时间
