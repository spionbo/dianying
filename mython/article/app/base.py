# -*- coding: utf-8 -*-
from django.db import models

class Base(models.Model):
    #创建时间
    create_time = models.DateTimeField(auto_now_add=True)
    #更新时间
    update_time = models.DateTimeField(auto_now=True)
    #是否逻辑删除
    del_tag = models.BooleanField(default=1)
    #修改人ID
    last_modify_user_id = models.BigIntegerField(null=True)
    #创建人id
    create_user_id = models.BigIntegerField(null=True)

    class Meta:
        #抽象
        abstract = True
