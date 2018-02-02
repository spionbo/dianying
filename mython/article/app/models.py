# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from app.base import Base

# Create your models here.

#小说栏目分类
class AtilceClassify(Base):
    #文章类别名称
    classify_name = models.CharField(max_length=20)

    class Meta:
        db_table="xiaoshuo_classify"
        #包名
        app_label = "app"

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