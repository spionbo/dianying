# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.

class TestScrapy(models.Model):
    text = models.CharField(max_length=255)
    author = models.CharField(max_length=255)

    class Meta:
        #指定 它属于warehouse 包
        app_label="warehouse"
        db_table ="test_scrapy"