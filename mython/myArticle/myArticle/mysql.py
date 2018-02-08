# -*- coding: utf-8 -*-

import MySQLdb
import sys
reload(sys)
sys.setdefaultencoding("utf-8")

config = {
    'host': 'localhost',
    'port': 3306,
    'user': 'root',
    'passwd': '123456',
    'db': 'test',
    'charset': 'utf8',
    #'allowMultiQueries': 'true'
}

# 打开数据库连接
db = MySQLdb.connect(**config)
# 使用cursor()方法获取操作游标
cursor = db.cursor()
class Mysql :
    #获取数据库版本号
    def getVersion(self):
        # 使用execute方法执行SQL语句
        cursor.execute("SELECT VERSION()")

        # 使用 fetchone() 方法获取一条数据
        data = cursor.fetchone()

        print "Database version : %s " % data
        # 关闭数据库连接
        db.close()

    #插入数据
    def inset(self , sql):
        try:
            # 执行sql语句

            cursor.execute(sql)
            # 提交到数据库执行
            db.commit()
        except:
            # Rollback in case there is any error
            db.rollback()
        # 关闭数据库连接
        db.close()

    #小说信息
    @staticmethod
    def xiaoshuoAbout(list):
        # 打开数据库连接
        sql = "insert into xiaoshuo_about(parent_id,title,`dec`,`count`,author,status,sort) values(%s,%s,%s,%s,%s,%s,%s)"
        try:
            cursor.executemany(sql,list)
            list = []
        except:
            print("mysql失败了:"+''.join(list))
            db.rollback()
        finally:
            cursor.close()
            db.commit()
            db.close()