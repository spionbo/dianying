from scrapy_redis.spiders import RedisSpider

class MySpider(RedisSpider):
    name = 'myspider'
    redis_key = 'myspider:start_urls'
    allowed_domains = ['www.duorongcf.com']
    # start_urls = [
    #     'http://www.duorongcf.com/about/newsDetail/XWXQ_html_219.html',
    # ]

    def parse(self, response):
        #do_something_with_response
        pass