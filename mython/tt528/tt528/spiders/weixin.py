# -*- coding: utf-8 -*-
from scrapy.http import Request
from scrapy.spiders import Spider
from scrapy.http.headers import Headers
import json
from scrapy.log import logger


class WeiXinSpider(Spider):
    name = 'weixin'
    # main address since it has the fun list of the products
    start_urls = [
        'https://passport.baidu.com'
    ]

    allowed_domains = [
        'sogou.com'
    ]

    def __init__(self, *args, **kwargs):
         super(WeiXinSpider, self).__init__(*args, **kwargs)

    RENDER_HTML_URL = "http://192.168.99.100:8050/render.html"


    def start_requests(self):
        #text/html; charset=utf-8
        for url in self.start_urls:
            body = json.dumps({"url": url, "wait": 2.5,'images':0,'allowed_content_types':'text/html; charset=utf-8'})
            headers = Headers({'Content-Type': 'application/json'})
            yield Request(self.RENDER_HTML_URL, self.parse, method="POST",
                                 body=body, headers=headers)
        pass

    def parse(self, response):
        self.logger.info('now you can see the url %s' % response.url)
        div_results = response.xpath('//div[@class="results"]/div')
        if not div_results:
            logger.error(msg='there is not any body in the %s' % response.body)
            return
        for div_item in div_results:
            title = div_item.xpath('descendant::div[@class="txt-box"]//h4//text()')
            if title:
                txt = ''.join(title.extract())
                yield {'title':txt}