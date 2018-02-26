# -*- coding: utf-8 -*-

# Scrapy settings for myArticle project
#
# For simplicity, this file contains only settings considered important or
# commonly used. You can find more settings consulting the documentation:
#
#     http://doc.scrapy.org/en/latest/topics/settings.html
#     http://scrapy.readthedocs.org/en/latest/topics/downloader-middleware.html
#     http://scrapy.readthedocs.org/en/latest/topics/spider-middleware.html
import pymysql
pymysql.install_as_MySQLdb()

BOT_NAME = 'myArticle'

SPIDER_MODULES = ['myArticle.spiders']
NEWSPIDER_MODULE = 'myArticle.spiders'

# redis 设置
# 指定使用scrapy-redis的Scheduler
# SCHEDULER = "scrapy_redis.scheduler.Scheduler"
# # 在redis中保持scrapy-redis用到的各个队列，从而允许暂停和暂停后恢复
# SCHEDULER_PERSIST = True
# # 指定排序爬取地址时使用的队列，默认是按照优先级排序
# SCHEDULER_QUEUE_CLASS = 'scrapy_redis.queue.SpiderPriorityQueue'
# # 可选的先进先出排序
# # SCHEDULER_QUEUE_CLASS = 'scrapy_redis.queue.SpiderQueue'
# # 可选的后进先出排序
# # SCHEDULER_QUEUE_CLASS = 'scrapy_redis.queue.SpiderStack'
# # 只在使用SpiderQueue或者SpiderStack是有效的参数,，指定爬虫关闭的最大空闲时间
# SCHEDULER_IDLE_BEFORE_CLOSE = 10
#
# # 指定redis的连接参数，REDIS_PASS是我自己加上的redis连接密码，需要简单修改scrapy-redis的源代码以支持使用密码连接redis
# REDIS_HOST = '127.0.0.1'
# REDIS_PORT = 6379
# REDIS_PASS = 'password'
# end redis

# Crawl responsibly by identifying yourself (and your website) on the user-agent
#USER_AGENT = 'myArticle (+http://www.yourdomain.com)'

# Obey robots.txt rules
ROBOTSTXT_OBEY = True

# Configure maximum concurrent requests performed by Scrapy (default: 16)
#CONCURRENT_REQUESTS = 32

# Configure a delay for requests for the same website (default: 0)
# See http://scrapy.readthedocs.org/en/latest/topics/settings.html#download-delay
# See also autothrottle settings and docs
#DOWNLOAD_DELAY = 3
# The download delay setting will honor only one of:
#CONCURRENT_REQUESTS_PER_DOMAIN = 16
#CONCURRENT_REQUESTS_PER_IP = 16

# Disable cookies (enabled by default)
#COOKIES_ENABLED = False

# Disable Telnet Console (enabled by default)
#TELNETCONSOLE_ENABLED = False

# Override the default request headers:
#DEFAULT_REQUEST_HEADERS = {
#   'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
#   'Accept-Language': 'en',
#}

# Enable or disable spider middlewares
# See http://scrapy.readthedocs.org/en/latest/topics/spider-middleware.html
#SPIDER_MIDDLEWARES = {
#    'myArticle.middlewares.MyarticleSpiderMiddleware': 543,
#}

# Enable or disable downloader middlewares
# See http://scrapy.readthedocs.org/en/latest/topics/downloader-middleware.html
#DOWNLOADER_MIDDLEWARES = {
#    'myArticle.middlewares.MyCustomDownloaderMiddleware': 543,
#}

# Enable or disable extensions
# See http://scrapy.readthedocs.org/en/latest/topics/extensions.html
#EXTENSIONS = {
#    'scrapy.extensions.telnet.TelnetConsole': None,
#}

# Configure item pipelines
# See http://scrapy.readthedocs.org/en/latest/topics/item-pipeline.html
ITEM_PIPELINES = {
   'myArticle.pipelines.MyarticlePipeline': 300,
    #'scrapy_redis.pipelines.RedisPipeline': 400,
}
# 指定RedisPipeline用以在redis中保存爬取到的item
# ITEM_PIPELINES = {
#     'example.pipelines.ExamplePipeline': 300,
#     'scrapy_redis.pipelines.RedisPipeline': 400
# }

# Enable and configure the AutoThrottle extension (disabled by default)
# See http://doc.scrapy.org/en/latest/topics/autothrottle.html
#AUTOTHROTTLE_ENABLED = True
# The initial download delay
#AUTOTHROTTLE_START_DELAY = 5
# The maximum download delay to be set in case of high latencies
#AUTOTHROTTLE_MAX_DELAY = 60
# The average number of requests Scrapy should be sending in parallel to
# each remote server
#AUTOTHROTTLE_TARGET_CONCURRENCY = 1.0
# Enable showing throttling stats for every response received:
#AUTOTHROTTLE_DEBUG = False

# Enable and configure HTTP caching (disabled by default)
# See http://scrapy.readthedocs.org/en/latest/topics/downloader-middleware.html#httpcache-middleware-settings
#HTTPCACHE_ENABLED = True
#HTTPCACHE_EXPIRATION_SECS = 0
#HTTPCACHE_DIR = 'httpcache'
#HTTPCACHE_IGNORE_HTTP_CODES = []
#HTTPCACHE_STORAGE = 'scrapy.extensions.httpcache.FilesystemCacheStorage'
