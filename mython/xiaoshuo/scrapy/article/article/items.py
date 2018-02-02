# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy
from djangoModel.models import AtilceClassify,AticleAbout,AticleChapter,AticleArticle
from scrapy_djangoitem import DjangoItem

class ArticleItem(DjangoItem):
    # define the fields for your item here like:
    # name = scrapy.Field()
    django_model = AticleAbout
    pass

class AtilceClassifyItem(DjangoItem):
    django_model = AtilceClassify
    pass

class AticleAboutItem(DjangoItem):
    django_model = AticleAbout
    pass

class AticleChapter(DjangoItem):
    django_model = AticleChapter
    pass

class AticleArticleItem(DjangoItem):
    django_model = AticleArticle
    pass