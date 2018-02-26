from scrapy import cmdline
import scrapy
from scrapy.crawler import CrawlerProcess
# from xiaoshuo import XiaoshuoSpider

# process = CrawlerProcess()
# process.crawl(XiaoshuoSpider)
# process.start()

cmdline.execute("scrapy crawl xiaoshuo".split())