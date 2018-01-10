# -*- coding: utf-8 -*-
import sys
import os
import django
#添加自定义模块
sys.path.append('../../../helloscrapy')
os.environ['DJANGO_SETTINGS_MODULE'] = "helloscrapy.settings"
django.setup()