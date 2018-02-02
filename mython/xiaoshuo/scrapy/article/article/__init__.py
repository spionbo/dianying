# -*- coding: utf-8 -*-
import sys
import os
import django
#添加自定义模块
sys.path.append('../../../xiaoshuo')
os.environ['DJANGO_SETTINGS_MODULE'] = "xiaoshuo.settings"
django.setup()