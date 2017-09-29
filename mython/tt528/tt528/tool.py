#!/usr/bin/python
# -*- coding: UTF-8 -*-
from faker import Factory
f = Factory.create()
class Tool:
    headers = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
        'Accept-Encoding': 'gzip, deflate, sdch, br',
        'Accept-Language': 'zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4',
        'Connection': 'keep-alive',
        'User-Agent': f.user_agent()
    }

    def __init__(self , host):
        self.headers.host = host

    def key(self):
        pass