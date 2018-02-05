# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from djangoModel.base import Base

# Create your models here.

#小说栏目分类
class AtilceClassify(Base,models.Model):
    #文章类别名称
    classify_name = models.CharField(max_length=20)

    class Meta:
        db_table="xiaoshuo_classify"
        #包名
        app_label = "xiaoshuo"

#小说相关信息
class AticleAbout(Base,models.Model):
    #文章名称
    title = models.CharField(max_length=255)
    #约束 classify id
    parent_id = models.ForeignKey(AtilceClassify)
    #状态 正在更新，完结
    status = models.BooleanField(default=0)
    #简介
    dec = models.CharField(max_length=500)
    #排序，展示列表时，小说优先级
    sort = models.BigIntegerField(20,null=False)
    #作者
    author = models.CharField(max_length=20)
    #字数
    count = models.BigIntegerField()

    class Meta:
        #表名
        db_table="xiaoshuo_about"
        # 包名
        app_label = "xiaoshuo"

#小说章节
class AticleChapter(Base,models.Model):
    #章节名称
    title = models.CharField(max_length=255)
    #第几章
    num = models.BigIntegerField(20,null=False)
    #约束 aticleabout
    parent_id = models.ForeignKey(AticleAbout)
    
    class Meta:
        #表名
        db_table="xiaoshuo_chapter"
        # 包名
        app_label = "app"

#小说内容
class AticleArticle(Base,models.Model):
    #章节名称
    title = models.CharField(max_length=255)
    #内容
    content = models.TextField()
    #id
    article_id = models.BigIntegerField()
    # 约束 xiaoshuo_about id
    parent_id = models.ForeignKey(AticleChapter)
    #排序
    sort = models.BigIntegerField(20,null=False)

    class Meta:
        #表名
        db_table="xiaoshuo_article"
        # 包名
        app_label = "xiaoshuo"
